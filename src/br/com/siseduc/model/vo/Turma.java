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
public class Turma<A, P> implements ITurma<A, P> {

    /**
     *
     */
    private A instanceA;
    /**
     *
     */
    private P instanceP;

    private String modalidade;
    private String descricaoSerie;
    private String turno;
    private String turma;
    private String descricaoTurma;
    private String dataMatricula;
    private String dataCancelamento;
    private String dataInativacao;
    private String dataModificacao;
    private String status;
    private String serie;
    
    private int countAluno;
    private int countProfessor;
    private int numeroTurma;

    private boolean turmaAtiva;
    private boolean turmaCancelada;

    /**
     *
     * @return uma instancia de um objeto
     */
    @Override
    public A getInstanceA() {
        return instanceA;
    }

    /**
     *
     * @param instance o tipo do objeto
     */
    @Override
    public void setInstanceA(A instance) {
        this.instanceA = instance;
    }

    /**
     *
     * @return uma instancia de um objeto
     */
    @Override
    public P getInstanceP() {
        return instanceP;
    }

    /**
     *
     * @param instancia o tipo do objeto
     */
    @Override
    public void setInstanceP(P instancia) {
        this.instanceP = instancia;
    }

    /**
     *
     * @return a modalidade
     */
    public String getModalidade() {
        return modalidade;
    }

    /**
     *
     * @param modalidade informa a modalidade
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    /**
     *
     * @return a serie
     */
    public String getDescricaoSerie() {
        return descricaoSerie;
    }

    /**
     *
     * @param descricaoSerie informa a serie
     */
    public void setDescricaoSerie(String descricaoSerie) {
        this.descricaoSerie = descricaoSerie;
    }

    /**
     *
     * @return o turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     *
     * @param turno inforna o turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     *
     * @return a turma
     */
    public String getTurma() {
        return turma;
    }

    /**
     *
     * @param turma informa a turma
     */
    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
     *
     * @return a descrição da turma
     */
    public String getDescricaoTurma() {
        return descricaoTurma;
    }

    /**
     *
     * @param descricaoTurma informa a descrição da turma
     */
    public void setDescricaoTurma(String descricaoTurma) {
        this.descricaoTurma = descricaoTurma;
    }

    /**
     *
     * @return a data da matricula
     */
    public String getDataMatricula() {
        return dataMatricula;
    }

    /**
     *
     * @param dataMatricula informa a data da maticula
     */
    public void setDataMatricula(String dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     *
     * @return a data do cancelamento
     */
    public String getDataCancelamento() {
        return dataCancelamento;
    }

    /**
     *
     * @return o status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status informa o status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public int getCountAluno() {
        return countAluno;
    }

    public void setCountAluno(int countAluno) {
        this.countAluno = countAluno;
    }

    public int getCountProfessor() {
        return countProfessor;
    }

    public void setCountProfessor(int countProfessor) {
        this.countProfessor = countProfessor;
    }
    
    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDataInativacao() {
        return dataInativacao;
    }

    public void setDataInativacao(String dataInativacao) {
        this.dataInativacao = dataInativacao;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumeroTurma() {
        return numeroTurma;
    }

    public void setNumeroTurma(int numeroTurma) {
        this.numeroTurma = numeroTurma;
    }

    public boolean isTurmaAtiva() {
        return turmaAtiva;
    }

    public void setTurmaAtiva(boolean turmaAtiva) {
        this.turmaAtiva = turmaAtiva;
    }

    public boolean isTurmaCancelada() {
        return turmaCancelada;
    }

    public void setTurmaCancelada(boolean turmaCancelada) {
        this.turmaCancelada = turmaCancelada;
    }
}