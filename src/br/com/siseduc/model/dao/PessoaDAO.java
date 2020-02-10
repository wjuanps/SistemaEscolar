/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.vo.Generica;
import br.com.siseduc.model.vo.IGenerica;
import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public abstract class PessoaDAO implements IPessoaDAO, Statement {

    /**
     * 
     */
    protected static Connection con = null;
    
    /**
     * 
     * @param ip
     * @param obj
     * @param tipo
     * @return
     */
    @Override
    public boolean confirmarCadastroAtualizacao(IPessoaDAO ip, int tipo, Object... obj) {
        
        final String mensagem = (Pessoa.CADASTRAR == tipo) ? "e cadastro" : "a atualização";
        final TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO,  String.format("%s%s?", Mensagem.CONFIRMAR_CADASTRO_ATUALIZACAO, mensagem), TipoMensagem.MSG_QUESTION);
        if (response != TipoMensagem.OPCAO_OK) {
            return false;
        }
        
        con = Conexao.getConnection(con);
        boolean retorno = false;
        try {
            con.setAutoCommit(false);
            retorno = ip.cadastrarAtualizar(con, obj);
            if (retorno) {
                final String msg = (tipo == Pessoa.CADASTRAR) ? "Cadastro realizado" : "Atualização realizada";
                Siseduc.showMessage(Siseduc.TITULO,  msg.concat(" com sucesso!!"), TipoMensagem.MSG_INFORMATION);
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {}
            Siseduc.showMessage("Erro ".concat((tipo == Pessoa.CADASTRAR) ? "no cadastro" : "na atualização"), Mensagem.ERRO_BANCO_DE_DADOS, TipoMensagem.MSG_ERROR);
            retorno = false;
        } finally {
            Conexao.closeConnection(con);
        }
        return retorno;
    }
   
    /**
     *
     * @param con dhnbaed
     * @param tabela dhbnd
     * @return dghnbdg
     * @throws SQLException dgngn
     */
    public static int lastInsertId(Connection con, String tabela) throws SQLException {

        final String sql = String.format("SELECT MAX(id_%1$s) AS last_id FROM %1$s", tabela);
        int last_id = 0;

        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null && rs.next()) {
                last_id = rs.getInt(1);
            }
        }
        return last_id;
    }

    /**
     *
     * @param con dgn
     * @param tabela gnf
     * @return dgng
     * @throws SQLException ndgndg
     */
    public static List<IGenerica<Pessoa>> getPessoa(Connection con, String tabela) throws SQLException {
        return getPessoa(con, tabela, null);
    }

    /**
     *
     * @param con ggnb
     * @param tabela dngn
     * @param nome dgngt
     * @return fnsr
     * @throws SQLException fgnfsg
     */
    public static List<IGenerica<Pessoa>> getPessoa(Connection con, String tabela, String nome) throws SQLException {

        final List<IGenerica<Pessoa>> lista = new ArrayList<>();
        final String sql = (nome == null) ? 
                String.format("SELECT id_%1$s, nome, email, telefone FROM %1$s WHERE is_ativo = 1", tabela) :
                String.format("SELECT id_%1$s, nome, email, telefone FROM %1$s WHERE is_ativo = 1 AND nome LIKE \'%%%2$s%%\' ORDER BY nome ASC", tabela, nome);

        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {

                    final IGenerica<Pessoa> p = new Generica<>();
                    p.setInstance(Pessoa.getInstance());

                    p.getInstance().setMatricula(String.valueOf(rs.getInt(1)));
                    p.getInstance().setNome(rs.getString(2));
                    p.getInstance().setEmail(rs.getString(3));
                    p.getInstance().setTelefone(rs.getString(4));

                    lista.add(p);
                }
                return lista;
            }
        }
        return null;
    }

    /**
     *
     * @param con
     * @param option
     * @return
     * @throws SQLException
     */
    public static List<Integer> listaId(Connection con, String... option) throws SQLException {
        final String sql = (option.length == 1)
                ? String.format("SELECT id_%1$s FROM %1$s", option[0])
                : String.format("SELECT %s FROM %s WHERE %s = '%s'", option[0], option[1], option[2], option[3]);
        final List<Integer> listaId = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    listaId.add(rs.getInt(1));
                }
                return listaId;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param con
     * @param sql
     * @return
     * @throws SQLException 
     */
    public static int get(Connection con, String sql) throws SQLException {
       try (PreparedStatement stm = con.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next()) ? rs.getInt(1) : 0;
            }
        } 
    } 
}