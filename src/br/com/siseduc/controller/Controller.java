/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.vo.Generica;
import br.com.siseduc.model.vo.IGenerica;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Sophia
 * @param <E>
 */
public final class Controller<E> implements ActionListener {

    /**
     *
     */
    private Session session;
    /**
     *
     */
    private final E instance;

    /**
     *
     */
    private boolean abrir;

    /**
     *
     * @param session
     */
    public Controller(Session session) {
        this(null, session);
    }

    /**
     *
     * @param instance
     * @param session
     */
    public Controller(E instance, Session session) {
        this(instance, session, true);
    }

    /**
     *
     * @param instance
     * @param session
     * @param abrir
     */
    public Controller(E instance, Session session, boolean abrir) {
        this.instance = instance;
        this.session = session;
        this.abrir = abrir;
    }

    public class MouseMotionEventListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            session.setIterator(0);
        }
    }

    /**
     *
     */
    public void acaoJanela() {
        IGenerica<JDialog> janela = new Generica<>();
        janela.setInstance((JDialog) this.instance);

        if (this.abrir) {
            janela.getInstance().addMouseMotionListener(this.new MouseMotionEventListener());
            janela.getInstance().setVisible(true);
            janela.getInstance().setLocationRelativeTo(null);
        } else {
            janela.getInstance().dispose();
        }
    }

    /**
     *
     * @param janela
     */
    public static void doClose(JDialog janela) {
        String cancelName = "Cancelar";
        InputMap inputMap = janela.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = janela.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
                janela.dispose();
            }
        });
    }

    /**
     * 
     * @param components 
     */
    public static void rollover(Component[] components) {
        final int length = components.length;
        final JButton[] botoes = new JButton[length];
        
        for (int i = 0; i < length; i++) {
            botoes[i] = (JButton) components[i];
        }
        
        final MouseListener MouseL = new MouseAdapter() {
            private JButton botao;
            private void dispararEvento(MouseEvent e, Color color) {
                botao = (JButton) e.getSource();
                botao.setForeground(color);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                this.dispararEvento(e, Color.WHITE);
            }
            @Override
            public void mouseEntered(MouseEvent e){
                this.dispararEvento(e, Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                this.dispararEvento(e, Color.WHITE);
            }
        };
        for (JButton botao : botoes) {
            botao.addMouseListener(MouseL);
        }
    }

    /**
     * Método que, ao ser chamado, avança uma tab da JDialog que o chamou.
     *
     * @param tabedPane
     */
    public static void nextTab(JTabbedPane tabedPane) {
        int index = tabedPane.getSelectedIndex();
        int limit = tabedPane.getTabCount() - 1;
        if (index >= 0) {
            if (index == limit) {
                index = -1;
            }
            tabedPane.setSelectedIndex(++index);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.acaoJanela();
    }
}
