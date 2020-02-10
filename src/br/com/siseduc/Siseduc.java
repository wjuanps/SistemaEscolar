/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc;

import br.com.siseduc.controller.AlunoController;
import br.com.siseduc.controller.FornecedorController;
import br.com.siseduc.controller.FuncionarioController;
import br.com.siseduc.controller.ProfessorController;
import br.com.siseduc.controller.TelaInicialController;
import br.com.siseduc.controller.TurmaController;
import br.com.siseduc.model.bo.Logger;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.bo.UsuarioBO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.view.TelaDeclaracao;
import br.com.siseduc.view.TelaDiarioClasse;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaMensagem;
import br.com.siseduc.view.TelaPesquisa;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Sophia
 */
public final class Siseduc {
    /**
     * Constante utilizada para finalizar o sistema.
     * Rertona para o sistema operacional que não houve erro
     * na ececução da aplicação.
     */
    public static final int SAIR = 0;
    /**
     * Constante utilizada para finalizar o sistema.
     * Rertona para o sistema operacional que houve erro
     * na execução da aplicação.
     */
    public static final int FECHAR = 1;
    /**
     * Titulo do sistema
     */
    public static final String TITULO = "SISEDUC - Sistema Educacional";
     
    /**
     * Define o visual do sistema.
     */
    static {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}
    }
    
    /**
     * 
     */
    private static final TelaMensagem mensagem = new TelaMensagem();
    
    /**
     * Cria os diretórios caso eles não existão.
     */
    static {
        if (!Arquivo.criarDiretorios()) {
            System.exit(Siseduc.SAIR);
        }
     }
        
    /**
     * 
     * @param titulo
     * @param msg
     * @param tipo
     * @return 
     */
    public static TipoMensagem showMessage(String titulo, String msg, TipoMensagem tipo) {
        return mensagem.showMessage(titulo, msg, tipo);
    }    
   
    /**
     * Verifica se a conexão com o banco de dados
     * é bem sucedida. Caso contrário o banco de dados
     * será inicializado para posteriormente o sistema
     * ser inicializado. Caso o erro persista o sistema
     * é fechado
     */
    static {
        Conexao.testarConexao();
    } 
    
    /**
     * Verifica se o usuário Máster está cadastrado,
     * caso não esteja ele será cadastrado pelo sistema.
     */
    static {
        UsuarioBO.usuarioMaster();
    }
    
    /**
     * Verifica se o sietema já está aberto na máquina.
     */
    static {
        if (Session.isOn()) {
            Siseduc.showMessage(Siseduc.TITULO, Mensagem.SISTEMA_ABERTO, TipoMensagem.MSG_ERROR);
            System.exit(Siseduc.SAIR);
        }
    }
    
    /**
     * Encerra o sistema gerando o
     * <code>Log</code> de finalização do sistema
     * e retornando para o sistema operacional que o programa
     * foi encerrado de forma correta.
     *
     * @see Logger     * 
     */
    public static void finalizarSistema() {
        String acao = "--:: Sistema finalizado ~".concat(Session.getIp()+"~");
        Logger.logger(acao);
        Logger.logger(null, null, "--");        
        
        Logger.logger(Logger.INFO, "--#--", "IP da Máquina:", String.valueOf(Logger.LOG));
        System.exit(Siseduc.SAIR);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        /* Cria e inicializa o sistema */
        EventQueue.invokeLater(() -> {
            final TelaInicial telaInicial = new TelaInicial();

            final TelaInicialController telaInicialController = new TelaInicialController(telaInicial);
            final TelaDeclaracao telaDeclaracao = telaInicialController.getTelaDeclaracao();
            final Session session = telaInicialController.getSession();
            final TelaDiarioClasse telaDiarioClasse = telaInicialController.getTelaDiarioClasse();

            final TurmaController turmaController = new TurmaController(telaInicial, session);
            final TelaPesquisa telaPesquisaTurma = turmaController.getTelaCadastroTurma();

            new ProfessorController(telaInicial, telaDiarioClasse, session);
            new AlunoController(telaInicial, telaDeclaracao, telaPesquisaTurma, session);
            new FuncionarioController(telaInicial, session);
            new FornecedorController(telaInicial, session);

            Logger.logger("--:: Sistema iniciado ~".concat(Session.getIp() + "~"));
            Logger.logger(Logger.INFO, Session.getIp(), "IP da Máquina:", String.valueOf(Logger.INFO));
        });
    }
    
    
    PrintService listaDeImpressoras[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
    System.out.println(listaDeImpressoras.length);
    
    PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
    System.out.println(inpressoraPadrao.getName());
    
    DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    HashDocAttributeSet set = new HashDocAttributeSet();
    
    try {
	    FileInputStream file = new FileInputStream("juan.txt");
	    Doc doc = new SimpleDoc(file, docFlavor, set);
	    PrintRequestAttributeSet atrr = new HashPrintRequestAttributeSet();
	    printService printService = ServiceUI.printDialog(null, 300, 300, listaDeImpressoras, impressoraPadrao, docFlavor, atrr);
	    
	    if (printService != null) {
	    	DocPrintJob job = printService.createPrintJob();
	    	try {
	    		job.print(doc, atrr);
	    	} catch (PrintException x) {
	    		Logger.getLogger(Classe.class.getName()).log(Level.SEVERE, null, ex);		
	    	}
	    }
    } catch (FileNotFoundException ex) {
    	Logger.getLogger(Classe.class.getName()).log(Level.SEVERE, null, ex);
    }   
}