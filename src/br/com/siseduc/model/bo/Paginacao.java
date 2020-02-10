/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.model.dao.PessoaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Sophia
 */
public final class Paginacao {
     
    /**
     * Define a quantidade de itens que ira aparecer em cada página.
     * Valor default é 10
     */
    private static int itensPorPagina = 0xA;
    /**
     * Armazena a quantidade de registros que a consulta retornou
     */
    private static int totalResultados;
    /**
     * Indica a pagina atual
     */
    private static int pagina = 0;
    
    /**
     * Indica se sera mostrada a proxima pagina
     * 
     * @see #setPagina() 
     * @see #avancarPagina() 
     */
    private static boolean proximo  = false;
    /**
     * indica se sera mostrada a pagina anterior
     * 
     * @see #setPagina() 
     * @see #voltarPagina() 
     */
    private static boolean anterior = false;
    
    /**
     * Verifica se o pagina selecionada e a ultima
     * 
     * @see #habilitarDesabilitarBotao(javax.swing.JButton, javax.swing.JButton) 
     */
    private static boolean ULTIMA_PAGINA;
    /**
     * Verifica se nao ha paginas para a consulta
     * 
     * @see #habilitarDesabilitarBotao(javax.swing.JButton, javax.swing.JButton) 
     */
    private static boolean SEM_PAGINAS;
    /**
     * Verifica se e a primeira pagina da consulta
     * 
     * @see #habilitarDesabilitarBotao(javax.swing.JButton, javax.swing.JButton) 
     */
    private static boolean PRIMEIRA_PAGINA;
    /**
     * Verifica se e alguma pagina intermediaria da consulta
     * 
     * @see #habilitarDesabilitarBotao(javax.swing.JButton, javax.swing.JButton) 
     */
    private static boolean PAGINAS_INTERMEDIARIAS;
    /**
     * Verifica se a consulta retornou apenas uma pagina
     * 
     * @see #habilitarDesabilitarBotao(javax.swing.JButton, javax.swing.JButton) 
     */
    private static boolean UMA_PAGINA;

    /**
     * 
     * Retorna a quantidade de tuplas retornadas na consulta
     * 
     * @return o numero de tuplas
     */
    public static int getTotalResultados() {
        return totalResultados;
    }

    /**
     * 
     * Define a quantidade de registros retornados do banco de dados
     * 
     * 
     * @param con
     * @param sql 
     * @throws SQLException 
     * 
     * @see PessoaDAO#get(java.sql.Connection, java.lang.String)
     * @see #setPagina()
     */
    public static void setTotalResultados(Connection con, String sql) throws SQLException {
        totalResultados = PessoaDAO.get(con, sql);
        setPagina();
    }
    
    /**
     * 
     * Retorna a quantidade de paginas de acordo com os registros
     * 
     * @return a quantidade de paginas
     */
    public static int numPaginas() {
        return (
            (int) (Math.ceil(totalResultados / (double) itensPorPagina))
        );
    }
    
    /**
     *
     * Indica o inicio para a busca no banco de dados
     * 
     * <pre>
     *      SELECT * FROM `aluno` LIMIT Paginacao.inicio(), Paginacao.getItensPorPagina()
     * </pre>
     * 
     * @return 
     */
    public static int inicio() {
        if (pagina > 0) {
            return (
                (pagina * itensPorPagina) - itensPorPagina
            );
        } else {
            return 0;
        }
    }
    
    /**
     * Define a pagina atual
     */
    private static void setPagina() {
        if (!isProximo() && !isAnterior()) {
            pagina = (totalResultados > 0) ? 1 : 0;
        } else if (isProximo()) {
            if (pagina < numPaginas()) {
                ++pagina;
                proximo = false;
            }
        } else if (isAnterior()) {
            if (pagina > 1) {
                --pagina;
                anterior = false;
            }
        }
    }
    
    /**
     * 
     * @param inicio
     * @param totalPaginas
     * @param total
     */
    public static void printResultados(JLabel inicio, JLabel totalPaginas, JLabel total) {
        inicio.setText(String.valueOf(getPagina()));
        totalPaginas.setText(String.valueOf(numPaginas()));
        total.setText(String.valueOf(getTotalResultados()));
    }
    
    /**
     * 
     * @param anterior
     * @param proximo
     */
    public static void habilitarDesabilitarBotao(JButton anterior, JButton proximo) {
        ULTIMA_PAGINA          = (numPaginas() == pagina);
        SEM_PAGINAS            = (numPaginas() == 0);
        PRIMEIRA_PAGINA        = (pagina == 1);
        PAGINAS_INTERMEDIARIAS = (!ULTIMA_PAGINA & !SEM_PAGINAS);
        UMA_PAGINA             = (totalResultados <= itensPorPagina);
        
        if (SEM_PAGINAS) {
            anterior.setEnabled(false);
            proximo.setEnabled(false);
        } else if (ULTIMA_PAGINA) {
            proximo.setEnabled(false);
            anterior.setEnabled(true);
            
            if (UMA_PAGINA)
                anterior.setEnabled(false);
        } else if (PAGINAS_INTERMEDIARIAS) {
            anterior.setEnabled(true);
            proximo.setEnabled(true);
            
            if (PRIMEIRA_PAGINA)
                anterior.setEnabled(false);            
        }
    }
    
    /**
     *
     * @return 
     */
    public static int getPagina() {
        return pagina;
    }
    
    /**
     * 
     */
    public static void avancarPagina() {
        proximo = true;
    }
    
    /**
     *
     */
    public static void voltarPagina() {
        anterior = true;
    }

   /**
    * 
    * @return 
    */
    public static boolean isProximo() {
        return proximo;
    }

    /**
     * 
     * @return 
     */
    public static boolean isAnterior() {
        return anterior;
    }

    public static int getItensPorPagina() {
        return itensPorPagina;
    }

    public static void setItensPorPagina(int itensPorPagina) {
        Paginacao.itensPorPagina = itensPorPagina;
    }
}
