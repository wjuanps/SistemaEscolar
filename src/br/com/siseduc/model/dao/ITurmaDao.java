/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.vo.Turma;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public interface ITurmaDao {
    
    //
    String SQL_GET_TURMA = "SELECT turma FROM turma WHERE is_ativa = 1 AND modalidade = ? AND serie = ? AND turno = ?";
    
    //
    String SQL_GET_TURMA_ALL = "CALL SP_turma(?)";
    
    //
    String SQL_GET_DESCRICAO_SERIE = "SELECT descricao_serie FROM turma WHERE is_ativa = 1 AND modalidade = ? AND serie = ? LIMIT 1";
    
    //
    String SQL_GET_TURMA_TURMA = "SELECT *, '' AS status, '' AS count_professor, '' AS count_aluno FROM turma WHERE turma = ?";
    
    /**
     *
     */
    String SQL_GET_ID = "SELECT id_turma FROM turma WHERE turma = ?";

    /**
     * 
     * @param con
     * @param professores
     * @param turma
     * @return
     * @throws SQLException 
     */
    boolean inserirProfTurma(Connection con, List<String> professores, Turma turma) throws SQLException;

    /**
     * 
     * @param con
     * @param professores
     * @param turma
     * @return
     * @throws SQLException 
     */
    boolean updateProfTurma(Connection con, List<String> professores, Turma turma) throws SQLException;
    
    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException 
     */
    List<String> getProfTurma(Connection con, String turma) throws SQLException;
    
    /**
     * 
     * @param con shsetbset
     * @param modalidade shsethsehte
     * @param serie shsehbsreth
     * @param turno sthsethsh
     * @return sehtsthsreht
     * @throws SQLException  sehsethseh
     */
    List<Turma> getTurma(Connection con, String modalidade, String serie, String turno) throws SQLException;
    
    /**
     * 
     * @param con fhjdfdrydry
     * @param order rjnrjdrjr
     * @return jmdyjdryj
     * @throws SQLException ryjdryjdry
     */
    List<Turma> getTurma(Connection con, String order) throws SQLException;
    
    /**
     * 
     * @param con djdryjryj
     * @param order dryjdryjdryj
     * @param turma drhjdyjdrj
     * @return rytjhdryjdryj
     * @throws SQLException  rjdryjdyjdy
     */
    List<Turma> getTurma(Connection con, String order, String turma) throws SQLException;
    
    /**
     * 
     * @param con dyjdryjrdyj
     * @param ativa ryjrykrdd
     * @return dyjdryjdryj
     * @throws SQLException ydrjdryjdry
     */
    List<Turma> getTurma(Connection con, boolean ativa) throws SQLException;
    
    /**
     *  
     * @param con djdryjdyrjd
     * @param turma djydyjdyj
     * @param ativa djdryjdyrjdy
     * @return djydryjdryjdry
     * @throws SQLException  dyjdryndryjdyt
     */
    List<Turma> getTurma(Connection con, String turma, boolean ativa) throws SQLException;
    
    /**
     * 
     * @return  fjydyrjdd
     * @throws SQLException dyndjdyjmdry
     */
    List<String> getTurma() throws SQLException;
    
    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException 
     */
    int getId(Connection con, String turma) throws SQLException;
    
    /**
     * 
     * @param con ndyjdryjdyj
     * @param modalidade dryjdyjdj
     * @param serie drjdryjdryj
     * @return dyjdryjdyrj
     * @throws SQLException dyjytgjxyjsyr
     */
    String getDescricaoSerie(Connection con, String modalidade, String serie) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idProfessor
     * @param idDisciplina
     * @return
     * @throws SQLException 
     */
    List<String> getProf(Connection con, int idProfessor, int idDisciplina) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idProfessor
     * @param idDisciplina
     * @param turma
     * @return
     * @throws SQLException 
     */
    boolean hasData(Connection con, int idProfessor, int idDisciplina, String turma) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idNovoProfessor
     * @param idProfessorAntigo
     * @param idDisciplina
     * @param turma
     * @return 
     * @throws SQLException 
     */
    boolean alterarProfeTurma(Connection con, int idNovoProfessor, int idProfessorAntigo, int idDisciplina, String turma) throws SQLException;
}