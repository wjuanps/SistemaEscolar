/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Juan Soares
 */
public class TelaMensagem extends javax.swing.JDialog {
    
    /**
     * Creates new form Mensagem
     */
    public TelaMensagem() {
        super((Frame) null, true);
        initComponents();        
        
        String okName = "Ok";
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), okName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), okName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(okName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensagem.setOpcao(TipoMensagem.OPCAO_OK);
                setVisible(false);
                dispose();
            }
        });
    }

    /**
     * 
     * @param titulo
     * @param msg 
     * @param tipo 
     * @return  
     */
    public TipoMensagem showMessage(String titulo, String msg, TipoMensagem tipo) {
        this.setTitle(titulo);
        this.txtMessenger.setText(msg);
        
        switch (tipo) {
            case MSG_INFORMATION :
                laOk.setText("Ok");
                jpCancelar.setBorder(null);
                laCancelar.setVisible(false);
                break;
            case MSG_QUESTION :
                laOk.setText("Sim");
                jpCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
                laCancelar.setVisible(true);
                break;
            case MSG_ERROR:
                laOk.setText("Ok");
                jpCancelar.setBorder(null);
                laCancelar.setVisible(false);
                Toolkit.getDefaultToolkit().beep();
                break;
            default :
                return null;
        }       
                
        this.pack();
//        this.laCancelar.setForeground(Color.BLACK);
//        this.laOk.setForeground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        return Mensagem.getOpcao();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtMessenger = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jpOk = new javax.swing.JPanel();
        laOk = new javax.swing.JLabel();
        jpCancelar = new javax.swing.JPanel();
        laCancelar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(getClass().getResource("/images/icone.png")).getImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        txtMessenger.setEditable(false);
        txtMessenger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        txtMessenger.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtMessenger.setFocusable(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/msg.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jpOk.setBackground(new java.awt.Color(255, 255, 255));
        jpOk.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        laOk.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        laOk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        laOk.setText("Ok");
        laOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laOkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laOkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laOkMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpOkLayout = new javax.swing.GroupLayout(jpOk);
        jpOk.setLayout(jpOkLayout);
        jpOkLayout.setHorizontalGroup(
            jpOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laOk, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jpOkLayout.setVerticalGroup(
            jpOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jpCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        laCancelar.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        laCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        laCancelar.setText("Cancelar");
        laCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laCancelarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpCancelarLayout = new javax.swing.GroupLayout(jpCancelar);
        jpCancelar.setLayout(jpCancelarLayout);
        jpCancelarLayout.setHorizontalGroup(
            jpCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jpCancelarLayout.setVerticalGroup(
            jpCancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(190, 190, 190))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMessenger)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMessenger, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void laOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laOkMouseClicked
        this.txtMessenger.setText(null);
        laOk.setForeground(Color.BLACK);
        jpOk.setBackground(Color.WHITE);
        this.dispose();
        this.setVisible(false);
        
        Mensagem.setOpcao(TipoMensagem.OPCAO_OK);
    }//GEN-LAST:event_laOkMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Mensagem.setOpcao(TipoMensagem.OPCAO_CANCELAR);        
    }//GEN-LAST:event_formWindowClosing

    private void laCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laCancelarMouseClicked
         Mensagem.setOpcao(TipoMensagem.OPCAO_CANCELAR);
        
        laCancelar.setForeground(Color.BLACK);
        jpCancelar.setBackground(Color.WHITE);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_laCancelarMouseClicked

    private void laOkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laOkMouseEntered
        laOk.setForeground(new Color(30,67,128));
        jpOk.setBackground(new Color(188,188,188));
    }//GEN-LAST:event_laOkMouseEntered

    private void laOkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laOkMouseExited
        laOk.setForeground(Color.BLACK);
        jpOk.setBackground(Color.WHITE);
    }//GEN-LAST:event_laOkMouseExited

    private void laCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laCancelarMouseEntered
        laCancelar.setForeground(new Color(30,67,128));
        jpCancelar.setBackground(new Color(188,188,188));
    }//GEN-LAST:event_laCancelarMouseEntered

    private void laCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laCancelarMouseExited
        laCancelar.setForeground(Color.BLACK);
        jpCancelar.setBackground(Color.WHITE);
    }//GEN-LAST:event_laCancelarMouseExited
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jpCancelar;
    private javax.swing.JPanel jpOk;
    private javax.swing.JLabel laCancelar;
    private javax.swing.JLabel laOk;
    private javax.swing.JTextPane txtMessenger;
    // End of variables declaration//GEN-END:variables
}
