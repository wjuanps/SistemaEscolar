/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.AlunoBO;
import br.com.siseduc.model.bo.Paginacao;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.bo.TurmaBO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Estados;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Responsavel;
import br.com.siseduc.model.vo.Turma;
import br.com.siseduc.view.TelaCadastroAluno;
import br.com.siseduc.view.TelaDeclaracao;
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
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class AlunoController extends AlunoBO implements ActionListener, ItemListener, ChangeListener {

    /**
     *
     */
    private final TelaPesquisa telaPesquisa;

    /**
     *
     */
    private final TelaCadastroAluno telaCadastro;

    /**
     *
     */
    private final TelaInicial telaInicial;
    /**
     *
     */
    private final TelaDeclaracao telaDeclaracao;
    /**
     * 
     */
    private final TelaPesquisa telaPesquisaTurma;

    /**
     *
     */
    private Aluno aluno;
    /**
     *
     */
    private Responsavel pai;
    /**
     *
     */
    private Responsavel mae;

    /**
     *
     */
    private final TurmaBO turmaBO = new TurmaBO();

    private String foto;
    private String temp = null;
    private String aux = "";
    
    /**
     * Define o tipo de evento que será realizado.
     * 1 - Insere um novo aluno.
     * 2 - Atualiza o cadastro do aluno.
     */
    private int tipo = -1;
    /**
     * 
     */
    private final String[] columns    = {"Matricula", "Nome", "Telefone", "Celular", "Email"};
    /**
     * 
     */
    private final String[] strings    = {"Nome", "Data Cadastro", "Matricula", "Data Nascimento", "Naturalidade"};
    /**
     * 
     */
    private final String[] itens      = {"Editar Aluno", "Gerar Histórico", "Gerar Boletim", "Gerar Declaração"};
    /**
     * 
     */
    private final boolean[] separator = {true, false, false, false};
    /**
     * 
     */
    private final String[] images     = {"compose-mini.png", "Gerar Histórico", "Gerar Boletim", "Gerar Declaração"};
    /**
     *
     * @param telaInicial
     * @param telaDeclaracao
     * @param telaPesquisaTurma
     * @param session
     */
    public AlunoController(TelaInicial telaInicial, TelaDeclaracao telaDeclaracao, TelaPesquisa telaPesquisaTurma, Session session) {
        this.telaInicial = telaInicial;
        this.telaDeclaracao = telaDeclaracao;
        this.telaPesquisaTurma = telaPesquisaTurma;

        this.telaPesquisa = new TelaPesquisa(this.telaInicial, true, columns);
        this.telaPesquisa.setMenuItem(itens, images, separator);
        this.telaPesquisa.setString(strings);
        this.telaCadastro = new TelaCadastroAluno(this.telaInicial, true);

        this.telaInicial.getJmiAluno().addActionListener(this);
        this.telaInicial.getBtnAluno().addActionListener(this);

        this.telaPesquisa.getBtnNovo().addActionListener(this);
        this.telaPesquisa.getBtnSair().addActionListener(new Controller(telaPesquisa, session, false));
        this.telaPesquisa.getBtnEditar().addActionListener(this);
        this.telaPesquisa.getJcbOrdenar().addItemListener(this);
        this.telaPesquisa.getJcbItensPorPagina().addItemListener(this);
        this.telaPesquisa.getTabelaListar().addMouseListener(new AdaptadorMouse());
        this.telaPesquisa.getTxtPesquisar().addKeyListener(new AdaptadorTeclado());
        this.telaPesquisa.getBtnAnterior().addActionListener(this);
        this.telaPesquisa.getBtnProxima().addActionListener(this);
        for (JMenuItem item : telaPesquisa.getMenuItem()) {
            item.addActionListener(this);
        }

        this.telaCadastro.getBtnProximoPasso().addActionListener(this);
        this.telaCadastro.getBtnCancelar().addActionListener(this);
        this.telaCadastro.getBtnConfirmar().addActionListener(new BotaoConfirmarListener());

        this.telaCadastro.getJcbModalidadeAluno().addItemListener(this);
        this.telaCadastro.getJcbSerieAluno().addItemListener(this);
        this.telaCadastro.getJcbTurnoAluno().addItemListener(this);
        this.telaCadastro.getJcbTurmaAluno().addItemListener(this);
        this.telaCadastro.getCheckPaiDeclarado().addItemListener(this);
        this.telaCadastro.getRbMoraComFilhoSimMae().addItemListener(this);
        this.telaCadastro.getRbMoraComFilhoSimPai().addItemListener(this);

        this.telaCadastro.getTxtNomeAluno().addKeyListener(new AdaptadorTeclado());

        this.telaCadastro.getLbFotoAluno().addMouseListener(new AdaptadorMouse());

        this.telaCadastro.getJtPainelCadastroAluno().addChangeListener(this);
        
        Controller.rollover(telaPesquisa.getBarraDeFerramentas().getComponents());
    }

    /**
     *
     * @return
     */
    private Aluno dadosAluno(Aluno aluno) {

        aluno = new Aluno();
        final Turma turma = new Turma();

        aluno.setFoto(this.foto);
        this.telaCadastro.getLbFotoAluno().setName(foto);

        aluno.setNome(telaCadastro.getTxtNomeAluno().getText());
        aluno.setMatricula(telaCadastro.getTxtMatricula().getText());
        aluno.setSexo((telaCadastro.getRbSexoMasculino().isSelected()) ? "Masculino" : "Feminino");
        aluno.setNacionalidade(telaCadastro.getTxtNacionalidadeAluno().getText());
        aluno.setDataNascimento(PessoaBO.data(((JTextField) telaCadastro.getTxtDataNascimento().getDateEditor().getUiComponent()).getText(), '/', '-'));
        aluno.setNaturalidade(telaCadastro.getTxtNaturalidadeAluno().getText());
        aluno.setUfNaturalidade(telaCadastro.getJcbUfNaturalidadeAluno().getSelectedItem().toString());
        aluno.setPraticaEdFisica(telaCadastro.getRbEdFisicaSim().isSelected());
        aluno.setIrmaoNaEscola(telaCadastro.getRbIrmaoSim().isSelected());
        aluno.setRepetente(telaCadastro.getRbRepetenteSim().isSelected());
        aluno.setDataCadastro(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
        aluno.setTelefone(telaCadastro.getTxtTelefoneAluno().getText());
        aluno.setCelular(telaCadastro.getTxtCelularAluno().getText());
        aluno.setEmail(telaCadastro.getTxtEmailAluno().getText());

        aluno.setPaiDeclarado(!telaCadastro.getCheckPaiDeclarado().isSelected());

        try {
            turma.setNumeroTurma(Integer.parseInt(telaCadastro.getTxtNumeroTurmaAluno().getText()));
        } catch (NumberFormatException n) {
            /**
             * Ignora *
             */
        }

        turma.setTurma(this.telaCadastro.getJcbTurmaAluno().getSelectedItem().toString());

        aluno.setEndereco(telaCadastro.getTxtEnderecoAluno().getText());
        aluno.setComplemento(telaCadastro.getTxtComplementoEnderecoAluno().getText());
        aluno.setBairro(telaCadastro.getTxtBairroAluno().getText());
        aluno.setCidade(telaCadastro.getTxtCidadeAluno().getText());
        aluno.setUf(telaCadastro.getJcbUfResidenciaAluno().getSelectedItem().toString());
        aluno.setCep(telaCadastro.getTxtCepAluno().getText());
        
        aluno.setTurma(turma);

        return aluno;
    }

    /**
     *
     * @param responsavel
     * @param mae
     * @return
     */
    private Responsavel dadosResponsavel(Responsavel responsavel, boolean mae) {

        responsavel = new Responsavel();

        responsavel.setNome((mae) ? telaCadastro.getTxtNomeMae().getText() : telaCadastro.getTxtNomePai().getText());
        responsavel.setParentesco((mae) ? "Mãe" : "Pai");
        responsavel.setIdentidade((mae) ? telaCadastro.getTxtIdentidadeMae().getText() : telaCadastro.getTxtIdentidadePai().getText());
        responsavel.setCpf((mae) ? telaCadastro.getTxtCpfMae().getText() : telaCadastro.getTxtCpfPai().getText());
        responsavel.setTelefone((mae) ? telaCadastro.getTxtTelefoneMae().getText() : telaCadastro.getTxtTelefonePai().getText());
        responsavel.setCelular((mae) ? telaCadastro.getTxtCelularMae().getText() : telaCadastro.getTxtCelularPai().getText());
        responsavel.setEmail((mae) ? telaCadastro.getTxtEmailMae().getText() : telaCadastro.getTxtEmailPai().getText());
        responsavel.setDataCadastro(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
        responsavel.setMoraComFilho((mae) ? telaCadastro.getRbMoraComFilhoSimMae().isSelected() : telaCadastro.getRbMoraComFilhoSimPai().isSelected());
        responsavel.setOutroFilhoNaEscola((mae) ? telaCadastro.getRbOutroFilhoSimMae().isSelected() : telaCadastro.getRbOutroFilhoSimPai().isSelected());

        responsavel.setEndereco((mae) ? telaCadastro.getTxtEnderecoMae().getText() : telaCadastro.getTxtEnderecoPai().getText());
        responsavel.setComplemento((mae) ? telaCadastro.getTxtComplementoEnderecoMae().getText() : telaCadastro.getTxtComplementoEnderecoPai().getText());
        responsavel.setBairro((mae) ? telaCadastro.getTxtBairroMae().getText() : telaCadastro.getTxtBairroPai().getText());
        responsavel.setCidade((mae) ? telaCadastro.getTxtCidadeMae().getText() : telaCadastro.getTxtCidadePai().getText());
        responsavel.setUf((mae) ? telaCadastro.getJcbUfMae().getSelectedItem().toString() : telaCadastro.getJcbUfPai().getSelectedItem().toString());
        responsavel.setCep((mae) ? telaCadastro.getTxtCepMae().getText() : telaCadastro.getTxtCepPai().getText());

        return responsavel;
    }

    /**
     *
     */
    private void limparTodosCampos() {
        this.foto = null;
        this.telaCadastro.getLbFotoAluno().setName(foto);
        this.telaCadastro.getLbFotoAluno().setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat("default.png")));

        this.telaCadastro.getTxtNomeAlunoDesabilitado().setText(null);
        this.telaCadastro.getTxtNomeAluno().setText(null);
        this.telaCadastro.getTxtMatricula().setText(null);
        this.telaCadastro.getRbSexoMasculino().setSelected(true);
        this.telaCadastro.getTxtNacionalidadeAluno().setText(null);
        this.telaCadastro.getTxtDataNascimento().setDate(new Date());
        this.telaCadastro.getTxtNaturalidadeAluno().setText(null);
        this.telaCadastro.getJcbUfNaturalidadeAluno().setSelectedItem(Estados.PA);
        this.telaCadastro.getRbEdFisicaSim().setSelected(true);
        this.telaCadastro.getRbIrmaoSim().setSelected(false);
        this.telaCadastro.getRbRepetenteSim().setSelected(false);
        this.telaCadastro.getTxtTelefoneAluno().setText(null);
        this.telaCadastro.getTxtCelularAluno().setText(null);
        this.telaCadastro.getTxtEmailAluno().setText(null);

        this.telaCadastro.getJcbModalidadeAluno().setSelectedIndex(0);

        this.telaCadastro.getTxtEnderecoAluno().setText(null);
        this.telaCadastro.getTxtComplementoEnderecoAluno().setText(null);
        this.telaCadastro.getTxtBairroAluno().setText(null);
        this.telaCadastro.getTxtCidadeAluno().setText(null);
        this.telaCadastro.getJcbUfResidenciaAluno().setSelectedItem(Estados.PA);
        this.telaCadastro.getTxtCepAluno().setText(null);

        this.telaCadastro.getTxtNomeMae().setText(null);
        this.telaCadastro.getTxtIdentidadeMae().setText(null);
        this.telaCadastro.getTxtCpfMae().setText(null);
        this.telaCadastro.getTxtTelefoneMae().setText(null);
        this.telaCadastro.getTxtCelularMae().setText(null);
        this.telaCadastro.getTxtEmailMae().setText(null);

        this.telaCadastro.getTxtEnderecoMae().setText(null);
        this.telaCadastro.getTxtComplementoEnderecoMae().setText(null);
        this.telaCadastro.getTxtBairroMae().setText(null);
        this.telaCadastro.getTxtCidadeMae().setText(null);
        this.telaCadastro.getJcbUfMae().setSelectedItem(Estados.PA);
        this.telaCadastro.getTxtCepMae().setText(null);

        if (!this.telaCadastro.getCheckPaiDeclarado().isSelected()) {
            this.telaCadastro.getTxtNomePai().setText(null);
            this.telaCadastro.getTxtIdentidadePai().setText(null);
            this.telaCadastro.getTxtCpfPai().setText(null);
            this.telaCadastro.getTxtTelefonePai().setText(null);
            this.telaCadastro.getTxtCelularPai().setText(null);
            this.telaCadastro.getTxtEmailPai().setText(null);

            this.telaCadastro.getTxtEnderecoPai().setText(null);
            this.telaCadastro.getTxtComplementoEnderecoPai().setText(null);
            this.telaCadastro.getTxtBairroPai().setText(null);
            this.telaCadastro.getTxtCidadePai().setText(null);
            this.telaCadastro.getJcbUfPai().setSelectedItem(Estados.PA);
            this.telaCadastro.getTxtCepPai().setText(null);

            this.pai = null;
        }

        this.telaCadastro.getCheckPaiDeclarado().setSelected(false);
        this.temp = this.foto;
        this.aux = "";

        this.mae = null;
        this.aluno = null;
        this.tipo = -1;

        this.telaCadastro.getJtPainelCadastroAluno().setSelectedIndex(0);
    }

    /**
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.telaCadastro.getJtPainelCadastroAluno()) {

            if (this.foto == null) {
                this.aux = "default.png";
                this.foto = PessoaBO.criptografar(aux.concat(String.valueOf(new Random(0xAAFBFC)))).concat(".png");
            }

            this.aluno = dadosAluno(aluno);

            if (this.aluno != null) {
                final int index = this.telaCadastro.getJtPainelCadastroAluno().getSelectedIndex();

                if (this.telaCadastro.getJtPainelCadastroAluno().getTitleAt(index).equals("Confirmar Cadastro")) {

                    this.mae = dadosResponsavel(mae, true);
                    StringBuilder confirmarDados;

                    if (this.telaCadastro.getCheckPaiDeclarado().isSelected()) {
                        confirmarDados = super.confirmarDados(aluno, mae);
                    } else {
                        this.pai = dadosResponsavel(pai, false);
                        confirmarDados = super.confirmarDados(aluno, mae, pai);
                    }

                    this.telaCadastro.getTxtConfirmar().setText(confirmarDados.toString());
                }
            }
        }
    }

    /**
     *
     */
    private final class AdaptadorTeclado extends KeyAdapter {
        /**
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == AlunoController.this.telaCadastro.getTxtNomeAluno()) {
                AlunoController.this.telaCadastro.getTxtNomeAlunoDesabilitado().setText(telaCadastro.getTxtNomeAluno().getText());
                return;
            }

            if (e.getSource() == AlunoController.this.telaPesquisa.getTxtPesquisar()) {
                AlunoController.this.ordenarAluno();
            }
        }
    }

    /**
     *
     */
    private final class AdaptadorMouse extends MouseAdapter {
        private void verificarDisparo(MouseEvent event) {
            if (event.isPopupTrigger()) {
                AlunoController.this.telaPesquisa.getMenuFlutuante().show(event.getComponent(), event.getX(), event.getY());
            }
        }

        /**
         *
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {
            this.verificarDisparo(e);
        }

        /**
         *
         * @param e
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            this.verificarDisparo(e);
        }

        /**
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == AlunoController.this.telaCadastro.getLbFotoAluno()) {
                AlunoController.this.foto = PessoaBO.alterarFoto(AlunoController.this.telaCadastro.getLbFotoAluno(), AlunoController.this.foto);
                return;
            }
            if (e.getSource() == AlunoController.this.telaPesquisa.getTabelaListar()) {
                if (e.getClickCount() > 1) {
                    AlunoController.this.editarAluno();
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == this.telaCadastro.getJcbModalidadeAluno()) {

                this.turmaBO.modalidade(this.telaCadastro.getJcbTurnoAluno(), this.telaCadastro.getJcbSerieAluno(), this.telaCadastro.getJcbModalidadeAluno());

            } else if (e.getSource() == this.telaCadastro.getJcbSerieAluno()) {
                if (this.telaCadastro.getJcbSerieAluno().getSelectedIndex() == 0) {
                    this.telaCadastro.getTxtDescricaoSerieAluno().setText(null);
                    return;
                }

                final String modalidade = this.telaCadastro.getJcbModalidadeAluno().getSelectedItem().toString();
                final String serie = this.telaCadastro.getJcbSerieAluno().getSelectedItem().toString();

                final String descricao = super.descricaoSerie(modalidade, serie);
                this.telaCadastro.getTxtDescricaoSerieAluno().setText(descricao);
            } else if (e.getSource() == this.telaCadastro.getJcbTurnoAluno()) {
                this.telaCadastro.getJcbTurmaAluno().removeAllItems();
                this.telaCadastro.getJcbTurmaAluno().addItem("Escolha a Turma");

                if (this.telaCadastro.getJcbTurnoAluno().getSelectedIndex() == 0) {
                    return;
                }

                final String modalidade = this.telaCadastro.getJcbModalidadeAluno().getSelectedItem().toString();
                final String serie = this.telaCadastro.getJcbSerieAluno().getSelectedItem().toString();
                final String turno = this.telaCadastro.getJcbTurnoAluno().getSelectedItem().toString();

                for (String turma : super.turmas(modalidade, serie, turno)) {
                    this.telaCadastro.getJcbTurmaAluno().addItem(turma);
                }

            } else if (e.getSource() == this.telaCadastro.getJcbTurmaAluno()) {
                if (this.telaCadastro.getJcbModalidadeAluno().getSelectedIndex() == 0) {
                    this.telaCadastro.getTxtNumeroTurmaAluno().setText(null);
                    this.telaCadastro.getTxtDescricaoTurmaAluno().setText(null);
                    return;
                }
                if (this.telaCadastro.getJcbTurmaAluno().getSelectedIndex() == 0) {
                    this.telaCadastro.getTxtDescricaoTurmaAluno().setText(null);
                    this.telaCadastro.getTxtNumeroTurmaAluno().setText(null);

                    this.telaCadastro.getRbAtivaSim().setSelected(false);
                    return;
                }

                final String strTurma = this.telaCadastro.getJcbTurmaAluno().getSelectedItem().toString();
                final Turma turma = super.turma(strTurma);

                this.telaCadastro.getTxtDescricaoTurmaAluno().setText(turma.getDescricaoTurma());
                this.telaCadastro.getTxtNumeroTurmaAluno().setText(String.valueOf(turma.getNumeroTurma()));

                this.telaCadastro.getRbAtivaSim().setSelected(turma.isTurmaAtiva());

            } else if (e.getSource() == this.telaPesquisa.getJcbOrdenar()) {
                this.ordenarAluno();
            } else if (e.getSource() == this.telaPesquisa.getJcbItensPorPagina()) {
                final int itensPorPagina = Integer.valueOf(this.telaPesquisa.getJcbItensPorPagina().getSelectedItem().toString());
                Paginacao.setItensPorPagina(itensPorPagina);
                
                this.ordenarAluno();
            }
        }

        if (e.getSource() == this.telaCadastro.getRbMoraComFilhoSimMae()) {
            if (this.telaCadastro.getRbMoraComFilhoSimMae().isSelected()) {
                this.telaCadastro.getTxtEnderecoMae().setText(telaCadastro.getTxtEnderecoAluno().getText());
                this.telaCadastro.getTxtComplementoEnderecoMae().setText(telaCadastro.getTxtComplementoEnderecoAluno().getText());
                this.telaCadastro.getTxtBairroMae().setText(telaCadastro.getTxtBairroAluno().getText());
                this.telaCadastro.getTxtCidadeMae().setText(telaCadastro.getTxtCidadeAluno().getText());
                this.telaCadastro.getTxtCepMae().setText(telaCadastro.getTxtCepAluno().getText());
                this.telaCadastro.getJcbUfMae().setSelectedItem(telaCadastro.getJcbUfResidenciaAluno().getSelectedItem());
            }
        } else if (e.getSource() == this.telaCadastro.getRbMoraComFilhoSimPai()) {
            if (this.telaCadastro.getRbMoraComFilhoSimPai().isSelected()) {
                this.telaCadastro.getTxtEnderecoPai().setText(telaCadastro.getTxtEnderecoAluno().getText());
                this.telaCadastro.getTxtComplementoEnderecoPai().setText(telaCadastro.getTxtComplementoEnderecoAluno().getText());
                this.telaCadastro.getTxtBairroPai().setText(telaCadastro.getTxtBairroAluno().getText());
                this.telaCadastro.getTxtCidadePai().setText(telaCadastro.getTxtCidadeAluno().getText());
                this.telaCadastro.getTxtCepPai().setText(telaCadastro.getTxtCepAluno().getText());
                this.telaCadastro.getJcbUfPai().setSelectedItem(telaCadastro.getJcbUfResidenciaAluno().getSelectedItem());
            }
        } else if (e.getSource() == this.telaCadastro.getCheckPaiDeclarado()) {

            if (this.telaCadastro.getCheckPaiDeclarado().isSelected()) {
                this.telaCadastro.getTxtNomePai().setText(null);
                this.telaCadastro.getTxtNomePai().setEnabled(false);

                this.telaCadastro.getTxtIdentidadePai().setText(null);
                this.telaCadastro.getTxtIdentidadePai().setEnabled(false);

                this.telaCadastro.getTxtCpfPai().setText(null);
                this.telaCadastro.getTxtCpfPai().setEnabled(false);

                this.telaCadastro.getTxtTelefonePai().setText(null);
                this.telaCadastro.getTxtTelefonePai().setEnabled(false);

                this.telaCadastro.getTxtCelularPai().setText(null);
                this.telaCadastro.getTxtCelularPai().setEnabled(false);

                this.telaCadastro.getTxtEmailPai().setText(null);
                this.telaCadastro.getTxtEmailPai().setEnabled(false);

                this.telaCadastro.getRbMoraComFilhoSimPai().setEnabled(false);
                this.telaCadastro.getRbMoraComFilhoNaoPai().setEnabled(false);

                this.telaCadastro.getRbOutroFilhoSimPai().setEnabled(false);
                this.telaCadastro.getRbOutroFilhoNaoPai().setEnabled(false);

                this.telaCadastro.getTxtEnderecoPai().setText(null);
                this.telaCadastro.getTxtEnderecoPai().setEnabled(false);

                this.telaCadastro.getTxtComplementoEnderecoPai().setText(null);
                this.telaCadastro.getTxtComplementoEnderecoPai().setEnabled(false);

                this.telaCadastro.getTxtBairroPai().setText(null);
                this.telaCadastro.getTxtBairroPai().setEnabled(false);

                this.telaCadastro.getTxtCidadePai().setText(null);
                this.telaCadastro.getTxtCidadePai().setEnabled(false);

                this.telaCadastro.getJcbUfPai().setEnabled(false);

                this.telaCadastro.getTxtCepPai().setText(null);
                this.telaCadastro.getTxtCepPai().setEnabled(false);

                this.telaCadastro.getRbMoraComFilhoNaoPai().setSelected(true);
            } else {

                this.telaCadastro.getTxtNomePai().setEnabled(true);
                this.telaCadastro.getTxtIdentidadePai().setEnabled(true);
                this.telaCadastro.getTxtCpfPai().setEnabled(true);
                this.telaCadastro.getTxtTelefonePai().setEnabled(true);
                this.telaCadastro.getTxtCelularPai().setEnabled(true);
                this.telaCadastro.getTxtEmailPai().setEnabled(true);
                this.telaCadastro.getRbMoraComFilhoNaoPai().setEnabled(true);
                this.telaCadastro.getRbOutroFilhoNaoPai().setEnabled(true);
                this.telaCadastro.getRbMoraComFilhoSimPai().setEnabled(true);
                this.telaCadastro.getRbOutroFilhoSimPai().setEnabled(true);
                this.telaCadastro.getTxtEnderecoPai().setEnabled(true);
                this.telaCadastro.getTxtComplementoEnderecoPai().setEnabled(true);
                this.telaCadastro.getTxtBairroPai().setEnabled(true);
                this.telaCadastro.getTxtCidadePai().setEnabled(true);
                this.telaCadastro.getJcbUfPai().setEnabled(true);
                this.telaCadastro.getTxtCepPai().setEnabled(true);
            }
        }
    }

    /**
     *
     */
    private final class BotaoConfirmarListener implements ActionListener {

        /**
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == AlunoController.this.telaCadastro.getBtnConfirmar()) {
                TipoMensagem response = TipoMensagem.OPCAO_OK;
                if (AlunoController.this.tipo == Pessoa.CADASTRAR) {
                    final String msg = new StringBuilder()
                            .append(String.format("Você tem certeza que deseja cadastrar ~%s~ nessa turma?", aluno.getNome()))
                            .append(String.format("\n\nTurma:......%s", aluno.getTurma().getTurma()))
                            .append(String.format("\nModalidade:.%s", telaCadastro.getJcbModalidadeAluno().getSelectedItem().toString()))
                            .append(String.format("\nTurno:......%s\n\n", telaCadastro.getJcbTurnoAluno().getSelectedItem().toString()))
                            .toString();
                    response = Siseduc.showMessage("Confirmar Cadastro", msg, TipoMensagem.MSG_QUESTION);
                }
                final String usuario = telaInicial.getLbUsuario().getText();
                
                if (response == TipoMensagem.OPCAO_OK) {
                    try {
                        if (AlunoController.this.telaCadastro.getCheckPaiDeclarado().isSelected()) {
                            try {
                                if (!AlunoController.super.confirmarCadastro(aluno, usuario, tipo, mae)) {
                                    throw new SiseducException();
                                }
                            } catch (SiseducException ex) {
                                return;
                            }
                        } else {
                            try {
                                if (!AlunoController.super.confirmarCadastro(aluno, usuario, tipo, mae, pai)) {
                                    throw new SiseducException();
                                }
                            } catch (SiseducException ex) {
                                return;
                            }
                        }
                        
                        ordenar();

                        if (!AlunoController.this.aux.equals("default.png")) {
                            Arquivo.moverArquivo(Arquivo.DIR_TEMP.concat(foto), Arquivo.DIR_FOTOS.concat(aluno.getFoto()));
                        } else {
                            final String fileName = Arquivo.DIR_FOTOS.concat(foto);
                            Arquivo.copiarArquivo(Arquivo.DIR_FOTOS.concat(aux), fileName);
                        }

                        if (!foto.equals(temp) && temp != null) {
                            Arquivo.excluirArquivo(Arquivo.DIR_FOTOS, temp);
                        }

                        AlunoController.this.limparTodosCampos();
                        AlunoController.this.telaCadastro.dispose();

                    } catch (IOException ex) {
                        Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                    }
                }
            }
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadastro.getBtnProximoPasso()) {
            Controller.nextTab(this.telaCadastro.getJtPainelCadastroAluno());
            return;
        }

        if (e.getSource() == this.telaPesquisa.getBtnNovo()) {
            if (turmaBO.hasTurma()) {
                this.telaCadastro.getTxtMatricula().setText(super.matricula());

                this.tipo = Pessoa.CADASTRAR;
                this.telaCadastro.setVisible(true);
            } else {
                final TipoMensagem resp = Siseduc.showMessage(Siseduc.TITULO, "Não existe turma cadastrada ou ativa no sistema.\n\nVocê deseja cadastrar uma turma agora?", TipoMensagem.MSG_QUESTION);
                
                if (resp == TipoMensagem.OPCAO_OK) {
                    final JTable table = telaPesquisaTurma.getTabelaListar();
                    final DefaultTableModel model = telaPesquisaTurma.getModel();
                    
                    turmaBO.preencherTabelaTurma(model, table);
                    this.telaPesquisaTurma.setVisible(true);
                }
            }
            return;
        }

        if (e.getSource() == this.telaInicial.getBtnAluno() || e.getSource() == this.telaInicial.getJmiAluno()) {
            final int itensPorPagina = Integer.valueOf(this.telaPesquisa.getJcbItensPorPagina().getSelectedItem().toString());
            Paginacao.setItensPorPagina(itensPorPagina);
            
            ordenar();
            this.telaPesquisa.setVisible(true);
            
            return;
        }
        
        if (e.getSource() == this.telaPesquisa.getBtnEditar() || e.getSource() == this.telaPesquisa.getMenuItem()[0]) {
            this.editarAluno();
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnCancelar()) {
            try {
                this.foto = (this.foto == null) ? "null.png" : this.foto;
                Arquivo.excluirArquivo(Arquivo.DIR_TEMP, this.foto);

                this.limparTodosCampos();

                this.telaCadastro.setVisible(false);
                this.telaCadastro.dispose();
            } catch (IOException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }
            return;
        }
        
        if (e.getSource() == this.telaPesquisa.getMenuItem()[3]) {
            if (this.telaPesquisa.getTabelaListar().getSelectedRowCount() == 1) {
                this.telaDeclaracao.setVisible(true);
            }
            return;
        }
        
        if (e.getSource() == this.telaPesquisa.getMenuItem()[2]) {
            final JTable table = this.telaPesquisa.getTabelaListar();
            
            if (table.getSelectedRowCount() == 1) {
                String[] matricula = {table.getValueAt(table.getSelectedRow(), 0).toString()};
                String[] field = {"matricula"};
                
                final String nome = table.getValueAt(table.getSelectedRow(), 1).toString();
                
                this.telaPesquisa.dispose();
                this.telaPesquisa.setVisible(false);
                
                final String usuario = telaInicial.getLbUsuario().getText();
                super.gerarBoletim(field, matricula, nome, usuario);
            }
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnAnterior()) {
            Paginacao.voltarPagina();
            ordenarAluno();
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnProxima()) {
            Paginacao.avancarPagina();
            ordenarAluno();
        }
    }

    /**
     *
     */
    private void editarAluno() {
        final JTable table = this.telaPesquisa.getTabelaListar();

        if (table.getSelectedRowCount() == 1) {
            try {
                final int matricula = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

                this.aluno = super.atualizarCadastro(telaCadastro, matricula);
                this.temp = this.foto = this.aluno.getFoto();

                this.tipo = Pessoa.ATUALIZAR;

                this.telaCadastro.setVisible(true);
            } catch (NullPointerException n) {}
        }
    }

    /**
     *
     */
    private void ordenarAluno() {
        final JTable table = this.telaPesquisa.getTabelaListar();
        final DefaultTableModel model = this.telaPesquisa.getModel();
        
        final JLabel  inicio       = telaPesquisa.getLbInicio();
        final JLabel  totalPaginas = telaPesquisa.getLbTotalPaginas();
        final JLabel  total        = telaPesquisa.getLbTotal();
        final JButton anterior     = telaPesquisa.getBtnAnterior();
        final JButton proxima      = telaPesquisa.getBtnProxima();

        final String order = telaPesquisa.getJcbOrdenar().getSelectedItem().toString().toLowerCase().replace(' ', '_');
        final String nome  = telaPesquisa.getTxtPesquisar().getText().trim();
        
        preencherTabelaAluno(table, model, ordenarAluno(order, nome));
        Paginacao.printResultados(inicio, totalPaginas, total);
        Paginacao.habilitarDesabilitarBotao(anterior, proxima);
    }
    
    private void ordenar() {
        final JTable            table = this.telaPesquisa.getTabelaListar();
        final DefaultTableModel model = this.telaPesquisa.getModel();
        
        final JLabel  inicio       = telaPesquisa.getLbInicio();
        final JLabel  totalPaginas = telaPesquisa.getLbTotalPaginas();
        final JLabel  total        = telaPesquisa.getLbTotal();
        final JButton anterior     = telaPesquisa.getBtnAnterior();
        final JButton proxima      = telaPesquisa.getBtnProxima();
        
        AlunoController.super.preencherTabelaAluno(table, model);
        Paginacao.printResultados(inicio, totalPaginas, total);
        Paginacao.habilitarDesabilitarBotao(anterior, proxima);
    }
}