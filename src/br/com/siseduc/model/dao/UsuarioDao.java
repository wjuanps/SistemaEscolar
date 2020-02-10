package br.com.siseduc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Juan Soares
 */
public class UsuarioDao extends FuncionarioDAO implements IUsuarioDAO {

    /**
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> getUsuario(Connection con) throws SQLException {
        
        final List<String> lista = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_USUARIOS);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    lista.add(rs.getString(1));
                }
                return lista;
            }
        }        
        return null;
    }

    /**
     * 
     * @param con
     * @param login
     * @param senha
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean confirmarLoginSenha(Connection con, String login, String senha) throws SQLException {
        
        try (PreparedStatement stm = con.prepareStatement(SQL_CONFIRMAR_LOGIN_SENHA)) {
            stm.setString(1, login);
            stm.setString(2, senha);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return (rs.getInt(1) == 1);
                }
            }
        }       
        return false;
    }
    
    /**
     * 
     * @param con
     * @param login
     * @return
     * @throws SQLException 
     */
    @Override
    public String getNomeFuncionario(Connection con, String login) throws SQLException {        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_NOME)) {
            stm.setString(1, login);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
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
    public static boolean hasMaster(Connection con) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_HAS_MASTER)) {
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next());
            }
        }
    }
    
    /**
     * 
     * @param con
     * @throws SQLException 
     */
    public static void cadastrarMaster(Connection con) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_INSERIR_MASTER)) {
            stm.executeUpdate();
        }
    }

    /**
     * 
     * @param con
     * @param login
     * @return
     * @throws SQLException 
     */
    @Override
    public int getIdUsuario(Connection con, String login) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_ID_USUARIO)) {
            stm.setString(1, login);
            try (ResultSet rs = stm.executeQuery()) {
                if (null != rs) {
                    if (rs.next()) {
                        return rs.getInt("id_funcionario");
                    }
                }
            }
        }
        return 0;
    }
}
