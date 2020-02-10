/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

/**
 *
 * @author Juan Soares
 * @param <E> um objeto
 */
public class Generica<E> implements IGenerica<E> {

    /**
     * 
     */
    E instance;
    
    /**
     * 
     * @return uma instancia de um objeto
     */
    @Override
    public E getInstance() {
        return this.instance;
    }

    /**
     * 
     * @param instance de um objeto
     */
    @Override
    public void setInstance(E instance) {
        this.instance = instance;
    }      
}