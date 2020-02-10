package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.Paginacao;
import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Responsavel;
import br.com.siseduc.model.vo.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Soares
 *
 */
public abstract class AlunoDAO extends PessoaDAO implements IAlunoDAO {
    /**
     * Lista de todos os alunos
     */
    private List<Aluno> listaAlunos;
    /**
     *
     */
    private Aluno aluno;
    
    /**
     * 
     * Método para cadastrar e atulizar cadastro dos Alunos
     * de acordo com o tipo especificado.
     * 
     * @param con Conexão com o banco de dados
     * @param objeto obj[0] representa o <code>Aluno</code>
     *            obj[1] representa o tipo de alteração que será feita no banco de dados
     *            obj[2] representa a mãe
     *            obj[3] representa o pai, se houver
     * @return true se o cadastro/alteração for executado com sucesso
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Object... objeto) throws SQLException {
        
        final String sql = ((int) objeto[1] == 1)
                ? SQL_NOVO_ALUNO
                : SQL_UPDATE_CADASTRO_ALUNO;
        
        Aluno aluno = (Aluno) objeto[0];
        Responsavel[] responsaveis = (Responsavel[]) objeto[2];
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            
            /* Dados do aluno */
            stm.setString(1, aluno.getNome());
            stm.setString(2, aluno.getMatricula());
            stm.setString(3, aluno.getSexo());
            stm.setString(4, aluno.getFoto());
            stm.setString(5, aluno.getNacionalidade());
            stm.setString(6, aluno.getDataNascimento());
            stm.setString(7, aluno.getNaturalidade());
            stm.setString(8, aluno.getUfNaturalidade());
            stm.setInt(9, (aluno.isPraticaEdFisica()) ? 1 : 0);
            stm.setInt(10, (aluno.isIrmaoNaEscola()) ? 1 : 0);
            stm.setString(11, aluno.getTelefone());
            stm.setString(12, aluno.getCelular());
            stm.setString(13, aluno.getEmail());
            stm.setInt(14, (aluno.isPaiDeclarado()) ? 1 : 0);
            
            /* Dados do endereco do aluno */
            stm.setString(15, aluno.getEndereco());
            stm.setString(16, aluno.getComplemento());
            stm.setString(17, aluno.getBairro());
            stm.setString(18, aluno.getCidade());
            stm.setString(19, aluno.getUf());
            stm.setString(20, aluno.getCep());
            
            /* Dados da mãe */
            stm.setString(21, responsaveis[MAE].getNome());
            stm.setString(22, responsaveis[MAE].getIdentidade());
            stm.setString(23, responsaveis[MAE].getCpf());
            stm.setString(24, responsaveis[MAE].getTelefone());
            stm.setString(25, responsaveis[MAE].getCelular());
            stm.setString(26, responsaveis[MAE].getEmail());
            stm.setInt(27, (responsaveis[MAE].isMoraComFilho()) ? 1 : 0);
            stm.setInt(28, (responsaveis[MAE].isOutroFilhoNaEscola()) ? 1 : 0);
            
            /* Dados do endereco da mãe */
            stm.setString(29, responsaveis[MAE].getEndereco());
            stm.setString(30, responsaveis[MAE].getComplemento());
            stm.setString(31, responsaveis[MAE].getBairro());
            stm.setString(32, responsaveis[MAE].getCidade());
            stm.setString(33, responsaveis[MAE].getUf());
            stm.setString(34, responsaveis[MAE].getCep());            
            
            /* Dados do Pai */
            stm.setString(35, (responsaveis.length > 1) ? responsaveis[PAI].getNome() : null);
            stm.setString(36, (responsaveis.length > 1) ? responsaveis[PAI].getIdentidade() : null);
            stm.setString(37, (responsaveis.length > 1) ? responsaveis[PAI].getCpf() : null);
            stm.setString(38, (responsaveis.length > 1) ? responsaveis[PAI].getTelefone() : null);
            stm.setString(39, (responsaveis.length > 1) ? responsaveis[PAI].getCelular() : null);
            stm.setString(40, (responsaveis.length > 1) ? responsaveis[PAI].getEmail() : null);
            stm.setInt(41, (responsaveis.length > 1) ? ((responsaveis[PAI].isMoraComFilho()) ? 1 : 0) : -1);
            stm.setInt(42, (responsaveis.length > 1) ? ((responsaveis[PAI].isOutroFilhoNaEscola()) ? 1 : 0) : -1);

            /* Dados do endereco do Pai */
            stm.setString(43, (responsaveis.length > 1) ? responsaveis[PAI].getEndereco() : null);
            stm.setString(44, (responsaveis.length > 1) ? responsaveis[PAI].getComplemento() : null);
            stm.setString(45, (responsaveis.length > 1) ? responsaveis[PAI].getBairro() : null);
            stm.setString(46, (responsaveis.length > 1) ? responsaveis[PAI].getCidade() : null);
            stm.setString(47, (responsaveis.length > 1) ? responsaveis[PAI].getUf() : null);
            stm.setString(48, (responsaveis.length > 1) ? responsaveis[PAI].getCep() : null);
            
            /* Dados do aluno na turma */
            stm.setInt(49, aluno.isRepetente() ? 1 : 0);
            stm.setInt(50, aluno.getTurma().getNumeroTurma());
            
            return (stm.executeUpdate() > 0);
        }
    }

    private Aluno getAluno(ResultSet rs, Aluno aluno) throws SQLException {

        aluno = new Aluno();
        final Turma turma = new Turma();

        aluno.setFoto(rs.getString("a.foto"));

        aluno.setNome(rs.getString("a.nome"));
        aluno.setMatricula(rs.getString("a.matricula"));
        aluno.setSexo(rs.getString("a.sexo"));
        aluno.setNacionalidade(rs.getString("a.nacionalidade"));
        aluno.setDataNascimento(rs.getString("a.data_nascimento"));
        aluno.setNaturalidade(rs.getString("a.naturalidade"));
        aluno.setUfNaturalidade(rs.getString("a.uf_naturalidade"));
        aluno.setPraticaEdFisica(rs.getInt("a.pratica_ed_fisica") == 1);
        aluno.setIrmaoNaEscola(rs.getInt("a.irmao_na_escola") == 1);
        aluno.setRepetente(rs.getInt("aht.repetente") == 1);
        aluno.setDataCadastro(rs.getString("a.data_cadastro"));
        aluno.setTelefone(rs.getString("a.telefone"));
        aluno.setCelular(rs.getString("a.celular"));
        aluno.setEmail(rs.getString("a.email"));
        aluno.setPaiDeclarado(rs.getInt("a.pai_declarado") == 1);

        turma.setNumeroTurma(rs.getInt("t.id_turma"));
        turma.setModalidade(rs.getString("t.modalidade"));
        turma.setTurma(rs.getString("t.turma"));
        turma.setSerie(rs.getString("t.serie"));
        turma.setTurno(rs.getString("t.turno"));
        turma.setTurma(rs.getString("t.turma"));
        
        aluno.setTurma(turma);

        aluno.setEndereco(rs.getString("e1.endereco"));
        aluno.setComplemento(rs.getString("e1.complemento"));
        aluno.setBairro(rs.getString("e1.bairro"));
        aluno.setCidade(rs.getString("e1.cidade"));
        aluno.setUf(rs.getString("e1.uf"));
        aluno.setCep(rs.getString("e1.cep"));

        return aluno;
    }

    /**
     *
     * @param rs
     * @return
     */
    private Responsavel getResponsavel(ResultSet rs) throws SQLException {

        Responsavel responsavel = new Responsavel();

        responsavel.setNome(rs.getString("r.nome"));
        responsavel.setParentesco(rs.getString("r.parentesco"));
        responsavel.setIdentidade(rs.getString("r.identidade"));
        responsavel.setCpf(rs.getString("r.cpf"));
        responsavel.setTelefone(rs.getString("r.telefone"));
        responsavel.setCelular(rs.getString("r.celular"));
        responsavel.setEmail(rs.getString("r.email"));
        responsavel.setMoraComFilho(rs.getInt("r.mora_com_filho") == 1);
        responsavel.setOutroFilhoNaEscola(rs.getInt("r.outro_filho_na_escola") == 1);

        responsavel.setEndereco(rs.getString("e2.endereco"));
        responsavel.setComplemento(rs.getString("e2.complemento"));
        responsavel.setBairro(rs.getString("e2.bairro"));
        responsavel.setCidade(rs.getString("e2.cidade"));
        responsavel.setUf(rs.getString("e2.uf"));
        responsavel.setCep(rs.getString("e2.cep"));

        return responsavel;
    }

    /**
     *
     * @param con conexão com o banco de dados
     * @return lista de alunos
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public List<Aluno> listaAluno(Connection con) throws SQLException {

        this.listaAlunos = new ArrayList<>();
        Paginacao.setTotalResultados(con, SQL_GET_ALUNO);
        
        final String sql = SQL_GET_ALUNO.concat(String.format(" GROUP BY id_aluno LIMIT %d, %d", Paginacao.inicio(), Paginacao.getItensPorPagina()));
        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    final Aluno aluno = new Aluno();

                    aluno.setMatricula(String.valueOf(rs.getInt("matricula")));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setTelefone(rs.getString("telefone"));
                    aluno.setCelular(rs.getString("celular"));
                    aluno.setEmail(rs.getString("email"));

                    this.listaAlunos.add(aluno);
                }
                return this.listaAlunos;
            }
        }
        return null;
    }

    /**
     *
     * @param con conexão com o banco de dados
     * @param matricula cindição para recuperar os dados
     * @return um map com o aluno e a lista com os responsaveis
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public Map<Aluno, List<Responsavel>> listaAluno(Connection con, int matricula) throws SQLException {

        final Map<Aluno, List<Responsavel>> listaAluno = new HashMap<>();
        final List<Responsavel> listaResponsavel = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_ALUNO_ALL)) {
            stm.setInt(1, matricula);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        this.aluno = this.getAluno(rs, this.aluno);
                        listaResponsavel.add(this.getResponsavel(rs));
                    }
                    listaAluno.put(aluno, listaResponsavel);
                    return listaAluno;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param con conexão com o banco de dados
     * @param opc opcionais
     * @param tabela tabela para consulta
     * @return o id
     * @throws SQLException para todo erro no banco de dados
     */
    public int getId(Connection con, String tabela, String... opc) throws SQLException {

        final String sql;
        sql = String.format("SELECT id_%s FROM %s WHERE %s = ?", tabela, tabela, opc[0]);

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, opc[1]);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param con coneção com o banco de dados
     * @param order ordena a busca
     * @return uma lista com todos os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public List<Aluno> listaAluno(Connection con, String order) throws SQLException {
        return this.listaAluno(con, order, null);
    }

    /**
     *
     * @param con conexão de banco de dados
     * @param order ordena a busca
     * @param nome condição da busca
     * @return uma lista com os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public List<Aluno> listaAluno(Connection con, String order, String nome) throws SQLException {

        listaAlunos = new ArrayList<>();

        String statement;
        String sql;
        if (nome == null) {
            sql = String.format("SELECT COUNT(*) FROM aluno WHERE is_ativo = 1 ORDER BY %s DESC", order);
            Paginacao.setTotalResultados(con, sql);
            
            statement = 
                    String.format(
                            "SELECT matricula, nome, telefone, celular, email FROM aluno WHERE is_ativo = 1"
                                    + " ORDER BY %s DESC LIMIT %d, %d", order, Paginacao.inicio(), Paginacao.getItensPorPagina()
                    );
        } else {
            sql = String.format("SELECT COUNT(*) FROM aluno WHERE is_ativo = 1 AND nome LIKE \'%%%s%%\' ORDER BY %s DESC", nome, order);
            Paginacao.setTotalResultados(con, sql);
            
            statement = 
                    String.format(
                            "SELECT matricula, nome, telefone, celular, email FROM aluno WHERE is_ativo = 1 "
                                    + "AND nome LIKE \'%%%s%%\' ORDER BY %s DESC LIMIT %d, %d", 
                                        nome, order, Paginacao.inicio(), Paginacao.getItensPorPagina()
                    );
        }
       

        try (PreparedStatement stm = con.prepareStatement(statement);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    final Aluno aluno = new Aluno();

                    aluno.setMatricula(String.valueOf(rs.getInt("matricula")));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setTelefone(rs.getString("telefone"));
                    aluno.setCelular(rs.getString("celular"));
                    aluno.setEmail(rs.getString("email"));

                    this.listaAlunos.add(aluno);
                }
                return this.listaAlunos;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param con conexão com o banco de dados
     * @param opc opcional
     * @return uma lista com os alunos
     * @throws SQLException para todo erro no banco de dados
     */
    @Override
    public List<Aluno> listaAluno(Connection con, String ... opc) throws SQLException {
        
        listaAlunos = new ArrayList<>();
        
        final String sql;
        sql = "CALL SP_aluno_boletim(?,?)";
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, opc[0]);
            stm.setString(2, opc[1]);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        final Aluno aluno = new Aluno();
                        final Turma turma = new Turma();
                        
                        aluno.setMatricula(rs.getString(1));
                        aluno.setNome(rs.getString(2));
                        turma.setTurma(rs.getString(3));
                        turma.setSerie(rs.getString(4));
                        turma.setModalidade(rs.getString(5));
                        turma.setTurno(rs.getString(6));
                        aluno.setTurma(turma);
                        
                        listaAlunos.add(aluno);
                    }
                    return listaAlunos;
                }
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param con
     * @param idAluno
     * @param idProfessor
     * @param idTurma
     * @param idDisciplina
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Double> listaNotas(Connection con, int idAluno, int idProfessor, int idTurma, int idDisciplina) throws SQLException {
        
        final List<Double> listaNotas = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_NOTAS)) {
            stm.setInt(1, idTurma);
            stm.setInt(2, idDisciplina);
            stm.setInt(3, idAluno);
            stm.setInt(4, idProfessor);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listaNotas.add(rs.getDouble("1_avaliacao"));
                        listaNotas.add(rs.getDouble("2_avaliacao"));
                        listaNotas.add(rs.getDouble("3_avaliacao"));
                        listaNotas.add(rs.getDouble("4_avaliacao"));
                        
                        listaNotas.add(rs.getDouble("1_recuperacao"));
                        listaNotas.add(rs.getDouble("2_recuperacao"));
                        
                        listaNotas.add(rs.getDouble("id_nota"));
                    }
                    return listaNotas;
                }
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param con
     * @param hasNota
     * @param obj
     * @throws SQLException 
     */
    @Override
    public void lancarNota(Connection con, boolean hasNota, Object ... obj) throws SQLException {   
        try (PreparedStatement stm = con.prepareStatement(SQL_LANCAR_NOTAS)) {
            int i = 2;
            stm.setBoolean(1, hasNota);
            for (Object object : obj) {
                stm.setObject(i++, object);
            }            
            stm.executeUpdate();
        }        
    }
}