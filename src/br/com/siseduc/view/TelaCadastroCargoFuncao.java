/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import br.com.siseduc.Siseduc;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Juan Soares
 */
public class TelaCadastroCargoFuncao extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 4756142916856688365L;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    /**
     * Creates new form TelaCadastroAluno
     *
     * @param parent super classe
     * @param modal informa se a janela é modal
     */
    public TelaCadastroCargoFuncao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /**
     * 
     * @param cargos 
     */
    public void setFuncao(List<String> cargos) {
        if (cargos != null) {
            if (!cargos.isEmpty()) {
               this.jcbCargos.removeAllItems();
               this.jcbCargos.addItem("Cargos/Funções");

               cargos.stream().forEach((cargo) -> {
                   jcbCargos.addItem(cargo);
               });
            }
        }
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

        grupoTurmaAtiva = new javax.swing.ButtonGroup();
        grupoCancelada = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        txtId = new javax.swing.JTextField();
        lbMatricula = new javax.swing.JLabel();
        jtPainelCadastro = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        painelEscolaAluno = new javax.swing.JPanel();
        txtCargo = new javax.swing.JTextField();
        lbDescricaoSerieAluno = new javax.swing.JLabel();
        painelAtiva1 = new javax.swing.JPanel();
        rbAtivaSim = new javax.swing.JRadioButton();
        rbAtivaNao = new javax.swing.JRadioButton();
        txtDataCadastro = new javax.swing.JFormattedTextField();
        lbDataMatriculaAluno = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lbDataMatriculaAluno1 = new javax.swing.JLabel();
        txtDataModifiacao = new javax.swing.JFormattedTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        jcbCargos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Siseduc.TITULO);
        setIconImage(new ImageIcon(getClass().getResource("/images/icone.png")).getImage());
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(204, 204, 204));
        txtId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbMatricula.setForeground(new java.awt.Color(255, 255, 255));
        lbMatricula.setText("Identificador");

        jtPainelCadastro.setBackground(new java.awt.Color(204, 204, 204));
        jtPainelCadastro.setPreferredSize(new java.awt.Dimension(914, 300));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setOpaque(false);

        painelEscolaAluno.setBackground(new java.awt.Color(102, 102, 102));
        painelEscolaAluno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargo/Função", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        painelEscolaAluno.setOpaque(false);

        lbDescricaoSerieAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDescricaoSerieAluno.setForeground(new java.awt.Color(255, 255, 255));
        lbDescricaoSerieAluno.setText("Cargo/Função (Obrigatório)");

        painelAtiva1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ativa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        painelAtiva1.setOpaque(false);

        grupoTurmaAtiva.add(rbAtivaSim);
        rbAtivaSim.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivaSim.setText("Sim");
        rbAtivaSim.setOpaque(false);

        grupoTurmaAtiva.add(rbAtivaNao);
        rbAtivaNao.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivaNao.setSelected(true);
        rbAtivaNao.setText("Não");
        rbAtivaNao.setOpaque(false);

        javax.swing.GroupLayout painelAtiva1Layout = new javax.swing.GroupLayout(painelAtiva1);
        painelAtiva1.setLayout(painelAtiva1Layout);
        painelAtiva1Layout.setHorizontalGroup(
            painelAtiva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAtiva1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbAtivaSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAtivaNao)
                .addGap(16, 16, 16))
        );
        painelAtiva1Layout.setVerticalGroup(
            painelAtiva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAtiva1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(painelAtiva1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAtivaSim)
                    .addComponent(rbAtivaNao)))
        );

        txtDataCadastro.setEditable(false);
        try {
            txtDataCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataCadastro.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        lbDataMatriculaAluno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDataMatriculaAluno.setForeground(new java.awt.Color(255, 255, 255));
        lbDataMatriculaAluno.setText("Data Cadastro");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Descrição");

        lbDataMatriculaAluno1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDataMatriculaAluno1.setForeground(new java.awt.Color(255, 255, 255));
        lbDataMatriculaAluno1.setText("Modificada em");

        txtDataModifiacao.setEditable(false);
        try {
            txtDataModifiacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataModifiacao.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        javax.swing.GroupLayout painelEscolaAlunoLayout = new javax.swing.GroupLayout(painelEscolaAluno);
        painelEscolaAluno.setLayout(painelEscolaAlunoLayout);
        painelEscolaAlunoLayout.setHorizontalGroup(
            painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                        .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDescricaoSerieAluno)
                            .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataMatriculaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                                .addComponent(lbDataMatriculaAluno1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDataModifiacao, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolaAlunoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(painelAtiva1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        painelEscolaAlunoLayout.setVerticalGroup(
            painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelEscolaAlunoLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolaAlunoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolaAlunoLayout.createSequentialGroup()
                                    .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbDescricaoSerieAluno)
                                        .addComponent(lbDataMatriculaAluno))
                                    .addGap(7, 7, 7)
                                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolaAlunoLayout.createSequentialGroup()
                                    .addComponent(lbDataMatriculaAluno1)
                                    .addGap(27, 27, 27)))))
                    .addComponent(txtDataModifiacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEscolaAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolaAlunoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(painelAtiva1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );

        btnCancelar.setText("Cancelar");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkmark-round.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnConfirmar.setIconTextGap(20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelEscolaAluno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelEscolaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtPainelCadastro.addTab("Informações da função", jPanel1);

        jcbCargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargos/Funções" }));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cargos/Funções");

        jDesktopPane1.setLayer(txtId, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbMatricula, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jtPainelCadastro, javax.swing.JLayeredPane.PALETTE_LAYER);
        jDesktopPane1.setLayer(jcbCargos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtPainelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMatricula)
                    .addComponent(jcbCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtPainelCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JRadioButton getRbAtivaNao() {
        return rbAtivaNao;
    }

    public JRadioButton getRbAtivaSim() {
        return rbAtivaSim;
    }

    public JTextField getTxtCargo() {
        return txtCargo;
    }

    public JFormattedTextField getTxtDataCadastro() {
        return txtDataCadastro;
    }

    public JFormattedTextField getTxtDataModifiacao() {
        return txtDataModifiacao;
    }

    public JTextArea getTxtDescricao() {
        return txtDescricao;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JComboBox<String> getJcbCargos() {
        return jcbCargos;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.ButtonGroup grupoCancelada;
    private javax.swing.ButtonGroup grupoTurmaAtiva;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbCargos;
    private javax.swing.JTabbedPane jtPainelCadastro;
    private javax.swing.JLabel lbDataMatriculaAluno;
    private javax.swing.JLabel lbDataMatriculaAluno1;
    private javax.swing.JLabel lbDescricaoSerieAluno;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JPanel painelAtiva1;
    private javax.swing.JPanel painelEscolaAluno;
    private javax.swing.JRadioButton rbAtivaNao;
    private javax.swing.JRadioButton rbAtivaSim;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JFormattedTextField txtDataCadastro;
    private javax.swing.JFormattedTextField txtDataModifiacao;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

    private final int returnStatus = RET_CANCEL;
}
