package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.model.vo.Turma;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Sophia
 */
public interface IProfessorDAO {
    
    /**
     * 
     * @param con
     * @param id
     * @return
     * @throws SQLException
     */
    List<Professor> listaProfessor(Connection con, int id) throws SQLException;
    
    /**
     * 
     * @param con
     * @param group
     * @param text
     * @return
     * @throws SQLException
     */
    List<Professor> listaProfessor(Connection con, String group, String text) throws SQLException;
    
    /**
     * 
     * @param con
     * @param nome
     * @return
     * @throws SQLException
     */
    Map<String, Turma<Set<String>, Set<String>>> getProfessor(Connection con, String nome) throws SQLException;
    
    /**
     * 
     * @param con
     * @param nome
     * @param disciplina
     * @return
     * @throws SQLException
     */
    List<String> getNomes(Connection con, String nome, String disciplina) throws SQLException; 
    
    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException
     */
    List<String> getProfessores(Connection con, String turma) throws SQLException;
}
