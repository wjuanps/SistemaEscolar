/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Fornecedor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public interface IFornecedorDAO {
    
    /**
     * 
     * @param con gawrgawrg
     * @return argtwagewr
     * @throws SQLException  ergyaewrgewr
     */
    List<Fornecedor> getFornecedor(Connection con) throws SQLException;
    
    /**
     * 
     * @param con argawergawr
     * @param order argwargwa
     * @return rgaewrgwr
     * @throws SQLException  awrgtawrg
     */
    List<Fornecedor> getFornecedor(Connection con, String order) throws SQLException;
    
    /**
     * 
     * @param con agargerw
     * @param nome awrgawrga
     * @param order aergarwg
     * @return argawerg
     * @throws SQLException  awrgawrgr
     */
    List<Fornecedor> getFornecedor(Connection con, String nome, String order) throws SQLException;
    
    /**
     * 
     * @param con argawraw
     * @param id argarga
     * @return awrgawgewgewa
     * @throws SQLException awrgawgawe
     */
    Fornecedor getFornecedor(Connection con, int id) throws SQLException;
}