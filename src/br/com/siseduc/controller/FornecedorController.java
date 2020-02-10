/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.FornecedorBO;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Fornecedor;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.view.TelaCadastroFornecedor;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class FornecedorController extends FornecedorBO implements ActionListener, ItemListener {

    /**
     *
     */
    private final TelaPesquisa telaPesquisa;

    /**
     *
     */
    private final TelaCadastroFornecedor telaCadastro;

    /**
     *
     */
    private final TelaInicial telaInicial;
    /**
     *
     */
    private Fornecedor fornecedor;
    /**
     * 
     */
    private String logo;
    /**
     * 
     */
    private String temp = null;
    private String aux = "";
    /**
     * 
     */
    private int tipo = -1;
    /**
     * 
     */
    private final String[] columns = {"Cadastro", "Nome Fantasia", "Segmento", "Telefone", "Email", "Site"};
    /**
     * 
     */
    private final String[] strings = {"Cadastro", "Nome Fantasia", "Segmento", "Cidade", "Estado"};
    /**
     * 
     */
    private final String[] itens = {""};
    /**
     * 
     */
    private final boolean[] separator = {false};
    /**
     * 
     */
    private final String[] images = {""};

    /**
     *
     * @param telaInicial
     * @param session
     */
    public FornecedorController(TelaInicial telaInicial, Session session) {
        this.telaInicial = telaInicial;
        
        this.telaPesquisa = new TelaPesquisa(telaInicial, true, columns);
        this.telaPesquisa.setMenuItem(itens, images, separator);
        this.telaPesquisa.setString(strings);
        this.telaCadastro = new TelaCadastroFornecedor(telaInicial, true);

        this.telaInicial.getJmiFornecedor().addActionListener(this);
        this.telaInicial.getBtnFornecedor().addActionListener(this);

        this.telaPesquisa.getBtnNovo().addActionListener(this);
        this.telaPesquisa.getBtnEditar().addActionListener(this);
        this.telaPesquisa.getBtnSair().addActionListener(new Controller(this.telaPesquisa, session, false));
        this.telaPesquisa.getJcbOrdenar().addItemListener(this);
        this.telaPesquisa.getTxtPesquisar().addKeyListener(this.new AdaptadorTeclado());
        this.telaPesquisa.getTabelaListar().addMouseListener(this.new AdaptadorMouse());

        this.telaCadastro.getBtnConfirmar().addActionListener(this);
        this.telaCadastro.getBtnCancelar().addActionListener(this);
        this.telaCadastro.getBtnCancelarFornecedor().addActionListener(new Controller(this.telaCadastro, session, false));
        this.telaCadastro.getLbLogo().addMouseListener(this.new AdaptadorMouse());
        this.telaCadastro.getTxtNomeFantasia().addKeyListener(this.new AdaptadorTeclado());
        
        Controller.rollover(telaPesquisa.getBarraDeFerramentas().getComponents());
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == this.telaPesquisa.getJcbOrdenar()) {
            this.ordenarTabela();
        }
    }

    /**
     * 
     */
    private final class AdaptadorMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == FornecedorController.this.telaCadastro.getLbLogo()) {
                FornecedorController.this.logo = PessoaBO.alterarFoto(FornecedorController.this.telaCadastro.getLbLogo(), logo);
            } else if (e.getSource() == FornecedorController.this.telaPesquisa.getTabelaListar()) {
                if (e.getClickCount() > 1) {
                    FornecedorController.this.editarFornecedor();
                }
            }
        }
    }

    /**
     * 
     */
    private final class AdaptadorTeclado extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == FornecedorController.this.telaCadastro.getTxtNomeFantasia()) {
                FornecedorController.this.telaCadastro.getTxtNomeDesabilitado().setText(FornecedorController.this.telaCadastro.getTxtNomeFantasia().getText());
            } else if (e.getSource() == FornecedorController.this.telaPesquisa.getTxtPesquisar()) {
                FornecedorController.this.ordenarTabela();
            }
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        final JTable table = this.telaPesquisa.getTabelaListar();
        final DefaultTableModel model = this.telaPesquisa.getModel();

        if (e.getSource() == this.telaInicial.getJmiFornecedor()
                || e.getSource() == this.telaInicial.getBtnFornecedor()) {

            super.preencherTabela(table, model);

            this.telaPesquisa.setVisible(true);
            return;
        }

        if (e.getSource() == this.telaPesquisa.getBtnNovo()) {

            this.tipo = Pessoa.CADASTRAR;
            this.telaCadastro.getTxtMatricula().setText(super.getCadastro());

            this.telaCadastro.setVisible(true);
            return;
        }
        
        if (e.getSource() == this.telaPesquisa.getBtnEditar()) {
            this.editarFornecedor();
            return;
        }

        if (e.getSource() == this.telaCadastro.getBtnCancelar()) {
            logo = (logo == null) ? "null.png" : logo;
            try {
                Arquivo.excluirArquivo(Arquivo.DIR_TEMP, logo);
            } catch (IOException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }

            this.telaCadastro.setVisible(false);
            this.telaCadastro.dispose();

            this.limparTodosCampos();
        }
            
        if (e.getSource() == this.telaCadastro.getBtnConfirmar()) {
            if (this.logo == null) {
                aux = "default.png";
                logo = PessoaBO.criptografar(aux.concat(String.valueOf(new Random(1_000_000).nextInt()))).concat(".png");
            }
            
            this.fornecedor = this.dadosFornecedor(fornecedor);

            if (this.fornecedor != null) {
                String usuario = this.telaInicial.getLbUsuario().getText();
                if (super.confirmarCadastro(fornecedor, usuario, tipo)) {
                    if (!this.aux.equals("default.png")) {
                        try {
                            Arquivo.moverArquivo(Arquivo.DIR_TEMP.concat(logo), Arquivo.DIR_FOTOS.concat(fornecedor.getFoto()));
                        } catch (IOException ex) {
                            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                        }
                    } else {
                        final String fileName = Arquivo.DIR_FOTOS.concat(this.logo);
                        try {
                            Arquivo.copiarArquivo(Arquivo.DIR_FOTOS.concat(aux), fileName);
                        } catch (IOException ex) {
                            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                        }
                    }

                    if (!logo.equals(temp) && temp != null) {
                        try {
                            Arquivo.excluirArquivo(Arquivo.DIR_FOTOS, temp);
                        } catch (IOException ex) {
                            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                        }
                    }
                    
                    super.preencherTabela(table, model);

                    this.limparTodosCampos();
                    this.telaCadastro.dispose();
                }
            }
        }
    }

    /**
     *
     * @return
     */
    private Fornecedor dadosFornecedor(Fornecedor fornecedor) {

        fornecedor = new Fornecedor();

        fornecedor.setFoto(logo);
        this.telaCadastro.getLbLogo().setName(logo);

        fornecedor.setMatricula(telaCadastro.getTxtMatricula().getText());

        fornecedor.setNomeFantasia(telaCadastro.getTxtNomeFantasia().getText());
        fornecedor.setRazaoSocial(telaCadastro.getTxtRazaoSocial().getText());
        fornecedor.setSegmento(telaCadastro.getTxtSegmento().getText());
        fornecedor.setInscricaoEstadual(telaCadastro.getTxtIE().getText());
        fornecedor.setCnpj(telaCadastro.getTxtCpfCnpj().getText());
        fornecedor.setSite(telaCadastro.getTxtSite().getText());
        fornecedor.setTipo((telaCadastro.getRbPessoaFisica().isSelected()) ? "Pessoa Física" : "Pessoa Jurídica");
        fornecedor.setDataFundacao(telaCadastro.getTxtDataFundacao().getText());

        fornecedor.setTelefone(telaCadastro.getTxtTelefone().getText());
        fornecedor.setCelular(telaCadastro.getTxtCelular().getText());
        fornecedor.setEmail(telaCadastro.getTxtEmail().getText());

        fornecedor.setEndereco(telaCadastro.getTxtEndereco().getText());
        fornecedor.setComplemento(telaCadastro.getTxtComplementoEndereco().getText());
        fornecedor.setBairro(telaCadastro.getTxtBairro().getText());
        fornecedor.setCidade(telaCadastro.getTxtCidade().getText());
        fornecedor.setUf(telaCadastro.getJcbUfResidencia().getSelectedItem().toString());
        fornecedor.setCep(telaCadastro.getTxtCep().getText());

        return fornecedor;
    }

    /**
     * 
     */
    private void limparTodosCampos() {
        this.logo = null;

        this.telaCadastro.getLbLogo().setName(logo);
        this.telaCadastro.getLbLogo().setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat("default.png")));

        this.telaCadastro.getTxtMatricula().setText(null);
        this.telaCadastro.getTxtNomeDesabilitado().setText(null);
        this.telaCadastro.getTxtNomeFantasia().setText(null);
        this.telaCadastro.getTxtRazaoSocial().setText(null);
        this.telaCadastro.getTxtSegmento().setText(null);
        this.telaCadastro.getTxtIE().setText(null);
        this.telaCadastro.getTxtCpfCnpj().setText(null);
        this.telaCadastro.getTxtSite().setText(null);
        this.telaCadastro.getRbPessoaFisica().setSelected(true);
        this.telaCadastro.getTxtDataFundacao().setText(null);

        this.telaCadastro.getTxtTelefone().setText(null);
        this.telaCadastro.getTxtCelular().setText(null);
        this.telaCadastro.getTxtEmail().setText(null);

        this.telaCadastro.getTxtEndereco().setText(null);
        this.telaCadastro.getTxtComplementoEndereco().setText(null);
        this.telaCadastro.getTxtBairro().setText(null);
        this.telaCadastro.getTxtCidade().setText(null);
        this.telaCadastro.getJcbUfResidencia().setSelectedItem("PA");
        this.telaCadastro.getTxtCep().setText(null);

        this.temp = this.logo;
        this.aux = "";
        this.fornecedor = null;
        
        this.tipo = -1;
    }
    
    /**
     * 
     */
    private void ordenarTabela() {
        
        final JTable table = FornecedorController.this.telaPesquisa.getTabelaListar();
        final DefaultTableModel model = FornecedorController.this.telaPesquisa.getModel();

        final String nome = FornecedorController.this.telaPesquisa.getTxtPesquisar().getText().trim();
        String order = FornecedorController.this.telaPesquisa.getJcbOrdenar().getSelectedItem().toString().toLowerCase().replace(' ', '_');

        order = (order.equals("cadastro"))
                ? "id_fornecedor"
                : (order.equals("estado"))
                    ? "uf"
                    : order;

        FornecedorController.super.preencherTabela(table, model, order, nome);
    }
    
    /**
     * 
     */
    private void editarFornecedor() {
        JTable table = this.telaPesquisa.getTabelaListar();
        if (table.getSelectedRowCount() == 1) {
            final int matricula = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            
            this.fornecedor = super.getFornecedor(matricula);
            this.temp = this.logo = this.fornecedor.getFoto();
            
            this.tipo = Pessoa.ATUALIZAR;
            
            this.telaCadastro.editarFornecedor(this.fornecedor);
            this.telaCadastro.setVisible(true);
        }
    }
}