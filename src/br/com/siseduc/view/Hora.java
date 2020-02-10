/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import java.awt.EventQueue;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.accessibility.Accessible;
import javax.swing.JLabel;

/**
 *
 * @author Juan
 */
public final class Hora extends JLabel implements Accessible, Serializable {

    /**
     * For Serialization
     */
    private static final long serialVersionUID = -804583891978120419L;

    /**
     * Define o formato da <code>Hora</code>
     */
    private static final String HORA = "HH:mm:ss";
    /**
     * 
     */
    private Thread threadHora;

    /**
     * Inicializa a <code>Thread</code> que faz rodar a <code>Hora</code>.
     */
    public Hora() {

        this.start();

    }

    /**
     * Inicia a <code>Hora</code>
     */
    public void start() {
        this.threadHora = new Thread(new HoraRunnable(), "Thread Hora");
        this.threadHora.setDaemon(true);
        this.threadHora.start();
    }
    
    /**
     * Interrompe a thread
     */
    public void interrupt() {
        this.threadHora.interrupt();
    }
    
    /**
     *
     * @param date
     */
    private synchronized void setHora(Date date) {

        this.setText(new SimpleDateFormat(HORA).format(date));

    }

    /**
     *
     * @author Juan
     *
     */
    private class HoraRunnable implements Runnable {

        /**
         *
         */
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public void run() {

            try {

                /**
                 *
                 */
                while (true) {

                    EventQueue.invokeLater(() -> {
                        /**
                         *
                         */
                        Hora.this.setHora(new Date());
                    });

                    /**
                     *
                     */
                    Thread.sleep(1000);

                }

            } catch (InterruptedException e) {
                /**
                 * ignora *
                 */
            }

        }

    }

}