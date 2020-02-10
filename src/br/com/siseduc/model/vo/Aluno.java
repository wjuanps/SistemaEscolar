package br.com.siseduc.model.vo;

/**
 * 
 * @author Juan Soares
 *
 */
public class Aluno extends Pessoa {
    
    private Turma turma;
    /* Informa se o aluno é repetente */
    private boolean repetente;
    /* Informa se aluno pratica ed. Física */
    private boolean praticaEdFisica;
    /* Informa se o aluno tem irmão(s) na escola */
    private boolean irmaoNaEscola;
    /* Informa se o pai foi declarado */
    private boolean paiDeclarado;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    public boolean isRepetente() {
        return repetente;
    }

    public void setRepetente(boolean repetente) {
        this.repetente = repetente;
    }

    public boolean isPraticaEdFisica() {
        return praticaEdFisica;
    }

    public void setPraticaEdFisica(boolean praticaEdFisica) {
        this.praticaEdFisica = praticaEdFisica;
    }

    public boolean isIrmaoNaEscola() {
        return irmaoNaEscola;
    }

    public void setIrmaoNaEscola(boolean irmaoNaEscola) {
        this.irmaoNaEscola = irmaoNaEscola;
    }

    public boolean isPaiDeclarado() {
        return paiDeclarado;
    }

    public void setPaiDeclarado(boolean paiDeclarado) {
        this.paiDeclarado = paiDeclarado;
    }
    //</editor-fold>
    
}
