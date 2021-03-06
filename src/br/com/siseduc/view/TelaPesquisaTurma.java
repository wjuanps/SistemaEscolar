/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import br.com.siseduc.Siseduc;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class TelaPesquisaTurma extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -1332968298766913710L;
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
    private DefaultTableModel model;
    
    /**
     * Creates new form TelaCadastroPesquisaAluno
     * @param parent super classe
     * @param modal informa se a janela é modal
     */
    public TelaPesquisaTurma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuFlutuante = new javax.swing.JPopupMenu();
        jmiEditar = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        barraDeFerramentas = new javax.swing.JToolBar();
        btnNovaTurma = new javax.swing.JButton();
        btnEditarTurma = new javax.swing.JButton();
        btnExcluirTurma = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jcbOrdenar = new javax.swing.JComboBox<>();
        txtPesquisar = new javax.swing.JTextField();
        lbOrdenar = new javax.swing.JLabel();
        lbPesquisar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaListarTurma = new javax.swing.JTable();

        menuFlutuante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jmiEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compose-mini.png"))); // NOI18N
        jmiEditar.setText("Editar Turma");
        menuFlutuante.add(jmiEditar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Siseduc.TITULO);
        setMinimumSize(new java.awt.Dimension(871, 488));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        barraDeFerramentas.setBorder(null);
        barraDeFerramentas.setFloatable(false);
        barraDeFerramentas.setRollover(true);

        btnNovaTurma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovaTurma.setForeground(new java.awt.Color(255, 255, 255));
        btnNovaTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-people.png"))); // NOI18N
        btnNovaTurma.setText("Nova Turma");
        btnNovaTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovaTurma.setFocusable(false);
        btnNovaTurma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovaTurma.setPreferredSize(new java.awt.Dimension(90, 90));
        btnNovaTurma.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnNovaTurma);

        btnEditarTurma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditarTurma.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compose.png"))); // NOI18N
        btnEditarTurma.setText("Editar Turma");
        btnEditarTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarTurma.setFocusable(false);
        btnEditarTurma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditarTurma.setPreferredSize(new java.awt.Dimension(90, 90));
        btnEditarTurma.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnEditarTurma);

        btnExcluirTurma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExcluirTurma.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirTurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-close-outline.png"))); // NOI18N
        btnExcluirTurma.setText("Excluir Turma");
        btnExcluirTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirTurma.setFocusable(false);
        btnExcluirTurma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluirTurma.setPreferredSize(new java.awt.Dimension(90, 90));
        btnExcluirTurma.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnExcluirTurma);

        btnRelatorios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-paper-outline.png"))); // NOI18N
        btnRelatorios.setText("Relatórios");
        btnRelatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRelatorios.setFocusable(false);
        btnRelatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRelatorios.setPreferredSize(new java.awt.Dimension(90, 90));
        btnRelatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        barraDeFerramentas.add(btnRelatorios);

        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log-out.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setPreferredSize(new java.awt.Dimension(90, 90));
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnSair);

        jcbOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Turma", "Data Cadastro", "Modalidade", "Serie" }));

        lbOrdenar.setForeground(new java.awt.Color(255, 255, 255));
        lbOrdenar.setText("Ordenar por:");

        lbPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        lbPesquisar.setText("Pesquisa por turma");

        tabelaListarTurma.setModel(model = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turma", "Série", "Modalidade", "Alunos", "Professores", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaListarTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelaListarTurma.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaListarTurma);

        jDesktopPane1.setLayer(barraDeFerramentas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jcbOrdenar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtPesquisar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbOrdenar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbPesquisar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(barraDeFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(lbOrdenar)
                                .addGap(0, 126, Short.MAX_VALUE))
                            .addComponent(jcbOrdenar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPesquisar)
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(barraDeFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbOrdenar)
                            .addComponent(lbPesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    public JButton getBtnEditarTurma() {
        return btnEditarTurma;
    }

    public JButton getBtnExcluirTurma() {
        return btnExcluirTurma;
    }

    public JButton getBtnNovaTurma() {
        return btnNovaTurma;
    }

    public JButton getBtnRelatorios() {
        return btnRelatorios;
    }

    public JButton getBtnSair() {
        return btnSair;
    }

    public JComboBox<String> getJcbOrdenar() {
        return jcbOrdenar;
    }

    public JTable getTabelaListarTurma() {
        return tabelaListarTurma;
    }

    public JTextField getTxtPesquisar() {
        return txtPesquisar;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JMenuItem getJmiEditar() {
        return jmiEditar;
    }

    public JPopupMenu getMenuFlutuante() {
        return menuFlutuante;
    }

    public JToolBar getBarraDeFerramentas() {
        return barraDeFerramentas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraDeFerramentas;
    private javax.swing.JButton btnEditarTurma;
    private javax.swing.JButton btnExcluirTurma;
    private javax.swing.JButton btnNovaTurma;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbOrdenar;
    private javax.swing.JMenuItem jmiEditar;
    private javax.swing.JLabel lbOrdenar;
    private javax.swing.JLabel lbPesquisar;
    private javax.swing.JPopupMenu menuFlutuante;
    private javax.swing.JTable tabelaListarTurma;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    private final int returnStatus = RET_CANCEL;
}