/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;


import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.Paginacao;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class TelaPesquisa extends javax.swing.JDialog {

    /**
    * 
    */
   private static final long serialVersionUID = 4848102157511887812L;
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
    private boolean canEdit[];
    /**
     * 
     */
    private String columns[];
    /**
     * 
     */
    private JMenuItem[] menuItem;
    
    /**
     * 
     */
    private DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form TelaCadastroPesquisaAluno
     * @param parent super classe
     * @param modal informa se a janela é modal
     * @param columns
     */
    public TelaPesquisa(java.awt.Frame parent, boolean modal, String ... columns) {
        super(parent, modal);
        this.columns = columns;
        initComponents();
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    /**
     * 
     * @return 
     */
    private boolean[] canEdit() {
        canEdit = new boolean[columns.length];
        for (int i = 0; i < canEdit.length; i++) {
            canEdit[i] = false;
        }
        return canEdit;
    }

    /**
     * 
     * @param itens
     * @param images
     * @param separator 
     */
    public void setMenuItem(String[] itens, String[] images, boolean[] separator) {
        menuItem = new JMenuItem[itens.length];
        for (int i = 0; i < itens.length; i++) {
            menuItem[i] = new JMenuItem(itens[i]);
            menuItem[i].setActionCommand(itens[i]);
            menuItem[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            if (images != null) {
                try {
                    menuItem[i].setIcon(new ImageIcon(getClass().getResource("/images/" + images[i])));
                } catch (NullPointerException | ArrayIndexOutOfBoundsException a) {}
            }
            
            menuFlutuante.add(menuItem[i]);
            
            if (separator[i]) {
                menuFlutuante.add(new JPopupMenu.Separator());
            }
        }
    }
    
    /**
     * 
     * @param strings 
     */
    public void setString(String ... strings) {
        for (String string : strings) {
            jcbOrdenar.addItem(string);
        }
    }
    
    public JMenuItem[] getMenuItem() {
        return this.menuItem;
    }
    
    private String[] getColumns() {
        return columns;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuFlutuante = new javax.swing.JPopupMenu();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        barraDeFerramentas = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        btnEditarAluno = new javax.swing.JButton();
        btnExcluirAluno = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jcbOrdenar = new javax.swing.JComboBox<>();
        txtPesquisar = new javax.swing.JTextField();
        lbOrdenar = new javax.swing.JLabel();
        lbPesquisar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaListar = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbTotalPaginas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbInicio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnProxima = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        jcbItensPorPagina = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        menuFlutuante.setOpaque(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Siseduc.TITULO);
        setMinimumSize(new java.awt.Dimension(871, 488));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        barraDeFerramentas.setBorder(null);
        barraDeFerramentas.setFloatable(false);
        barraDeFerramentas.setRollover(true);

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/person-add.png"))); // NOI18N
        btnNovo.setMnemonic('N');
        btnNovo.setText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setPreferredSize(new java.awt.Dimension(90, 90));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnNovo);

        btnEditarAluno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditarAluno.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compose.png"))); // NOI18N
        btnEditarAluno.setMnemonic('E');
        btnEditarAluno.setText("Editar");
        btnEditarAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarAluno.setFocusable(false);
        btnEditarAluno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditarAluno.setPreferredSize(new java.awt.Dimension(90, 90));
        btnEditarAluno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnEditarAluno);

        btnExcluirAluno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExcluirAluno.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-close-outline.png"))); // NOI18N
        btnExcluirAluno.setMnemonic('X');
        btnExcluirAluno.setText("Excluir");
        btnExcluirAluno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluirAluno.setFocusable(false);
        btnExcluirAluno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluirAluno.setPreferredSize(new java.awt.Dimension(90, 90));
        btnExcluirAluno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnExcluirAluno);

        btnRelatorios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-paper-outline.png"))); // NOI18N
        btnRelatorios.setMnemonic('R');
        btnRelatorios.setText("Relatórios");
        btnRelatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRelatorios.setFocusable(false);
        btnRelatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRelatorios.setPreferredSize(new java.awt.Dimension(90, 90));
        btnRelatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnRelatorios);

        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log-out.png"))); // NOI18N
        btnSair.setMnemonic('S');
        btnSair.setText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.setFocusable(false);
        btnSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSair.setPreferredSize(new java.awt.Dimension(90, 90));
        btnSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraDeFerramentas.add(btnSair);

        lbOrdenar.setForeground(new java.awt.Color(255, 255, 255));
        lbOrdenar.setText("Ordenar por:");

        lbPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        lbPesquisar.setText("Pesquisa por nome");

        tabelaListar.setModel(model = new DefaultTableModel(
            new Object [][] {},
            getColumns()
        ) {
            boolean[] canEdit = canEdit();
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaListar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelaListar.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaListar);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setOpaque(false);

        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("0");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("-  total de registros");

        lbTotalPaginas.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalPaginas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalPaginas.setText("0");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("de");

        lbInicio.setForeground(new java.awt.Color(255, 255, 255));
        lbInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInicio.setText(String.valueOf(Paginacao.getPagina()));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mostrando página");

        btnProxima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-arrow-forward-mini.png"))); // NOI18N
        btnProxima.setToolTipText("Avançar uma página");
        btnProxima.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProxima.setMinimumSize(new java.awt.Dimension(0, 0));
        btnProxima.setPreferredSize(new java.awt.Dimension(40, 35));

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-arrow-back-mini.PNG"))); // NOI18N
        btnAnterior.setToolTipText("Voltar uma página");
        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.setEnabled(false);
        btnAnterior.setMinimumSize(new java.awt.Dimension(0, 0));
        btnAnterior.setPreferredSize(new java.awt.Dimension(40, 35));

        jcbItensPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "5", "10", "15", "20", "25", "30", "35", "40", "50", "100", "200", "500", "1000" }));
        jcbItensPorPagina.setSelectedIndex(2);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Itens por página");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbItensPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbItensPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalPaginas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jDesktopPane1.setLayer(barraDeFerramentas, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jcbOrdenar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtPesquisar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbOrdenar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbPesquisar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(barraDeFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbOrdenar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(lbPesquisar)
                                .addGap(0, 120, Short.MAX_VALUE))
                            .addComponent(txtPesquisar)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(barraDeFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbOrdenar)
                            .addComponent(lbPesquisar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public JButton getBtnEditar() {
        return btnEditarAluno;
    }

    public JButton getBtnExcluir() {
        return btnExcluirAluno;
    }

    public JButton getBtnNovo() {
        return btnNovo;
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

    public JTable getTabelaListar() {
        return tabelaListar;
    }

    public JTextField getTxtPesquisar() {
        return txtPesquisar;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JPopupMenu getMenuFlutuante() {
        return menuFlutuante;
    }

    public JToolBar getBarraDeFerramentas() {
        return barraDeFerramentas;
    }

    public JLabel getLbInicio() {
        return lbInicio;
    }

    public JLabel getLbTotalPaginas() {
        return lbTotalPaginas;
    }

    public JLabel getLbTotal() {
        return lbTotal;
    }

    public JButton getBtnAnterior() {
        return btnAnterior;
    }

    public JButton getBtnProxima() {
        return btnProxima;
    }

    public JComboBox<String> getJcbItensPorPagina() {
        return jcbItensPorPagina;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraDeFerramentas;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEditarAluno;
    private javax.swing.JButton btnExcluirAluno;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnProxima;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbItensPorPagina;
    private javax.swing.JComboBox<String> jcbOrdenar;
    private javax.swing.JLabel lbInicio;
    private javax.swing.JLabel lbOrdenar;
    private javax.swing.JLabel lbPesquisar;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalPaginas;
    private javax.swing.JPopupMenu menuFlutuante;
    private javax.swing.JTable tabelaListar;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

    private final int returnStatus = RET_CANCEL;
}
