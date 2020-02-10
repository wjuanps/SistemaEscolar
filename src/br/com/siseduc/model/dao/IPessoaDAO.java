/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Juan Soares
 */
public interface IPessoaDAO {
    
    /**
     * 
     * @param con
     * @param obj
     * @return
     * @throws SQLException
     */
    boolean cadastrarAtualizar(Connection con, Object ... obj) throws SQLException;
    /**
     * 
     * @param ip
     * @param tipo
     * @param obj
     * @return 
     * @throws SQLException
     */
    boolean confirmarCadastroAtualizacao(IPessoaDAO ip, int tipo, Object... obj)  throws SQLException;
}