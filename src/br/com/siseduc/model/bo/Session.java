/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.controller.TelaInicialController;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Log;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaLogin;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JFrame;

/**
 *
 * @author Juan Soares
 */
public class Session {
    
    /**
     * Esse campo é usada como condição para 
     * finalizar uma sessão.
     * 
     * @see TelaInicialController
     * @see #endSession(boolean)
     */
    public static final boolean ENCERRAR_SESSAO = true;
    
    /**
     * Esse campo é usado para determinar se 
     * já existe uma instancia do sistema
     * aberto.
     * 
     * @see Siseduc
     * @see #isOn()
     */
    private static boolean on = false;
    
    private boolean continuar = true;
    
    private int time = 1200;
    private int iterator;
    private int idUsuario;
    
    private Thread thread;
    
    private final TelaLogin telaLogin;
    private final TelaInicial telaInicial;
    
    private static String ip;
    private static String usuario;

    /**
     * 
     * @param telaLogin
     * @param telaInicial 
     */
    public Session(TelaLogin telaLogin, TelaInicial telaInicial) {
        this.telaLogin = telaLogin;
        this.telaInicial = telaInicial;
    }
    
    /**
     * Verifica o estado da sessão.
     * 
     * @return true se o sistema já está aberto.
     *         false se o sistema não está aberto.
     */
    public synchronized static boolean isOn() {
        Log log = (Log) Arquivo.getSer(Arquivo.DIR_LOG.concat(Log.INFO));
        try {
            on = (log.getInfo().equals(Session.getIp()) && log.getOpcao().equals("1"));
        } catch (NullPointerException npe) {on = false;}
        return on;
    }
    
    /**
     * Recupera o usuario que está online.
     * 
     * @return o nome do usuário online.
     *         se não houver usário online retorna null.
     */
    public static String getUsuario() {
        Log log = (Log) Arquivo.getSer(Arquivo.DIR_LOG.concat(Log.SESSION));
        try {
            usuario = log.getInfo();
        } catch (NullPointerException npe) {
            return null;
        }
        return usuario;
    }
    
    /**
     * Recupera o ip da máquina onde
     * o sistema está em execução.
     * 
     * @return o ip da máquina.
     */
    public static String getIp() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return ip;
    }
    
    /**
     * Inicializa uma sessão
     * 
     * @param usuario
     */
    public synchronized void start(String usuario) {
        String acao = "Sessão iniciada por:";
        String servico = "";

        Logger.logger(usuario, acao, servico);
        Logger.logger(usuario, getIp());
     
        startThread();
    }
    
    /**
     * Finaliza uma sessão
     * 
     * @param continuar 
     */
    public void endSession(boolean continuar) {
        this.continuar = !continuar;
    }
    
    /**
     * 
     */
    private void deslogar() {        
        String usuario = telaInicial.getLbUsuario().getText();
        String acao = "Sessão finalizada por:";
        String servico = "";

        Logger.logger(usuario, acao, servico);
        Logger.logger(null, getIp());
        
        telaLogin.config();

        telaInicial.setVisible(false);
        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaInicial.dispose();        
    }
          
    /**
     * Inicializa a Thread da sessão.
     */
    public void startThread() {
        this.thread = new Thread(this.new SessionRunnable(), "Thread Session");
        this.thread.setDaemon(true);
        this.thread.start();
    }
        
    /**
     * Interrompe a Thread da Sessão
     */
    public void interruptThread() {
        this.thread.interrupt();
    }
    
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public synchronized void setIterator(int iterator) {
        this.iterator = iterator;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * 
     */
    @SuppressWarnings("SleepWhileInLoop")
    private final class SessionRunnable implements Runnable {
        
        private int i = 0;
        
        public SessionRunnable() {
            if (!continuar)
                continuar = true;
        }
        
        @Override
        public synchronized void run() {            
            do {                
                i = ++iterator;
                continuar = !(i == time);
                
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                    break;
                }
            } while (continuar);
            
            Session.this.deslogar();
            Session.this.interruptThread();
        }        
    }    
}