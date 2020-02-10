package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.Paginacao;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Formacao;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.model.vo.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Sophia
 */
public abstract class ProfessorDAO extends PessoaDAO implements IProfessorDAO {

    //
    private List<Professor> listaProfessor;

    /**
     * 
     * @param con
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Object ... obj) throws SQLException {
        
        String sql_professor;
        sql_professor = ((int) obj[3] == Pessoa.CADASTRAR) ? SQL_NOVO_PROFESSOR : SQL_UPDATE_PROFESSOR;
        
        final Professor professor = (Professor) obj[0];
        final List<Formacao> formacoes = (List<Formacao>) obj[1];
        final List<Integer> disciplinas = (List<Integer>) obj[2];

        try (PreparedStatement stm = con.prepareStatement(sql_professor)) {
            stm.setString(1, professor.getNome());
            stm.setString(2, professor.getSexo());
            stm.setString(3, professor.getDataNascimento());
            stm.setString(4, professor.getNacionalidade());
            stm.setString(5, professor.getNaturalidade());
            stm.setString(6, professor.getUfNaturalidade());
            stm.setString(7, professor.getIdentidade());
            stm.setString(8, professor.getCpf());
            stm.setString(9, professor.getFoto());
            stm.setString(10, professor.getTelefone());
            stm.setString(11, professor.getCelular());
            stm.setString(12, professor.getEmail());
            stm.setString(13, professor.getEndereco());
            stm.setString(14, professor.getComplemento());
            stm.setString(15, professor.getBairro());
            stm.setString(16, professor.getCidade());
            stm.setString(17, professor.getUf());
            stm.setString(18, professor.getCep());

            if ((int) obj[3] == Pessoa.ATUALIZAR) {
                stm.setInt(19, Integer.parseInt(professor.getMatricula()));
            }

            stm.executeUpdate();
        }

        final int id_professor = ((int) obj[3] == Pessoa.CADASTRAR) ? lastInsertId(con, "professor") : Integer.parseInt(professor.getMatricula());
        try (PreparedStatement stm = con.prepareStatement(SQL_INSERIR_FORMACAO)) {
            if (!formacoes.isEmpty()) {
                for (Formacao formacao : formacoes) {
                    stm.setInt(1, id_professor);
                    stm.setString(2, formacao.getTitulo());
                    stm.setString(3, formacao.getCurso());
                    stm.setString(4, formacao.getInstituicao());
                    stm.setString(5, formacao.getDiploma());
                    stm.setInt(6, formacao.getAnoInicio());
                    stm.setInt(7, formacao.getAnoTermino());

                    stm.executeUpdate();
                }
            }
        }

        try (PreparedStatement stm = con.prepareStatement(SQL_INSERIR_DISCIPLINA)) {
            if (!disciplinas.isEmpty()) {
                for (Integer disciplina : disciplinas) {
                    stm.setInt(1, disciplina);
                    stm.setInt(2, id_professor);

                    stm.executeUpdate();
                }
            }
        }
        return true;
    }

    /**
     *
     * @param con
     * @param order
     * @param nome
     * @return
     * @throws SQLException
     */
    @Override
    public List<Professor> listaProfessor(Connection con, String order, String nome) throws SQLException {

        listaProfessor = new ArrayList<>();
        Paginacao.setTotalResultados(con, SQL_GET_PROFESSOR_COUNT.concat(String.format(" AND nome LIKE \'%%%s%%\'", nome)));
        final String sql = SQL_GET_PROFESSOR.concat(String.format(" AND nome LIKE \'%%%s%%\' ORDER BY %s ASC LIMIT ?, ?", nome, order));
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, Paginacao.inicio());
            stm.setInt(2, Paginacao.getItensPorPagina());
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        Professor professor = new Professor();
                        professor.setMatricula(PessoaBO.getCadastro(rs.getInt("id_professor")));
                        professor.setNome(rs.getString("nome"));
                        professor.setTelefone(rs.getString("telefone"));
                        professor.setCelular(rs.getString("celular"));
                        professor.setEmail(rs.getString("email"));

                        listaProfessor.add(professor);
                    }
                    return listaProfessor;
                }
            }
        }
        return null;
    }
    
    /**
     * 
     * @param con
     * @param nome
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, Turma<Set<String>, Set<String>>> getProfessor(Connection con, String nome) throws SQLException {
        
        String n = "";
        final StringBuilder sql = new StringBuilder(); 
        sql.append("SELECT DISTINCT p.nome, t.turma, d.disciplina \n")
            .append("FROM professor p \n")
            .append("INNER JOIN professor_has_turma pht ON (p.id_professor = pht.id_professor) \n")
            .append("INNER JOIN turma t ON (t.id_turma = pht.id_turma) \n")
            .append("INNER JOIN professor_has_disciplina phd ON (p.id_professor = phd.id_professor) \n")
            .append("INNER JOIN disciplina d ON (d.id_disciplina = phd.id_disciplina) \n")
            .append("WHERE p.nome = ? \n")
            .append("GROUP BY p.nome, t.turma, d.disciplina");
        
        final Map<String, Turma<Set<String>, Set<String>>> map = new HashMap<>();
        
        final Set<String> disciplinas = new HashSet<>();
        final Set<String> turmas = new HashSet<>();
        
        final Turma<Set<String>, Set<String>> t = new Turma();
        t.setInstanceA(disciplinas);
        t.setInstanceP(turmas);
        
        try (PreparedStatement stm = con.prepareStatement(sql.toString())) {
            stm.setString(1, nome);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        t.getInstanceP().add(rs.getString(2));
                        t.getInstanceA().add(rs.getString(3));
                        n = rs.getString(1);
                    }
                    map.put(n, t);
                    return map;
                }
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param con
     * @param nome
     * @param disciplina
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> getNomes(Connection con, String nome, String disciplina) throws SQLException {
        
        final String sql = (null != disciplina) 
                ? (null != nome) ? SQL_GET_PROFESSOR_DIS : SQL_GET_PROFESSOR_DIS2 : "SELECT nome FROM professor WHERE nome LIKE ? LIMIT 3";
        final List<String> listNomes = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            if (null != nome)
                stm.setString(1, "%" + nome + "%");
            if (null != disciplina)
                if (null == nome)
                    stm.setString(1, "%" + disciplina + "%");
                else 
                    stm.setString(2, "%" + disciplina + "%");
                
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listNomes.add(rs.getString(1));
                    }
                    return listNomes;
                }
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param con
     * @param turma
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> getProfessores(Connection con, String turma) throws SQLException {
        final List<String> listProfessores = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_PROFESSOR_NOTA)) {
            stm.setString(1, turma);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listProfessores.add(rs.getString("Professor"));
                    }
                    return listProfessores;
                }
            }
        }        
        return null;
    }
    
    /**
     *
     * @param con
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Professor> listaProfessor(Connection con, int id) throws SQLException {

        listaProfessor = new ArrayList<>();
        final Set<Disciplina> listaDisciplina = new HashSet<>();
        final Set<Formacao> listaFormacao = new HashSet<>();
        
        final Map<Integer, Disciplina> m1 = new HashMap<>();
        final Map<String, Formacao> m2 = new HashMap<>();
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_PROFESSOR_ALL)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    final Professor professor = new Professor();
                    while (rs.next()) {
                        professor.setMatricula(PessoaBO.getCadastro(rs.getInt("id_professor")));
                        professor.setNome(rs.getString("nome"));
                        professor.setFoto(rs.getString("foto"));
                        professor.setSexo(rs.getString("sexo"));
                        professor.setDataNascimento(rs.getString("data_nascimento"));
                        professor.setNacionalidade(rs.getString("nacionalidade"));
                        professor.setNaturalidade(rs.getString("naturalidade"));
                        professor.setUfNaturalidade(rs.getString("uf_naturalidade"));
                        professor.setIdentidade(rs.getString("identidade"));
                        professor.setCpf(rs.getString("cpf"));
                        professor.setDataCadastro(rs.getString("data_cadastro"));

                        professor.setEndereco(rs.getString("endereco"));
                        professor.setComplemento(rs.getString("complemento"));
                        professor.setBairro(rs.getString("bairro"));
                        professor.setCidade(rs.getString("cidade"));
                        professor.setUf(rs.getString("uf"));
                        professor.setCep(rs.getString("cep"));
                        professor.setTelefone(rs.getString("telefone"));
                        professor.setCelular(rs.getString("celular"));
                        professor.setEmail(rs.getString("email"));

                        final Disciplina disciplina = new Disciplina();
                        disciplina.setId(rs.getInt("id_disciplina"));
                        disciplina.setDisciplina(rs.getString("disciplina"));
                        
                        final Formacao formacao = new Formacao();
                        formacao.setTitulo(rs.getString("titulo"));
                        formacao.setCurso(rs.getString("curso"));
                        formacao.setInstituicao(rs.getString("instituicao"));
                        formacao.setDiploma(rs.getString("diploma"));
                        formacao.setAnoInicio(rs.getInt("ano_inicio"));
                        formacao.setAnoTermino(rs.getInt("ano_termino"));

                        m1.put(disciplina.getId(), disciplina);
                        m2.put(formacao.getDiploma(), formacao);
                    }

                    Set<Integer> s1 = m1.keySet();
                    Set<String> s2 = m2.keySet();
                    
                    s1.stream().forEach((i) -> {
                        listaDisciplina.add(m1.get(i));
                    });
                    s2.stream().forEach((i) -> {
                        listaFormacao.add(m2.get(i));
                    });
                    
                    professor.setDisciplina(new ArrayList<>(listaDisciplina));
                    professor.setFormacao(new ArrayList<>(listaFormacao));
                    listaProfessor.add(professor);

                    return listaProfessor;
                }
            }
        } catch (Exception e) {}
        return null;
    }
}