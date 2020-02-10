/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

/**
 *
 * @author Juan Soares
 * @param <E> o tipo do objeto
 */
public interface IGenerica<E> {
    
    /**
     * 
     * @return uma instancia de um objeto
     */
    E getInstance();
    
    /**
     * 
     * @param instance de um objeto
     */
    void setInstance(E instance);    
}
