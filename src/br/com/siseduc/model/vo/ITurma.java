/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

/**
 *
 * @author Sophia
 * @param <A> o tipo do objeto
 * @param <P> o tipo do objeto
 */
public interface ITurma<A, P> {
    
    int ATUALIZAR = Pessoa.ATUALIZAR;
    int CADASTRAR = Pessoa.CADASTRAR;
    
    /**
     * 
     * @return uma instancia de um objeto
     */
    A getInstanceA();
    
    /**
     * 
     * @param instance o tipo do objeto
     */
    void setInstanceA(A instance);
    
    /**
     * 
     * @return uma instancia de um objeto
     */
    P getInstanceP();
    
    /**
     * 
     * @param instancia o tipo do objeto
     */
    void setInstanceP(P instancia);
}
