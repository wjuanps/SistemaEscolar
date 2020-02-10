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
public class Responsavel extends Pessoa {
    
    private boolean moraComFilho;
    private boolean outroFilhoNaEscola;
    
    private String parentesco;

    public boolean isMoraComFilho() {
        return moraComFilho;
    }

    public void setMoraComFilho(boolean moraComFilho) {
        this.moraComFilho = moraComFilho;
    }

    public boolean isOutroFilhoNaEscola() {
        return outroFilhoNaEscola;
    }

    public void setOutroFilhoNaEscola(boolean outroFilhoNaEscola) {
        this.outroFilhoNaEscola = outroFilhoNaEscola;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }    
}