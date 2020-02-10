package br.com.siseduc.model.vo;

/**
 * 
 * @author Juan Soares
 */
public class Usuario extends Funcionario {

    transient private String senha;
    transient private String rSenha;
    private String usuario;
    
    private boolean usuarioMaster;
    
    private boolean cadastrarAtualizarAluno;
    private boolean cadastrarAtualizarProfessor;
    private boolean cadastrarAtualizarFuncionario;
    private boolean cadastrarUsuario;
    private boolean controleFinanceiro;
    private boolean excluirAluno;
    private boolean excluirProfessor;
    private boolean excluirUsuario;
    private boolean emitirRelatorios;
    private boolean cadastrarAlterarTurma;
    private boolean cadastrarAlterarDisciplina;
    private boolean excluirTurma;
    private boolean excluirDisciplina;
    private boolean emitirDocumentos;
    private boolean cadastrarAtualizarFornecedores;
    private boolean cadastrarAtualizarBensPatrimoniais;
    private boolean excluirBensPatrimoniais;
    private boolean excluirFornecedores;
    private boolean excluirFuncionario;

    public boolean isExcluirFuncionario() {
        return excluirFuncionario;
    }

    public void setExcluirFuncionario(boolean excluirFuncionario) {
        this.excluirFuncionario = excluirFuncionario;
    }
    
    public boolean isUsuarioMaster() {
        return usuarioMaster;
    }

    public void setUsuarioMaster(boolean usuarioMaster) {
        this.usuarioMaster = usuarioMaster;
    }
    
    public boolean isCadastrarAtualizarAluno() {
        return cadastrarAtualizarAluno;
    }

    public void setCadastrarAtualizarAluno(boolean cadastrarAtualizarAluno) {
        this.cadastrarAtualizarAluno = cadastrarAtualizarAluno;
    }

    public boolean isCadastrarAtualizarProfessor() {
        return cadastrarAtualizarProfessor;
    }

    public void setCadastrarAtualizarProfessor(boolean cadastrarAtualizarProfessor) {
        this.cadastrarAtualizarProfessor = cadastrarAtualizarProfessor;
    }

    public boolean isCadastrarAtualizarFuncionario() {
        return cadastrarAtualizarFuncionario;
    }

    public void setCadastrarAtualizarFuncionario(boolean cadastrarAtualizarFuncionario) {
        this.cadastrarAtualizarFuncionario = cadastrarAtualizarFuncionario;
    }

    public boolean isCadastrarUsuario() {
        return cadastrarUsuario;
    }

    public void setCadastrarUsuario(boolean cadastrarUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
    }

    public boolean isControleFinanceiro() {
        return controleFinanceiro;
    }

    public void setControleFinanceiro(boolean controleFinanceiro) {
        this.controleFinanceiro = controleFinanceiro;
    }

    public boolean isExcluirAluno() {
        return excluirAluno;
    }

    public void setExcluirAluno(boolean excluirAluno) {
        this.excluirAluno = excluirAluno;
    }

    public boolean isExcluirProfessor() {
        return excluirProfessor;
    }

    public void setExcluirProfessor(boolean excluirProfessor) {
        this.excluirProfessor = excluirProfessor;
    }

    public boolean isExcluirUsuario() {
        return excluirUsuario;
    }

    public void setExcluirUsuario(boolean excluirUsuario) {
        this.excluirUsuario = excluirUsuario;
    }

    public boolean isEmitirRelatorios() {
        return emitirRelatorios;
    }

    public void setEmitirRelatorios(boolean emitirRelatorios) {
        this.emitirRelatorios = emitirRelatorios;
    }

    public boolean isCadastrarAlterarTurma() {
        return cadastrarAlterarTurma;
    }

    public void setCadastrarAlterarTurma(boolean cadastrarAlterarTurma) {
        this.cadastrarAlterarTurma = cadastrarAlterarTurma;
    }

    public boolean isCadastrarAlterarDisciplina() {
        return cadastrarAlterarDisciplina;
    }

    public void setCadastrarAlterarDisciplina(boolean cadastrarAlterarDisciplina) {
        this.cadastrarAlterarDisciplina = cadastrarAlterarDisciplina;
    }

    public boolean isExcluirTurma() {
        return excluirTurma;
    }

    public void setExcluirTurma(boolean excluirTurma) {
        this.excluirTurma = excluirTurma;
    }

    public boolean isExcluirDisciplina() {
        return excluirDisciplina;
    }

    public void setExcluirDisciplina(boolean excluirDisciplina) {
        this.excluirDisciplina = excluirDisciplina;
    }

    public boolean isEmitirDocumentos() {
        return emitirDocumentos;
    }

    public void setEmitirDocumentos(boolean emitirDocumentos) {
        this.emitirDocumentos = emitirDocumentos;
    }

    public boolean isCadastrarAtualizarFornecedores() {
        return cadastrarAtualizarFornecedores;
    }

    public void setCadastrarAtualizarFornecedores(boolean cadastrarAtualizarFornecedores) {
        this.cadastrarAtualizarFornecedores = cadastrarAtualizarFornecedores;
    }

    public boolean isCadastrarAtualizarBensPatrimoniais() {
        return cadastrarAtualizarBensPatrimoniais;
    }

    public void setCadastrarAtualizarBensPatrimoniais(boolean cadastrarAtualizarBensPatrimoniais) {
        this.cadastrarAtualizarBensPatrimoniais = cadastrarAtualizarBensPatrimoniais;
    }

    public boolean isExcluirBensPatrimoniais() {
        return excluirBensPatrimoniais;
    }

    public void setExcluirBensPatrimoniais(boolean excluirBensPatrimoniais) {
        this.excluirBensPatrimoniais = excluirBensPatrimoniais;
    }

    public boolean isExcluirFornecedores() {
        return excluirFornecedores;
    }

    public void setExcluirFornecedores(boolean excluirFornecedores) {
        this.excluirFornecedores = excluirFornecedores;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getrSenha() {
        return rSenha;
    }

    public void setrSenha(String rSenha) {
        this.rSenha = rSenha;
    }
    
}
