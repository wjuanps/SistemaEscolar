/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.Paginacao;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.ProfessorBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Formacao;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.view.TelaCadastroProfessor;
import br.com.siseduc.view.TelaDiarioClasse;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class ProfessorController extends ProfessorBO implements ActionListener, ChangeListener, ItemListener {

    /**
     *
     */
    private final TelaInicial telaInicial;
    /**
     * 
     */
    private final TelaDiarioClasse telaDiarioClasse;
    /**
     *
     */
    private final TelaPesquisa telaPesquisa;
    /**
     *
     */
    private final TelaCadastroProfessor telaCadastro;
    /**
     *
     */
    private List<Formacao> listaDeFormacao = new ArrayList<>();
    /**
     *
     */
    private final List<Formacao> listaAux = new ArrayList<>();
    /**
     *
     */
    private List<String> listaDisciplinas = new ArrayList<>();
    /**
     *
     */
    private Professor professor = new Professor();
    /**
     *
     */
    private String foto;
    /**
     *
     */
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
    private final String[] strings = {"Matricula", "Nome", "Data Cadastro", "Data Nascimento", "Naturalidade", "Estado", "Cidade", "Bairro"};
    /**
     * 
     */
    private final String[] itens = {"Editar Professor", "Gerar Diário Classe"};
    /**
     * 
     */
    private final boolean[] separator = {true, false};
    /**
     * 
     */
    private final String[] images = {"compose-mini.png", ""};

    /**
     *
     * @param telaInicial
     * @param telaDiarioClasse
     * @param session
     */
    public ProfessorController(TelaInicial telaInicial, TelaDiarioClasse telaDiarioClasse, Session session) {

        this.telaInicial = telaInicial;
        this.telaDiarioClasse = telaDiarioClasse;
        
        this.telaPesquisa = new TelaPesquisa(this.telaInicial, true, columns);
        this.telaPesquisa.setMenuItem(itens, images, separator);
        this.telaPesquisa.setString(strings);
        this.telaCadastro = new TelaCadastroProfessor(this.telaInicial, true);

        this.telaPesquisa.addMouseMotionListener(new Controller(session).new MouseMotionEventListener());
        
        this.telaInicial.getJmiProfessor().addActionListener(this.new PesquisarProfessor());
        this.telaInicial.getBtnProfessor().addActionListener(new PesquisarProfessor());

        this.telaPesquisa.getBtnNovo().addActionListener(this.new NovoProfessor());
        this.telaPesquisa.getBtnSair().addActionListener(new Controller<>(telaPesquisa, session, false));
        this.telaPesquisa.getTabelaListar().addMouseListener(this.new AdaptadorMouse());
        this.telaPesquisa.getTabelaListar().addMouseMotionListener(new Controller(session).new MouseMotionEventListener());
        this.telaPesquisa.getBtnEditar().addActionListener(this);
        this.telaPesquisa.getBtnProxima().addActionListener(this);
        this.telaPesquisa.getBtnAnterior().addActionListener(this);
        for (JMenuItem item : telaPesquisa.getMenuItem()) {
            item.addActionListener(this);
        }

        this.telaPesquisa.getJcbOrdenar().addItemListener(this);
        this.telaPesquisa.getJcbItensPorPagina().addItemListener(this);
        this.telaCadastro.getJcbAnoInicio().addItemListener(this);
        this.telaPesquisa.getTxtPesquisar().addKeyListener(this.new AdaptadorTeclado());

        this.telaCadastro.getBtnProximoPassoDadosProfessor().addActionListener(this);
        this.telaCadastro.getBtnCancelarDadosProfessor().addActionListener(this);
        this.telaCadastro.getBtnAdicionarFormacao().addActionListener(this);
        this.telaCadastro.getBtnEscolherDiploma().addActionListener(this);
        this.telaCadastro.getBtnEditarFormacao().addActionListener(this);
        this.telaCadastro.getBtnRemoverFormacao().addActionListener(this);
        this.telaCadastro.getLbFotoProfessor().addMouseListener(this.new AdaptadorMouse());
        this.telaCadastro.getTabelaFormacao().addMouseListener(this.new AdaptadorMouse());

        this.telaCadastro.getTxtNomeProfessor().addKeyListener(this.new AdaptadorTeclado());
        this.telaCadastro.getBtnConfirmar().addActionListener(this.new BotaoConfirmarListener());
        this.telaCadastro.getBtnAdicionarDisciplina().addActionListener(this.new DisciplinasController());
        this.telaCadastro.getBtnRemoverDisciplina().addActionListener(this.new DisciplinasController());
        this.telaCadastro.getBtnRemoverTodasDisciplinas().addActionListener(this.new DisciplinasController());
        this.telaCadastro.getJlDisciplinas().addMouseListener(this.new DisciplinasController());
        this.telaCadastro.getJlDisciplinasCopy().addMouseListener(this.new DisciplinasController());

        this.telaCadastro.getJtPainelCadastroProfessor().addChangeListener(this);
        
        Controller.rollover(telaPesquisa.getBarraDeFerramentas().getComponents());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == telaPesquisa.getJcbOrdenar()) {
                ordenarProfessor();
            } else if (e.getSource() == telaPesquisa.getJcbItensPorPagina()) {
                final int itensPorPagina = Integer.valueOf(telaPesquisa.getJcbItensPorPagina().getSelectedItem().toString());
                Paginacao.setItensPorPagina(itensPorPagina);
                
                this.ordenarProfessor();
            } else if (e.getSource() == telaCadastro.getJcbAnoInicio()) {
                telaCadastro.addListAnoTermino();
            }
        }
    }

    /**
     *
     */
    private final class PesquisarProfessor implements ActionListener {

        final JTable table = telaPesquisa.getTabelaListar();
        final DefaultTableModel model = telaPesquisa.getModel();

        @Override
        public void actionPerformed(ActionEvent e) {
            final int itensPorPagina = Integer.valueOf(telaPesquisa.getJcbItensPorPagina().getSelectedItem().toString());
            Paginacao.setItensPorPagina(itensPorPagina);
            
            ordenarProfessor();
            telaPesquisa.setVisible(true);
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.telaCadastro.getJtPainelCadastroProfessor()) {
            if (professor != null) {
                final int index = this.telaCadastro.getJtPainelCadastroProfessor().getSelectedIndex();
                if (this.telaCadastro.getJtPainelCadastroProfessor().getTitleAt(index).equals("Confirmar Cadastro")) {

                    if (foto == null) {
                        aux = "default.png";
                        foto = PessoaBO.criptografar(aux.concat(String.valueOf(new Random(1_000_000).nextInt()))).concat(".png");
                    }

                    professor.setFoto(foto);
                    telaCadastro.getLbFotoProfessor().setName(foto);

                    professor.setNome(telaCadastro.getTxtNomeProfessor().getText());
                    professor.setSexo((telaCadastro.getRbSexoMasculino().isSelected()) ? "Masculino" : "Feminino");
                    professor.setIdentidade(telaCadastro.getTxtIdentidadeProfessor().getText());
                    professor.setCpf(telaCadastro.getTxtCpfProfessor().getText());

                    this.telaCadastro.getTxtConfirmarCadastro().setText(super.confirmarDados(professor, listaDeFormacao, listaDisciplinas).toString());
                }
            }
        }
    }

    private final class NovoProfessor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tipo = Pessoa.CADASTRAR;
            telaCadastro.getTxtMatricula().setText(ProfessorController.super.matriculaProfessor());
            telaCadastro.getJlDisciplinas().setListData((ProfessorController.super.disciplinas() != null) ? ProfessorController.super.disciplinas() : new String[]{});
            telaCadastro.setVisible(true);
        }
    }

    /**
     *
     */
    private final class BotaoConfirmarListener implements ActionListener {
        /**
         *
         * @return
         */
        private Professor dadosProfessor() throws IOException {

            professor.setMatricula(telaCadastro.getTxtMatricula().getText());
            professor.setDataNascimento(PessoaBO.data(telaCadastro.getTxtDataNascimentoProfessor().getText(), '/', '-'));
            professor.setNacionalidade(telaCadastro.getTxtNacionalidadeProfessor().getText());
            professor.setNaturalidade(telaCadastro.getTxtNaturalidadeProfessor().getText());
            professor.setUfNaturalidade(telaCadastro.getJcbUfNaturalidadeProfessor().getSelectedItem().toString());
            professor.setDataCadastro(PessoaBO.data(telaCadastro.getTxtDataCadastroProfessor().getText(), '/', '-'));
            professor.setEndereco(telaCadastro.getTxtEnderecoProfessor().getText());
            professor.setComplemento(telaCadastro.getTxtComplementoEnderecoProfessor().getText());
            professor.setBairro(telaCadastro.getTxtBairroProfessor().getText());
            professor.setCidade(telaCadastro.getTxtCidadeProfessor().getText());
            professor.setUf(telaCadastro.getJcbUfResidenciaProfessor().getSelectedItem().toString());
            professor.setCep(telaCadastro.getTxtCepProfessor().getText());
            professor.setTelefone(telaCadastro.getTxtTelefoneProfessor().getText());
            professor.setCelular(telaCadastro.getTxtCelularProfessor().getText());
            professor.setEmail(telaCadastro.getTxtEmailProfessor().getText());

            for (Formacao formacao : listaDeFormacao) {
                final String path = formacao.getDiploma();
                final String fileName = PessoaBO.criptografar(path).concat(".pdf");

                Arquivo.copiarArquivo(path, Arquivo.DIR_DIPLOMAS.concat(fileName));

                formacao.setDiploma(fileName);
            }
            return professor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (listaDisciplinas.isEmpty()) {
                    Siseduc.showMessage(Siseduc.TITULO, "Selecione ao menos uma disciplina", TipoMensagem.MSG_ERROR);
                    telaCadastro.getJtPainelCadastroProfessor().setSelectedIndex(2);
                    return;
                }

                if (listaDeFormacao.isEmpty()) {
                    Siseduc.showMessage(Siseduc.TITULO, "Cadastre ao menos uma formação", TipoMensagem.MSG_ERROR);
                    telaCadastro.getJtPainelCadastroProfessor().setSelectedIndex(1);
                    return;
                }
                
                final String usuario = telaInicial.getLbUsuario().getText();
                if (confirmarCadastro(this.dadosProfessor(), usuario, listaDeFormacao, listaDisciplinas, tipo)) {
                    ordenarProfessor();

                    if (!aux.equals("default.png")) {
                        Arquivo.moverArquivo(Arquivo.DIR_TEMP.concat(foto), Arquivo.DIR_FOTOS.concat(professor.getFoto()));
                    } else {
                        final String fileName = Arquivo.DIR_FOTOS.concat(foto);
                        Arquivo.copiarArquivo(Arquivo.DIR_FOTOS.concat(aux), fileName);
                    }

                    if (!foto.equals(temp) && temp != null) {
                        Arquivo.excluirArquivo(Arquivo.DIR_FOTOS, temp);
                    }

                    ProfessorController.this.limparTodosCampos();
                    telaCadastro.dispose();
                }
            } catch (SiseducException | IOException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            } 
        }
    }

    /**
     *
     */
    private final class AdaptadorMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == ProfessorController.this.telaCadastro.getLbFotoProfessor()) {
                ProfessorController.this.foto = PessoaBO.alterarFoto(ProfessorController.this.telaCadastro.getLbFotoProfessor(), ProfessorController.this.foto);
                return;
            }

            if (e.getSource() == ProfessorController.this.telaPesquisa.getTabelaListar()) {
                if (e.getClickCount() > 1) {
                    ProfessorController.this.editarProfessor();
                }
                return;
            }

            if (e.getSource() == ProfessorController.this.telaCadastro.getTabelaFormacao()) {
                if (e.getClickCount() > 1) {
                    ProfessorController.this.preencherTabelaFormacao(ProfessorController.this.editarFormacao(ProfessorController.this.listaDeFormacao));
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent evt) {
            this.verificarDisparo(evt);
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            this.verificarDisparo(evt);
        }

        /**
         *
         * @param e
         */
        public void verificarDisparo(MouseEvent e) {
            if (e.isPopupTrigger()) {

                final JTable table = ProfessorController.this.telaPesquisa.getTabelaListar();

                if (table.getSelectedRowCount() == 1) {
                    final String nome = table.getValueAt(table.getSelectedRow(), 1).toString();

                    ProfessorController.this.telaPesquisa.getMenuItem()[0].setText("Editar professor ".concat(nome));
                    ProfessorController.this.telaPesquisa.getMenuItem()[1].setText("Diário de Classe para ".concat(nome));

                    ProfessorController.this.telaPesquisa.getMenuFlutuante().show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    }

    /**
     *
     */
    private final class AdaptadorTeclado extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getSource() == ProfessorController.this.telaCadastro.getTxtNomeProfessor()) {
                ProfessorController.this.telaCadastro.getTxtNomeProfessorDesabilitado().setText(ProfessorController.this.telaCadastro.getTxtNomeProfessor().getText());
                return;
            }

            if (e.getSource() == ProfessorController.this.telaPesquisa.getTxtPesquisar()) {
                ordenarProfessor();
            }
        }
    }

    /**
     *
     */
    private final class DisciplinasController extends MouseAdapter implements ActionListener {

        final JList<String> origem = ProfessorController.this.telaCadastro.getJlDisciplinas();
        final JList<String> destino = ProfessorController.this.telaCadastro.getJlDisciplinasCopy();

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == ProfessorController.this.telaCadastro.getBtnAdicionarDisciplina()) {
                ProfessorController.this.listaDisciplinas = ProfessorController.super.copy(origem, destino, listaDisciplinas);
                return;
            }

            if (e.getSource() == ProfessorController.this.telaCadastro.getBtnRemoverDisciplina()) {
                ProfessorController.this.listaDisciplinas = ProfessorController.super.remover(destino, listaDisciplinas);
                return;
            }

            if (e.getSource() == ProfessorController.this.telaCadastro.getBtnRemoverTodasDisciplinas()) {
                ProfessorController.this.listaDisciplinas = ProfessorController.super.removerTodas(destino, listaDisciplinas);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() > 1) {
                if (e.getSource() == ProfessorController.this.telaCadastro.getJlDisciplinas()) {
                    ProfessorController.this.listaDisciplinas = ProfessorController.super.copy(origem, destino, listaDisciplinas);
                    return;
                }

                if (e.getSource() == ProfessorController.this.telaCadastro.getJlDisciplinasCopy()) {
                    ProfessorController.this.listaDisciplinas = ProfessorController.super.remover(destino, listaDisciplinas);
                }
            }
        }
    }

    /**
     *
     * @param lista
     */
    private void preencherTabelaFormacao(List<Formacao> lista) {

        while (this.telaCadastro.getModeloFormacao().getRowCount() > 0) {
            this.telaCadastro.getModeloFormacao().removeRow(0);
        }

        int linha = 0;
        for (Formacao f : lista) {
            this.telaCadastro.getModeloFormacao().addRow(new String[1]);

            this.telaCadastro.getTabelaFormacao().setValueAt(f.getTitulo(), linha, 0);
            this.telaCadastro.getTabelaFormacao().setValueAt(f.getCurso(), linha, 1);
            this.telaCadastro.getTabelaFormacao().setValueAt(f.getInstituicao(), linha, 2);
            this.telaCadastro.getTabelaFormacao().setValueAt(f.getAnoInicio(), linha, 3);
            this.telaCadastro.getTabelaFormacao().setValueAt(f.getAnoTermino(), linha, 4);

            linha++;
        }
        this.telaCadastro.getTabelaFormacao().revalidate();
    }

    /**
     *
     * @return
     */
    private List<Formacao> listaFormacao() {
        Formacao formacao = new Formacao();
        listaAux.removeAll(listaAux);
        try {
            formacao.setId(this.telaCadastro.getLbMatricula().getName() != null ? Integer.parseInt(this.telaCadastro.getLbMatricula().getName()) : 0);
            this.telaCadastro.getLbMatricula().setName(null);
            formacao.setTitulo(this.telaCadastro.getJcbTitulo().getSelectedItem().toString());
            formacao.setCurso(this.telaCadastro.getTxtCurso().getText());
            formacao.setInstituicao(this.telaCadastro.getTxtInstituicao().getText());
            formacao.setAnoInicio((Integer) this.telaCadastro.getJcbAnoInicio().getSelectedItem());
            formacao.setAnoTermino((Integer) this.telaCadastro.getJcbAnoTermino().getSelectedItem());
            formacao.setDiploma(this.telaCadastro.getTxtDiploma().getText());

            if (validarFormacao(formacao)) {
                listaDeFormacao.add(formacao);
                this.limparCamposFormacao();
            }
        } catch (SiseducException | NumberFormatException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } catch (NullPointerException n) {
            Siseduc.showMessage(Siseduc.TITULO, "Informe o ano de término", TipoMensagem.MSG_ERROR);
        } 

        return listaDeFormacao;
    }

    /**
     *
     * @param lista
     * @return
     */
    private List<Formacao> editarFormacao(List<Formacao> lista) {

        if (!listaAux.isEmpty()) {
            final TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, "Você ainda não terminou de editar uma formação.\nVocê deseja descartá-la?", TipoMensagem.MSG_QUESTION);
            if (TipoMensagem.OPCAO_OK != response) {
                return lista;
            }
        }
        
        listaAux.removeAll(listaAux);
        if (this.telaCadastro.getTabelaFormacao().getSelectedRowCount() == 1) {
            final int index = this.telaCadastro.getTabelaFormacao().getSelectedRow();
            final Formacao formacao = lista.get(index);

            this.telaCadastro.getLbMatricula().setName(String.valueOf(formacao.getId()));
            this.telaCadastro.getJcbTitulo().setSelectedItem(formacao.getTitulo());
            this.telaCadastro.getTxtCurso().setText(formacao.getCurso());
            this.telaCadastro.getTxtInstituicao().setText(formacao.getInstituicao());
            this.telaCadastro.getJcbAnoInicio().setSelectedItem(formacao.getAnoInicio());
            this.telaCadastro.getJcbAnoTermino().setSelectedItem(formacao.getAnoTermino());
            this.telaCadastro.getTxtDiploma().setText(formacao.getDiploma());

            listaAux.add(lista.get(index));
            lista.remove(index);
        }
        return lista;
    }

    /**
     *
     * @param lista
     * @return
     */
    private List<Formacao> removerFormacao(List<Formacao> lista) {

        if (this.telaCadastro.getTabelaFormacao().getSelectedRowCount() == 1) {
            final int index = this.telaCadastro.getTabelaFormacao().getSelectedRow();
            lista.remove(index);
        }
        return lista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.telaCadastro.getBtnProximoPassoDadosProfessor()) {
            Controller.nextTab(this.telaCadastro.getJtPainelCadastroProfessor());
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnAdicionarFormacao()) {
            this.preencherTabelaFormacao(this.listaFormacao());
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnEscolherDiploma()) {
            this.telaCadastro.getTxtDiploma().setText(Arquivo.selecionarArquivo(Arquivo.ABRIR).toString());
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnEditarFormacao()) {
            this.preencherTabelaFormacao(this.editarFormacao(listaDeFormacao));
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnRemoverFormacao()) {
            this.preencherTabelaFormacao(this.removerFormacao(listaDeFormacao));
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnCancelarDadosProfessor()) {
            try {
                this.foto = (this.foto == null) ? "j.png" : this.foto;
                Arquivo.excluirArquivo(Arquivo.DIR_TEMP, this.foto);

                this.limparTodosCampos();

                this.telaCadastro.setVisible(false);
                this.telaCadastro.dispose();
            } catch (IOException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }
            return;
        }

        if (e.getSource() == this.telaPesquisa.getMenuItem()[0] || e.getSource() == this.telaPesquisa.getBtnEditar()) {
            this.editarProfessor();
            return;
        }
        
        if (e.getSource() == telaPesquisa.getMenuItem()[1]) {
                        
            final String nome = this.telaPesquisa.getTabelaListar().getValueAt(this.telaPesquisa.getTabelaListar().getSelectedRow(), 1).toString();
            final JComboBox jcbDisciplina = this.telaDiarioClasse.getJcbDisciplina();
            final JComboBox jcbTurma = this.telaDiarioClasse.getJcbTurma();
            
            super.getProfessor(nome, jcbDisciplina, jcbTurma);
            this.telaPesquisa.setVisible(false);
            this.telaPesquisa.dispose();
            
            this.telaDiarioClasse.getTxtNome().setText(nome);
            this.telaDiarioClasse.setVisible(true);
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnProxima()) {
            Paginacao.avancarPagina();
            this.ordenarProfessor();
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnAnterior()) {
            Paginacao.voltarPagina();
            this.ordenarProfessor();
        }
    }
    
    private void ordenarProfessor() {
        final JTable            table = telaPesquisa.getTabelaListar();
        final DefaultTableModel model = telaPesquisa.getModel();

        String order      = telaPesquisa.getJcbOrdenar().getSelectedItem().toString().toLowerCase().replace(' ', '_').trim();
        final String text = telaPesquisa.getTxtPesquisar().getText().trim();

        order = (order.equals("matricula"))
                ? "p.id_professor"
                : (order.equals("estado"))
                    ? "uf"
                    : order;
        
        final JLabel  inicio       = telaPesquisa.getLbInicio();
        final JLabel  totalPaginas = telaPesquisa.getLbTotalPaginas();
        final JLabel  total        = telaPesquisa.getLbTotal();
        final JButton anterior     = telaPesquisa.getBtnAnterior();
        final JButton proxima      = telaPesquisa.getBtnProxima();
        
        ProfessorController.super.preencherTabelaProfessor(table, model, order, text);
        Paginacao.printResultados(inicio, totalPaginas, total);
        Paginacao.habilitarDesabilitarBotao(anterior, proxima);
    }

    /**
     * 
     */
    private void editarProfessor() {
        final JTable table = this.telaPesquisa.getTabelaListar();

        if (table.getSelectedRowCount() == 1) {
            final int id_professor = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

            Map<Professor, List<Formacao>> mapProfessor = super.getProfessor(id_professor);

            if (mapProfessor != null) {
                Set<Professor> professores = mapProfessor.keySet();

                professores.stream().map((prof) -> {
                    this.professor = prof;
                    return prof;
                }).forEach((prof) -> {
                    this.listaDeFormacao = mapProfessor.get(prof);
                });

                this.professor.getDisciplina().stream().forEach((Disciplina disciplina) -> {
                    this.listaDisciplinas.add(String.format("%s - %s", PessoaBO.getCadastro(disciplina.getId()), disciplina.getDisciplina()));
                });

                this.temp = this.foto = this.professor.getFoto();
                this.tipo = Pessoa.ATUALIZAR;

                this.telaCadastro.atualizarCadastro(this.professor, this.listaDeFormacao);
                this.telaCadastro.getJlDisciplinas().setListData(super.disciplinas());
                this.telaCadastro.setVisible(true);
            }
        }
    }

    /**
     *
     */
    private void limparTodosCampos() {
        this.telaCadastro.getTxtNomeProfessorDesabilitado().setText(null);
        this.telaCadastro.getTxtNomeProfessor().setText(null);
        this.telaCadastro.getLbFotoProfessor().setName(null);
        this.telaCadastro.getLbFotoProfessor().setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat("default.png")));
        this.telaCadastro.getRbSexoMasculino().setSelected(true);
        this.telaCadastro.getTxtDataNascimentoProfessor().setText(null);
        this.telaCadastro.getTxtNacionalidadeProfessor().setText(null);
        this.telaCadastro.getTxtNaturalidadeProfessor().setText(null);
        this.telaCadastro.getJcbUfNaturalidadeProfessor().setSelectedItem("PA");
        this.telaCadastro.getTxtIdentidadeProfessor().setText(null);
        this.telaCadastro.getTxtCpfProfessor().setText(null);
        this.telaCadastro.getTxtDataCadastroProfessor().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));

        this.telaCadastro.getTxtEnderecoProfessor().setText(null);
        this.telaCadastro.getTxtComplementoEnderecoProfessor().setText(null);
        this.telaCadastro.getTxtBairroProfessor().setText(null);
        this.telaCadastro.getTxtCidadeProfessor().setText(null);
        this.telaCadastro.getJcbUfResidenciaProfessor().setSelectedItem("PA");
        this.telaCadastro.getTxtCepProfessor().setText(null);
        this.telaCadastro.getTxtTelefoneProfessor().setText(null);
        this.telaCadastro.getTxtCelularProfessor().setText(null);
        this.telaCadastro.getTxtEmailProfessor().setText(null);

        this.limparCamposFormacao();

        while (this.telaCadastro.getModeloFormacao().getRowCount() > 0) {
            this.telaCadastro.getModeloFormacao().removeRow(0);
        }
        
        final String nula[] = {};
        this.telaCadastro.getJlDisciplinasCopy().setListData(nula);

        this.telaCadastro.getJtPainelCadastroProfessor().setSelectedIndex(0);

        this.listaDeFormacao.removeAll(listaDeFormacao);
        this.listaDisciplinas.removeAll(listaDisciplinas);

        this.temp = this.foto = null;
        this.aux = "";
        this.tipo = -1;
    }

    /**
     *
     */
    private void limparCamposFormacao() {
        this.telaCadastro.getJcbTitulo().setSelectedIndex(0);
        this.telaCadastro.getTxtCurso().setText(null);
        this.telaCadastro.getTxtInstituicao().setText(null);
        this.telaCadastro.getJcbAnoInicio().setSelectedItem(Integer.valueOf(String.format("%tY", Calendar.getInstance())));
        this.telaCadastro.getTxtDiploma().setText(null);
    }
}