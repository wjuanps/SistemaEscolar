/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Soares
 */
public class FuncionarioDAO extends PessoaDAO implements IFuncionarioDAO {

    /**
     * Lista com todos os funcionarios
     */
    private List<Usuario> listaFuncionario;

    /**
     * 
     * @param con
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Object ... obj) throws SQLException {
        
        Funcionario funcionario = (Funcionario) obj[0];
        Usuario usuario = (obj[1] != null) ? (Usuario) obj[1] : null;
        
        final String sql = ((int) obj[2] == Pessoa.CADASTRAR)
                ? SQL_CADASTRAR_FUNCIONARIO
                : SQL_ATULIZAR_FUNCIONARIO;
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
                        
            stm.setString(1, funcionario.getCargoFuncao());
            stm.setString(2, funcionario.getNome());
            stm.setString(3, funcionario.getSexo());
            stm.setString(4, funcionario.getFoto());
            stm.setString(5, funcionario.getEmail());
            stm.setString(6, funcionario.getTelefone());
            stm.setString(7, funcionario.getDataNascimento());
            stm.setString(8, funcionario.getNacionalidade());
            stm.setString(9, funcionario.getNaturalidade());
            stm.setString(10, funcionario.getUfNaturalidade());
            stm.setString(11, funcionario.getIdentidade());
            stm.setString(12, funcionario.getCpf());
            stm.setInt(13, funcionario.getCargaHoraria());
            stm.setString(14, funcionario.getEscolaridade());
            stm.setString(15, funcionario.getNumeroCtps());
            stm.setString(16, funcionario.getSerieCtps());
            stm.setString(17, funcionario.getDataEmissaoCarteira());
            stm.setString(18, funcionario.getNumeroPis());
            stm.setInt(19, funcionario.getDependentes());
            stm.setInt(20, (funcionario.isUsuario()) ? 1 : 0);
            stm.setString(21, funcionario.getCelular());
            
            stm.setString(22, funcionario.getEndereco());
            stm.setString(23, funcionario.getComplemento());
            stm.setString(24, funcionario.getBairro());
            stm.setString(25, funcionario.getCidade());
            stm.setString(26, funcionario.getUf());
            stm.setString(27, funcionario.getCep());
            
            stm.setString(28, (usuario != null) ? usuario.getUsuario() : null);
            stm.setString(29, (usuario != null) ? usuario.getSenha() : null);
            stm.setInt(30, (usuario != null) ? ((usuario.isUsuarioMaster()) ? 1 : 0) : -1);
            stm.setInt(31, (usuario != null) ? ((usuario.isCadastrarAtualizarAluno()) ? 1 : 0) : -1);
            stm.setInt(32, (usuario != null) ? ((usuario.isCadastrarAtualizarProfessor()) ? 1 : 0) : -1);
            stm.setInt(33, (usuario != null) ? ((usuario.isCadastrarAtualizarFuncionario()) ? 1 : 0) : -1);
            stm.setInt(34, (usuario != null) ? ((usuario.isCadastrarUsuario()) ? 1 : 0) : -1);
            stm.setInt(35, (usuario != null) ? ((usuario.isControleFinanceiro()) ? 1 : 0) : -1);
            stm.setInt(36, (usuario != null) ? ((usuario.isExcluirAluno()) ? 1 : 0) : -1);
            stm.setInt(37, (usuario != null) ? ((usuario.isExcluirProfessor()) ? 1 : 0) : -1);
            stm.setInt(38, (usuario != null) ? ((usuario.isExcluirUsuario()) ? 1 : 0) : -1);
            stm.setInt(39, (usuario != null) ? ((usuario.isEmitirRelatorios()) ? 1 : 0) : -1);
            stm.setInt(40, (usuario != null) ? ((usuario.isCadastrarAlterarTurma()) ? 1 : 0) : -1);
            stm.setInt(41, (usuario != null) ? ((usuario.isCadastrarAlterarDisciplina()) ? 1 : 0) : -1);
            stm.setInt(42, (usuario != null) ? ((usuario.isExcluirTurma()) ? 1 : 0) : -1);
            stm.setInt(43, (usuario != null) ? ((usuario.isExcluirDisciplina()) ? 1 : 0) : -1);
            stm.setInt(44, (usuario != null) ? ((usuario.isEmitirDocumentos()) ? 1 : 0) : -1);
            stm.setInt(45, (usuario != null) ? ((usuario.isCadastrarAtualizarBensPatrimoniais()) ? 1 : 0) : -1);
            stm.setInt(46, (usuario != null) ? ((usuario.isCadastrarAtualizarFornecedores()) ? 1 : 0) : -1);
            stm.setInt(47, (usuario != null) ? ((usuario.isExcluirBensPatrimoniais()) ? 1 : 0) : -1);
            stm.setInt(48, (usuario != null) ? ((usuario.isExcluirFornecedores()) ? 1 : 0) : -1);
            stm.setInt(49, (usuario != null) ? ((usuario.isExcluirFuncionario()) ? 1 : 0) : -1);
            
            if ((int) obj[2] == Pessoa.ATUALIZAR) {
                stm.setInt(50, Integer.parseInt(funcionario.getMatricula()));
            }
            
            stm.executeUpdate();
        }
        return true;
    }
   
    /**
     *
     * @param rs agawrgawrg
     * @return awrghawrgawr
     */
    private Usuario funcionario(ResultSet rs, int id) throws SQLException {
        final Usuario usuario = new Usuario();

        usuario.setFoto(rs.getString("foto"));

        usuario.setMatricula(PessoaBO.getCadastro(rs.getInt("id_funcionario")));
        usuario.setNome(rs.getString("nome"));
        usuario.setSexo(rs.getString("sexo"));
        usuario.setDataNascimento(PessoaBO.data(rs.getString("data_nascimento"), '-', '/'));
        usuario.setNacionalidade(rs.getString("nacionalidade"));
        usuario.setNaturalidade(rs.getString("naturalidade"));
        usuario.setUfNaturalidade(rs.getString("uf_naturalidade"));
        usuario.setIdentidade(rs.getString("identidade"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setCelular(rs.getString("celular"));
        usuario.setEmail(rs.getString("email"));
        usuario.setDataCadastro(PessoaBO.data(rs.getString("data_cadastro"), '-', '/'));
        
        usuario.setComplemento(rs.getString("complemento"));
        usuario.setEndereco(rs.getString("endereco"));
        usuario.setBairro(rs.getString("bairro"));
        usuario.setCidade(rs.getString("cidade"));
        usuario.setCep(rs.getString("cep"));
        usuario.setUf(rs.getString("uf"));

        usuario.setCargaHoraria(rs.getInt("carga_horaria"));

        usuario.setCargoFuncao(rs.getString("cargo_funcao"));
        usuario.setEscolaridade(rs.getString("escolaridade"));
        usuario.setNumeroCtps(rs.getString("numeroCTPS"));
        usuario.setSerieCtps(rs.getString("serieCTPS"));
        usuario.setDataEmissaoCarteira(PessoaBO.data(rs.getString("data_emissao"), '-', '/'));
        usuario.setNumeroPis(rs.getString("numeroPIS"));
        usuario.setDependentes(rs.getInt("dependentes"));

        usuario.setUsuario(rs.getInt("is_usuario") == 1);
        
        if (usuario.isUsuario() && id != 0) {
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setrSenha(rs.getString("senha"));
            usuario.setUsuarioMaster((rs.getInt("is_usuario_master")) == 1);

            usuario.setCadastrarAtualizarAluno((rs.getInt("is_cadastrar_atualizar_aluno")) == 1);
            usuario.setCadastrarAtualizarProfessor((rs.getInt("is_cadastrar_atualizar_professor")) == 1);
            usuario.setCadastrarAtualizarFuncionario((rs.getInt("is_cadastrar_atualizar_funcionario")) == 1);
            usuario.setCadastrarUsuario((rs.getInt("is_cadastrar_usuario")) == 1);
            usuario.setControleFinanceiro((rs.getInt("is_controle_financeiro")) == 1);
            usuario.setExcluirAluno((rs.getInt("is_excluir_aluno")) == 1);
            usuario.setExcluirProfessor((rs.getInt("is_excluir_professor")) == 1);
            usuario.setExcluirFuncionario((rs.getInt("is_excluir_funcionario")) == 1);
            usuario.setExcluirUsuario((rs.getInt("is_excluir_usuario")) == 1);
            usuario.setEmitirRelatorios((rs.getInt("is_emitir_relatorios")) == 1);
            usuario.setCadastrarAlterarTurma((rs.getInt("is_cadastrar_alterar_turma")) == 1);
            usuario.setCadastrarAlterarDisciplina((rs.getInt("is_cadastrar_alterar_disciplina")) == 1);
            usuario.setExcluirTurma((rs.getInt("is_excluir_turma")) == 1);
            usuario.setExcluirDisciplina((rs.getInt("is_excluir_disciplina")) == 1);
            usuario.setEmitirDocumentos((rs.getInt("is_emitir_documentos")) == 1);
            usuario.setCadastrarAtualizarFornecedores((rs.getInt("is_cadastrar_atualizar_fornecedores")) == 1);
            usuario.setCadastrarAtualizarBensPatrimoniais((rs.getInt("is_cadastrar_atualizar_bens_patrimoniais")) == 1);
            usuario.setExcluirFornecedores((rs.getInt("is_excluir_fornecedores")) == 1);
            usuario.setExcluirBensPatrimoniais((rs.getInt("is_excluir_bens_patrimoniais")) == 1);
        }
        return usuario;
    }

    /**
     *
     * @param con rgawrgwrgaweg
     * @return awgawgawrgwa
     * @throws SQLException dghgherghewr
     */
    @Override
    public List<Usuario> getFuncionario(Connection con) throws SQLException {
        return this.getFuncionario(con, 0);
    }
    
    /**
     * 
     * @param con ghegerge
     * @param id argaewrgerg
     * @return ergergerga
     * @throws SQLException  sertergegehge
     */
    @Override
    public List<Usuario> getFuncionario(Connection con, int id) throws SQLException {
        listaFuncionario = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_FUNCIONARIO)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listaFuncionario.add(funcionario(rs, id));
                    }
                    return listaFuncionario;
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param con sdhfzbndgnz
     * @param order zhbnzdnjdgtz
     * @return zhbzdfbstnhda
     * @throws SQLException  dhdfbhsethsahna
     */
    @Override
    public List<Usuario> getFuncionario(Connection con, String order) throws SQLException {
        return this.getFuncionario(con, order, null);
    }

    /**
     * 
     * @param con shfhesahsaebhesa
     * @param order asheashbsethesa
     * @param nome sethsetserth
     * @return sehsethserthshb
     * @throws SQLException  sthsebsrthste
     */
    @Override
    public List<Usuario> getFuncionario(Connection con, String order, String nome) throws SQLException {

        listaFuncionario = new ArrayList<>();

        final String innerJoin = "INNER JOIN cargo_funcao ON (funcionario.id_cargo_funcao = cargo_funcao.id_cargo_funcao) "
                               + "INNER JOIN endereco ON (funcionario.id_endereco = endereco.id_endereco)";
        final String sql = (nome == null)
                ? String.format("SELECT * FROM funcionario %s WHERE is_ativo = 1 ORDER BY %s DESC", innerJoin, order)
                : String.format("SELECT * FROM funcionario %s WHERE is_ativo = 1 AND nome LIKE %s ORDER BY %s DESC", innerJoin, "'%" + nome + "%'", order);

        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    listaFuncionario.add(funcionario(rs, 0));
                }
                return listaFuncionario;
            }
        }
        return null;
    }
}