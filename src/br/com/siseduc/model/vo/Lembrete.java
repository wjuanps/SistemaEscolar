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
public class Lembrete {

    /**
     *
     */
    public static final String ANIVERSARIO = "Aniversário";

    /**
     *
     */
    public static final String REUNIAO = "Reunião";

    /**
     *
     */
    public static final String CASAMENTO = "Casamento";

    /**
     *
     */
    public static final String ENCONTRO = "Encontro";

    /**
     *
     */
    public static final String PADRAO = "Padrão";

    /**
     *
     */
    public static final int ATUALIZAR = Pessoa.ATUALIZAR;

    /**
     *
     */
    public static final int CADASTRAR = Pessoa.CADASTRAR;

    private String titulo;
    private String descricao;
    private String tempoRestante;
    private String tipo;
    private String data;

    private boolean ativo;

    /**
     *
     * @param titulo
     * @param descricao
     * @param tipo
     * @param data
     * @param ativo
     */
    public Lembrete(String titulo, String descricao, String tipo, String data, boolean ativo) {
        this.ativo = ativo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
    }
    /**
     * 
     * @return 
     */
    public static String[] getTipos() {
        return (
            new String[]{ANIVERSARIO, REUNIAO, CASAMENTO, ENCONTRO, PADRAO}
        );
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(String tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
