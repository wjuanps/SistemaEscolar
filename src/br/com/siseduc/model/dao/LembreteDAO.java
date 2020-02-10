/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Lembrete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sophia
 */
public class LembreteDAO implements ILembreteDAO {

    /**
     * 
     * @param con
     * @param lembrete
     * @param idUsuario
     * @param Tipo
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Lembrete lembrete, int idUsuario, int Tipo) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_CADASTRAR_LEMBRETE)) {
            stm.setInt(1, idUsuario);
            stm.setString(2, lembrete.getTitulo());
            stm.setString(3, lembrete.getTipo());
            stm.setString(4, lembrete.getDescricao());
            stm.setString(5, lembrete.getData());
            stm.setInt(6, (lembrete.isAtivo()) ? 1 : 0);
            
            return (stm.executeUpdate() == 1);
        }
    }

    /**
     * 
     * @param con
     * @param idUsuario
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Lembrete> getLembretes(Connection con, int idUsuario) throws SQLException {
        final List<Lembrete> lista = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_LEMBRETE)) {
            stm.setInt(1, idUsuario);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        final Lembrete lembrete 
                                = new Lembrete(rs.getString("titulo"), rs.getString("descricao"), rs.getString("tipo"), rs.getString("data"), rs.getInt("is_ativo") == 1);
                        lista.add(lembrete);
                    }
                    return lista;
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param con
     * @param idUsuario
     * @param data
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean hasLembrete(Connection con, int idUsuario, String data) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_HAS_LEMBRETE)) {
            stm.setInt(1, idUsuario);
            stm.setString(2, "%" + data + "%");
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next());
            }
        }
    }
}
