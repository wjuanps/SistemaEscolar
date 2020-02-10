/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

import br.com.siseduc.model.bo.Session;

/**
 *
 * @author Sophia
 */
public interface Mensagens {
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens do Sistema">
    /**
     *
     */
    String SISTEMA_ABERTO = String.format("O sistema já se encontra aberto nessa máquina.\n\n::Usuário: %s\n::IP: %s", (Session.getUsuario() == null) ? "" : Session.getUsuario(), Session.getIp());
    
    /**
     *
     */
    String BOAS_VINDAS = "Bem vindo ao Siseduc - Sistema Educacional\n\nAntes de começar a usar o sistema\ncadastre sua escola.";
    
    /**
     *
     */
    String SAIR_SISTEMA = "Você tem certeza que deseja sair do sistema?";

    /**
     *
     */
    String CONCLUIDO = "Concluído com sucesso!!";

    /**
     *
     */
    String ARQUIVO_DANIFICACO = "-::~ Arquivo danificado ~::-\nChame o suporte para resolver o problema\n::102::";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Banco de Dados">

    /**
     *
     */
    String ERRO_BANCO_DE_DADOS = "Não foi possível acessar o banco de dados\nChame o suporte para resolver o problema.";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Escola">

    /**
     *
     */
    String ESCOLA_CADASTRADA = "Escola cadastrada com sucesso!!";

    /**
     *
     */
    String ERRO_CADASTRO_ESCOLA = "Não foi possível cadastrar a Escola agora, tente novamente mais tarde.";

    /**
     *
     */
    String SALVAR_CONTRATO = "Você deseja salvar o número do contrato?";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Login">

    /**
     *
     */
    String USUARIO_SENHA_INVALIDO = "Usuário ou senha inválidos!!";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Disciplina">

    /**
     *
     */
    String CADASTRAR_DISCIPLINA = "Disciplinas a serem cadastradas.\n\n";
    /**
     *
     */
    String CONFIRMAR_CADASTRO_DISCIPLINA = "\nVocê realmente deseja cadastrar essas disciplinas?";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Pessoa">

    /**
     *
     */
    String NOME_INVALIDO = "Nome não pode ficar em branco";
    
    /**
     *
     */
    String FOTO_INVALIDA = "Formato da foto está inválido\nUse fotos nos formatos: ";
    
    /**
     *
     */
    String ERRO_CRIPTOGRAFIA = "Não foi possível realizar a criptografia do arquivo.\nChame o suporte para resolver o problema.";

    /**
     *
     */
    String CONFIRMAR_CADASTRO_ATUALIZACAO = "Você tem certeza que deseja confirmar ess";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mensagens Aluno">
    
    // </editor-fold>
}
