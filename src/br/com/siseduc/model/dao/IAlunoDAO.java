package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Responsavel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Soares
 *
 */
public interface IAlunoDAO {
    /**
     * 
     */
    int MAE = 0;
    /**
     * 
     */
    int PAI = 1;
    
    /**
     *
     * @param con conexão com o banco de dados
     * @return uma lista com os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    List<Aluno> listaAluno(Connection con) throws SQLException;
    
    /**
     * 
     * @param con conexão com o banco de dados
     * @param order ordena a busca
     * @return uma lista com os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    List<Aluno> listaAluno(Connection con, String order) throws SQLException;
        
    /**
     * 
     * @param con conexão com o banco de dados
     * @param opc opcional
     * @return uma lista com os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    List<Aluno> listaAluno(Connection con, String ... opc) throws SQLException;
    
    /**
     * 
     * @param con conexão com o banco de dados
     * @param order ordena a busca
     * @param nome sgae
     * @return asgaws
     * @throws SQLException vasgvb
     */
    List<Aluno> listaAluno(Connection con, String order, String nome) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idAluno
     * @param idProfessor
     * @param idTurma
     * @param idDisciplina
     * @return
     * @throws SQLException 
     */
    List<Double> listaNotas(Connection con, int idAluno, int idProfessor, int idTurma, int idDisciplina) throws SQLException;
    
    /**
     * 
     * @param con
     * @param hasNota
     * @param obj
     * @throws SQLException 
     */
    void lancarNota(Connection con, boolean hasNota, Object ... obj) throws SQLException;

    /**
     * boolean hasNotas
     * @param con sgvas
     * @param matricula vdva
     * @return wgfwr
     * @throws SQLException awrgwar
     */
    Map<Aluno, List<Responsavel>> listaAluno(Connection con, int matricula) throws SQLException;    
}