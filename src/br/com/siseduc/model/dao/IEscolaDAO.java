/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.IGenerica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Soares
 */
public interface IEscolaDAO {
    
    //
    String SQL_CADASTRAR_DISCIPLINA = "INSERT INTO disciplina (disciplina, data_cadastro, data_modificacao, descricao, is_ativa) VALUES (?,?,?,?,?)";
    
    //
    String SQL_UPDATE_DISCIPLINA = "UPDATE disciplina SET disciplina = ?, data_cadastro = ?, data_modificacao = ?, descricao = ?, is_ativa = ? WHERE id_disciplina = ?";
    
    //
    String SQL_GET_DISCIPLINAS = "SELECT * FROM disciplina";
    
    //
    String SQL_GET_DISCIPLINAS_P = new StringBuilder()
            .append("SELECT DISTINCT CONCAT(d.id_disciplina, ' - ', d.disciplina) AS Disciplina FROM professor p ")
            .append("INNER JOIN professor_has_disciplina ph ON (p.id_professor = ph.id_professor) ")
            .append("INNER JOIN disciplina d ON (ph.id_disciplina = d.id_disciplina) ")
            .append("INNER JOIN professor_has_turma pt ON (p.id_professor = pt.id_professor AND d.id_disciplina = pt.id_disciplina) ")
            .append("INNER JOIN turma t ON (pt.id_turma = t.id_turma) ")
            .append("WHERE p.id_professor = ? ")
            .append("ORDER BY d.id_disciplina")
            .toString();
    
    //
    String SQL_GET_DISCIPLINA = "SELECT * FROM disciplina WHERE disciplina = ?";
    
    //
    String SQL_CADASTRAR_FUNCAO = "INSERT INTO cargo_funcao (cargo_funcao, data_cadastro, data_modificacao, descricao, is_ativa) VALUES (?,?,?,?,?)";
    
    //
    String SQL_UPDATE_FUNCAO = "UPDATE cargo_funcao SET cargo_funcao = ?, data_cadastro = ?, data_modificacao = ?, descricao = ?, is_ativa = ? WHERE id_cargo_funcao = ?";
    
    //
    String SQL_GET_FUNCAO = "SELECT * FROM cargo_funcao WHERE cargo_funcao = ?";
    
    //
    String SQL_GET_FUNCOES = "SELECT * FROM cargo_funcao";
    
    //
    String SQL_GET_DESCRICAO = "SELECT ? FROM ? WHERE cargo_funcao = ?";
    
    //
    String SQL_HAS_ESCOLA = "SELECT * FROM escola";
    
    //
    String SQL_CADASTRAR_ESCOLA = "CALL SP_cadastrar_escola(?,?,?,?,?,?,?,?,?,?,?)";
    
    //
    String SQL_GET_NUM_CONTRATO = "SELECT `contrato` FROM `escola`";
    
    /**
     * 
     * @param con
     * @param disciplina
     * @param usuario
     * @return
     * @throws SQLException
     * @throws SiseducException
     */
    int cadastrarDisciplina(Connection con, Disciplina disciplina, String usuario) throws SQLException, SiseducException;
    
    /**
     * 
     * @param con 
     * @param disciplina 
     * @throws SQLException 
     * @throws br.com.siseduc.model.bo.SiseducException 
     */
    void updateDisciplina(Connection con, Disciplina disciplina) throws SQLException, SiseducException;
    
    /**
     * 
     * @param con 
     * @return 
     * @throws SQLException 
     */
    Map<Integer, String> listaDisciplina(Connection con) throws SQLException;
    
    /**
     * 
     * @param con 
     * @param disciplina 
     * @return 
     * @throws SQLException 
     */
    List<Disciplina> listaDisciplina(Connection con, String disciplina) throws SQLException;
    
    /**
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    boolean hasEscola(Connection con) throws SQLException;
    
    /**
     * 
     * @param con
     * @param escola
     * @return
     * @throws SQLException 
     */
    boolean cadastrarEscola(Connection con, List<String> escola) throws SQLException;
    
    /**
     * 
     * @param con
     * @param tabela 
     * @param cond 
     * @param id 
     * @return 
     * @throws SQLException 
     */
    boolean get(Connection con, String tabela, String cond, int id) throws SQLException;
    
    /**
     * 
     * @param con 
     * @param tabela 
     * @param campo 
     * @param condicao 
     * @return 
     * @throws SQLException
     */
    String get(Connection con, String tabela, String campo, String condicao) throws SQLException;
    
    /**
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    String getNumContrato(Connection con) throws SQLException;
    
    /**
     * 
     * @param con
     * @param idProfessor
     * @return
     * @throws SQLException 
     */
    List<String> get(Connection con, int idProfessor) throws SQLException;
    
    /**
     * 
     * @param con
     * @return
     * @throws SQLException
     */
    List<Disciplina> getDisciplina(Connection con) throws SQLException;
    
    /**
     * 
     * @param con
     * @param funcao
     * @param usuario
     * @return
     * @throws SQLException
     */
    int cadastrarFuncao(Connection con, IGenerica<Funcionario> funcao, String usuario) throws SQLException;
    
    /**
     * 
     * @param con
     * @param funcao
     * @throws SQLException
     */
    void updateFuncao(Connection con, IGenerica<Funcionario> funcao) throws SQLException;
    
    /**
     * 
     * @param con
     * @param ativa
     * @return
     * @throws SQLException
     */
    List<IGenerica<Funcionario>> getFuncao(Connection con, boolean ativa) throws SQLException;
    
    /**
     * 
     * @param con
     * @param funcao
     * @return
     * @throws SQLException
     */
    List<IGenerica<Funcionario>> getFuncao(Connection con, String funcao) throws SQLException;    
}