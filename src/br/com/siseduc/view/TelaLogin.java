/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import br.com.siseduc.Siseduc;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Juan Soares
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -477732654697382085L;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    /**
     *
     */
    private final Hora hora = new Hora();
    /**
     *
     */
    private final BarraDeStatus barra = new BarraDeStatus();
    /**
     *
     */
    private final Status status = new Status();
    /**
     * 
     */
    private final TelaInicial telaInicial;

    /**
     * Creates new form TelaLogin
     *
     * @param telaInicial telainicial
     */
    public TelaLogin(TelaInicial telaInicial) {
        initComponents();
        this.telaInicial = telaInicial;
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    /**
     * Configura alguns elementos da tela.
     */
    public void config() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        this.getOkButton().setEnabled(true);
        this.getOkButton().requestFocus();
        this.getCancelButton().setEnabled(true);
        this.getTxtLogin().setEnabled(true);
        this.getTxtSenha().setEnabled(true);
        this.getStatus().setStatus(null);
        this.getJpBarraStatus().setPreferredSize(new Dimension(0, 0));
        this.getJpBarraStatus().setOpaque(false);
        this.getBarra().setStatus(0);
        this.getBarra().setPorcentagem("");
        this.getHora().start();

        this.getTxtLogin().setText("Máster");
        this.getTxtSenha().setText(null);

        this.setVisible(true);
        this.getTxtLogin().requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        lbHora = hora;
        jpBarraStatus = barra;
        lbStatus = status;
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        setTitle(Siseduc.TITULO);
        setMinimumSize(new java.awt.Dimension(450, 320));
        setResizable(false);

        this.setIconImage(new ImageIcon(this.getClass().getResource("/images/icone.png")).getImage());
        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acesso ao Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        txtLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLogin.setText("Máster");
        txtLogin.requestFocus();

        txtSenha.setBackground(new java.awt.Color(153, 153, 153));
        txtSenha.setPreferredSize(new java.awt.Dimension(6, 29));
        txtSenha.setScrollOffset(1);

        okButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        okButton.setText("Acessar");

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cancelButton.setText("Cancelar");

        lbHora.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbHora.setForeground(new java.awt.Color(255, 255, 255));
        lbHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHora.setText("<Hora>");

        jpBarraStatus.setBackground(new java.awt.Color(15, 60, 32));
        jpBarraStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jpBarraStatus.setOpaque(false);
        jpBarraStatus.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout jpBarraStatusLayout = new javax.swing.GroupLayout(jpBarraStatus);
        jpBarraStatus.setLayout(jpBarraStatusLayout);
        jpBarraStatusLayout.setHorizontalGroup(
            jpBarraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpBarraStatusLayout.setVerticalGroup(
            jpBarraStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        lbStatus.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jpBarraStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                        .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLogin))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(lbStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHora, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jpBarraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        getRootPane().setDefaultButton(okButton);

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @author Juan
     *
     */
    @SuppressWarnings("serial")
    public final class BarraDeStatus extends JPanel {

        /**
         *
         */
        private int status;

        /**
         *
         */
        private String porcentagem = "";

        /**
         *
         */
        private final Random random = new Random();
        /**
         *
         */
        private Thread threadBarraDeStatus;

        /**
         *
         */
        public BarraDeStatus() {}

        /**
         *
         */
        public void start() {
            threadBarraDeStatus = new Thread(new BarraDeStatusRunnable(), "Barra De Status Thread");
            threadBarraDeStatus.setDaemon(true);
            threadBarraDeStatus.start();
        }

        /**
         *
         */
        public void interrupt() {
            threadBarraDeStatus.interrupt();
        }

        /**
         *
         * @param status informa o status
         */
        public void setStatus(int status) {
            this.status = status;
        }

        /**
         *
         * @return o status
         */
        public int getStatus() {
            return status;
        }

        /**
         *
         * @param porcentagem informa a porcentagem
         */
        public void setPorcentagem(String porcentagem) {
            this.porcentagem = porcentagem;
        }

        /**
         *
         * @return a porcentagem
         */
        public String getPorcentagem() {
            return porcentagem;
        }

        /**
         *
         */
        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(20, 118, 22));
            g2.fillRect(0, 0, getStatus(), 15);
            g2.drawLine(WIDTH, WIDTH, WIDTH, WIDTH);

            g2.setColor(Color.WHITE);
            g2.drawString(getPorcentagem(), 125, 12);

            Toolkit.getDefaultToolkit().sync();

            g.dispose();
        }

        /**
         *
         * @author Juan
         *
         */
        private class BarraDeStatusRunnable implements Runnable {

            /**
             *
             */
            @Override
            @SuppressWarnings("SleepWhileInLoop")
            public void run() {
                try {
                    for (int i = 0; i <= 282; i++) {
                        repaint();

                        setStatus(i);
                        setPorcentagem(String.format("%d%%", ((100 * i) / 282)));

                        Thread.sleep(random.nextInt(65));
                    }
                } catch (Exception e) {} 
                finally {
                    BarraDeStatus.this.interrupt();
                }
            }
        }
    }

    /**
     *
     * @author Juan
     *
     */
    @SuppressWarnings("serial")
    public final class Status extends JLabel {

        /**
         *
         */
        private final String strStatus[] = {"Autenticando Usuário", "Carregando Banco de Dados", "Criando Janelas"};
        /**
         *
         */
        private Thread statusThread;

        /**
         *
         */
        public Status() {}

        /**
         *
         */
        public void start() {
            statusThread = new Thread(new StatusRunnable(), "Status Thread");
            statusThread.setDaemon(true);
            statusThread.start();
        }

        /**
         *
         */
        public void interrupt() {
            statusThread.interrupt();
        }

        /**
         *
         * @param status informa o status
         */
        public void setStatus(String status) {
            this.setText(status);
        }

        /**
         *
         * @author Juan
         *
         */
        private class StatusRunnable implements Runnable {

            /**
             *
             */
            @Override
            @SuppressWarnings("SleepWhileInLoop")
            public void run() {

                for (String status : strStatus) {
                    try {
                        setStatus(String.format("Status: %s...", status));
                        Thread.sleep(3000);
                    } catch (Exception e) {}
                }

                try {
                    dispose();
                    Thread.sleep(1000);
                    TelaLogin.this.telaInicial.setVisible(true);
                    TelaLogin.this.getHora().interrupt();
                } catch (Exception e) {} 
                finally {
                    Status.this.interrupt();
                }
            }
        }
    }
    
    /* GETTERS and SETTERS */
    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JTextField getTxtLogin() {
        return txtLogin;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public Hora getHora() {
        return hora;
    }

    public BarraDeStatus getBarra() {
        return barra;
    }

    public Status getStatus() {
        return status;
    }

    public JPanel getJpBarraStatus() {
        return jpBarraStatus;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpBarraStatus;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

    private final int returnStatus = RET_CANCEL;
}
