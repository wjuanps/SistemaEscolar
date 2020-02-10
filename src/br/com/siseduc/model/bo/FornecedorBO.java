/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.FornecedorDAO;
import br.com.siseduc.model.vo.Fornecedor;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class FornecedorBO extends FornecedorDAO {

    /**
     *
     * @param fornecedor
     * @param usuario
     * @param tipo
     * @return
     */
    public boolean confirmarCadastro(Fornecedor fornecedor, String usuario, int tipo) {
        try {
            if (!PessoaBO.validarNome(fornecedor.getNomeFantasia())) {
                return false;
            }
            if (!PessoaBO.validarFormatoFoto(fornecedor.getFoto())) {
                return false;
            }

            if (super.confirmarCadastroAtualizacao(this, tipo, fornecedor, tipo)) {
                String acao = (tipo == 1)
                        ? String.format("Fornecedor %s cadastrado por:", fornecedor.getNomeFantasia())
                        : String.format("Cadastrado do fornecedor %s atualizado por:", fornecedor.getNomeFantasia());
                Logger.logger(usuario, acao, "");

                return true;
            }

        } catch (SiseducException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return false;
    }

    /**
     *
     * @param table
     * @param model
     */
    public void preencherTabela(JTable table, DefaultTableModel model) {
        this.preencherTabela(table, model, null, null);
    }

    /**
     *
     * @param table
     * @param model
     * @param order
     * @param nome
     */
    public void preencherTabela(JTable table, DefaultTableModel model, String order, String nome) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }

        try {
            List<Fornecedor> fornecedores;
            fornecedores = (order == null && nome == null)
                    ? super.getFornecedor(con)
                    : nome.isEmpty()
                            ? super.getFornecedor(con, order)
                            : super.getFornecedor(con, nome, order);

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            if (!fornecedores.isEmpty()) {
                int linha = 0;
                for (Fornecedor fornecedor : fornecedores) {
                    model.addRow(new String[1]);

                    table.setValueAt(PessoaBO.getCadastro(Integer.parseInt(fornecedor.getMatricula())), linha, 0);
                    table.setValueAt(fornecedor.getNomeFantasia(), linha, 1);
                    table.setValueAt(fornecedor.getSegmento(), linha, 2);
                    table.setValueAt(fornecedor.getTelefone(), linha, 3);
                    table.setValueAt(fornecedor.getEmail(), linha, 4);
                    table.setValueAt(fornecedor.getSite(), linha, 5);

                    linha++;
                }
                table.repaint();
                table.revalidate();
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getCadastro() {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            final int id = lastInsertId(con, "fornecedor")+1;
            return PessoaBO.getCadastro(id);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Fornecedor getFornecedor(int id) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            Fornecedor fornecedor = super.getFornecedor(con, id);
            if (fornecedor != null) {
                return fornecedor;
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
}