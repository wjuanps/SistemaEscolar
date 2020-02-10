/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.vo.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public class TurmaDAO extends PessoaDAO implements ITurmaDao {

    /**
     * 
     */
    private List<Turma> listaTurma;

    /**
     *
     * @param con
     * @param objeto
     * @return
     * @throws SQLException
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Object... objeto) throws SQLException {

        Turma turma = (Turma) objeto[0];
        final int tipo = (int) objeto[1];
        final List<String> professores = (List<String>) objeto[2];

        if (tipo == 1) {
            int result;
            try (PreparedStatement stm = con.prepareStatement(SQL_NOVA_TURMA)) {

                stm.setString(1, turma.getTurma());
                stm.setString(2, turma.getModalidade());
                stm.setString(3, turma.getSerie());
                stm.setString(4, turma.getDescricaoSerie());
                stm.setString(5, turma.getTurno());
                stm.setString(6, turma.getDescricaoTurma());
                stm.setString(7, (turma.getDataMatricula().equals("    -  -  ")) ? null : turma.getDataMatricula());
                stm.setString(8, (turma.getTurma().equals("    -  -  ")) ? null : turma.getDataModificacao());
                stm.setInt(9, (turma.isTurmaAtiva()) ? 1 : 0);
                stm.setInt(10, (turma.isTurmaCancelada()) ? 1 : 0);
                stm.setString(11, (turma.getDataInativacao().equals("    -  -  ")) ? null : turma.getDataInativacao());
                stm.setString(12, (turma.getDataCancelamento().equals("    -  -  ")) ? null : turma.getDataCancelamento());

                result = stm.executeUpdate();
            }
            
            return ((result == 1) && inserirProfTurma(con, professores, turma));
        } else {
            try (PreparedStatement stm = con.prepareStatement(SQL_UPDATE_TURMA)) {
                stm.setString(1, turma.getDescricaoTurma());
                stm.setString(2, String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
                stm.setInt(3, (turma.isTurmaAtiva()) ? 1 : 0);
                stm.setInt(4, (turma.isTurmaCancelada()) ? 1 : 0);
                stm.setString(5, (turma.getDataInativacao().equals("    -  -  ")) ? null : turma.getDataInativacao());
                stm.setString(6, (turma.getDataCancelamento().equals("    -  -  ")) ? null : turma.getDataCancelamento());
                stm.setString(7, turma.getTurma());
                
                stm.executeUpdate();
            }
            return updateProfTurma(con, professores, turma);
        }
    }
    
    /**
     * 
     * @param con
     * @param professores
     * @param turma
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean inserirProfTurma(Connection con, List<String> professores, Turma turma) throws SQLException {
        for (String professor : professores) {
            final int idTurma = getId(con, turma.getTurma());
            final int idProfessor = Integer.parseInt(professor.split("-")[0].trim());
            final int idDisciplina = Integer.parseInt(professor.split("-")[1].trim());
            try (PreparedStatement stm = con.prepareStatement(SQL_PROF_TURMA)) {
                stm.setInt(1, idTurma);
                stm.setInt(2, idProfessor);
                stm.setInt(3, idDisciplina);

                stm.executeUpdate();
            }
        }
        return true;
    }
    
    /**
     * 
     * @param con
     * @param professores
     * @param turma
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean updateProfTurma(Connection con, List<String> professores, Turma turma) throws SQLException {
        final int idTurma = getId(con, turma.getTurma());
        try (PreparedStatement stm = con.prepareStatement(SQL_DEL_PROF_TURMA)) {
            stm.setInt(1, idTurma);

            stm.executeUpdate();
        }
        return inserirProfTurma(con, professores, turma);
    }

    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> getProfTurma(Connection con, String turma) throws SQLException {
        final List<String> lista = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_PROF_TURMA)) {
            stm.setString(1, turma);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        lista.add(rs.getString("Professor"));
                    }
                    return lista;
                }
            }
        }
        return null;
    }
    
    /**
     *
     * @param rs
     * @return
     */
    private Turma turma(ResultSet rs) throws SQLException {
        final Turma turma = new Turma();

        turma.setModalidade(rs.getString("modalidade"));
        turma.setDescricaoSerie(rs.getString("descricao_serie"));
        turma.setTurno(rs.getString("turno"));
        turma.setTurma(rs.getString("turma"));
        turma.setDescricaoTurma(rs.getString("descricao_turma"));
        turma.setDataMatricula(rs.getString("data_cadastro"));
        turma.setDataCancelamento(rs.getString("data_cancelamento"));
        turma.setDataInativacao(rs.getString("data_ativacao"));
        turma.setDataModificacao(rs.getString("data_modificacao"));
        turma.setStatus(rs.getString("status"));
        turma.setCountAluno(rs.getInt("count_aluno"));
        turma.setCountProfessor(rs.getInt("count_professor"));

        turma.setSerie(rs.getString("serie"));
        turma.setNumeroTurma(rs.getInt("id_turma"));

        turma.setTurmaAtiva(rs.getInt("is_ativa") == 1);
        turma.setTurmaCancelada(rs.getInt("is_cancelada") == 1);

        return turma;
    }

    /**
     *
     * @param con
     * @param modalidade
     * @param serie
     * @return
     * @throws SQLException
     */
    @Override
    public String getDescricaoSerie(Connection con, String modalidade, String serie) throws SQLException {

        String desc = "";

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_DESCRICAO_SERIE)) {
            stm.setString(1, modalidade);
            stm.setString(2, serie);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        desc = rs.getString(1);
                    }
                    return desc;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param con
     * @param modalidade
     * @param serie
     * @param turno
     * @return
     * @throws SQLException
     */
    @Override
    public List<Turma> getTurma(Connection con, String modalidade, String serie, String turno) throws SQLException {

        listaTurma = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_TURMA)) {
            stm.setString(1, modalidade);
            stm.setString(2, serie);
            stm.setString(3, turno);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        final Turma turma = new Turma();
                        turma.setTurma(rs.getString(1));

                        listaTurma.add(turma);
                    }
                    return listaTurma;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public List<Turma> getTurma(Connection con, boolean ativa) throws SQLException {

        listaTurma = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_TURMA_ALL)) {
            stm.setBoolean(1, ativa);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listaTurma.add(turma(rs));
                    }
                    return listaTurma;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public List<Turma> getTurma(Connection con) throws SQLException {
        return this.getTurma(con, true);
    }

    /**
     *
     * @param con
     * @param turma
     * @param ativa
     * @return
     * @throws SQLException
     */
    @Override
    public List<Turma> getTurma(Connection con, String turma, boolean ativa) throws SQLException {

        listaTurma = new ArrayList<>();

        String sql = ((ativa) ? SQL_GET_TURMA_TURMA.concat(" AND is_ativa = 1") : SQL_GET_TURMA_TURMA);

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, turma);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listaTurma.add(turma(rs));
                    }
                    return listaTurma;
                }
            }
        }
        return null;
    }

    /**
     *
     * @return @throws SQLException
     */
    @Override
    public List<String> getTurma() throws SQLException {
        final String sql = "SELECT turma FROM turma";
        final List<String> turmas = new ArrayList<>();
        Connection con = null;
        con = Conexao.getConnection(con);
        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    turmas.add(rs.getString(1));
                }
                return turmas;
            }
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param order
     * @return
     * @throws SQLException
     */
    @Override
    public List<Turma> getTurma(Connection con, String order) throws SQLException {
        return this.getTurma(con, order, null);
    }

    /**
     *
     * @param con
     * @param order
     * @param turma
     * @return
     * @throws SQLException
     */
    @Override
    public List<Turma> getTurma(Connection con, String order, String turma) throws SQLException {

        listaTurma = new ArrayList<>();

        final String sql = (turma == null)
                ? String.format("SELECT *, status_turma(is_ativa, is_cancelada) AS status, count_aluno(id_turma) AS count_aluno, count_professor(id_turma) AS count_professor FROM turma ORDER BY %s DESC", order)
                : String.format("SELECT *, status_turma(is_ativa, is_cancelada) AS status, count_aluno(id_turma) AS count_aluno, count_professor(id_turma) AS count_professor FROM turma WHERE turma LIKE \'%%%s%%\' ORDER BY %s DESC", turma, order);

        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    listaTurma.add(turma(rs));
                }
                return listaTurma;
            }
        }
        return null;
    }

    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException 
     */
    @Override
    public int getId(Connection con, String turma) throws SQLException {
        int id = 0;
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_ID)) {
            stm.setString(1, turma);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        id = rs.getInt("id_turma");
                    }
                    return id;
                }
            }
        }        
        return 0;
    }

    @Override
    public List<String> getProf(Connection con, int idProfessor, int idDisciplina) throws SQLException {
        final List<String> lista = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_PROF)) {
            stm.setInt(1, idProfessor);
            stm.setInt(2, idDisciplina);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        lista.add(String.format("%s - %s - %s", PessoaBO.getCadastro(rs.getInt(1)), rs.getString(2), rs.getString(3)));
                    }
                    return lista;
                }
            }
        }
        return null;
    }

    @Override
    public boolean hasData(Connection con, int idProfessor, int idDisciplina, String turma) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_VER_DATA)) {
            stm.setInt(1, idProfessor);
            stm.setInt(2, idDisciplina);
            stm.setString(3, turma);
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next());
            }
        }
    }

    @Override
    public boolean alterarProfeTurma(Connection con, int idNovoProfessor, int idProfessorAntigo, int idDisciplina, String turma) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_UPDATE_PROF_NOTA)) {
            stm.setInt(1, idNovoProfessor);
            stm.setString(2, turma);
            stm.setInt(3, idDisciplina);
            stm.setInt(4, idProfessorAntigo);
            
            return (stm.executeUpdate() == 1);
        }
    }
}