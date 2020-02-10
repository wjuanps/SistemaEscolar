/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public interface IFuncionarioDAO {
   
    /**
     * 
     * @param con gaewrgawrg
     * @return awsgawrgawr
     * @throws SQLException argawrgaw
     */
    List<Usuario> getFuncionario(Connection con) throws SQLException;
    
    /**
     * 
     * @param con awrgbawrgbaewr
     * @param id awrgawrgawrg
     * @return awrgawgawe
     * @throws SQLException arwgawrgawrga
     */
    List<Usuario> getFuncionario(Connection con, int id) throws SQLException;
    
    /**
     * 
     * @param con awrgwargawegrw
     * @param order argawrgbawrg
     * @return awrgawrgawg
     * @throws SQLException awrgtawrgawgawr
     */
    List<Usuario> getFuncionario(Connection con, String order) throws SQLException;
    
    /**
     * 
     * @param con sgasgawr
     * @param order grawgawrgawg
     * @param nome awrgawrgawerg
     * @return awgfawrgawrg
     * @throws SQLException gwregawregawegre
     */
    List<Usuario> getFuncionario(Connection con, String order, String nome) throws SQLException;    
}