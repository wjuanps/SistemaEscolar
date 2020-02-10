/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.view;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Estados;
import br.com.siseduc.model.vo.Formacao;
import br.com.siseduc.model.vo.Professor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class TelaCadastroProfessor extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = -6271152515845242443L;
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
    public static final Integer[] anos = new Integer[81];
    static {
        int ano = 1970;
        for (int i = 0; i < anos.length; i++) {
            anos[i] = ano++;
        }
    }
    
    /**
     *
     */
    private final DefaultTableModel modeloFormacao = new DefaultTableModel();

    /**
     * Creates new form TelaCadastroAluno
     *
     * @param parent super classe
     * @param modal informa se a janela é modal
     */
    public TelaCadastroProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.addListAnoTermino();
    }

    /**
     * 
     * @param professor dados do professor
     * @param formacoes lista com as formações
     */
    public void atualizarCadastro(Professor professor, List<Formacao> formacoes) {
        this.txtMatricula.setText(professor.getMatricula());
        
        this.txtNomeProfessor.setText(professor.getNome());
        this.lbFotoProfessor.setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat(professor.getFoto())));
        this.lbFotoProfessor.setName(professor.getFoto());
        this.txtNomeProfessorDesabilitado.setText(professor.getNome());
        this.rbSexoFeminino.setSelected(professor.getSexo().equals("Feminino"));
        this.rbSexoMasculino.setSelected(professor.getSexo().equals("Masculino"));
        this.txtDataNascimentoProfessor.setText(PessoaBO.data(professor.getDataNascimento(), '-', '/'));
        this.txtNacionalidadeProfessor.setText(professor.getNacionalidade());
        this.txtNaturalidadeProfessor.setText(professor.getNaturalidade());
        this.jcbUfNaturalidadeProfessor.setSelectedItem(professor.getUfNaturalidade());
        this.txtIdentidadeProfessor.setText(professor.getIdentidade());
        this.txtCpfProfessor.setText(professor.getCpf());
        this.txtDataCadastroProfessor.setText(PessoaBO.data(professor.getDataCadastro(), '-', '/'));

        this.txtEnderecoProfessor.setText(professor.getEndereco());
        this.txtComplementoEnderecoProfessor.setText(professor.getComplemento());
        this.txtBairroProfessor.setText(professor.getBairro());
        this.txtCidadeProfessor.setText(professor.getCidade());
        this.jcbUfResidenciaProfessor.setSelectedItem(professor.getUf());
        this.txtCepProfessor.setText(professor.getCep());
        this.txtTelefoneProfessor.setText(professor.getTelefone());
        this.txtCelularProfessor.setText(professor.getCelular());
        this.txtEmailProfessor.setText(professor.getEmail());

        while (this.modeloFormacao.getRowCount() > 0) {
            this.modeloFormacao.removeRow(0);
        }

        int linha = 0;
        for (Formacao formacao : formacoes) {

            this.modeloFormacao.addRow(new String[1]);

            this.tabelaFormacao.setValueAt(formacao.getTitulo(), linha, 0);
            this.tabelaFormacao.setValueAt(formacao.getCurso(), linha, 1);
            this.tabelaFormacao.setValueAt(formacao.getInstituicao(), linha, 2);
            this.tabelaFormacao.setValueAt(formacao.getAnoInicio(), linha, 3);
            this.tabelaFormacao.setValueAt(formacao.getAnoTermino(), linha, 4);

            linha++;
        }

        String[] listData = new String[professor.getDisciplina().size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = String.format("%s - %s", PessoaBO.getCadastro(professor.getDisciplina().get(i).getId()), professor.getDisciplina().get(i).getDisciplina());
        }

        this.jlDisciplinasCopy.setListData(listData);
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

        grupoSexo = new javax.swing.ButtonGroup();
        grupoTurmaAtiva = new javax.swing.ButtonGroup();
        grupoRepetente = new javax.swing.ButtonGroup();
        grupoEdFisica = new javax.swing.ButtonGroup();
        grupoIrmaosNaEscola = new javax.swing.ButtonGroup();
        grupoMoraComFilhoMae = new javax.swing.ButtonGroup();
        grupoMoraComFilhoPai = new javax.swing.ButtonGroup();
        grupoOutroFilhoMae = new javax.swing.ButtonGroup();
        grupoOutroFilhoPai = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        txtNomeProfessorDesabilitado = new javax.swing.JTextField();
        lbNomeProfessorDesabilitado = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        lbMatricula = new javax.swing.JLabel();
        jtPainelCadastroProfessor = new javax.swing.JTabbedPane();
        painelDadosDoProfessor = new javax.swing.JPanel();
        painelDadosPessoaisProfessor = new javax.swing.JPanel();
        txtNomeProfessor = new javax.swing.JTextField();
        lbNomeProfessor = new javax.swing.JLabel();
        painelSexo = new javax.swing.JPanel();
        rbSexoMasculino = new javax.swing.JRadioButton();
        rbSexoFeminino = new javax.swing.JRadioButton();
        lbDataNascimentoProfessor = new javax.swing.JLabel();
        lbNacionalidadeProfessor = new javax.swing.JLabel();
        txtNacionalidadeProfessor = new javax.swing.JTextField();
        lbNaturalidadeProfessor = new javax.swing.JLabel();
        txtNaturalidadeProfessor = new javax.swing.JTextField();
        jcbUfNaturalidadeProfessor = new javax.swing.JComboBox<>();
        lbUfNaturalidadeProfessor = new javax.swing.JLabel();
        txtDataNascimentoProfessor = new javax.swing.JFormattedTextField();
        painelFotoProfessor = new javax.swing.JPanel();
        lbFotoProfessor = new javax.swing.JLabel();
        lbIdentidadeProfessor = new javax.swing.JLabel();
        txtIdentidadeProfessor = new javax.swing.JFormattedTextField();
        txtCpfProfessor = new javax.swing.JFormattedTextField();
        lbCpfProfessor = new javax.swing.JLabel();
        lbDataCadastroProfessor = new javax.swing.JLabel();
        txtDataCadastroProfessor = new javax.swing.JTextField();
        painelEnderecoProfessor = new javax.swing.JPanel();
        txtEnderecoProfessor = new javax.swing.JTextField();
        lbEnderecoProfessor = new javax.swing.JLabel();
        txtComplementoEnderecoProfessor = new javax.swing.JTextField();
        lbComplementoEnderecoProfessor = new javax.swing.JLabel();
        txtCidadeProfessor = new javax.swing.JTextField();
        lbCidadeProfessor = new javax.swing.JLabel();
        jcbUfResidenciaProfessor = new javax.swing.JComboBox<>();
        lbUfResidenciaProfessor = new javax.swing.JLabel();
        txtCepProfessor = new javax.swing.JFormattedTextField();
        txtTelefoneProfessor = new javax.swing.JFormattedTextField();
        lbCepProfessor = new javax.swing.JLabel();
        lbTelefoneProfessor = new javax.swing.JLabel();
        lbCelularProfessor = new javax.swing.JLabel();
        lbEmailProfessor = new javax.swing.JLabel();
        txtCelularProfessor = new javax.swing.JFormattedTextField();
        txtEmailProfessor = new javax.swing.JTextField();
        txtBairroProfessor = new javax.swing.JTextField();
        lbBairroProfessor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jcbTitulo = new javax.swing.JComboBox<>();
        lbTitulo = new javax.swing.JLabel();
        lbCurso = new javax.swing.JLabel();
        txtCurso = new javax.swing.JTextField();
        txtInstituicao = new javax.swing.JTextField();
        lbInstituicao = new javax.swing.JLabel();
        lbAnoInicio = new javax.swing.JLabel();
        lbAnoTermino = new javax.swing.JLabel();
        txtDiploma = new javax.swing.JTextField();
        btnEscolherDiploma = new javax.swing.JButton();
        lbDiploma = new javax.swing.JLabel();
        jcbAnoInicio = new javax.swing.JComboBox<>();
        jcbAnoTermino = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFormacao = new javax.swing.JTable() {
            public boolean isCellEditable(int col, int row) {
                return false;
            }
        };
        btnAdicionarFormacao = new javax.swing.JButton();
        btnRemoverFormacao = new javax.swing.JButton();
        btnEditarFormacao = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlDisciplinas = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlDisciplinasCopy = new javax.swing.JList<>();
        btnAdicionarDisciplina = new javax.swing.JButton();
        btnRemoverDisciplina = new javax.swing.JButton();
        btnRemoverTodasDisciplinas = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtConfirmarCadastro = new javax.swing.JTextPane();
        jPanel8 = new javax.swing.JPanel();
        btnConfirmar = new javax.swing.JButton();
        btnCorrigir = new javax.swing.JButton();
        btnProximoPassoDadosProfessor = new javax.swing.JButton();
        btnCancelarDadosProfessor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(Siseduc.TITULO);
        setIconImage(new ImageIcon(getClass().getResource("/images/icone.png")).getImage());
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        txtNomeProfessorDesabilitado.setEditable(false);
        txtNomeProfessorDesabilitado.setBackground(new java.awt.Color(204, 204, 204));
        txtNomeProfessorDesabilitado.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        txtNomeProfessorDesabilitado.setForeground(new java.awt.Color(0, 25, 38));

        lbNomeProfessorDesabilitado.setForeground(new java.awt.Color(255, 255, 255));
        lbNomeProfessorDesabilitado.setText("Nome do(a) Professor(a)");

        txtMatricula.setEditable(false);
        txtMatricula.setBackground(new java.awt.Color(204, 204, 204));
        txtMatricula.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMatricula.setForeground(new java.awt.Color(1, 33, 41));
        txtMatricula.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbMatricula.setForeground(new java.awt.Color(255, 255, 255));
        lbMatricula.setText("Matrícula");

        jtPainelCadastroProfessor.setBackground(new java.awt.Color(204, 204, 204));
        jtPainelCadastroProfessor.setPreferredSize(new java.awt.Dimension(914, 300));

        painelDadosDoProfessor.setBackground(new java.awt.Color(204, 204, 204));
        painelDadosDoProfessor.setOpaque(false);

        painelDadosPessoaisProfessor.setBackground(new java.awt.Color(102, 102, 102));
        painelDadosPessoaisProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pessoal", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        painelDadosPessoaisProfessor.setOpaque(false);

        lbNomeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNomeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbNomeProfessor.setLabelFor(txtNomeProfessor);
        lbNomeProfessor.setText("Nome Completo (Obrigatório)");

        painelSexo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        painelSexo.setOpaque(false);

        grupoSexo.add(rbSexoMasculino);
        rbSexoMasculino.setForeground(new java.awt.Color(255, 255, 255));
        rbSexoMasculino.setSelected(true);
        rbSexoMasculino.setText("Masculino");
        rbSexoMasculino.setOpaque(false);

        grupoSexo.add(rbSexoFeminino);
        rbSexoFeminino.setForeground(new java.awt.Color(255, 255, 255));
        rbSexoFeminino.setText("Feminino");
        rbSexoFeminino.setOpaque(false);

        javax.swing.GroupLayout painelSexoLayout = new javax.swing.GroupLayout(painelSexo);
        painelSexo.setLayout(painelSexoLayout);
        painelSexoLayout.setHorizontalGroup(
            painelSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSexoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSexoMasculino)
                .addGap(18, 18, 18)
                .addComponent(rbSexoFeminino)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        painelSexoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rbSexoFeminino, rbSexoMasculino});

        painelSexoLayout.setVerticalGroup(
            painelSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSexoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(painelSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSexoMasculino)
                    .addComponent(rbSexoFeminino)))
        );

        painelSexoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rbSexoFeminino, rbSexoMasculino});

        lbDataNascimentoProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDataNascimentoProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbDataNascimentoProfessor.setLabelFor(txtDataNascimentoProfessor);
        lbDataNascimentoProfessor.setText("Nascimento (Obrigatório)");

        lbNacionalidadeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNacionalidadeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbNacionalidadeProfessor.setLabelFor(txtNacionalidadeProfessor);
        lbNacionalidadeProfessor.setText("Nacionalidade");

        lbNaturalidadeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNaturalidadeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbNaturalidadeProfessor.setLabelFor(txtNaturalidadeProfessor);
        lbNaturalidadeProfessor.setText("Naturalidade");

        jcbUfNaturalidadeProfessor.setModel(new javax.swing.DefaultComboBoxModel<>(Estados.values()));
        jcbUfNaturalidadeProfessor.setSelectedItem("PA");
        jcbUfNaturalidadeProfessor.setSelectedItem(Estados.PA);
        jcbUfNaturalidadeProfessor.setToolTipText("");
        jcbUfNaturalidadeProfessor.setOpaque(false);

        lbUfNaturalidadeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUfNaturalidadeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbUfNaturalidadeProfessor.setLabelFor(jcbUfNaturalidadeProfessor);
        lbUfNaturalidadeProfessor.setText("UF");

        try {
            txtDataNascimentoProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        painelFotoProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        painelFotoProfessor.setOpaque(false);

        lbFotoProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-contact-outline.png"))); // NOI18N
        lbFotoProfessor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout painelFotoProfessorLayout = new javax.swing.GroupLayout(painelFotoProfessor);
        painelFotoProfessor.setLayout(painelFotoProfessorLayout);
        painelFotoProfessorLayout.setHorizontalGroup(
            painelFotoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbFotoProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelFotoProfessorLayout.setVerticalGroup(
            painelFotoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbFotoProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbIdentidadeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbIdentidadeProfessor.setText("Identidade");

        try {
            txtIdentidadeProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCpfProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbCpfProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbCpfProfessor.setText("Cpf");

        lbDataCadastroProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbDataCadastroProfessor.setText("Data Cadastro");

        txtDataCadastroProfessor.setEditable(false);
        txtDataCadastroProfessor.setBackground(new java.awt.Color(204, 204, 204));
        txtDataCadastroProfessor.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        javax.swing.GroupLayout painelDadosPessoaisProfessorLayout = new javax.swing.GroupLayout(painelDadosPessoaisProfessor);
        painelDadosPessoaisProfessor.setLayout(painelDadosPessoaisProfessorLayout);
        painelDadosPessoaisProfessorLayout.setHorizontalGroup(
            painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNomeProfessor)
                            .addComponent(txtNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(painelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosPessoaisProfessorLayout.createSequentialGroup()
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosPessoaisProfessorLayout.createSequentialGroup()
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                                        .addComponent(txtIdentidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCpfProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                                        .addComponent(lbIdentidadeProfessor)
                                        .addGap(178, 178, 178)
                                        .addComponent(lbCpfProfessor)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataCadastroProfessor)
                                    .addComponent(txtDataCadastroProfessor)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosPessoaisProfessorLayout.createSequentialGroup()
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataNascimentoProfessor)
                                    .addComponent(txtDataNascimentoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNacionalidadeProfessor)
                                    .addComponent(txtNacionalidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNaturalidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNaturalidadeProfessor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbUfNaturalidadeProfessor)
                                    .addComponent(jcbUfNaturalidadeProfessor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(69, 69, 69)))
                .addComponent(painelFotoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelDadosPessoaisProfessorLayout.setVerticalGroup(
            painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(painelDadosPessoaisProfessorLayout.createSequentialGroup()
                                .addComponent(lbNomeProfessor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNacionalidadeProfessor)
                                .addComponent(lbNaturalidadeProfessor)
                                .addComponent(lbUfNaturalidadeProfessor))
                            .addComponent(lbDataNascimentoProfessor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNacionalidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNaturalidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbUfNaturalidadeProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataNascimentoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbIdentidadeProfessor)
                            .addComponent(lbCpfProfessor)
                            .addComponent(lbDataCadastroProfessor)))
                    .addComponent(painelFotoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosPessoaisProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdentidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataCadastroProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        painelEnderecoProfessor.setBackground(new java.awt.Color(102, 102, 102));
        painelEnderecoProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Residencial", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        painelEnderecoProfessor.setOpaque(false);

        lbEnderecoProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbEnderecoProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbEnderecoProfessor.setLabelFor(txtEnderecoProfessor);
        lbEnderecoProfessor.setText("Endereço");

        lbComplementoEnderecoProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbComplementoEnderecoProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbComplementoEnderecoProfessor.setLabelFor(txtComplementoEnderecoProfessor);
        lbComplementoEnderecoProfessor.setText("Complemento do Endereço");

        lbCidadeProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCidadeProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbCidadeProfessor.setText("Cidade");

        jcbUfResidenciaProfessor.setModel(new javax.swing.DefaultComboBoxModel<>(Estados.values()));
        jcbUfResidenciaProfessor.setSelectedItem(Estados.PA);
        jcbUfResidenciaProfessor.setSelectedItem("PA");

        lbUfResidenciaProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUfResidenciaProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbUfResidenciaProfessor.setText("UF");

        try {
            txtCepProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefoneProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbCepProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCepProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbCepProfessor.setText("CEP");

        lbTelefoneProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTelefoneProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbTelefoneProfessor.setText("Telefone");

        lbCelularProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCelularProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbCelularProfessor.setText("Celular");

        lbEmailProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbEmailProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbEmailProfessor.setText("Email");

        try {
            txtCelularProfessor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbBairroProfessor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbBairroProfessor.setForeground(new java.awt.Color(255, 255, 255));
        lbBairroProfessor.setLabelFor(txtBairroProfessor);
        lbBairroProfessor.setText("Bairro");

        javax.swing.GroupLayout painelEnderecoProfessorLayout = new javax.swing.GroupLayout(painelEnderecoProfessor);
        painelEnderecoProfessor.setLayout(painelEnderecoProfessorLayout);
        painelEnderecoProfessorLayout.setHorizontalGroup(
            painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCidadeProfessor)
                            .addComponent(txtCidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUfResidenciaProfessor)
                            .addComponent(jcbUfResidenciaProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCepProfessor)
                            .addComponent(txtCepProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefoneProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTelefoneProfessor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCelularProfessor)
                            .addComponent(txtCelularProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailProfessor)
                            .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                                .addComponent(lbEmailProfessor)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEnderecoProfessor)
                            .addComponent(txtEnderecoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComplementoEnderecoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbComplementoEnderecoProfessor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                                .addComponent(lbBairroProfessor)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtBairroProfessor))))
                .addContainerGap())
        );

        painelEnderecoProfessorLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCelularProfessor, txtTelefoneProfessor});

        painelEnderecoProfessorLayout.setVerticalGroup(
            painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoProfessorLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEnderecoProfessor)
                    .addComponent(lbComplementoEnderecoProfessor)
                    .addComponent(lbBairroProfessor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnderecoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplementoEnderecoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairroProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCidadeProfessor)
                    .addComponent(lbUfResidenciaProfessor)
                    .addComponent(lbCepProfessor)
                    .addComponent(lbTelefoneProfessor)
                    .addComponent(lbCelularProfessor)
                    .addComponent(lbEmailProfessor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEnderecoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidadeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbUfResidenciaProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCepProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefoneProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelularProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelDadosDoProfessorLayout = new javax.swing.GroupLayout(painelDadosDoProfessor);
        painelDadosDoProfessor.setLayout(painelDadosDoProfessorLayout);
        painelDadosDoProfessorLayout.setHorizontalGroup(
            painelDadosDoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosDoProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosDoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelEnderecoProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelDadosPessoaisProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        painelDadosDoProfessorLayout.setVerticalGroup(
            painelDadosDoProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosDoProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDadosPessoaisProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelEnderecoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jtPainelCadastroProfessor.addTab("Informações do(a) Professor(a)", painelDadosDoProfessor);

        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        jcbTitulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Graduação", "Especialização", "Mestrado", "Doutorado", "Pós Doutorado" }));

        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setText("Titulo (Obrigatório)");

        lbCurso.setForeground(new java.awt.Color(255, 255, 255));
        lbCurso.setText("Curso (Obrigatório)");

        lbInstituicao.setForeground(new java.awt.Color(255, 255, 255));
        lbInstituicao.setText("Instituição");

        lbAnoInicio.setForeground(new java.awt.Color(255, 255, 255));
        lbAnoInicio.setText("Ano Início (Obrigatório)");

        lbAnoTermino.setForeground(new java.awt.Color(255, 255, 255));
        lbAnoTermino.setText("Ano Término (Obrigatório)");

        txtDiploma.setEditable(false);

        btnEscolherDiploma.setText("Escolher Arquivo");

        lbDiploma.setForeground(new java.awt.Color(255, 255, 255));
        lbDiploma.setText("Diploma (Obrigatório)");

        jcbAnoInicio.setMaximumRowCount(5);
        jcbAnoInicio.setModel(new DefaultComboBoxModel<Integer>(anos));
        jcbAnoInicio.setSelectedItem(Integer.valueOf(String.format("%tY", Calendar.getInstance())));

        jcbAnoTermino.setMaximumRowCount(5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCurso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbInstituicao)
                                .addGap(0, 302, Short.MAX_VALUE))
                            .addComponent(txtInstituicao)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAnoInicio)
                            .addComponent(jcbAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbAnoTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAnoTermino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbDiploma)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDiploma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEscolherDiploma)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    .addComponent(lbCurso)
                    .addComponent(lbInstituicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAnoInicio)
                    .addComponent(lbAnoTermino)
                    .addComponent(lbDiploma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiploma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEscolherDiploma)
                    .addComponent(jcbAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbAnoTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista De Formação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        tabelaFormacao.setModel(modeloFormacao);
        criarTabela();
        tabelaFormacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelaFormacao.setOpaque(false);
        tabelaFormacao.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaFormacao);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAdicionarFormacao.setText("Adicionar Formação");

        btnRemoverFormacao.setText("Remover Formação");

        btnEditarFormacao.setText("Editar Formação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdicionarFormacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoverFormacao, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(btnEditarFormacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnAdicionarFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(86, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jtPainelCadastroProfessor.addTab("Formação Acadêmica", jPanel1);

        jPanel4.setOpaque(false);

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleção de Disciplinas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setOpaque(false);

        jlDisciplinas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jScrollPane2.setViewportView(jlDisciplinas);

        jlDisciplinasCopy.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jScrollPane3.setViewportView(jlDisciplinasCopy);

        btnAdicionarDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-arrow-forward.png"))); // NOI18N
        btnAdicionarDisciplina.setText("Adicionar");
        btnAdicionarDisciplina.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAdicionarDisciplina.setIconTextGap(20);

        btnRemoverDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ios7-arrow-back.png"))); // NOI18N
        btnRemoverDisciplina.setText("Remover");
        btnRemoverDisciplina.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnRemoverDisciplina.setIconTextGap(20);

        btnRemoverTodasDisciplinas.setText("Remover Todas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdicionarDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoverDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoverTodasDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnAdicionarDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverTodasDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jtPainelCadastroProfessor.addTab("Cadastro de Disciplinas", jPanel4);

        jPanel6.setOpaque(false);

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirmar Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setOpaque(false);

        txtConfirmarCadastro.setEditable(false);
        txtConfirmarCadastro.setBackground(new java.awt.Color(204, 204, 204));
        txtConfirmarCadastro.setBorder(null);
        txtConfirmarCadastro.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        txtConfirmarCadastro.setMargin(new java.awt.Insets(30, 30, 30, 30));
        txtConfirmarCadastro.setOpaque(false);
        jScrollPane4.setViewportView(txtConfirmarCadastro);

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comandos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel8.setOpaque(false);

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkmark-round.png"))); // NOI18N
        btnConfirmar.setText("CONFIRMAR");
        btnConfirmar.setIconTextGap(10);

        btnCorrigir.setText("CORRIGIR");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCorrigir, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCorrigir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtPainelCadastroProfessor.addTab("Confirmar Cadastro", jPanel6);

        btnProximoPassoDadosProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow-right-c.png"))); // NOI18N
        btnProximoPassoDadosProfessor.setText("Próximo Passo");
        btnProximoPassoDadosProfessor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnProximoPassoDadosProfessor.setIconTextGap(20);

        btnCancelarDadosProfessor.setText("Cancelar");

        jDesktopPane1.setLayer(txtNomeProfessorDesabilitado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbNomeProfessorDesabilitado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtMatricula, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lbMatricula, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jtPainelCadastroProfessor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnProximoPassoDadosProfessor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnCancelarDadosProfessor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(558, 558, 558)
                        .addComponent(btnProximoPassoDadosProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarDadosProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNomeProfessorDesabilitado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeProfessorDesabilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtPainelCadastroProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeProfessorDesabilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNomeProfessorDesabilitado)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtPainelCadastroProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarDadosProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProximoPassoDadosProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void criarTabela() {
        modeloFormacao.addColumn("Titulo");
        modeloFormacao.addColumn("Curso");
        modeloFormacao.addColumn("Instituição");
        modeloFormacao.addColumn("Ano Início");
        modeloFormacao.addColumn("Ano Término");

        tabelaFormacao.getColumnModel().getColumn(3).setPreferredWidth(10);
        tabelaFormacao.getColumnModel().getColumn(4).setPreferredWidth(10);
        
    }

    public JLabel getLbMatricula() {
        return lbMatricula;
    }
    
    public JButton getBtnAdicionarDisciplina() {
        return btnAdicionarDisciplina;
    }

    public JButton getBtnAdicionarFormacao() {
        return btnAdicionarFormacao;
    }

    public JButton getBtnCancelarDadosProfessor() {
        return btnCancelarDadosProfessor;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnCorrigir() {
        return btnCorrigir;
    }

    public JButton getBtnEditarFormacao() {
        return btnEditarFormacao;
    }

    public JButton getBtnEscolherDiploma() {
        return btnEscolherDiploma;
    }

    public JButton getBtnProximoPassoDadosProfessor() {
        return btnProximoPassoDadosProfessor;
    }

    public JButton getBtnRemoverDisciplina() {
        return btnRemoverDisciplina;
    }

    public JButton getBtnRemoverFormacao() {
        return btnRemoverFormacao;
    }

    public JButton getBtnRemoverTodasDisciplinas() {
        return btnRemoverTodasDisciplinas;
    }

    public JComboBox<Estados> getJcbUfNaturalidadeProfessor() {
        return jcbUfNaturalidadeProfessor;
    }

    public JComboBox<Estados> getJcbUfResidenciaProfessor() {
        return jcbUfResidenciaProfessor;
    }

    public JList<String> getJlDisciplinasCopy() {
        return jlDisciplinasCopy;
    }

    public JList<String> getJlDisciplinas() {
        return jlDisciplinas;
    }

    public JTabbedPane getJtPainelCadastroProfessor() {
        return jtPainelCadastroProfessor;
    }

    public JLabel getLbFotoProfessor() {
        return lbFotoProfessor;
    }

    public JRadioButton getRbSexoFeminino() {
        return rbSexoFeminino;
    }

    public JRadioButton getRbSexoMasculino() {
        return rbSexoMasculino;
    }

    public JTable getTabelaFormacao() {
        return tabelaFormacao;
    }

    public JComboBox<Integer> getJcbAnoInicio() {
        return jcbAnoInicio;
    }

    public JComboBox<Integer> getJcbAnoTermino() {
        return jcbAnoTermino;
    }
    
    public JTextField getTxtBairroProfessor() {
        return txtBairroProfessor;
    }

    public JFormattedTextField getTxtCelularProfessor() {
        return txtCelularProfessor;
    }

    public JFormattedTextField getTxtCepProfessor() {
        return txtCepProfessor;
    }

    public JTextField getTxtCidadeProfessor() {
        return txtCidadeProfessor;
    }

    public JTextField getTxtComplementoEnderecoProfessor() {
        return txtComplementoEnderecoProfessor;
    }

    public JTextPane getTxtConfirmarCadastro() {
        return txtConfirmarCadastro;
    }

    public JFormattedTextField getTxtCpfProfessor() {
        return txtCpfProfessor;
    }

    public JTextField getTxtCurso() {
        return txtCurso;
    }

    public JTextField getTxtDataCadastroProfessor() {
        return txtDataCadastroProfessor;
    }

    public JFormattedTextField getTxtDataNascimentoProfessor() {
        return txtDataNascimentoProfessor;
    }

    public JTextField getTxtDiploma() {
        return txtDiploma;
    }

    public JTextField getTxtEmailProfessor() {
        return txtEmailProfessor;
    }

    public JTextField getTxtEnderecoProfessor() {
        return txtEnderecoProfessor;
    }

    public JFormattedTextField getTxtIdentidadeProfessor() {
        return txtIdentidadeProfessor;
    }

    public JTextField getTxtInstituicao() {
        return txtInstituicao;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextField getTxtNacionalidadeProfessor() {
        return txtNacionalidadeProfessor;
    }

    public JTextField getTxtNaturalidadeProfessor() {
        return txtNaturalidadeProfessor;
    }

    public JTextField getTxtNomeProfessor() {
        return txtNomeProfessor;
    }

    public JTextField getTxtNomeProfessorDesabilitado() {
        return txtNomeProfessorDesabilitado;
    }

    public JFormattedTextField getTxtTelefoneProfessor() {
        return txtTelefoneProfessor;
    }

    public DefaultTableModel getModeloFormacao() {
        return modeloFormacao;
    }

    public JComboBox<String> getJcbTitulo() {
        return jcbTitulo;
    }

    public static Integer[] getAnos() {
        return anos;
    }
    
    public void addListAnoTermino() {
        this.jcbAnoTermino.setEnabled(true);
        this.jcbAnoTermino.removeAllItems();
        final int index = jcbAnoInicio.getSelectedIndex();
        for (int i = index; i < anos.length; i++) {
            jcbAnoTermino.addItem(anos[i]);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarDisciplina;
    private javax.swing.JButton btnAdicionarFormacao;
    private javax.swing.JButton btnCancelarDadosProfessor;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnCorrigir;
    private javax.swing.JButton btnEditarFormacao;
    private javax.swing.JButton btnEscolherDiploma;
    private javax.swing.JButton btnProximoPassoDadosProfessor;
    private javax.swing.JButton btnRemoverDisciplina;
    private javax.swing.JButton btnRemoverFormacao;
    private javax.swing.JButton btnRemoverTodasDisciplinas;
    private javax.swing.ButtonGroup grupoEdFisica;
    private javax.swing.ButtonGroup grupoIrmaosNaEscola;
    private javax.swing.ButtonGroup grupoMoraComFilhoMae;
    private javax.swing.ButtonGroup grupoMoraComFilhoPai;
    private javax.swing.ButtonGroup grupoOutroFilhoMae;
    private javax.swing.ButtonGroup grupoOutroFilhoPai;
    private javax.swing.ButtonGroup grupoRepetente;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.ButtonGroup grupoTurmaAtiva;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<Integer> jcbAnoInicio;
    private javax.swing.JComboBox<Integer> jcbAnoTermino;
    private javax.swing.JComboBox<String> jcbTitulo;
    private javax.swing.JComboBox<Estados> jcbUfNaturalidadeProfessor;
    private javax.swing.JComboBox<Estados> jcbUfResidenciaProfessor;
    private javax.swing.JList<String> jlDisciplinas;
    private javax.swing.JList<String> jlDisciplinasCopy;
    private javax.swing.JTabbedPane jtPainelCadastroProfessor;
    private javax.swing.JLabel lbAnoInicio;
    private javax.swing.JLabel lbAnoTermino;
    private javax.swing.JLabel lbBairroProfessor;
    private javax.swing.JLabel lbCelularProfessor;
    private javax.swing.JLabel lbCepProfessor;
    private javax.swing.JLabel lbCidadeProfessor;
    private javax.swing.JLabel lbComplementoEnderecoProfessor;
    private javax.swing.JLabel lbCpfProfessor;
    private javax.swing.JLabel lbCurso;
    private javax.swing.JLabel lbDataCadastroProfessor;
    private javax.swing.JLabel lbDataNascimentoProfessor;
    private javax.swing.JLabel lbDiploma;
    private javax.swing.JLabel lbEmailProfessor;
    private javax.swing.JLabel lbEnderecoProfessor;
    private javax.swing.JLabel lbFotoProfessor;
    private javax.swing.JLabel lbIdentidadeProfessor;
    private javax.swing.JLabel lbInstituicao;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JLabel lbNacionalidadeProfessor;
    private javax.swing.JLabel lbNaturalidadeProfessor;
    private javax.swing.JLabel lbNomeProfessor;
    private javax.swing.JLabel lbNomeProfessorDesabilitado;
    private javax.swing.JLabel lbTelefoneProfessor;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUfNaturalidadeProfessor;
    private javax.swing.JLabel lbUfResidenciaProfessor;
    private javax.swing.JPanel painelDadosDoProfessor;
    private javax.swing.JPanel painelDadosPessoaisProfessor;
    private javax.swing.JPanel painelEnderecoProfessor;
    private javax.swing.JPanel painelFotoProfessor;
    private javax.swing.JPanel painelSexo;
    private javax.swing.JRadioButton rbSexoFeminino;
    private javax.swing.JRadioButton rbSexoMasculino;
    private javax.swing.JTable tabelaFormacao;
    private javax.swing.JTextField txtBairroProfessor;
    private javax.swing.JFormattedTextField txtCelularProfessor;
    private javax.swing.JFormattedTextField txtCepProfessor;
    private javax.swing.JTextField txtCidadeProfessor;
    private javax.swing.JTextField txtComplementoEnderecoProfessor;
    private javax.swing.JTextPane txtConfirmarCadastro;
    private javax.swing.JFormattedTextField txtCpfProfessor;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtDataCadastroProfessor;
    private javax.swing.JFormattedTextField txtDataNascimentoProfessor;
    private javax.swing.JTextField txtDiploma;
    private javax.swing.JTextField txtEmailProfessor;
    private javax.swing.JTextField txtEnderecoProfessor;
    private javax.swing.JFormattedTextField txtIdentidadeProfessor;
    private javax.swing.JTextField txtInstituicao;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNacionalidadeProfessor;
    private javax.swing.JTextField txtNaturalidadeProfessor;
    private javax.swing.JTextField txtNomeProfessor;
    private javax.swing.JTextField txtNomeProfessorDesabilitado;
    private javax.swing.JFormattedTextField txtTelefoneProfessor;
    // End of variables declaration//GEN-END:variables

    private final int returnStatus = RET_CANCEL;
}
