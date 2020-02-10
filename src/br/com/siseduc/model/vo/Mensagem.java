/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

import br.com.siseduc.view.TelaMensagem;

/**
 *
 * @author Sophia
 */
public final class Mensagem implements Mensagens {
    
    /**
     * Enumeração com as constantes para verificar o tipo 
     * de mensagem que será mostrada.
     */
    public enum TipoMensagem {
        /**
         * Constante utilizada para mostrar mensagens informativas.
         * 
         * @see TelaMensagem
         */
        MSG_INFORMATION,
        /**
         * Constante utilizada para mostrar mensagens interrogativas.
         * 
         * @see TelaMensagem
         */
        MSG_QUESTION,
        /**
         * Constante utilizada para mostrar mensagens de erro.
         * 
         * @see TelaMensagem 
         */
        MSG_ERROR,
        /**
         * Constatnte utilizada quando o usuário clica no botão Ok ou Sim da tela de mensagem.
         * 
         * @see TelaMensagem
         */
        OPCAO_OK,
        /**
         * Constatnte utilizada quando o usuário clica no botão Cancelar da tela de mensagem.
         * 
         * @see TelaMensage 
         */
        OPCAO_CANCELAR;
    }
    
    
    /**
     * Retorna a opção de mensagem escolhida
     */
    private static TipoMensagem opcao;

    public static TipoMensagem getOpcao() {
        return opcao;
    }

    public static void setOpcao(TipoMensagem opcao) {
        Mensagem.opcao = opcao;
    }
}
