/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.PessoaBO;
import static br.com.siseduc.model.dao.Statement.SQL_GET_FORNECEDOR;
import br.com.siseduc.model.vo.Fornecedor;
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
public abstract class FornecedorDAO extends PessoaDAO implements IFornecedorDAO {
    
    /**
     * Lista com os atributos dos fornecedores.
     */
    private List<Fornecedor> listaFornecedor;
    
    /**
     * 
     * @param con
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public boolean cadastrarAtualizar(Connection con, Object ... obj) throws SQLException {
        
        Fornecedor fornecedor = (Fornecedor) obj[0];
        final String sql = ((int) obj[1] == 1)
                ? SQL_NOVO_FORNECEDOR
                : SQL_UPDATE_FORNECEDOR;
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, fornecedor.getNomeFantasia());
            stm.setString(2, fornecedor.getTipo());
            stm.setString(3, fornecedor.getFoto());
            stm.setString(4, fornecedor.getRazaoSocial());
            stm.setString(5, PessoaBO.data(fornecedor.getDataFundacao(), '/', '-'));
            stm.setString(6, fornecedor.getSegmento());
            stm.setString(7, fornecedor.getInscricaoEstadual());
            stm.setString(8, fornecedor.getCnpj());
            stm.setString(9, fornecedor.getSite());
            stm.setString(10, fornecedor.getTelefone());
            stm.setString(11, fornecedor.getCelular());
            stm.setString(12, fornecedor.getEmail());
            
            stm.setString(13, fornecedor.getEndereco());
            stm.setString(14, fornecedor.getComplemento());
            stm.setString(15, fornecedor.getBairro());
            stm.setString(16, fornecedor.getCidade());
            stm.setString(17, fornecedor.getUf());
            stm.setString(18, fornecedor.getCep());
            
            if ((int) obj[1] == 2) {
                stm.setInt(19, Integer.parseInt(fornecedor.getMatricula()));
            }
            
            stm.executeUpdate(); 
        }
        return true;
    }
    
    /**
     *
     * @param rs agargawr
     * @return ahgaerghwrgw
     * @throws java.sql.SQLException hehawerhadzhg
     */
    public Fornecedor fornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setMatricula(String.valueOf(rs.getInt("id_fornecedor")));
        fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
        fornecedor.setTipo(rs.getString("tipo"));
        fornecedor.setFoto(rs.getString("logo"));
        fornecedor.setRazaoSocial(rs.getString("razao_social"));
        fornecedor.setDataFundacao(PessoaBO.data(rs.getString("data_fundacao"), '-', '/'));
        fornecedor.setSegmento(rs.getString("segmento"));
        fornecedor.setInscricaoEstadual(rs.getString("i_e"));
        fornecedor.setCnpj(rs.getString("cpf_cnpj"));
        fornecedor.setSite(rs.getString("site"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setCelular(rs.getString("celular"));
        fornecedor.setEmail(rs.getString("email"));

        fornecedor.setEndereco(rs.getString("endereco"));
        fornecedor.setComplemento(rs.getString("complemento"));
        fornecedor.setBairro(rs.getString("bairro"));
        fornecedor.setCidade(rs.getString("cidade"));
        fornecedor.setUf(rs.getString("uf"));
        fornecedor.setCep(rs.getString("cep"));

        return fornecedor;
    }
    
    /**
     *
     * @param con dhsdehsethse
     * @return dhedhset
     * @throws SQLException aehethsehres
     */
    @Override
    public List<Fornecedor> getFornecedor(Connection con) throws SQLException {
        return this.getFornecedor(con, null, null);
    }
    
    /**
     *
     * @param con sthesthsrthes
     * @param order sdhetherthe
     * @return shsethsehesth
     * @throws SQLException sehetyhhet
     */
    @Override
    public List<Fornecedor> getFornecedor(Connection con, String order) throws SQLException {
        return this.getFornecedor(con, null, order);
    }

    /**
     *
     * @param con hjsthjsrt
     * @param nome thsthsth
     * @param order dfhjsthsrt
     * @return hdrnhgnrf
     * @throws SQLException sthrtret
     */
    @Override
    public List<Fornecedor> getFornecedor(Connection con, String nome, String order) throws SQLException {

        listaFornecedor = new ArrayList<>();
        final String sql = (nome == null && order == null)
                ? SQL_GET_FORNECEDOR
                : (nome == null)
                        ? String.format("SELECT * FROM fornecedor f INNER JOIN endereco e ON (e.id_endereco = f.id_endereco) ORDER BY %s", order)
                        : String.format("SELECT * FROM fornecedor f INNER JOIN endereco e ON (e.id_endereco = f.id_endereco) WHERE nome_fantasia LIKE %s ORDER BY %s", "'%".concat(nome).concat("%'"), order);

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        listaFornecedor.add(fornecedor(rs));
                    }
                    return listaFornecedor;
                }
            }
        }
        return null;
    }
    
    /**
     * 
     * @param con dsghaeghaewr
     * @param id asfgbrw
     * @return sgasawergw
     * @throws SQLException  argagre
     */
    @Override
    public Fornecedor getFornecedor(Connection con, int id) throws SQLException {        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_FORNECEDOR2)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return fornecedor(rs);
                }
            }
        }        
        return null;
    }
}