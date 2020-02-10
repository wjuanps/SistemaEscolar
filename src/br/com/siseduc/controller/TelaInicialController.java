/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.Siseduc;
import br.com.siseduc.controller.Controller.MouseMotionEventListener;
import br.com.siseduc.model.bo.AlunoBO;
import br.com.siseduc.model.bo.EscolaBO;
import br.com.siseduc.model.bo.LembreteBO;
import br.com.siseduc.model.bo.Logger;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.ProfessorBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.bo.TurmaBO;
import br.com.siseduc.model.bo.UsuarioBO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Generica;
import br.com.siseduc.model.vo.Grade;
import br.com.siseduc.model.vo.IGenerica;
import br.com.siseduc.model.vo.Lembrete;
import br.com.siseduc.model.vo.Log;
import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.model.vo.Teclas;
import br.com.siseduc.view.LogView;
import br.com.siseduc.view.TelaCadastrarEscola;
import br.com.siseduc.view.TelaCadastroCargoFuncao;
import br.com.siseduc.view.TelaCadastroDisciplina;
import br.com.siseduc.view.TelaDeclaracao;
import br.com.siseduc.view.TelaDiarioClasse;
import br.com.siseduc.view.TelaGerarBoletim;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaLancarNota;
import br.com.siseduc.view.TelaLembrete;
import br.com.siseduc.view.TelaLogin;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class TelaInicialController implements ActionListener {
    /**
     * Declaração da <code>TelaLogin</code>
     */
    private final TelaLogin telaLogin;
    /**
     * Declaração da <code>TelaInicial</code>
     */
    private final TelaInicial telaInicial;
    /**
     * Declaração da <code>TelaDeclaracao/code>
     */
    private final TelaDeclaracao telaDeclaracao;
    /**
     * Declaração da <code>TelaCadastroDisciplina</code>
     */
    private final TelaCadastroDisciplina telaDisciplina;
    /**
     * Declaração da <code>TelaCadastroCargoFuncao</code>
     */
    private final TelaCadastroCargoFuncao telaCadastroFuncao;
    /**
     * Declaração e inicialização da <code>EscolaBO</code>
     */
    private final EscolaBO escolaBO = new EscolaBO();
    /**
     * Declaração e inicialização da <code>AlunoBO</code>
     */
    private final AlunoBO alunoBo = new AlunoBO();
    /**
     * Declaração e inicialização da <code>ProfessorBO</code>
     */
    private final ProfessorBO professorBO = new ProfessorBO();
    /**
     * Declaração e inicialização da <code>UsuarioBO</code>
     */
    private final UsuarioBO ususarioBO = new UsuarioBO();
    /**
     * Declaração e inicialização da <code>TurmaBO</code>
     */
    private final TurmaBO turmaBo = new TurmaBO();
    /**
     * Declaração da <code>TelaDiarioClasse</code>
     */
    private final TelaDiarioClasse diarioClasse;
    /**
     * Declaração da <code>TelaGerearBoletim</code>
     */
    private final TelaGerarBoletim boletim;
    /**
     * Declaração da <code>LogView</code>
     */
    private final LogView logView;
    /**
     * Declaração da <code>TelaLancarNota</code>
     */
    private final TelaLancarNota telaLancarNota;
    /**
     * Declaração da <code>TelaLembrete</code>
     */
    private final TelaLembrete telaLembrete;
    /**
     * Declaração da <code>LembreteBO</code>
     */
    private final LembreteBO lembreteBo = new LembreteBO();
    /**
     * Declaração da <code>Session</code>
     */
    private final Session session;
    /**
     * Declaração da <code>TelaCadastrarEscola</code>
     */
    private final TelaCadastrarEscola telaCadastrarEscola;
        
    /**
     *
     * Método construtor da <code>TelaInicialController</code>
     * Nele são iniciados os evcentos que serão
     * nos componenetes da #TelaInicial.
     * 
     * @param telaInicial
     */
    public TelaInicialController(TelaInicial telaInicial) {
        
        telaLogin = new TelaLogin(telaInicial);
        this.telaInicial = telaInicial;
        this.session = new Session(telaLogin, telaInicial);
        
        // <editor-fold defaultstate="collapsed" desc="Inicialização das Janelas">
        this.telaDeclaracao = new TelaDeclaracao(telaInicial, true);
        this.telaDisciplina = new TelaCadastroDisciplina(telaInicial, true);
        this.telaCadastroFuncao = new TelaCadastroCargoFuncao(telaInicial, true);
        this.diarioClasse = new TelaDiarioClasse(telaInicial, true);
        this.boletim = new TelaGerarBoletim(telaInicial, true);
        this.telaLancarNota = new TelaLancarNota(telaInicial, true);
        this.logView = new LogView(telaInicial, true);
        this.telaCadastrarEscola = new TelaCadastrarEscola();
        this.telaLembrete = new TelaLembrete(telaInicial, true);
        // </editor-fold>
        
        inicializarEventos();
        
        if (!escolaBO.hasEscola()) {            
            Siseduc.showMessage(Siseduc.TITULO, Mensagem.BOAS_VINDAS, TipoMensagem.MSG_INFORMATION);
            telaCadastrarEscola.setVisible(true);
        } else {
            telaLogin.setVisible(true);
        }        
    }
    
    /**
     * Inicializador de Eventos
     */
    private void inicializarEventos() {
        // <editor-fold defaultstate="collapsed" desc="Inicializador de Eventos">
        
        // <editor-fold defaultstate="collapsed" desc="Eventos de janela">        
        this.telaInicial.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, Mensagem.SAIR_SISTEMA, TipoMensagem.MSG_QUESTION);
                if (response == TipoMensagem.OPCAO_OK)
                    TelaInicialController.this.session.endSession(Session.ENCERRAR_SESSAO);
            }
        });
        
        this.telaLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Siseduc.finalizarSistema();
            }
            @Override
            public void windowOpened(WindowEvent e) {
                telaLogin.getTxtLogin().requestFocus();
            }
        });
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaInicial">
        final MouseMotionEventListener mouseMotionEventListener = new Controller(session).new MouseMotionEventListener();
        
        this.telaInicial.addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBarraDeMenus().addMouseMotionListener(mouseMotionEventListener);
        
        this.telaCadastrarEscola.getBtnConfirmar().addActionListener(this);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Eventos TelaLogin">
        final TelaLoginController telaLoginController = this.new TelaLoginController();
        
        this.telaLogin.getCancelButton().addActionListener(telaLoginController);
        this.telaLogin.getOkButton().addActionListener(telaLoginController);
        this.telaLogin.getTxtSenha().addActionListener(telaLoginController);
        this.telaLogin.getTxtLogin().addActionListener(telaLoginController);
        this.telaLogin.getTxtLogin().addFocusListener(telaLoginController);
        this.telaLogin.getTxtSenha().addFocusListener(telaLoginController);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos Tela inicial">
        final SairController sairController = this.new SairController();
        
        this.telaInicial.getJmiSair().addActionListener(sairController);
        this.telaInicial.getBtnSair().addActionListener(sairController);
        
        this.telaInicial.getBtnProfessor().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnAluno().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnFuncionario().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnFornecedor().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnTurma().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnSuporte().addMouseMotionListener(mouseMotionEventListener);
        this.telaInicial.getBtnSair().addMouseMotionListener(mouseMotionEventListener);
        
        this.telaInicial.getJmiCadastrarDisciplina().addActionListener(this.new DisciplinaController());
        this.telaInicial.getJmiCargosFuncoes().addActionListener(this.new CargoFuncaoController());
        this.telaInicial.getJmiGerarDeclaracao().addActionListener(new Controller<>(telaDeclaracao, session));
        this.telaInicial.getJmiGerarDiarioClasse().addActionListener(new Controller(diarioClasse, session));
        this.telaInicial.getJmiGerarBoletim().addActionListener(new Controller(boletim, session));
        this.telaInicial.getJmiLog().addActionListener(this.new LogListener());
        this.telaInicial.getJmiLancarNota().addActionListener(this.new TelaLancarNotaController());
        this.telaInicial.getJmiNumContrato().addActionListener(this);
        this.telaInicial.getBtnSuporte().addActionListener(this);
        
        Controller.rollover(telaInicial.getBarraDeFerramentas().getComponents());
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaDeclaracao">
        final DeclaracaoController declaracaoController = this.new DeclaracaoController();
        
        this.telaDeclaracao.getBtnCancelar().addActionListener(declaracaoController);
        this.telaDeclaracao.getJcbTipo().addItemListener(declaracaoController);
        this.telaDeclaracao.getTabelaListar().addMouseListener(declaracaoController);
        this.telaDeclaracao.getTxtPesquisar().addKeyListener(declaracaoController);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaDisciplina">
        final DisciplinaController disciplinaController = this.new DisciplinaController();
        
        this.telaDisciplina.getBtnConfirmar().addActionListener(disciplinaController);
        this.telaDisciplina.getJcbDisciplinas().addItemListener(disciplinaController);
        this.telaDisciplina.getBtnCancelar().addActionListener(disciplinaController);
        this.telaDisciplina.getBtnGrade().addActionListener(disciplinaController);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaCadastroFuncao">
        final CargoFuncaoController cargoFuncaoController = this.new CargoFuncaoController();
        
        this.telaCadastroFuncao.getBtnCancelar().addActionListener(cargoFuncaoController);
        this.telaCadastroFuncao.getBtnConfirmar().addActionListener(cargoFuncaoController);
        this.telaCadastroFuncao.getJcbCargos().addItemListener(cargoFuncaoController);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaDiarioClasse">
        final DiarioClasseListenner diarioClasseListenner = this.new DiarioClasseListenner();
        
        this.diarioClasse.getTxtNome().addKeyListener(diarioClasseListenner);
        this.diarioClasse.getTxtNome().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getTxtNome().addActionListener(diarioClasseListenner);
        this.diarioClasse.getJlOpcoes().addMouseListener(diarioClasseListenner);
        this.diarioClasse.getJlOpcoes().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getJcbTurma().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getJcbDisciplina().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getBtnCancelar().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getBtnCancelar().addActionListener(diarioClasseListenner);
        this.diarioClasse.getBtnGerar().addFocusListener(diarioClasseListenner);
        this.diarioClasse.getBtnGerar().addActionListener(diarioClasseListenner);
        this.diarioClasse.getJpClasse().addMouseListener(diarioClasseListenner);
        this.diarioClasse.getJpClasse().addFocusListener(diarioClasseListenner);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos Boletim">
        final GerarBoletimListener gerarBoletimListener = this.new GerarBoletimListener();
        
        this.boletim.getTxtNome().addKeyListener(gerarBoletimListener);
        this.boletim.getJcbAno().addItemListener(gerarBoletimListener);
        this.boletim.getBtnCancelar().addActionListener(gerarBoletimListener);
        this.boletim.getBtnGerar().addActionListener(gerarBoletimListener);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaLancarNota">
        final TelaLancarNotaController telaLancarNotaController = this.new TelaLancarNotaController();
        
        this.telaLancarNota.getBtnLancarNotaAluno().addActionListener(telaLancarNotaController);
        this.telaLancarNota.getBtnCancelarAluno().addActionListener(telaLancarNotaController);
        this.telaLancarNota.getTxtNome().addKeyListener(telaLancarNotaController);
        this.telaLancarNota.getTable().addMouseListener(telaLancarNotaController);
        this.telaLancarNota.getJcbAno().addItemListener(telaLancarNotaController);
        this.telaLancarNota.getJcbProfessor().addItemListener(telaLancarNotaController);
        this.telaLancarNota.getJtpLancarNota().addChangeListener(telaLancarNotaController);
        this.telaLancarNota.getTelaConfirmarNota().getBtnConfirmar().addActionListener(telaLancarNotaController);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos LogView">
        final LogListener logListener = this.new LogListener();
        
        this.logView.getBtnCancelar().addActionListener(logListener);
        this.logView.getBtnLog().addActionListener(logListener);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Eventos TelaLembrete">
        final LembreteController lembreteC = this.new LembreteController();
        this.telaLembrete.getBtnConfirmar().addActionListener(lembreteC);
        this.telaLembrete.getBtnCancelar().addActionListener(lembreteC);
        this.telaInicial.getJmiAdicionarLembrete().addActionListener(lembreteC);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="doClose()">
        Controller.doClose(this.logView);
        Controller.doClose(this.boletim);
        Controller.doClose(this.diarioClasse);
        Controller.doClose(this.telaCadastroFuncao);
        Controller.doClose(this.telaDisciplina);
        Controller.doClose(this.telaDeclaracao);
        // </editor-fold>
        
        // </editor-fold>
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaCadastrarEscola.getBtnConfirmar()) {
            final List<String> escola = new ArrayList<>();
            try {
                PessoaBO.validarNome(telaCadastrarEscola.getTxtNome().getText());
                if (telaCadastrarEscola.getJcbTipo().getSelectedIndex() == 0) {
                    throw new SiseducException("Escolha o tipo da escola");
                }
                
                escola.add(telaCadastrarEscola.getTxtNome().getText());
                escola.add(telaCadastrarEscola.getJcbTipo().getSelectedItem().toString());
                escola.add(telaCadastrarEscola.getTxtTelefone().getText());
                escola.add(telaCadastrarEscola.getTxtCelular().getText());
                escola.add(telaCadastrarEscola.getTxtEmail().getText());
                escola.add(telaCadastrarEscola.getTxtEndereco().getText());
                escola.add(telaCadastrarEscola.getTxtComplementoEndereco().getText());
                escola.add(telaCadastrarEscola.getTxtBairro().getText());
                escola.add(telaCadastrarEscola.getTxtCidade().getText());
                escola.add(telaCadastrarEscola.getJcbUfResidencia().getSelectedItem().toString());
                escola.add(telaCadastrarEscola.getTxtCep().getText());
                
                if (escolaBO.cadastrarEscola(escola)) {
                    telaCadastrarEscola.setVisible(false);
                    telaCadastrarEscola.dispose();
                    
                    TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, Mensagem.SALVAR_CONTRATO, TipoMensagem.MSG_QUESTION);
                    if (response == TipoMensagem.OPCAO_OK) {
                        final String msg = new StringBuilder()
                                .append(String.format("%s%s\n", "Escola:...........................", escola.get(0)))
                                .append(String.format("%s%s", "Número do Contrato:..", escolaBO.mostrarNumContrato()))
                                .toString();
                        File file = Arquivo.selecionarArquivo(Arquivo.SALVAR);
                        if (file != null) {
                            Arquivo.saveAsPDF(file, msg);
                            try {
                                Process exec = Runtime.getRuntime().exec("cmd.exe /C start " + file);
                                exec.waitFor();
                            } catch (IOException | InterruptedException ex) {}
                        }
                    }
                    
                    Siseduc.showMessage(Siseduc.TITULO, Mensagem.ESCOLA_CADASTRADA, TipoMensagem.MSG_INFORMATION);                    
                    telaLogin.setVisible(true);
                } else {
                    Siseduc.showMessage(Siseduc.TITULO, Mensagem.ERRO_CADASTRO_ESCOLA, TipoMensagem.MSG_ERROR);
                    Siseduc.finalizarSistema();
                }
            } catch (SiseducException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }
        } else if (e.getSource() == this.telaInicial.getBtnSuporte() || e.getSource() == this.telaInicial.getJmiSuporte()) {
            try {
                final String url = System.getProperty("user.dir").concat("\\src\\web\\index.html");
                Runtime.getRuntime().exec("cmd.exe /C start ".concat(url));
            } catch (IOException ex) {}
        } else if (e.getSource() == TelaInicialController.this.telaInicial.getJmiNumContrato()) {
            this.escolaBO.mostrarNumContrato(TelaInicialController.this.telaInicial.getLbUsuario().getText());
        }
    }
    
    /**
     *
     */
    private final class TelaLoginController extends FocusAdapter implements ActionListener {        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.telaLogin.getCancelButton()) {

                Siseduc.finalizarSistema();

            } else if (e.getSource() == TelaInicialController.this.telaLogin.getOkButton()
                    || e.getSource() == TelaInicialController.this.telaLogin.getTxtSenha()) {

                try {
                    final String senha = new String(TelaInicialController.this.telaLogin.getTxtSenha().getPassword()).trim();
                    final String login = TelaInicialController.this.telaLogin.getTxtLogin().getText().trim();
                    
                    final Object logar = TelaInicialController.this.ususarioBO.confirmarLogin(login, senha);
                    if (logar == null) {
                        return;
                    }
                    
                    if ((boolean) logar) {                        
                        TelaInicialController.this.telaLogin.getBarra().start();
                        TelaInicialController.this.telaLogin.getStatus().start();

                        final String usuario = (login.equals("Máster")) ? "Administrador" : TelaInicialController.this.ususarioBO.getNome(login);

                        TelaInicialController.this.telaInicial.setUsuario(usuario);
                        TelaInicialController.this.telaInicial.setDataLogin(String.format("%1$tA, %1$td de %1$tB de %1$tY %1$tI:%1$tM %1$tp", Calendar.getInstance()));

                        TelaInicialController.this.telaLogin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        TelaInicialController.this.telaLogin.getOkButton().setEnabled(false);
                        TelaInicialController.this.telaLogin.getCancelButton().setEnabled(false);
                        TelaInicialController.this.telaLogin.getTxtLogin().setEnabled(false);
                        TelaInicialController.this.telaLogin.getTxtSenha().setEnabled(false);
                        TelaInicialController.this.telaLogin.getJpBarraStatus().setPreferredSize(new Dimension(284, 15));
                        TelaInicialController.this.telaLogin.getJpBarraStatus().setOpaque(true);
                        
                        session.setIdUsuario(TelaInicialController.this.ususarioBO.getIdUsuario(login));
                        TelaInicialController.this.session.start(usuario);
                        
                        final JCalendar calendario = telaInicial.getCalendario();
                        TelaInicialController.this.lembreteBo.setIconLembretes(calendario, session.getIdUsuario());
                    } else {
                        Siseduc.showMessage(Siseduc.TITULO, Mensagem.USUARIO_SENHA_INVALIDO, TipoMensagem.MSG_ERROR);
                        TelaInicialController.this.telaLogin.getTxtLogin().requestFocus();
                    }
                } catch (IllegalThreadStateException i) {
                    /**
                     * Ignora *
                     */
                }
            } else if (e.getSource() == TelaInicialController.this.telaLogin.getTxtLogin()) {
                TelaInicialController.this.telaLogin.getTxtSenha().requestFocus();
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            int start = 0;
            if (e.getSource() == TelaInicialController.this.telaLogin.getTxtLogin()) {
                int end = TelaInicialController.this.telaLogin.getTxtLogin().getText().length();

                TelaInicialController.this.telaLogin.getTxtLogin().setBackground(Color.WHITE);
                TelaInicialController.this.telaLogin.getTxtLogin().setSelectionStart(start);
                TelaInicialController.this.telaLogin.getTxtLogin().setSelectionEnd(end);
            } else if (e.getSource() == TelaInicialController.this.telaLogin.getTxtSenha()) {
                int end = TelaInicialController.this.telaLogin.getTxtSenha().getPassword().length;

                TelaInicialController.this.telaLogin.getTxtSenha().setBackground(Color.WHITE);
                TelaInicialController.this.telaLogin.getTxtSenha().setSelectionStart(start);
                TelaInicialController.this.telaLogin.getTxtSenha().setSelectionEnd(end);
            }
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == TelaInicialController.this.telaLogin.getTxtSenha()) {
                TelaInicialController.this.telaLogin.getTxtSenha().setBackground(new Color(153,153,153));
            } else if (e.getSource() == TelaInicialController.this.telaLogin.getTxtLogin()) {
                TelaInicialController.this.telaLogin.getTxtLogin().setBackground(new Color(153,153,153));
            }
        }
    }

    /**
     * 
     * @author Juan Soares
     */
    private final class DiarioClasseListenner extends KeyAdapter implements ActionListener, FocusListener, MouseListener {

        private String nome;
        private final JComboBox jcbDisciplina = TelaInicialController.this.diarioClasse.getJcbDisciplina();
        private final JComboBox jcbTurma      = TelaInicialController.this.diarioClasse.getJcbTurma();
        
        private final JPanel panel = TelaInicialController.this.diarioClasse.getJpClasse();
        private final JList list   = TelaInicialController.this.diarioClasse.getJlOpcoes();
        
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == TelaInicialController.this.diarioClasse.getTxtNome()) {

                this.nome = TelaInicialController.this.diarioClasse.getTxtNome().getText();
                final String[] nomes = professorBO.getNomes(nome);
                
                if (nomes != null) {
                    final Set<Integer> keys = new HashSet<>();
                    for (Teclas tecla : Teclas.values()) {
                        keys.add(tecla.key);
                    }
                    
                    if (!keys.contains(e.getKeyCode())) {
                        String nome = TelaInicialController.this.professorBO.getNome(nomes, this.nome);
                        TelaInicialController.this.diarioClasse.getTxtNome().setText((nome != null) ? nome : this.nome);

                        int size = TelaInicialController.this.diarioClasse.getTxtNome().getText().length();
                        TelaInicialController.this.diarioClasse.getTxtNome().setSelectionStart(this.nome.length());
                        TelaInicialController.this.diarioClasse.getTxtNome().setSelectionEnd(size);
                    }
                    this.list.setListData(nomes);
                    this.panel.add(TelaInicialController.this.diarioClasse.getJspListar());
                    TelaInicialController.this.diarioClasse.getJspListar().setSize(new Dimension(263, nomes.length*27));
                    this.panel.repaint();    
                } else {
                    this.list.setListData(new String[]{});
                    this.panel.remove(TelaInicialController.this.diarioClasse.getJspListar());
                    this.panel.repaint(); 
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.diarioClasse.getBtnGerar()) {

                if (TelaInicialController.this.diarioClasse.getTxtNome().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informe um professor");
                    return;
                }

                if (this.jcbDisciplina.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Escolha uma disciplina");
                    return;
                }

                if (this.jcbTurma.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Escolha uma turma");
                    return;
                }

                final String disciplina = jcbDisciplina.getSelectedItem().toString();
                final String turma = jcbTurma.getSelectedItem().toString();

                final String nome = TelaInicialController.this.diarioClasse.getTxtNome().getText();
                final String idDisciplina = PessoaBO.getId("disciplina", disciplina);
                final String idTurma = PessoaBO.getId("turma", turma);

                final String[] fields = {"nome", "id_disciplina", "id_turma"};
                final String[] values = {nome, idDisciplina, idTurma};

                this.limpar();

                TelaInicialController.this.professorBO.gerarDiarioClasse(fields, values, "Diário de Classe de ".concat(nome));

                String usuario = TelaInicialController.this.telaInicial.getLbUsuario().getText();
                String acao = "Diário de classe gerado por:";
                String opcao = "para ".concat(nome);

                Logger.logger(usuario, acao, opcao);

            } else if (e.getSource() == TelaInicialController.this.diarioClasse.getTxtNome()) {
                this.nome = TelaInicialController.this.diarioClasse.getTxtNome().getText();
                TelaInicialController.this.professorBO.getProfessor(nome, jcbDisciplina, jcbTurma);
                TelaInicialController.this.diarioClasse.getBtnGerar().requestFocus();

                this.panel.remove(TelaInicialController.this.diarioClasse.getJspListar());
                this.panel.repaint();
            } else if (e.getSource() == TelaInicialController.this.diarioClasse.getBtnCancelar()) {
                this.limpar();
            }
        }

        private void limpar() {
            this.jcbDisciplina.removeAllItems();
            this.jcbTurma.removeAllItems();
            TelaInicialController.this.diarioClasse.getTxtNome().setText(null);

            TelaInicialController.this.diarioClasse.dispose();
            TelaInicialController.this.diarioClasse.setVisible(false);
        }
        
        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == this.jcbDisciplina || e.getSource() == this.jcbTurma
                    || e.getSource() == TelaInicialController.this.diarioClasse.getBtnGerar() 
                    || e.getSource() == TelaInicialController.this.diarioClasse.getBtnCancelar()
                    || e.getSource() == this.panel) {
                this.panel.remove(TelaInicialController.this.diarioClasse.getJspListar());
                this.panel.repaint();
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.diarioClasse.getTxtNome()) {
                this.nome = TelaInicialController.this.diarioClasse.getTxtNome().getText();
                final String[] nomes = TelaInicialController.this.professorBO.getNomes(this.nome);
                if (!nome.isEmpty()) {
                    this.list.setListData(nomes);
                    this.panel.add(TelaInicialController.this.diarioClasse.getJspListar());
                    TelaInicialController.this.diarioClasse.getJspListar().setSize(new Dimension(263, nomes.length*27));
                    this.panel.repaint();
                }
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            /**
             * Ignora *
             */
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == this.panel) {
                this.panel.requestFocus();
                return;
            }
            
            if (e.getClickCount() > 1) {
                if (e.getSource() == this.list) {
                    final String nome = this.list.getSelectedValue().toString();
                    
                    TelaInicialController.this.diarioClasse.getTxtNome().setText(nome);
                    TelaInicialController.this.professorBO.getProfessor(nome, this.jcbDisciplina, this.jcbTurma);
                    TelaInicialController.this.diarioClasse.getBtnGerar().requestFocus();
                    
                    this.panel.remove(TelaInicialController.this.diarioClasse.getJspListar());
                    this.panel.repaint();
                }
            }
        }
        // <editor-fold defaultstate="collapsed" desc="Ignorado">
        @Override
        public void mousePressed(MouseEvent e) {
            /**
             * Ignora *
             */
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            /**
             * Ignora *
             */
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            /**
             * Ignora *
             */
        }

        @Override
        public void mouseExited(MouseEvent e) {
            /**
             * Ignora *
             */
        }
        // </editor-fold>
    }

    /**
     * 
     * @author Juan Soares
     */
    private final class GerarBoletimListener extends KeyAdapter implements ActionListener, ItemListener {

        final JTable table = TelaInicialController.this.boletim.getTable();
        final DefaultTableModel model = TelaInicialController.this.boletim.getModel();        

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == TelaInicialController.this.boletim.getTxtNome()) {
                this.selecionarAluno();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.boletim.getBtnCancelar()) {
                this.limparCampos();
            } else if (e.getSource() == TelaInicialController.this.boletim.getBtnGerar()) {
                if (this.table.getSelectedRowCount() == 1) {
                    final String matricula = this.table.getValueAt(this.table.getSelectedRow(), 0).toString();
                    final String nome = this.table.getValueAt(this.table.getSelectedRow(), 1).toString();
                    final String usuario = TelaInicialController.this.telaInicial.getLbUsuario().getText();

                    final String[] fields = {"matricula"};
                    final String[] values = {matricula};

                    this.limparCampos();
                    TelaInicialController.this.alunoBo.gerarBoletim(fields, values, nome, usuario);
                }
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == TelaInicialController.this.boletim.getJcbAno()) {
                    this.selecionarAluno();
                }
            }
        }

        private void limparCampos() {
            TelaInicialController.this.boletim.dispose();
            TelaInicialController.this.boletim.setVisible(false);

            TelaInicialController.this.boletim.getTxtNome().setText(null);
            TelaInicialController.this.boletim.getJcbAno().setSelectedItem(String.format("%tY", Calendar.getInstance()));

            while (this.model.getRowCount() > 0) {
                this.model.removeRow(0);
            }

            this.table.repaint();
            this.table.revalidate();
        }

        private void selecionarAluno() {
            final String nome = TelaInicialController.this.boletim.getTxtNome().getText().trim();
            final String ano  = TelaInicialController.this.boletim.getJcbAno().getSelectedItem().toString();

            TelaInicialController.this.alunoBo.preencherTabela(this.table,this.model, nome, ano);
        }
    }

    /**
     * Classe interna para controlar todos os eventos
     * relacionados a <code>TelaLancarNota</code>
     * 
     * @see TelaLancarNota
     * @see Professor
     * @see Aluno
     * 
     * @author Juan Soares
     */
    private final class TelaLancarNotaController extends KeyAdapter implements ActionListener, ItemListener, MouseListener, ChangeListener {
        
        final JTable table = TelaInicialController.this.telaLancarNota.getTable();
        final DefaultTableModel model = TelaInicialController.this.telaLancarNota.getModel(); 
        
        /** Identificação do aluno */
        private int idAluno;
        
        /** Identificação do Professor */
        private int idProfessor;
        
        /** Identificação da Turma */
        private int idTurma;
        
        /** Identificação da Disciplina */
        private int idDisciplina;
        
        /** Identificação da nota */
        private double idNota;
                
        /**
         * Informa se o aluno já possui notas cadastradas
         * de acordo com os parametros.
         * 
         * @see #idAluno
         * @see idProfessor
         * @see idturma
         * @see idDisplina
         */
        private boolean hasNotas;
        
        /**
         * Lista com todas as notas do aluno
         */
        private List<Double> notas = new ArrayList<>();
        
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == TelaInicialController.this.telaLancarNota.getTxtNome()) {
                this.selecionar();
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.telaLancarNota.getBtnCancelarAluno()) {
                TelaInicialController.this.telaLancarNota.dispose();
                TelaInicialController.this.telaLancarNota.setVisible(false);
                
                TelaInicialController.this.telaLancarNota.getTxtNome().setText(null);
                TelaInicialController.this.telaLancarNota.getJcbAno().setSelectedItem(String.format("%tY", Calendar.getInstance()));
                TelaInicialController.this.telaLancarNota.getJcbProfessor().removeAllItems();
                
                TelaInicialController.this.telaLancarNota.getJcbDisciplina().removeAllItems();
                TelaInicialController.this.telaLancarNota.getJcbDisciplina().setEnabled(false);
                
                while (TelaInicialController.this.telaLancarNota.getModel().getRowCount() > 0) {
                    TelaInicialController.this.telaLancarNota.getModel().removeRow(0);
                }
                TelaInicialController.this.telaLancarNota.getTable().repaint();
                TelaInicialController.this.telaLancarNota.getTable().revalidate();
                
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.telaInicial.getJmiLancarNota()) {
                TelaInicialController.this.telaLancarNota.setVisible(true);
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.telaLancarNota.getBtnLancarNotaAluno()) {                
                try {                
                    if (this.table.getSelectedRowCount() != 1) {
                        throw new SiseducException("Selecione um Aluno.");
                    }
                    
                    if (TelaInicialController.this.telaLancarNota.getJcbProfessor().getSelectedIndex() == 0) {
                        throw new SiseducException("Selecione um Professor.");
                    }
                    
                    if (TelaInicialController.this.telaLancarNota.getJcbDisciplina().getSelectedIndex() == 0) {
                        throw new SiseducException("Selecione uma Disciplina.");
                    }
                    
                    final String professor  = TelaInicialController.this.telaLancarNota.getJcbProfessor().getSelectedItem().toString().split("-")[1].trim();
                    final String disciplina = TelaInicialController.this.telaLancarNota.getJcbDisciplina().getSelectedItem().toString().split("-")[1].trim();
                    
                    final String aluno      = this.table.getValueAt(table.getSelectedRow(), 1).toString();
                    final String turma      = this.table.getValueAt(this.table.getSelectedRow(), 2).toString();
                    final String mat        = this.table.getValueAt(this.table.getSelectedRow(), 0).toString();
                    final String ano        = this.table.getValueAt(this.table.getSelectedRow(), 5).toString();
                    
                    this.notas = getNotas();
                    this.hasNotas = !(this.notas.isEmpty());
                                        
                    TelaInicialController.this.telaLancarNota.tabConfirmarNota(aluno, professor, turma, mat, disciplina, ano, this.hasNotas, this.notas);
                } catch (SiseducException s) {
                    Siseduc.showMessage(Siseduc.TITULO, s.getMessage(), TipoMensagem.MSG_ERROR);
                }
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.telaLancarNota.getTelaConfirmarNota().getBtnConfirmar()) {
                
                int i = 0;
                this.hasNotas = !(getNotas().isEmpty());
                
                final List<JComboBox> jcbs = TelaInicialController.this.telaLancarNota.getTelaConfirmarNota().getjcbs();
                for (JComboBox jcb : jcbs) {
                    this.notas.add(i++, (jcb.getSelectedIndex() != 0) ? Double.parseDouble(jcb.getSelectedItem().toString()) : 0.0);
                }
                this.notas.add(6, this.idNota);
                
                if (TelaInicialController.this.alunoBo.lancarNotas(this.hasNotas, this.idAluno, this.idProfessor, this.idTurma, this.idDisciplina, this.notas)) {
                    TelaInicialController.this.telaLancarNota.getTxtNome().setText(null);
                    TelaInicialController.this.telaLancarNota.getJcbAno().setSelectedItem(String.format("%tY", Calendar.getInstance()));
                    TelaInicialController.this.telaLancarNota.getJcbProfessor().removeAllItems();

                    TelaInicialController.this.telaLancarNota.getJcbDisciplina().removeAllItems();
                    TelaInicialController.this.telaLancarNota.getJcbDisciplina().setEnabled(false);
                    
                    TelaInicialController.this.telaLancarNota.removerTab();
                    TelaInicialController.this.telaLancarNota.getJtpLancarNota().setSelectedIndex(0);
                    
                    while (TelaInicialController.this.telaLancarNota.getModel().getRowCount() > 0) {
                        TelaInicialController.this.telaLancarNota.getModel().removeRow(0);
                    }
                    TelaInicialController.this.telaLancarNota.getTable().repaint();
                    TelaInicialController.this.telaLancarNota.getTable().revalidate();
                }
            }
        }        

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == TelaInicialController.this.telaLancarNota.getJcbAno()) {
                    selecionar();
                } else if (e.getSource() == TelaInicialController.this.telaLancarNota.getJcbProfessor()) {
                    if (TelaInicialController.this.telaLancarNota.getJcbProfessor().getSelectedIndex() == 0) {
                        TelaInicialController.this.telaLancarNota.getJcbDisciplina().removeAllItems();
                        TelaInicialController.this.telaLancarNota.getJcbDisciplina().setEnabled(false);
                    } else {
                        final JComboBox jcb = TelaInicialController.this.telaLancarNota.getJcbDisciplina();
                        final int id = Integer.parseInt(TelaInicialController.this.telaLancarNota.getJcbProfessor().getSelectedItem().toString().split("-")[0].trim());
                        
                        TelaInicialController.this.escolaBO.listarDisciplinas(jcb, id);
                    }
                }
            }
        }
        
        /**
         * 
         */
        private void selecionar() {
            final String nome = TelaInicialController.this.telaLancarNota.getTxtNome().getText().trim();
            final String ano  = TelaInicialController.this.telaLancarNota.getJcbAno().getSelectedItem().toString();
            
            TelaInicialController.this.alunoBo.preencherTabela(this.table, this.model, nome, ano);
        }
        
        /**
         * 
         * @return 
         */
        private List<Double> getNotas() {
            this.idAluno      = Integer.parseInt(this.table.getValueAt(this.table.getSelectedRow(), 0).toString().substring(4));
            this.idProfessor  = Integer.parseInt(TelaInicialController.this.telaLancarNota.getJcbProfessor().getSelectedItem().toString().split("-")[0].trim());
            this.idTurma      = TelaInicialController.this.turmaBo.getId(this.table.getValueAt(this.table.getSelectedRow(), 2).toString());
            this.idDisciplina = Integer.parseInt(TelaInicialController.this.telaLancarNota.getJcbDisciplina().getSelectedItem().toString().split("-")[0].trim());

            this.notas.removeAll(this.notas);
            this.notas = TelaInicialController.this.alunoBo.getNotas(this.idAluno, this.idProfessor, this.idTurma, this.idDisciplina);
            
            try {
                this.idNota = this.notas.get(6);
            } catch (IndexOutOfBoundsException i) {}
            
            return this.notas;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == this.table) {
                if (this.table.getSelectedRowCount() == 1) {
                    final String turma = this.table.getValueAt(this.table.getSelectedRow(), 2).toString();
                    final JComboBox jcb = TelaInicialController.this.telaLancarNota.getJcbProfessor();
                    
                    TelaInicialController.this.professorBO.listarProfessores(jcb, turma);
                }
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == TelaInicialController.this.telaLancarNota.getJtpLancarNota()) {
                if (TelaInicialController.this.telaLancarNota.getJtpLancarNota().getSelectedIndex() != 2) {
                    if (TelaInicialController.this.telaLancarNota.getJtpLancarNota().getTabCount() > 2) {
                        TelaInicialController.this.telaLancarNota.removerTab();
                    }
                } 
            }
        }
        
        // <editor-fold defaultstate="collapsed" desc="Ignorado">
        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
        // </editor-fold>
    }
    
    /**
     * Classe interna para controlar todos os eventos
     * relacionados a <code>LogView</code>
     * 
     * @see LogView
     * @see Logger
     * @see Log
     * 
     * @author Juan Soares
     */
    private class LogListener implements ActionListener {

        final JTextPane txt = TelaInicialController.this.logView.getTxtLog();
        final Logger logger = new Logger();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.logView.getBtnCancelar()) {
                TelaInicialController.this.logView.getTxtLog().setText(null);
                TelaInicialController.this.logView.setVisible(false);
                TelaInicialController.this.logView.dispose();
            } else if (e.getSource() == TelaInicialController.this.logView.getBtnLog()) {
                this.carregarLog();
            } else if (e.getSource() == TelaInicialController.this.telaInicial.getJmiLog()) {
                this.carregarLog();
                TelaInicialController.this.logView.setVisible(true);
            }
        }

        private void carregarLog() {
            try {
                final String url = Arquivo.DIR_LOG.concat(Log.LOG);
                TelaInicialController.this.logView.getLbSize().setText(String.format("%.0f KB", Math.ceil((double)(Files.size(Paths.get(url)))/1_024)));
                this.txt.setText(Logger.getLogger(url).toString());
                
                this.logger.styleFont(txt, false, false, false, "Tahoma", Color.BLACK, 12, 1, 0, this.txt.getText().length());
                this.logger.colorir(0, "Sessão iniciada", "\n", new Color(2, 68, 7), false, this.txt);
                this.logger.colorir(0, "Sessão finalizada", "\n", Color.RED, false, this.txt);
                this.logger.colorir(0, "Sistema iniciado", "::--", new Color(1, 10, 69), true, this.txt);
                this.logger.colorir(0, "Sistema finalizado", "::--", new Color(1, 10, 69), true, this.txt);
            } catch (NullPointerException ne) {
                Siseduc.showMessage(Siseduc.TITULO, Mensagem.ARQUIVO_DANIFICACO, TipoMensagem.MSG_ERROR);
                TelaInicialController.this.logView.setVisible(false);
                TelaInicialController.this.logView.dispose();
            } catch (IOException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }
        }
    }

    /**
     *
     */
    private final class SairController implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TipoMensagem opc = Siseduc.showMessage(Siseduc.TITULO, Mensagem.SAIR_SISTEMA, TipoMensagem.MSG_QUESTION);
            if (opc == TipoMensagem.OPCAO_OK)
                TelaInicialController.this.session.endSession(Session.ENCERRAR_SESSAO);
        }
    }

    /**
     * Classe interna para controlar todos os eventos 
     * relacionados a <code>TelaDisciplina</code>
     * 
     * @see EscolaBO
     * 
     * @author Juan Soares
     */
    private final class DisciplinaController implements ActionListener, ItemListener {
        private void limparCampos() {
            final String data = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());

            TelaInicialController.this.telaDisciplina.getTxtDisciplina().setText(null);
            TelaInicialController.this.telaDisciplina.getTxtDataCadastro().setText(data);
            TelaInicialController.this.telaDisciplina.getTxtModificadaEm().setText(data);
            TelaInicialController.this.telaDisciplina.getTxtDescricao().setText(null);

            TelaInicialController.this.telaDisciplina.getJcbDisciplinas().setSelectedIndex(0);
            TelaInicialController.this.telaDisciplina.getTxtMatricula().setText(TelaInicialController.this.escolaBO.getId("disciplina"));

            TelaInicialController.this.telaDisciplina.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.telaInicial.getJmiCadastrarDisciplina()) {
                TelaInicialController.this.telaDisciplina.setDisciplinas(TelaInicialController.this.escolaBO.get("disciplina"));
                TelaInicialController.this.telaDisciplina.getTxtMatricula().setText(TelaInicialController.this.escolaBO.getId("disciplina"));
                
                TelaInicialController.this.telaDisciplina.setVisible(true);
            } else if (e.getSource() == TelaInicialController.this.telaDisciplina.getBtnConfirmar()) {
                try {
                    if (TelaInicialController.this.telaDisciplina.getTxtDisciplina().getText().isEmpty()) {
                        throw new SiseducException("Informe o nome da disciplina");
                    }

                    final Disciplina disciplina = new Disciplina();
                    disciplina.setId(Integer.parseInt(TelaInicialController.this.telaDisciplina.getTxtMatricula().getText()));
                    disciplina.setDisciplina(TelaInicialController.this.telaDisciplina.getTxtDisciplina().getText());
                    disciplina.setDataCadastro(PessoaBO.data(TelaInicialController.this.telaDisciplina.getTxtDataCadastro().getText(), '/', '-'));
                    disciplina.setDataModificacao(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
                    disciplina.setDescricao(TelaInicialController.this.telaDisciplina.getTxtDescricao().getText());
                    disciplina.setAtiva(TelaInicialController.this.telaDisciplina.getRbAtivaSim().isSelected());

                    final String usuario = TelaInicialController.this.telaInicial.getLbUsuario().getText();
                    
                    if (TelaInicialController.this.escolaBO.confirmarCadastro(disciplina, usuario)) {
                        TelaInicialController.this.telaDisciplina.getJcbDisciplinas().removeAllItems();
                        TelaInicialController.this.telaDisciplina.getJcbDisciplinas().addItem("Disciplinas");

                        TelaInicialController.this.escolaBO.get("disciplina").stream().forEach((d) -> {
                            TelaInicialController.this.telaDisciplina.getJcbDisciplinas().addItem(d);
                        });

                        Siseduc.showMessage(Siseduc.TITULO, Mensagem.CONCLUIDO, TipoMensagem.MSG_INFORMATION);
                        this.limparCampos();
                    }
                } catch (SiseducException ex) {
                    Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                }
            } else if (e.getSource() == TelaInicialController.this.telaDisciplina.getBtnGrade()) {
                final String[] paths = {Grade.EJA_FILE, Grade.FUNDAMENTAL_1_FILE, Grade.FUNDAMENTAL_2_FILE, Grade.MEDIO_FILE};
                final Set<Object> disciplinas = new HashSet<>();
                
                for (String path : paths) {
                    Arquivo.getObjects(Arquivo.DIR_GRADE.concat(path)).stream().forEach((object) -> {
                        disciplinas.add(object);
                    });
                }
                StringBuilder conf = new StringBuilder(Mensagem.CADASTRAR_DISCIPLINA);
                int i = 1;
                for (Object d : disciplinas) {
                    conf.append(String.valueOf(i++)).append(" - ").append(d).append("\n");
                }
                conf.append(Mensagem.CONFIRMAR_CADASTRO_DISCIPLINA);
                
                final TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, conf.toString(), TipoMensagem.MSG_QUESTION);
                if (response == TipoMensagem.OPCAO_OK) {
                    final Disciplina disciplina = new Disciplina();
                    final String usuario = TelaInicialController.this.telaInicial.getLbUsuario().getText();
                    
                    for (Object d : disciplinas) {
                        disciplina.setId(-1);
                        disciplina.setDisciplina((String) d);
                        disciplina.setDataCadastro(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
                        disciplina.setDataModificacao(String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
                        disciplina.setDescricao((String) d);
                        disciplina.setAtiva(true);
                        
                        TelaInicialController.this.escolaBO.confirmarCadastro(disciplina, usuario);
                    }
                    Siseduc.showMessage(Siseduc.TITULO, Mensagem.CONCLUIDO, TipoMensagem.MSG_INFORMATION);
                    this.limparCampos();
                }                
            } else if (e.getSource() == TelaInicialController.this.telaDisciplina.getBtnCancelar()) {
                this.limparCampos();
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == TelaInicialController.this.telaDisciplina.getJcbDisciplinas()) {
                    if (TelaInicialController.this.telaDisciplina.getJcbDisciplinas().getSelectedIndex() != 0) {

                        final String disciplina = TelaInicialController.this.telaDisciplina.getJcbDisciplinas().getSelectedItem().toString();
                        final Disciplina d = (Disciplina) TelaInicialController.this.escolaBO.get(EscolaBO.DISCIPLINA, disciplina);

                        TelaInicialController.this.telaDisciplina.getTxtMatricula().setText(PessoaBO.getCadastro(d.getId()));
                        TelaInicialController.this.telaDisciplina.getTxtDisciplina().setText(d.getDisciplina());
                        TelaInicialController.this.telaDisciplina.getTxtDataCadastro().setText(PessoaBO.data(d.getDataCadastro(), '-', '/'));
                        TelaInicialController.this.telaDisciplina.getTxtModificadaEm().setText(PessoaBO.data(d.getDataModificacao(), '-', '/'));
                        TelaInicialController.this.telaDisciplina.getTxtDescricao().setText(d.getDescricao());

                        TelaInicialController.this.telaDisciplina.getRbAtivaSim().setSelected(d.isAtiva());
                        TelaInicialController.this.telaDisciplina.getRbAtivaNao().setSelected(!d.isAtiva());
                    } else {

                        final String data = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());

                        TelaInicialController.this.telaDisciplina.getTxtMatricula().setText(PessoaBO.getCadastro(Integer.parseInt(escolaBO.getId("disciplina"))));
                        TelaInicialController.this.telaDisciplina.getTxtDisciplina().setText(null);
                        TelaInicialController.this.telaDisciplina.getTxtDescricao().setText(null);
                        TelaInicialController.this.telaDisciplina.getTxtDataCadastro().setText(data);
                        TelaInicialController.this.telaDisciplina.getTxtModificadaEm().setText(data);
                    }
                }
            }
        }
    }

    /**
     * Classe interna para controlar todos os eventos da
     * <code>TelaDeclaracao</code>
     *
     * @see TelaDeclaracao
     * @see PessoaBO
     *
     * @author Juan Soares
     */
    private final class DeclaracaoController extends MouseAdapter implements ActionListener, ItemListener, KeyListener {

        /**
         * Método responsável por fazer a ordenacao de forma dinamica dos
         * resultados na tabela.
         */
        private void ordenarTabela() {
            final JTable table            = TelaInicialController.this.telaDeclaracao.getTabelaListar();
            final DefaultTableModel model = TelaInicialController.this.telaDeclaracao.getModel();

            TelaInicialController.this.telaDeclaracao.getTxtPesquisar().setEnabled(TelaInicialController.this.telaDeclaracao.getJcbTipo().getSelectedIndex() != 0);

            if (TelaInicialController.this.telaDeclaracao.getJcbTipo().getSelectedIndex() == 0) {
                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }
                return;
            }

            String tabela = PessoaBO.removerAcentos(TelaInicialController.this.telaDeclaracao.getJcbTipo().getSelectedItem().toString().toLowerCase());
            final String nome = TelaInicialController.this.telaDeclaracao.getTxtPesquisar().getText().trim();

            if (nome.isEmpty()) {
                PessoaBO.preencherTabela(table, model, tabela);
            } else {
                PessoaBO.preencherTabela(table, model, tabela, nome);
            }
        }

        /**
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            final JTable table = TelaInicialController.this.telaDeclaracao.getTabelaListar();

            if (e.getSource() == table && e.getClickCount() > 1) {
                if (table.getSelectedRowCount() == 1) {
                    
                }
            }
        }

        /**
         *
         * @param e
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == TelaInicialController.this.telaDeclaracao.getJcbTipo()) {
                    this.ordenarTabela();
                }
            }
        }
        
        // <editor-fold defaultstate="collapsed" desc="Igonarado">
        @Override
        public void keyTyped(KeyEvent e) {
            /**
             * Ignora *
             */
        }

        @Override
        public void keyPressed(KeyEvent e) {
            /**
             * Ignora *
             */
        }
        // </editor-fold>
        
        /**
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            ordenarTabela();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == TelaInicialController.this.telaDeclaracao.getBtnCancelar()) {
                TelaInicialController.this.telaDeclaracao.getJcbTipo().setSelectedIndex(0);
                TelaInicialController.this.telaDeclaracao.dispose();
            }
        }
    }

    /**
     * Classe interna para tratar todos os eventos da
     * <code>TelaCadastroCargoFuncao</code>
     *
     * @see TelaCadastroCargoFuncao
     *
     * @author Juan Soares
     */
    private final class CargoFuncaoController implements ActionListener, ItemListener {

        private void limparCampos() {

            final String data = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());

            TelaInicialController.this.telaCadastroFuncao.getTxtCargo().setText(null);
            TelaInicialController.this.telaCadastroFuncao.getTxtDataCadastro().setText(data);
            TelaInicialController.this.telaCadastroFuncao.getTxtDataModifiacao().setText(data);
            TelaInicialController.this.telaCadastroFuncao.getTxtDescricao().setText(null);

            TelaInicialController.this.telaCadastroFuncao.getJcbCargos().setSelectedIndex(0);
            TelaInicialController.this.telaCadastroFuncao.getTxtId().setText(escolaBO.getId("cargo_funcao"));

            TelaInicialController.this.telaCadastroFuncao.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == telaInicial.getJmiCargosFuncoes()) {
                telaCadastroFuncao.setFuncao(escolaBO.get("cargo_funcao"));
                telaCadastroFuncao.getTxtId().setText(escolaBO.getId("cargo_funcao"));
                
                telaCadastroFuncao.setVisible(true);
            } else if (e.getSource() == TelaInicialController.this.telaCadastroFuncao.getBtnCancelar()) {
                this.limparCampos();
            } else if (e.getSource() == TelaInicialController.this.telaCadastroFuncao.getBtnConfirmar()) {

                final IGenerica<Funcionario> funcao = new Generica<>();
                funcao.setInstance(new Funcionario());

                funcao.getInstance().setMatricula(TelaInicialController.this.telaCadastroFuncao.getTxtId().getText());
                funcao.getInstance().setCargoFuncao(TelaInicialController.this.telaCadastroFuncao.getTxtCargo().getText());
                funcao.getInstance().setDataCadastro(PessoaBO.data(TelaInicialController.this.telaCadastroFuncao.getTxtDataCadastro().getText(), '/', '-'));
                funcao.getInstance().setDataModificacao(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
                funcao.getInstance().setDescricaoFuncao(TelaInicialController.this.telaCadastroFuncao.getTxtDescricao().getText());
                funcao.getInstance().setAtiva((TelaInicialController.this.telaCadastroFuncao.getRbAtivaSim().isSelected()));

                final String usuario = telaInicial.getLbUsuario().getText();
                
                if (TelaInicialController.this.escolaBO.confirmarCadastro(funcao, usuario)) {

                    TelaInicialController.this.telaCadastroFuncao.getJcbCargos().removeAllItems();
                    TelaInicialController.this.telaCadastroFuncao.getJcbCargos().addItem("Cargos/Funções");

                    TelaInicialController.this.escolaBO.get("cargo_funcao").stream().forEach((func) -> {
                        TelaInicialController.this.telaCadastroFuncao.getJcbCargos().addItem(func);
                    });

                    Siseduc.showMessage(Siseduc.TITULO, Mensagem.CONCLUIDO, TipoMensagem.MSG_INFORMATION);
                    this.limparCampos();
                }
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == TelaInicialController.this.telaCadastroFuncao.getJcbCargos()) {
                    if (TelaInicialController.this.telaCadastroFuncao.getJcbCargos().getSelectedIndex() != 0) {

                        final String funcao = TelaInicialController.this.telaCadastroFuncao.getJcbCargos().getSelectedItem().toString();
                        final IGenerica<Funcionario> f = (IGenerica<Funcionario>) escolaBO.get(EscolaBO.CARGO_FUNCAO, funcao);

                        TelaInicialController.this.telaCadastroFuncao.getTxtId().setText(PessoaBO.getCadastro(Integer.parseInt(f.getInstance().getMatricula())));
                        TelaInicialController.this.telaCadastroFuncao.getTxtCargo().setText(f.getInstance().getCargoFuncao());
                        TelaInicialController.this.telaCadastroFuncao.getTxtDataCadastro().setText(PessoaBO.data(f.getInstance().getDataCadastro(), '-', '/'));
                        TelaInicialController.this.telaCadastroFuncao.getTxtDataModifiacao().setText(PessoaBO.data(f.getInstance().getDataModificacao(), '-', '/'));
                        TelaInicialController.this.telaCadastroFuncao.getTxtDescricao().setText(f.getInstance().getDescricaoFuncao());

                        TelaInicialController.this.telaCadastroFuncao.getRbAtivaSim().setSelected(f.getInstance().isAtiva());
                        TelaInicialController.this.telaCadastroFuncao.getRbAtivaNao().setSelected(!f.getInstance().isAtiva());

                    } else {
                        final String data = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());

                        TelaInicialController.this.telaCadastroFuncao.getTxtId().setText(PessoaBO.getCadastro(Integer.parseInt(escolaBO.getId("cargo_funcao"))));
                        TelaInicialController.this.telaCadastroFuncao.getTxtCargo().setText(null);
                        TelaInicialController.this.telaCadastroFuncao.getTxtDescricao().setText(null);
                        TelaInicialController.this.telaCadastroFuncao.getTxtDataCadastro().setText(data);
                        TelaInicialController.this.telaCadastroFuncao.getTxtDataModifiacao().setText(data);
                    }
                }
            }
        }
    }

    private final class LembreteController extends MouseAdapter implements ActionListener {
        
        private final Component[] dias;
        
        public LembreteController() {
            this.dias = TelaInicialController.this.telaInicial.getCalendario().getDayChooser().getDayPanel().getComponents();
            for (Component dia : dias) {
                ((JButton) dia).setComponentPopupMenu(TelaInicialController.this.telaInicial.getMenuPopUp());
                ((JButton) dia).addMouseListener(this);
            }
            TelaInicialController.this.telaInicial.getCalendario().addMouseListener(this);
            TelaInicialController.this.telaInicial.getTelaCalendario().addMouseListener(this);
            
            ((JSpinner) telaInicial.getCalendario().getYearChooser().getSpinner()).addChangeListener((ChangeEvent e) -> {
                telaInicial.getCalendario().getDayChooser().getDayPanel().requestFocus();
            });
            
            ((JPanel) telaInicial.getCalendario().getDayChooser().getDayPanel()).addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    final JCalendar calendario = telaInicial.getCalendario();
                    TelaInicialController.this.lembreteBo.setIconLembretes(calendario, session.getIdUsuario());
                }
            });
        }
        
        public void verificarDisparo(MouseEvent e) {
            final JMenu menu = TelaInicialController.this.telaInicial.getMenuMeusLembretes();
            final JMenuItem menuExcluir = TelaInicialController.this.telaInicial.getJmiExcluirLembrete();
            final JMenuItem menuEditar = TelaInicialController.this.telaInicial.getJmiEditarLembrete();
            final String data = String.format("%1$tY-%1$tm-%1$td", telaInicial.getCalendario().getDate());
            
            TelaInicialController.this.lembreteBo.meusLembretes(session.getIdUsuario(), menu);
            TelaInicialController.this.lembreteBo.hasLembrete(session.getIdUsuario(), menuExcluir, menuEditar, data);
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            verificarDisparo(e);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == TelaInicialController.this.telaLembrete.getBtnConfirmar()) {
                
                final Date date         = TelaInicialController.this.telaLembrete.getCalendario().getDate();
                final String hora       = TelaInicialController.this.telaLembrete.getTxtHora().getText();
                
                final String titulo     = TelaInicialController.this.telaLembrete.getTxtTItulo().getText();
                final String descricao  = TelaInicialController.this.telaLembrete.getTxtDescricao().getText();
                final String tipo       = TelaInicialController.this.telaLembrete.getJcbTipo().getSelectedItem().toString();
                final String data       = String.format("%1$tY-%1$tm-%1$td %2$s", date, hora);
                final boolean ativo     = TelaInicialController.this.telaLembrete.getJrbAtivoSim().isSelected();
                
                final Lembrete lembrete = new Lembrete(titulo, descricao, tipo, data, ativo);
                final int idUsuario = session.getIdUsuario();
                
                if (lembreteBo.validarCampos(lembrete) && lembreteBo.confirmarCadastro(lembrete, idUsuario, 0)) {
                        Siseduc.showMessage(Siseduc.TITULO, Mensagem.CONCLUIDO, TipoMensagem.MSG_INFORMATION);
                        final JCalendar calendario = telaInicial.getCalendario();
                        lembreteBo.setIconLembretes(calendario, idUsuario);
                        this.limparCampos();
                }
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.telaInicial.getJmiAdicionarLembrete()) {
                
                final Date data   = TelaInicialController.this.telaInicial.getCalendario().getDate();
                final String hora = TelaInicialController.this.telaInicial.getHora().getText().substring(0, 5);
                
                TelaInicialController.this.telaLembrete.getCalendario().setDate(data);
                TelaInicialController.this.telaLembrete.getTxtHora().setText(hora);
                
                TelaInicialController.this.telaLembrete.setVisible(true);
                return;
            }
            
            if (e.getSource() == TelaInicialController.this.telaLembrete.getBtnCancelar()) {
                this.limparCampos();
            }
        }

        private void limparCampos() {
            TelaInicialController.this.telaLembrete.setVisible(false);
            TelaInicialController.this.telaLembrete.dispose();
                        
            TelaInicialController.this.telaLembrete.getTxtDescricao().setText(null);
            TelaInicialController.this.telaLembrete.getTxtTItulo().setText(null);
            TelaInicialController.this.telaLembrete.getTxtTempoRestante().setText(null);
            TelaInicialController.this.telaLembrete.getTxtHora().setText(null);
            TelaInicialController.this.telaLembrete.getJcbTipo().setSelectedIndex(0);
            TelaInicialController.this.telaLembrete.getJrbAtivoSim().setSelected(true);
            TelaInicialController.this.telaLembrete.getCalendario().setDate(Calendar.getInstance().getTime());
        }
    }
    
    /**
     * @see TelaDeclaracao
     * 
     * @return uma instancia da TelaDeclaracao
     */
    public TelaDeclaracao getTelaDeclaracao() {
        if (this.telaDeclaracao != null) {
            return this.telaDeclaracao;
        }
        return null;
    }   
   
    /**
     * @see TelaDiarioClasse
     * 
     * @return uma instancia da TelaDiarioClasse
     */
    public TelaDiarioClasse getTelaDiarioClasse() {
        if (this.diarioClasse != null) {
            return this.diarioClasse;
        }        
        return null;
    }

    /**
     * @see Session
     * 
     * @return 
     */
    public Session getSession() {
        if (this.session != null) {
            return session;
        }
        return null;
    }    
}