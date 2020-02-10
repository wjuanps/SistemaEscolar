/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import java.io.File;

/**
 *
 * @author Sophia
 */
public interface Diretorio {
    
    /**
     * Separador de pastas padrão
     */
    public static final String separator = File.separator;
     /**
     * Diretório meus documentos do computador
     */
    public static final String ROOT = System.getProperty("user.home").concat(separator + "documents");

    /**
     * Diretório das aonde ficam armazenadas as fotos
     */
    public static final String DIR_FOTOS = ROOT.concat(String.format("%1$sSiseduc%1$ssrc%1$sfotos%1$s", separator));

    /**
     * Diretório para armazenar os arquivos temporários
     */
    public static final String DIR_TEMP = ROOT.concat(String.format("%1$sSiseduc%1$ssrc%1$stemp%1$s", separator));

    /**
     * Diretório para salvar os diplomas dos professores
     */
    public static final String DIR_DIPLOMAS = ROOT.concat(String.format("%1$sSiseduc%1$ssrc%1$sdiplomas%1$s", separator));

    /**
     * Diretório para armazenar os log's e configurações do sistemas
     */
    public static final String DIR_LOG = ROOT.concat(String.format("%1$sSiseduc%1$ssrc%1$slog%1$s", separator));
    
    /**
     * Diretório para armazenar a grade curricular
     */
    public static final String DIR_GRADE = ROOT.concat(String.format("%1$sSiseduc%1$ssrc%1$sgrade%1$s", separator));

    /**
     * Deretório para armazenar os relatórios
     */
    public static final String DIR_REPORT = System.getProperty("user.dir").concat(String.format("%1$ssrc%1$sreport%1$s", separator));

    /**
     * Deretório para armazenar os arquivos
     */
    public static final String DIR_FILES = System.getProperty("user.dir").concat(String.format("%1$sfiles%1$s", separator));
}
