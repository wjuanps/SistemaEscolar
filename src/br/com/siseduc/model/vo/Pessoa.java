package br.com.siseduc.model.vo;

import java.io.Serializable;

/**
 *
 * @author Juan Soares
 *
 */
public abstract class Pessoa implements Serializable {

    /**
     * For serialization
     */
    private static final long serialVersionUID = 5647016160360799518L;
    
    /* Informa se a os dados serão cadastrados no sistema */
    public static final int CADASTRAR = 1;
    
    /* Informa se a os dados serão atualizados no sistema */
    public static final int ATUALIZAR = 2;

    /* A matricula da pessoa no sistema */
    private String matricula;

    /* Nome da pessoa */
    private String nome;
    /* Sexo da pessoa */
    private String sexo;
    /* Data de nascimento da pessoa */
    private String dataNascimento;
    /* Endereco da pessoa */
    private String endereco;
    /* Complemento do endereco da pessoa */
    private String complemento;
    /* Bairro da pessoa */
    private String bairro;
    /* Cidade da pessoa */
    private String cidade;
    /* Uf da pessoa */
    private String uf;
    /* Cep da pessoa */
    private String cep;
    /* Telefone da pessoa */
    private String telefone;
    /* Celular da pessoa */
    private String celular;
    /* Email da passoa */
    private String email;
    /* Foto da pessoa */
    private String foto;
    /* Identidade da pessoa */
    private String identidade;
    /* Cpf da pessoa */
    private String cpf;
    /* Nacionalidade da pessoa */
    private String nacionalidade;
    /* Naturalidade da pessoa */
    private String naturalidade;
    /* Uf da naturalidade da pessoa */
    private String ufNaturalidade;
    /* Data de cadastro da pessoa */
    private String dataCadastro;

    /**
     *
     * @return uma instancia de uma <code>Pessoa</code>
     */
    public static Pessoa getInstance() {
        return new Pessoa() {};
    }

    //<editor-fold defaultstate="collapsed" desc="Get's and Set's Methods">
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getUfNaturalidade() {
        return ufNaturalidade;
    }

    public void setUfNaturalidade(String ufNaturalidade) {
        this.ufNaturalidade = ufNaturalidade;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    //</editor-fold>
}
