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
public class Disciplina {
    
    /* Identificação da disciplina */
    private int id;
    
    /* Nome da disciplina */
    private String disciplina;
    /* Data de cadastro da disciplina */
    private String dataCadastro;
    /* Data de modificação da disciplina */
    private String dataModificacao;
    /* Descrição da disciplina */
    private String descricao;
    
    /* Informa se a disciplina está ativa */
    private boolean ativa;

    //<editor-fold defaultstate="collapsed" desc="Get's and Set's Methods">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    //</editor-fold>
}
