/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Lembrete;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sophia
 */
interface ILembreteDAO extends Statement {
    
    /**
     * 
     * @param con
     * @param lembrete
     * @param idUsuario 
     * @param Tipo
     * @return
     * @throws SQLException 
     */
    boolean cadastrarAtualizar(Connection con, Lembrete lembrete, int idUsuario, int Tipo) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idUsuario 
     * @return
     * @throws SQLException 
     */
    List<Lembrete> getLembretes(Connection con, int idUsuario) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idUsuario
     * @param data
     * @return
     * @throws SQLException 
     */
    boolean hasLembrete(Connection con, int idUsuario, String data) throws SQLException;
}
