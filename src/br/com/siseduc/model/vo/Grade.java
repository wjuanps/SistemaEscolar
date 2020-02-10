/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

/**
 *
 * @author Sophia
 */
public interface Grade {

    /**
     * 
     */
    public enum ComponentesMedio {
        Língua_Portuguesa,
        Arte,
        Educação_Física,
        Matemática,
        Física,
        Biologia,
        Química,
        História,
        Geografia,
        Língua_Estrangeira_Moderna,
        Filosofia,
        Sociologia;
    }
    
    /**
     * 
     */
    public enum ComponentesFundamental1 {
        Língua_Portuguesa,
        Arte,
        Educação_Física,
        Matemática,
        Física,
        Biologia,
        História,
        Geografia;
    }
    
    /**
     * 
     */
    public enum ComponentesFundamental2 {
        Língua_Portuguesa,
        Arte,
        Educação_Física,
        Matemática,
        Física,
        Biologia,
        História,
        Geografia,
        Ensino_Religioso,
        Língua_Estrangeira_Moderna;
    }
    
    /**
     * 
     */
    public enum ComponentesEja {
        Língua_Portuguesa,
        Arte,
        Educação_Física,
        Matemática,
        Física,
        Biologia,
        História,
        Geografia,
        Ensino_Religioso,
        Língua_Estrangeira_Moderna;
    }
    
    /** 
     * Arquivo com a grade curricular EJA
     */
    String EJA_FILE = "eja.ini";
    
    /**
     * Arquivo com a grade curricular ensino fundamental 1 ao 5 ano
     */
    String FUNDAMENTAL_1_FILE = "fundamental_1ao5_ano.ini";
    
    /**
     * Arquivo com a grade curricular ensino fundamental 6 ao 9 ano
     */
    String FUNDAMENTAL_2_FILE = "fundamental_6ao9_ano.ini";
    
    /**
     * Arquivo com a grade curricular ensino medio
     */
    String MEDIO_FILE = "medio.ini";
    
    /**
     *
     */
    String EJA = "EJA";
    
    /**
     *
     */
    String FUNDAMENTAL = "Ensino Fundamental";
    
    /**
     *
     */
    String MEDIO = "Ensino Médio";
}
