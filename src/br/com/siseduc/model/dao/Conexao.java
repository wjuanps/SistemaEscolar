package br.com.siseduc.model.dao;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * Classe responsavel por abrir e fechar a <code>Conexao</code> com o banco de
 * dados Assim como verificar se a conexao foi bem estabelecida
 *
 * @author Juan Soares
 *
 * @see IConexao
 *
 */
public abstract class Conexao implements IConexao {
    
    /**
     * 
     */
    private static boolean test = false;
    /**
     * 
     */
    private static int itarator = 0;
    
    /**
     *
     * Método responsável por fazer a <code>Conexao</code> com o banco de dados
     *
     * @param con
     * @see IConexao#getConnection(java.sql.Connection)
     * 
     * @return
     */
    public static Connection getConnection(Connection con) {
        try {
            con = IConexao.getConnection(con);
        } catch (SQLException ex) {
            try {
                if (!test) {
                    Runtime.getRuntime().exec("cmd.exe /C start C:\\wamp\\wampmanager.exe");
                }
                if (itarator++ == 5) {
                    Siseduc.showMessage(Siseduc.TITULO, "Erro ao inicializar o sistema.\nTente novamente mais tarde.\n\n~::102::~", TipoMensagem.MSG_ERROR);
                    System.exit(Siseduc.SAIR);
                    return null;
                }                
                test = true;
            } catch (IOException ex1) {}
            try {
                Thread.sleep(0x13BA);
            } catch (InterruptedException ex1) {}
            getConnection(con);
        }
        itarator = 0;
        return con;
    }

    /**
     *
     * Método responsável por fechar a <code>Conexao</code> com o banco de dados
     *
     * @param con
     * @see IConexao#closeConnection(java.sql.Connection)
     *
     */
    public static void closeConnection(Connection con) {
        IConexao.closeConnection(con);
    }

    /**
     *
     * Método responsável por verificar se a <code>Conexao</code> com o banco de
     * dados foi bem sucedida
     *
     * @param con
     * @see IConexao#isConectado(java.sql.Connection)
     * 
     * @return
     */
    public static boolean isConectado(Connection con) {
        return IConexao.isConectado(con);
    }
    
    /**
     * 
     */
    public static void testarConexao() {
        PessoaDAO.con = getConnection(PessoaDAO.con);
        closeConnection(PessoaDAO.con);
    }
}
