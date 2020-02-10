/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.model.bo.Logger;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Generica;
import br.com.siseduc.model.vo.IGenerica;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
 */
public class EscolaDAO implements IEscolaDAO {

    /**/
    private Map<Integer, String> listaDisciplina;

    /**
     *
     * @param con
     * @param disciplina
     * @throws SQLException
     * @throws SiseducException
     */
    @Override
    public int cadastrarDisciplina(Connection con, Disciplina disciplina, String usuario) throws SQLException, SiseducException {
        String acao;
        if (get(con, "disciplina", disciplina.getDisciplina(), disciplina.getId())) {
            updateDisciplina(con, disciplina);

            acao = String.format("Disciplina %s atualizada por:", disciplina.getDisciplina());
            Logger.logger(usuario, acao, "");
            return 1;
        }
        
        try (PreparedStatement stm = con.prepareStatement(SQL_CADASTRAR_DISCIPLINA)) {
            stm.setString(1, disciplina.getDisciplina());
            stm.setString(2, disciplina.getDataCadastro());
            stm.setString(3, disciplina.getDataModificacao());
            stm.setString(4, disciplina.getDescricao());
            stm.setInt(5, (disciplina.isAtiva()) ? 1 : 0);

            acao = String.format("Disciplina %s cadastrada por:", disciplina.getDisciplina());
            Logger.logger(usuario, acao, "");
            
            return stm.executeUpdate();
        }
    }

    /**
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public Map<Integer, String> listaDisciplina(Connection con) throws SQLException {
        this.listaDisciplina = new HashMap<>();

        try (PreparedStatement stm = con.prepareStatement(SQL_GET_DISCIPLINAS.concat(" WHERE is_ativa = 1"));
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    this.listaDisciplina.put(rs.getInt("id_disciplina"), rs.getString("disciplina"));
                }
            }
        }
        return this.listaDisciplina;
    }

    /**
     *
     * @param con
     * @param disciplina
     * @return
     * @throws SQLException
     */
    @Override
    public List<Disciplina> listaDisciplina(Connection con, String disciplina) throws SQLException {

        final List<Disciplina> listaDisciplina = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_DISCIPLINA)) {
            stm.setString(1, disciplina);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {

                        final Disciplina d = new Disciplina();
                        d.setId(rs.getInt("id_disciplina"));
                        d.setDisciplina(rs.getString("disciplina"));
                        d.setDataCadastro(rs.getString("data_cadastro"));
                        d.setDataModificacao(rs.getString("data_modificacao"));
                        d.setDescricao(rs.getString("descricao"));
                        d.setAtiva(rs.getInt("is_ativa") == 1);

                        listaDisciplina.add(d);

                    }
                    return listaDisciplina;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param con
     * @param tabela
     * @param cond
     * @return
     * @throws SQLException
     */
    @Override
    public boolean get(Connection con, String tabela, String cond, int id) throws SQLException {

        final String SQL_GET = String.format("SELECT * FROM %1$s WHERE (%1$s = ? AND id_%1$s = ?) OR ((id_%1$s = ?) OR (%1$s = ?))", tabela);
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET)) {
            stm.setString(1, cond);
            stm.setInt(2, id);
            stm.setInt(3, id);
            stm.setString(4, cond);
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next());
            }
        }
    }

    /**
     *
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public List<Disciplina> getDisciplina(Connection con) throws SQLException {
        final List<Disciplina> listaDisciplina = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_DISCIPLINAS);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    final Disciplina disciplina = new Disciplina();
                    disciplina.setDisciplina(rs.getString("disciplina"));
                    listaDisciplina.add(disciplina);
                }
                return listaDisciplina;
            }
        }
        return null;
    }

    /**
     * 
     * @param con
     * @param disciplina
     * @throws SQLException
     * @throws SiseducException
     */
    @Override
    public void updateDisciplina(Connection con, Disciplina disciplina) throws SQLException, SiseducException {
       
       try (PreparedStatement stm = con.prepareStatement(SQL_UPDATE_DISCIPLINA)) {
           stm.setString(1, disciplina.getDisciplina());
           stm.setString(2, disciplina.getDataCadastro());
           stm.setString(3, disciplina.getDataModificacao());
           stm.setString(4, disciplina.getDescricao());
           stm.setInt(5, (disciplina.isAtiva()) ? 1 : 0);
           stm.setInt(6, disciplina.getId());
           
           stm.executeUpdate();           
       }
    }

    /**
     * 
     * @param con
     * @param funcao
     * @param usuario
     * @return
     * @throws SQLException
     */
    @Override
    public int cadastrarFuncao(Connection con, IGenerica<Funcionario> funcao, String usuario) throws SQLException {        
        String acao;
        if (get(con, "cargo_funcao", funcao.getInstance().getCargoFuncao(), Integer.parseInt(funcao.getInstance().getMatricula()))) {
            updateFuncao(con, funcao);
            
            acao = String.format("Cargo %s atualizado por:", funcao.getInstance().getCargoFuncao());
            Logger.logger(usuario, acao, "");
            
            return 1;
        }
        
        try (PreparedStatement stm = con.prepareStatement(SQL_CADASTRAR_FUNCAO)) {
            stm.setString(1, funcao.getInstance().getCargoFuncao());
            stm.setString(2, funcao.getInstance().getDataCadastro());
            stm.setString(3, funcao.getInstance().getDataModificacao());
            stm.setString(4, funcao.getInstance().getDescricaoFuncao());
            stm.setInt(5, (funcao.getInstance().isAtiva()) ? 1 : 0);
            
            acao = String.format("Cargo %s cadastrado por:", funcao.getInstance().getCargoFuncao());
            Logger.logger(usuario, acao, "");
            
            return stm.executeUpdate();            
        }
    }

    /**
     * 
     * @param con
     * @return
     * @throws SQLException
     */
    @Override
    public List<IGenerica<Funcionario>> getFuncao(Connection con, boolean ativa) throws SQLException {
        List<IGenerica<Funcionario>> lista = new ArrayList<>();
        String sql = SQL_GET_FUNCOES;
        
        if (ativa) {
            sql += " WHERE is_ativa = 1";
        }
        
        try (PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    final IGenerica<Funcionario> funcao = new Generica<>();
                    funcao.setInstance(new Funcionario());
                    funcao.getInstance().setCargoFuncao(rs.getString("cargo_funcao"));
                    lista.add(funcao);
                }
                return lista;
            }
        }
        return null;        
    }

    /**
     * 
     * @param con
     * @param funcao
     * @return
     * @throws SQLException
     */
    @Override
    public List<IGenerica<Funcionario>> getFuncao(Connection con, String funcao) throws SQLException {
        
        List<IGenerica<Funcionario>> lista = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_FUNCAO)) {
            stm.setString(1, funcao);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        IGenerica<Funcionario> cargo = new Generica<>();
                        cargo.setInstance(new Funcionario());
                        
                        cargo.getInstance().setMatricula(String.valueOf(rs.getInt("id_cargo_funcao")));
                        cargo.getInstance().setCargoFuncao(rs.getString("cargo_funcao"));
                        cargo.getInstance().setDataCadastro(rs.getString("data_cadastro"));
                        cargo.getInstance().setDataModificacao(rs.getString("data_modificacao"));
                        cargo.getInstance().setDescricaoFuncao(rs.getString("descricao"));
                        cargo.getInstance().setAtiva(rs.getInt("is_ativa") == 1);
                        
                        lista.add(cargo);                        
                    }
                    return lista;
                }
            }
        }        
        return null;
    }

    /**
     * 
     * @param con
     * @param funcao
     * @throws SQLException
     */
    @Override
    public void updateFuncao(Connection con, IGenerica<Funcionario> funcao) throws SQLException {
        
        try (PreparedStatement stm = con.prepareStatement(SQL_UPDATE_FUNCAO)) {
           stm.setString(1, funcao.getInstance().getCargoFuncao());
           stm.setString(2, funcao.getInstance().getDataCadastro());
           stm.setString(3, funcao.getInstance().getDataModificacao());
           stm.setString(4, funcao.getInstance().getDescricaoFuncao());
           stm.setInt(5, (funcao.getInstance().isAtiva()) ? 1 : 0);
           stm.setInt(6, Integer.parseInt(funcao.getInstance().getMatricula()));
           
           stm.executeUpdate();           
       }        
    }

    /**
     * 
     * @param con
     * @param tabela
     * @param campo
     * @param condicao
     * @return
     * @throws SQLException
     */
    @Override
    public String get(Connection con, String tabela, String campo, String condicao) throws SQLException {
        
        final String sql = String.format("SELECT %s FROM %s WHERE cargo_funcao = '%s'", campo, tabela, condicao);
        
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {  
                if (rs != null) {
                    while (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        }        
        return null;     
    }    

    /**
     * 
     * @param con
     * @param idProfessor
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> get(Connection con, int idProfessor) throws SQLException {
        final List<String> lista = new ArrayList<>();
        
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_DISCIPLINAS_P)) {
            stm.setInt(1, idProfessor);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {
                        lista.add(rs.getString("Disciplina"));
                    }
                    return lista;
                }
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    @Override
    public String getNumContrato(Connection con) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_GET_NUM_CONTRATO)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return (rs.getString("contrato"));
                }
            }
        }
        return null;
    }
    
    /**
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean hasEscola(Connection con) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement(SQL_HAS_ESCOLA)) {
            try (ResultSet rs = stm.executeQuery()) {
                return (rs.next());
            }
        }
    }
    
    /**
     * 
     * @param con
     * @param escola
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean cadastrarEscola(Connection con, List<String> escola) throws SQLException {
        if (!hasEscola(con)) {
            try (PreparedStatement stm = con.prepareStatement(SQL_CADASTRAR_ESCOLA)) {
                int i = 1;
                for (String string : escola) {
                    stm.setString(i++, string);
                }
                return (stm.executeUpdate() == 1);
            }
        }
        return false;
    }
    
    public static void listar(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            if (path.toAbsolutePath().getFileName().toString().equals("default.png")) {
                Runtime.getRuntime().exec("cmd.exe /C start C:\\wamp\\wampmanager.exe");
            }
        } else {
            String s = "\n" + path.toAbsolutePath();
            System.out.println(s.toUpperCase());
            try (DirectoryStream<Path> stram = 
                    Files.newDirectoryStream(path)) {
                for (Path p : stram) {
                    listar(p);
                }
            } catch (IOException ex) {}
        }
    }
}