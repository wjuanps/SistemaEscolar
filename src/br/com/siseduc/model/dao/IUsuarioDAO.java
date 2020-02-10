package br.com.siseduc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public interface IUsuarioDAO {

    /**
     *
     * @param con \ndtgnhjaetjnesat
     * @return wrghwrghaeh
     * @throws SQLException aehaethaeth
     */
    List<String> getUsuario(Connection con) throws SQLException;

    /**
     *
     * @param con ethjaesthaet
     * @param login  aujsatnuaseu5
     * @param senha wtvqauyaq3bu
     * @return avbuya35au
     *
     * @throws java.sql.SQLException \wvyau5yae5
     */
    boolean confirmarLoginSenha(Connection con, String login, String senha) throws SQLException;

    /**
     *
     * @param con haethaean
     * @param login \zb yau5ygvt6a
     * @return zsrba zumzsteuabua
     * @throws SQLException zsr7hq357q7jw73
     */
    String getNomeFuncionario(Connection con, String login) throws SQLException;

    /**
     * 
     * @param con
     * @param login
     * @return
     * @throws SQLException 
     */
    int getIdUsuario(Connection con, String login) throws SQLException;
}