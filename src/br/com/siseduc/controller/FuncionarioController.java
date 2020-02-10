/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.model.bo.EscolaBO;
import br.com.siseduc.model.bo.FuncionarioBO;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Usuario;
import br.com.siseduc.view.TelaCadastroFuncionario;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class FuncionarioController extends FuncionarioBO implements ActionListener, ItemListener, ChangeListener {

    private static final int CARGA_HORARIA_PADRAO = 44;

    /**
     *
     */
    private final TelaPesquisa telaPesquisa;

    /**
     *
     */
    private final TelaCadastroFuncionario telaCadastro;

    /**
     *
     */
    private final TelaInicial telaInicial;
    /**
     *
     */
    private final EscolaBO escolaBO = new EscolaBO();
    /**
     *
     */
    private Funcionario funcionario;
    /**
     *
     */
    private Usuario usuario;

    private String foto = null;
    private String temp = null;
    private String aux = "";
    /**
     * 
     */
    private int tipo = -1;
    /**
     * 
     */
    private final String[] columns = {"Matricula", "Nome", "Telefone", "Celular", "Email"};
    /**
     * 
     */
    private final String[] strings = {"Matricula", "Nome", "Cargo/Função", "Data Cadastro", "Naturalidade", "Estado", "Cidade", "Bairro"};
    /**
     * 
     */
    private final String[] itens = {""};
    /**
     * 
     */
    private final boolean[] separator = {false};
    /**
     * 
     */
    private final String[] images = {""};

    /**
     *
     * @param telaInicial
     * @param session
     */
    public FuncionarioController(TelaInicial telaInicial, Session session) {
        /* Inicializando as variaveis */
        this.telaInicial = telaInicial;
        
        telaPesquisa = new TelaPesquisa(this.telaInicial, true, columns);
        telaPesquisa.setMenuItem(itens, images, separator);
        telaPesquisa.setString(strings);
        telaCadastro = new TelaCadastroFuncionario(this.telaInicial, true);

        this.telaInicial.getJmiFuncionario().addActionListener(this);
        this.telaInicial.getBtnFuncionario().addActionListener(this);

        this.telaPesquisa.getBtnNovo().addActionListener(this);
        this.telaPesquisa.getBtnEditar().addActionListener(this);
        this.telaPesquisa.getBtnSair().addActionListener(new Controller(this.telaPesquisa, session, false));
        this.telaPesquisa.getJcbOrdenar().addItemListener(this);
        this.telaPesquisa.getTxtPesquisar().addKeyListener(this.new AdapdtadorTeclado());
        this.telaPesquisa.getTabelaListar().addMouseListener(this.new AdaptadorMouse());

        this.telaCadastro.getBtnProximoPasso().addActionListener(this);
        this.telaCadastro.getBtnCancelar().addActionListener(this);
        this.telaCadastro.getBtnConfirmar().addActionListener(this);
        this.telaCadastro.getJcbCargoFuncao().addItemListener(this);
        this.telaCadastro.getRbUsuarioSim().addItemListener(this);
        this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().addItemListener(this);
        this.telaCadastro.getJtPainelCadastro().addChangeListener(this);

        this.addItemListener(this);

        this.telaCadastro.getLbFoto().addMouseListener(this.new AdaptadorMouse());
        this.telaCadastro.getTxtNomeFuncionario().addKeyListener(new AdapdtadorTeclado());

        Controller.rollover(telaPesquisa.getBarraDeFerramentas().getComponents());
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.telaCadastro.getJtPainelCadastro()) {
            
            if (foto == null) {
                this.aux = "default.png";
                this.foto = PessoaBO.criptografar(aux.concat(String.valueOf(new Random(1_000_000).nextInt()))).concat(".png");
            }
            this.funcionario = this.dadosFuncionario(funcionario);
            
            if (this.funcionario != null) {
                final int index = this.telaCadastro.getJtPainelCadastro().getSelectedIndex();
                if (this.telaCadastro.getJtPainelCadastro().getTitleAt(index).equals("Confirmar Cadastro")) {

                    this.usuario = this.dadosUsuario(usuario);
                    final StringBuilder confirmarDados = super.dadosFuncionario(funcionario, usuario);

                    this.telaCadastro.getTxtConfirmar().setText(confirmarDados.toString());
                }
            }
        }
    }

    private final class AdaptadorMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == FuncionarioController.this.telaCadastro.getLbFoto()) {
                FuncionarioController.this.foto = PessoaBO.alterarFoto(FuncionarioController.this.telaCadastro.getLbFoto(), FuncionarioController.this.foto);
            } else if (e.getSource() == FuncionarioController.this.telaPesquisa.getTabelaListar()) {
                if (e.getClickCount() > 1) {
                    FuncionarioController.this.editarFuncionario();
                }
            }
        }
    }
    
    private final class AdapdtadorTeclado extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == FuncionarioController.this.telaCadastro.getTxtNomeFuncionario()) {
                FuncionarioController.this.telaCadastro.getTxtNomeDesabilitado().setText(FuncionarioController.this.telaCadastro.getTxtNomeFuncionario().getText());
                return;
            } 
            
            if (e.getSource() == FuncionarioController.this.telaPesquisa.getTxtPesquisar()) {
                FuncionarioController.this.ordenarFuncionario();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.telaInicial.getJmiFuncionario()
                || e.getSource() == this.telaInicial.getBtnFuncionario()) {
            
            final JTable table = this.telaPesquisa.getTabelaListar();
            final DefaultTableModel model = this.telaPesquisa.getModel();
            
            super.preencherTabela(table, model);
            
            this.telaPesquisa.setVisible(true);
            
            return;
        }
        
        if (e.getSource() == this.telaCadastro.getBtnProximoPasso()) {
            Controller.nextTab(this.telaCadastro.getJtPainelCadastro());
            return;
        }

        if (e.getSource() == this.telaPesquisa.getBtnNovo()) {
            this.telaCadastro.getTxtMatricula().setText(this.escolaBO.getId("funcionario"));
            this.telaCadastro.getJcbCargoFuncao().removeAllItems();
            this.telaCadastro.getJcbCargoFuncao().addItem("Cargo/Função");

            this.escolaBO.get(EscolaBO.STR_CARGO_FUNCAO, true).stream().forEach((funcao) -> {
                this.telaCadastro.getJcbCargoFuncao().addItem(funcao);
            });
            
            tipo = Pessoa.CADASTRAR;
            this.telaCadastro.setVisible(true);
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnCancelar()) {

            try {
                foto = (foto == null) ? "null.png" : foto;
                Arquivo.excluirArquivo(Arquivo.DIR_TEMP, foto);

                this.limparTodosCampos();

                this.telaCadastro.setVisible(false);
                this.telaCadastro.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnConfirmar()) {

            final String user = this.telaInicial.getLbUsuario().getText();

            boolean confirmar = (funcionario.isUsuario())
                    ? super.confirmarCadastro(funcionario, usuario, user, tipo)
                    : super.confirmarCadastro(funcionario, user, tipo);

            if (confirmar) {

                try {
                    if (!this.aux.equals("default.png")) {
                        Arquivo.moverArquivo(Arquivo.DIR_TEMP.concat(foto), Arquivo.DIR_FOTOS.concat(funcionario.getFoto()));
                    } else {
                        final String fileName = Arquivo.DIR_FOTOS.concat(foto);
                        Arquivo.copiarArquivo(Arquivo.DIR_FOTOS.concat(aux), fileName);
                    }

                    if (!(foto.equals(temp)) && (temp != null) && !(temp.equals("default.png"))) {
                        Arquivo.excluirArquivo(Arquivo.DIR_FOTOS, temp);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                
                final JTable table = this.telaPesquisa.getTabelaListar();
                final DefaultTableModel model = this.telaPesquisa.getModel();
                super.preencherTabela(table, model);
                
                this.limparTodosCampos();
                this.telaCadastro.setVisible(false);
                this.telaCadastro.dispose();
                
            }
            return;
        }
        
        if (e.getSource() == this.telaPesquisa.getBtnEditar()) {
            this.editarFuncionario();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == this.telaCadastro.getJcbCargoFuncao()) {
                if (this.telaCadastro.getJcbCargoFuncao().getSelectedIndex() != 0) {
                    final String cond = this.telaCadastro.getJcbCargoFuncao().getSelectedItem().toString();
                    this.telaCadastro.getTxtDescricaoFuncao().setText((String) this.escolaBO.get(EscolaBO.DESCRICAO, EscolaBO.STR_CARGO_FUNCAO, "descricao", cond));
                } else {
                    this.telaCadastro.getTxtDescricaoFuncao().setText(null);
                }
                return;
            }
            
            if (e.getSource() == this.telaPesquisa.getJcbOrdenar()) {
                this.ordenarFuncionario();
                return;
            }

        }

        if (e.getSource() == telaCadastro.getRbUsuarioSim()) {
            if (telaCadastro.getRbUsuarioSim().isSelected()) {
                telaCadastro.getJtPainelCadastro().insertTab("Dados do Usuário", null, telaCadastro.getTelaCadastroUsuario(), "", 2);
                telaCadastro.getJtPainelCadastro().setSelectedIndex(2);
            } else {
                telaCadastro.getJtPainelCadastro().removeTabAt(2);
            }
            return;
        }

        if (e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster()) {
            if (this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().isSelected()) {
                this.usuarioMaster();
            } else {
                this.usuarioPadrao();
            }
            return;
        }

        selectMaster(e);

    }

    /**
     *
     * @param funcionario
     * @return
     */
    private Funcionario dadosFuncionario(Funcionario funcionario) {

        funcionario = new Funcionario();
        
        funcionario.setFoto(foto);
        this.telaCadastro.getLbFoto().setName(foto);

        funcionario.setMatricula(this.telaCadastro.getTxtMatricula().getText());
        funcionario.setNome(this.telaCadastro.getTxtNomeFuncionario().getText());
        funcionario.setSexo((this.telaCadastro.getRbSexoMasculino().isSelected()) ? "Masculino" : "Feminino");
        funcionario.setDataNascimento(PessoaBO.data(this.telaCadastro.getTxtDataNascimentoFuncionario().getText(), '/', '-'));
        funcionario.setNacionalidade(this.telaCadastro.getTxtNacionalidadeFuncionario().getText());
        funcionario.setNaturalidade(this.telaCadastro.getTxtNaturalidadeFuncionario().getText());
        funcionario.setUfNaturalidade(this.telaCadastro.getJcbUfNaturalidadeFuncionario().getSelectedItem().toString());
        funcionario.setIdentidade(this.telaCadastro.getTxtIdentidadeFuncionario().getText());
        funcionario.setCpf(this.telaCadastro.getTxtCpfFuncionario().getText());
        funcionario.setDataCadastro(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));

        funcionario.setEndereco(this.telaCadastro.getTxtEnderecoFuncionario().getText());
        funcionario.setComplemento(this.telaCadastro.getTxtComplementoEnderecoFuncionario().getText());
        funcionario.setBairro(this.telaCadastro.getTxtBairroFuncionario().getText());
        funcionario.setCidade(this.telaCadastro.getTxtCidadeFuncionario().getText());
        funcionario.setUf(this.telaCadastro.getJcbUfResidenciaFuncionario().getSelectedItem().toString());
        funcionario.setCep(this.telaCadastro.getTxtCepFuncionario().getText());
        funcionario.setTelefone(this.telaCadastro.getTxtTelefoneFuncionario().getText());
        funcionario.setCelular(this.telaCadastro.getTxtCelularFuncionario().getText());
        funcionario.setEmail(this.telaCadastro.getTxtEmailFuncionario().getText());

        try {
            funcionario.setCargaHoraria(Integer.parseInt(this.telaCadastro.getTxtCargaHoraria().getText()));
        } catch (NumberFormatException n) {
            funcionario.setCargaHoraria(CARGA_HORARIA_PADRAO);
        }

        funcionario.setCargoFuncao(this.telaCadastro.getJcbCargoFuncao().getSelectedItem().toString());
        funcionario.setEscolaridade(this.telaCadastro.getJcbEscolaridade().getSelectedItem().toString());
        funcionario.setNumeroCtps(this.telaCadastro.getTxtNumeroCTPS().getText());
        funcionario.setSerieCtps(this.telaCadastro.getTxtSerieCTPS().getText());
        funcionario.setDataEmissaoCarteira(PessoaBO.data(this.telaCadastro.getTxtDataEmissao().getText(), '/', '-'));
        funcionario.setNumeroPis(this.telaCadastro.getTxtNumeroPIS().getText());
        funcionario.setDependentes(Integer.parseInt(this.telaCadastro.getTxtDependentes().getText()));

        funcionario.setUsuario(this.telaCadastro.getRbUsuarioSim().isSelected());

        return funcionario;
    }

    /**
     *
     * @param usuario
     * @return
     */
    private Usuario dadosUsuario(Usuario usuario) {

        usuario = new Usuario();

        usuario.setUsuario(this.telaCadastro.getTelaCadastroUsuario().getTxtLogin().getText());
        usuario.setSenha(new String(this.telaCadastro.getTelaCadastroUsuario().getTxtSenha().getPassword()));
        usuario.setrSenha(new String(this.telaCadastro.getTelaCadastroUsuario().getTxtRepetirSenha().getPassword()));
        usuario.setUsuarioMaster(this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().isSelected());

        usuario.setCadastrarAtualizarAluno(this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim().isSelected());
        usuario.setCadastrarAtualizarProfessor(this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim().isSelected());
        usuario.setCadastrarAtualizarFuncionario(this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim().isSelected());
        usuario.setCadastrarUsuario(this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim().isSelected());
        usuario.setControleFinanceiro(this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroSim().isSelected());
        usuario.setExcluirAluno(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoSim().isSelected());
        usuario.setExcluirProfessor(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorSim().isSelected());
        usuario.setExcluirFuncionario(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioSim().isSelected());
        usuario.setExcluirUsuario(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioSim().isSelected());
        usuario.setEmitirRelatorios(this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim().isSelected());
        usuario.setCadastrarAlterarTurma(this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim().isSelected());
        usuario.setCadastrarAlterarDisciplina(this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim().isSelected());
        usuario.setExcluirTurma(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaSim().isSelected());
        usuario.setExcluirDisciplina(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaSim().isSelected());
        usuario.setEmitirDocumentos(this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim().isSelected());
        usuario.setCadastrarAtualizarFornecedores(this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim().isSelected());
        usuario.setCadastrarAtualizarBensPatrimoniais(this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim().isSelected());
        usuario.setExcluirFornecedores(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresSim().isSelected());
        usuario.setExcluirBensPatrimoniais(this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoSim().isSelected());

        return usuario;
    }

    /**
     *
     */
    private void usuarioMaster() {
        this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().setSelected(true);

        this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoSim().setSelected(true);
    }

    /**
     *
     */
    private void usuarioPadrao() {

        this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresNao().setSelected(true);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoNao().setSelected(true);
    }

    /**
     *
     * @param e
     */
    private void selectMaster(ItemEvent e) {
        if (e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresSim()
                || e.getSource() == this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoSim()) {
            if (this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresSim().isSelected()
                    && this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoSim().isSelected()) {

                this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().setSelected(true);

            } else {
                this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().setSelected(false);
            }
        }
    }

    /**
     *
     * @param e
     */
    private void addItemListener(ItemListener e) {
        this.telaCadastro.getTelaCadastroUsuario().getRbCAAlunoSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAProfessorSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFuncionarioSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCadastrarUsuarioSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbControleFinanceiroSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirAlunoSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirProfessorSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFuncionarioSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirUsuarioSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirRelatoriosSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCATurmaSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCADisciplinaSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirTurmaSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirDisciplinaSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbEmitirDocumentosSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCAFornecedorSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbCABensConsumorSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirFornecedoresSim().addItemListener(e);
        this.telaCadastro.getTelaCadastroUsuario().getRbExcluirBensConsumoSim().addItemListener(e);
    }

    /**
     *
     */
    private void limparTodosCampos() {
        this.foto = null;

        this.telaCadastro.getLbFoto().setName(foto);
        this.telaCadastro.getLbFoto().setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat("default.png")));

        this.telaCadastro.getTxtNomeDesabilitado().setText(null);
        this.telaCadastro.getTxtNomeFuncionario().setText(null);
        this.telaCadastro.getTxtDataNascimentoFuncionario().setText(null);
        this.telaCadastro.getTxtNacionalidadeFuncionario().setText(null);
        this.telaCadastro.getTxtNaturalidadeFuncionario().setText(null);
        this.telaCadastro.getJcbUfNaturalidadeFuncionario().setSelectedItem("PA");
        this.telaCadastro.getTxtIdentidadeFuncionario().setText(null);
        this.telaCadastro.getTxtCpfFuncionario().setText(null);
        this.telaCadastro.getTxtDataCadastroFuncionario().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));

        this.telaCadastro.getTxtEnderecoFuncionario().setText(null);
        this.telaCadastro.getTxtComplementoEnderecoFuncionario().setText(null);
        this.telaCadastro.getTxtBairroFuncionario().setText(null);
        this.telaCadastro.getTxtCidadeFuncionario().setText(null);
        this.telaCadastro.getJcbUfResidenciaFuncionario().setSelectedItem("PA");
        this.telaCadastro.getTxtCepFuncionario().setText(null);
        this.telaCadastro.getTxtTelefoneFuncionario().setText(null);
        this.telaCadastro.getTxtCelularFuncionario().setText(null);
        this.telaCadastro.getTxtEmailFuncionario().setText(null);

        this.telaCadastro.getTxtCargaHoraria().setText(String.valueOf(CARGA_HORARIA_PADRAO));
        this.telaCadastro.getJcbCargoFuncao().setSelectedIndex(0);
        this.telaCadastro.getJcbEscolaridade().setSelectedIndex(0);
        this.telaCadastro.getTxtNumeroCTPS().setText(null);
        this.telaCadastro.getTxtSerieCTPS().setText(null);
        this.telaCadastro.getTxtDataEmissao().setText(null);
        this.telaCadastro.getTxtNumeroPIS().setText(null);
        this.telaCadastro.getTxtDependentes().setText("0");

        this.telaCadastro.getTelaCadastroUsuario().getTxtLogin().setText(null);
        this.telaCadastro.getTelaCadastroUsuario().getTxtSenha().setText(null);
        this.telaCadastro.getTelaCadastroUsuario().getTxtRepetirSenha().setText(null);

        this.telaCadastro.getRbUsuarioNao().setSelected(true);

        this.telaCadastro.getTelaCadastroUsuario().getCheckUsuarioMaster().setSelected(false);

        this.temp = this.foto;
        this.aux = "";

        this.usuario = null;
        this.funcionario = null;

        this.telaCadastro.getJtPainelCadastro().setSelectedIndex(0);
        tipo = -1;
    }
    
    /**
     * 
     */
    private void ordenarFuncionario() {
       final JTable table = this.telaPesquisa.getTabelaListar();
       final DefaultTableModel model = this.telaPesquisa.getModel();
       
       String order = this.telaPesquisa.getJcbOrdenar().getSelectedItem().toString().toLowerCase().replace(' ', '_').replace('/', '_');
       final String nome = this.telaPesquisa.getTxtPesquisar().getText().trim();
       
       order = (order.equals("matricula")) 
               ? "id_funcionario" 
               : (order.equals("data_cadastro")) 
                    ? "funcionario.data_cadastro"
                    : (order.equals("estado"))
                        ? "uf"
                        : PessoaBO.removerAcentos(order);
       
       super.preencherTabela(table, model, super.ordernarFuncionario(order, nome));
    }
    
    private void editarFuncionario() {
        JTable table = this.telaPesquisa.getTabelaListar();
        
        if (table.getSelectedRowCount() == 1) {
            try {
                this.telaCadastro.getJcbCargoFuncao().removeAllItems();
                this.telaCadastro.getJcbCargoFuncao().addItem("Cargo/Função");

                this.escolaBO.get(EscolaBO.STR_CARGO_FUNCAO, true).stream().forEach((funcao) -> {
                    this.telaCadastro.getJcbCargoFuncao().addItem(funcao);
                });

                final int matricula = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                usuario = super.getFuncionario(matricula);
                temp = foto = usuario.getFoto();

                tipo = Pessoa.ATUALIZAR;

                this.telaCadastro.editarFuncionario(usuario);
                this.telaCadastro.setVisible(true);
            } catch (NullPointerException n) {}
        }
    }
}