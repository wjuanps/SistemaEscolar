package br.com.siseduc.model.dao;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

/**
 *
 * Interface que contém as constantes que serão utilizadas para fazer a
 * <code>Conexao</code> com o banco de dados
 *
 * @author Juan Soares
 *
 * @see Conexao
 */
@FunctionalInterface
public interface IConexao extends Function<Object, Object> {

    /**
     * Constante que armazena o nome de usuário no banco de dados. Ela será
     * chamada pelo método que faz a conexão com o banco de dados.
     *
     * @see Conexao
     *
     */
    String USER = "root";

    /**
     * Constante que armazena a senha do usuário no banco de dados. Ela será
     * chamada pelo método que faz a conexão com o banco de dados.
     *
     * @see Conexao
     *
     */
    String PASSWORD = "";

    /**
     * Constante que armazena a url de conexão do banco de dados Ela será
     * chamada pelo método que faz a conexão com o banco de dados.
     *
     * @see Conexao
     *
     */
    String URL = "jdbc:mysql://127.0.0.1:3306/siseduc";

    /**
     *
     * Método responsável por fazer a <code>Conexao</code> com o banco de dados
     *
     * @param con
     * @return
     * @throws java.sql.SQLException
     */
    public static Connection getConnection(Connection con) throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }

    /**
     *
     * Método responsável por fechar a <code>Conexao</code> com o banco de dados
     *
     * @param con
     *
     */
    public static void closeConnection(Connection con) {
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            Siseduc.showMessage(Siseduc.TITULO, "Falha ao encerrar a conexão\n" + e.getMessage(), TipoMensagem.MSG_ERROR);
        } catch (NullPointerException e) {}
    }

    /**
     *
     * Método responsável por verificar se a <code>Conexao</code> com o banco de
     * dados foi bem sucedida
     *
     * @param con
     * @return
     */
    public static boolean isConectado(Connection con) {
        try {
            return (con != null);
        } catch (Exception e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return false;
    }
}
