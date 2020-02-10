/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.Siseduc;
import br.com.siseduc.controller.AlunoController;
import br.com.siseduc.controller.FornecedorController;
import br.com.siseduc.controller.FuncionarioController;
import br.com.siseduc.controller.ProfessorController;
import br.com.siseduc.controller.TelaInicialController;
import br.com.siseduc.model.bo.AlunoBO;
import br.com.siseduc.model.bo.FornecedorBO;
import br.com.siseduc.model.bo.FuncionarioBO;
import br.com.siseduc.model.bo.ProfessorBO;
import br.com.siseduc.model.bo.UsuarioBO;
import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Fornecedor;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.model.vo.Usuario;
import br.com.siseduc.view.TelaLogin;
import java.io.Serializable;

/**
 *
 * <p>
 *  Interface que reúne todas as sqls do sistema.
 * </p>
 *
 * <p>
 *  As classes ou interfaces que implementarem esta interface, seus métodos
 *  precisaram de <code>Conexão</code> com o banco de dados e poderam lançar
 *  excessões do tipo <code>SqlException</code>.
 * </p>
 *
 * @see PessoaDAO
 * @see IConexao
 * @see Conexao
 *
 * @author Juan
 */
public interface Statement extends Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="CRUD Aluno">
    /**
     * <p>
     * sql para inserção de um novo <code>Aluno</code> .
     * </p>
     *
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_NOVO_ALUNO = "CALL SP_inserir_novo_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * sql para atualizar cadastro do <code>Aluno</code>
     * </p>
     *
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_UPDATE_CADASTRO_ALUNO = "CALL SP_con_atualizacao_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * sql para recuperar dados do <code>Aluno</code>
     * </p>
     *
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_GET_ALUNO_ALL = "CALL SP_get_aluno(?)";

    /**
     * <p>
     * sql para recuperar dados do <code>Aluno</code>
     * </p>
     *
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_GET_ALUNO = "SELECT count(*), matricula, nome, telefone, celular, email FROM aluno WHERE is_ativo = 1";

    /**
     * <p>
     * sql para recuperar as notas do <code>Aluno</code>
     * </p>
     *
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_GET_NOTAS = "SELECT * FROM nota WHERE id_turma = ? AND id_disciplina = ? AND id_aluno = ? AND id_professor = ?";

    /**
     * <p>
     * sql para lançar as notas do <code>Aluno</code>
     * </p>
     *
     * @see TelaInicialController
     * @see AlunoController
     * @see AlunoDAO
     * @see AlunoBO
     * @see Aluno
     */
    String SQL_LANCAR_NOTAS = "CALL SP_inserir_nota(?,?,?,?,?,?,?,?,?,?,?,?)";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CRUD Professor">
    /**
     * <p>
     * Sql para inserção de <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_NOVO_PROFESSOR = "CALL SP_inserir_novo_professor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para inserção da(s) formação(ôes) do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_INSERIR_FORMACAO = "INSERT INTO formacao (id_professor, titulo, curso, instituicao, diploma, ano_inicio, ano_termino) VALUES (?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para inserção da(s) disciplina(s) que o <code>Professor</code> está
     * habilitado a ministrar
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_INSERIR_DISCIPLINA = "INSERT INTO professor_has_disciplina VALUES (?,?)";

    /**
     * <p>
     * Sql para recuperar informações do <code>Professor</code> .
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR_ALL = "CALL SP_get_professor(?)";

    /**
     * <p>
     * Sql para atuaização do cadastro do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_UPDATE_PROFESSOR = new StringBuilder()
            .append("UPDATE professor p INNER JOIN endereco e ON (p.id_endereco = e.id_endereco) ")
            .append("SET p.nome = ?, p.sexo = ?, p.data_nascimento = ?, p.nacionalidade = ?, p.naturalidade = ?, ")
            .append("p.uf_naturalidade = ?, p.identidade = ?, p.cpf = ?, p.foto = ?, p.telefone = ?, p.celular = ?, ")
            .append("p.email = ?, e.endereco = ?, e.complemento = ?, e.bairro = ?, e.cidade = ?, ")
            .append("e.uf = ?, e.cep = ? ")
            .append("WHERE p.id_professor = ?")
            .toString();

    /**
     * <p>
     * Sql para recuperar os dados do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR = "SELECT id_professor, nome, telefone, celular, email FROM professor p INNER JOIN endereco e ON (e.id_endereco = p.id_endereco) WHERE is_ativo = 1";

    /**
     * <p>
     * Sql para recuperar os dados do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR_COUNT = "SELECT COUNT(*) FROM professor p INNER JOIN endereco e ON (p.id_endereco = e.id_endereco) WHERE is_ativo = 1";

    /**
     * <p>
     * Sql para recuperar os dados do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR_NOTA = new StringBuilder()
            .append("SELECT DISTINCT CONCAT(p.id_professor, ' - ', p.nome) AS Professor FROM professor p ")
            .append("INNER JOIN professor_has_turma pt ON (p.id_professor = pt.id_professor) ")
            .append("INNER JOIN turma t ON (pt.id_turma = t.id_turma) ")
            .append("WHERE turma = ? ")
            .append("ORDER BY p.nome")
            .toString();

    /**
     * <p>
     * Sql para recuperar os dados do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR_DIS = new StringBuilder()
            .append("SELECT CONCAT(p.id_professor, ' - ', p.nome, ' - ', d.disciplina) AS Professor FROM professor p ")
            .append("INNER JOIN professor_has_disciplina pd ON (p.id_professor = pd.id_professor) ")
            .append("INNER JOIN disciplina d ON (pd.id_disciplina = d.id_disciplina) ")
            .append("WHERE p.nome LIKE ? AND d.disciplina LIKE ? ")
            .append("ORDER BY p.nome")
            .toString();

    /**
     * <p>
     * Sql para recuperar os dados do <code>Professor</code>
     * </p>
     *
     * @see ProfessorController
     * @see ProfessorDAO
     * @see ProfessorBO
     * @see Professor
     */
    String SQL_GET_PROFESSOR_DIS2 = new StringBuilder()
            .append("SELECT CONCAT(p.id_professor, ' - ', p.nome, ' - ', d.disciplina) AS Professor FROM professor p ")
            .append("INNER JOIN professor_has_disciplina pd ON (p.id_professor = pd.id_professor) ")
            .append("INNER JOIN disciplina d ON (pd.id_disciplina = d.id_disciplina) ")
            .append("WHERE d.disciplina LIKE ? ")
            .append("ORDER BY p.nome")
            .toString();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CRUD Fucionario e Usuario">
    /**
     * <p>
     * Sql para cadastrar <code>Funcionario</code> e <code>Usuario</code>
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_CADASTRAR_FUNCIONARIO = "CALL SP_novo_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para atualizar cadastro <code>Funcionario</code> e
     * <code>Usuario</code>
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_ATULIZAR_FUNCIONARIO = "CALL SP_atualizar_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para recuperar dados de um <code>Funcionario</code>
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     */
    String SQL_GET_FUNCIONARIO = "CALL SP_get_funcionario(?)";

    /**
     * <p>
     * Sql para recuperar <code>Usuario</code> .
     * </p>
     *
     * @see FuncionarioController
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_GET_USUARIOS = "SELECT usuario FROM usuario";

    /**
     * <p>
     * Sql para fazer login no sistema.
     * </p>
     *
     * @see TelaInicialController
     * @see UsuarioDao
     * @see UsuarioBO
     * @see TelaLogin
     * @see Usuario
     */
    String SQL_CONFIRMAR_LOGIN_SENHA = "SELECT log_in(?,?)";

    /**
     * <p>
     * Sql para recuperar o nome do <code>Usuario</code>
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_GET_NOME = "SELECT f.nome FROM funcionario AS f INNER JOIN usuario AS u ON (f.id_funcionario = u.id_funcionario AND u.usuario = ?)";
    
    /**
     * <p>
     * Sql para recuperar o id do <code>Usuario</code>
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_GET_ID_USUARIO = "SELECT id_funcionario FROM usuario WHERE usuario = ?";
    
    /**
     * <p>
     * Sql para verificar se o <code>Usuario</code> máster existe
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     * @see Siseduc
     */
    String SQL_HAS_MASTER = "SELECT * FROM usuario WHERE usuario = 'Máster'";
    
    /**
     * <p>
     * Sql para inserir o <code>Usuario</code> máster
     * </p>
     *
     * @see FuncionarioController
     * @see FuncionarioDAO
     * @see FuncionarioBO
     * @see Funcionario
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     * @see Siseduc
     */
    String SQL_INSERIR_MASTER = "CALL SP_inserir_master()";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CRUD Fornecedor">
    /**
     * <p>
     * Sql para cadastrar <code>Fornecedor</code>
     * </p>
     *
     * @see FornecedorController
     * @see FornecedorDAO
     * @see FornecedorBO
     * @see Fornecedor
     * @see UsuarioDao
     * @see UsuarioBO
     * @see Usuario
     */
    String SQL_NOVO_FORNECEDOR = "CALL SP_inserir_fornecedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para atualizar os dados do <code>Fornecedor</code>.
     * </p>
     *
     * @see FornecedorController
     * @see FornecedorDAO
     * @see FornecedorBO
     * @see Fornecedor
     */
    String SQL_UPDATE_FORNECEDOR = "CALL SP_update_fornecedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /**
     * <p>
     * Sql para recuperar todos os dados de todos os <code>Fornecedor</code>.
     * </p>
     *
     * @see FornecedorController
     * @see FornecedorDAO
     * @see FornecedorBO
     * @see Fornecedor
     */
    String SQL_GET_FORNECEDOR = "SELECT * FROM fornecedor f INNER JOIN endereco e ON (e.id_endereco = f.id_endereco)";

    /**
     * <p>
     * Sql para recuperar todos os dados de um <code>Fornecedor</code>.
     * </p>
     *
     * @see FornecedorController
     * @see FornecedorDAO
     * @see FornecedorBO
     * @see Fornecedor
     */
    String SQL_GET_FORNECEDOR2 = "SELECT * FROM fornecedor f INNER JOIN endereco e ON (e.id_endereco = f.id_endereco) WHERE f.id_fornecedor = ?";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CRUD Turma">
    //
    String CAMPOS = new StringBuilder()
            .append("turma, modalidade, serie, descricao_serie, turno, descricao_turma, ")
            .append("data_cadastro, data_modificacao, is_ativa, is_cancelada, ")
            .append("data_ativacao, data_cancelamento")
            .toString();

    //
    String SQL_NOVA_TURMA = "INSERT INTO turma (" + CAMPOS + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    //
    String SQL_UPDATE_TURMA = "UPDATE turma SET descricao_turma = ?, data_modificacao = ?, is_ativa = ?, is_cancelada = ?, data_ativacao = ?, data_cancelamento = ? WHERE turma = ?";

    //
    String SQL_GET_PROF_TURMA = new StringBuilder()
            .append("SELECT DISTINCT CONCAT(p.id_professor, ' - ', p.nome, ' - ', d.disciplina) AS Professor FROM professor p ")
            .append("INNER JOIN professor_has_turma ph ON (p.id_professor = ph.id_professor) ")
            .append("INNER JOIN turma t ON (ph.id_turma = t.id_turma) ")
            .append("INNER JOIN professor_has_disciplina pd ON (p.id_professor = pd.id_professor AND pd.id_disciplina = ph.id_disciplina) ")
            .append("INNER JOIN disciplina d ON (pd.id_disciplina = d.id_disciplina) ")
            .append("WHERE turma = ? ")
            .append("ORDER BY p.nome")
            .toString();
    
    //
    String SQL_PROF_TURMA = "INSERT INTO professor_has_turma VALUES (?,?,?)";
    
    //
    String SQL_DEL_PROF_TURMA = "DELETE FROM professor_has_turma WHERE id_turma = ?";
    
    //
    String SQL_VER_DATA = 
            "SELECT * \n" +
            "FROM siseduc.nota n \n" +
            "INNER JOIN turma t ON (n.id_turma = t.id_turma) \n" +
            "WHERE n.id_professor = ? AND n.id_disciplina = ? AND t.turma = ?";
    
    //
    String SQL_GET_PROF = new StringBuilder()
            .append("SELECT p.id_professor, p.nome, d.disciplina FROM professor p ")
            .append("INNER JOIN professor_has_disciplina pd ON (p.id_professor = pd.id_professor) ")
            .append("INNER JOIN disciplina d ON (pd.id_disciplina = d.id_disciplina) ")
            .append("WHERE p.id_professor <> ? AND d.id_disciplina = ?")
            .toString();
    
    //
    String SQL_UPDATE_PROF_NOTA = "UPDATE nota n LEFT OUTER JOIN turma t ON (n.id_turma = t.id_turma) SET id_professor = ? WHERE t.turma = ? AND id_disciplina = ? AND id_professor = ?";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CRUD Lembrete">

    /**
     *
     */
    String SQL_CADASTRAR_LEMBRETE = "INSERT INTO lembrete (id_usuario, titulo, tipo, descricao, data, is_ativo) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     *
     */
    String SQL_GET_LEMBRETE = "SELECT * FROM lembrete WHERE id_usuario = ?";

    /**
     *
     */
    String SQL_HAS_LEMBRETE = "SELECT * FROM lembrete WHERE id_usuario = ? AND data LIKE ?";
    //</editor-fold>
}
