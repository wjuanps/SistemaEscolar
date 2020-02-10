/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.model.vo.Pessoa;

/**
 *
 * @author Sophia
 */
public interface IPessoaBO {
    
    /**
     * 
     * @param pessoa
     * @return 
     */
    boolean confirmarCadastro(Pessoa pessoa);    
}
