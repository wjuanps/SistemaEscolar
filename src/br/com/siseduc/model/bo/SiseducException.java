/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

/**
 *
 * @author Juan Soares
 */
public class SiseducException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param message
     */
    public SiseducException(String message) {
        super(message);
    }

    /**
     * 
     */
    public SiseducException() {

    }

}
