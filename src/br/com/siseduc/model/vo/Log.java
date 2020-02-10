/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

import java.io.Serializable;

/**
 *
 * @author Juan Soares
 */
public class Log implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8895737519445968330L;

    /**
     * Arquivo de configuração.
     */
    public static final String INFO = "info.ini";
    /**
     * Arquivo de log do sistema.
     */
    public static final String LOG = "log.bin";
    /**
     * Arquivo com o nome do usuario online;
     */
    public static final String SESSION = "session.ini";
    
    private String info;

    private String acao;
    private String data;
    private String opcao;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }
}
