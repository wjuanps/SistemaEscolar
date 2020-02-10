/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.UsuarioDao;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Soares
 * 
 * @see UsuarioDao
 */
public class UsuarioBO extends UsuarioDao {

    /**
     * Método que faz a verificação da senha e do login 
     * informados pelo usuário para acessar o sistema.
     * 
     * @param login recebe o login informado pelo usuário
     * @param senha recebe a senha informada pelo usuário
     * @return Verdadeiro se a senha e o login estiverem corretos
     * caso contrario retorna false.
     */
    public Object confirmarLogin(String login, String senha) {

        if (login.isEmpty() || senha.isEmpty()) {
            return false;
        }
        
        con = Conexao.getConnection(con);        
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return (super.confirmarLoginSenha(con, login, senha));      
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return false;
    }

    /**
     *
     * @param senha
     * @param confirmar
     * @return
     */
    public boolean confirmarSenha(String senha, String confirmar) {

        if (senha.length() != confirmar.length()) {
            return false;
        }
        
        int i = senha.length()-1;
        char[] s = senha.toCharArray();
        char[] c = confirmar.toCharArray();
        
        do {
            if (s[i] != c[i]) {
                return false;
            }
        } while (--i != -1);

        return true;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public boolean isUsuario(String usuario) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }

        try {
            final List<String> lista = super.getUsuario(con);
            return (lista.contains(usuario));

        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            return false;
        } finally {
            Conexao.closeConnection(con);
        }
    }
    
    /**
     * 
     * @param login
     * @return 
     */
    public String getNome(String login) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return super.getNomeFuncionario(con, login);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
    
    /**
     * 
     * @param login
     * @return 
     */
    public int getIdUsuario(String login) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return 0;
        }
        
        try {
            final int idUsuario = super.getIdUsuario(con, login);
            return (idUsuario);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return 0;
    }
    
    /**
     * 
     */
    public static void usuarioMaster() {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        try {
            if (!hasMaster(con)) {
                cadastrarMaster(con);
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }
}