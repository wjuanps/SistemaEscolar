/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

/**
 *
 * @author Juan Soares
 */
public enum Relatorio {

    /**
     * Relatorio turma
     */
    RELATORIO_TURMA("relatorio-turma.jasper"),

    /**
     * Relatorio boletim
     */
    BOLETIM("boletim.jasper"),

    /**
     * Relatorio diário de classe
     */
    DIARIO_CLASSE("diario-de-classe.jasper");
    
    /**
     * armazena o nome do relatorio
     */
    public String value;
    
    /**
     * Método construtor para iniciar o relatorio
     * 
     * @param value Nome do relatorio
     */
    Relatorio(String value) {
        this.value = value;
    }
}
