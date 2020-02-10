/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.EscolaDAO;
import br.com.siseduc.model.dao.PessoaDAO;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.IGenerica;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UnknownFormatConversionException;
import javax.swing.JComboBox;

/**
 *
 * @author Juan Soares
 */
public class EscolaBO extends EscolaDAO {

    private Connection con = null;

    public static final String STR_DISCIPLINA = "disciplina";
    public static final String STR_CARGO_FUNCAO = "cargo_funcao";

    public static final int DISCIPLINA = 1;
    public static final int CARGO_FUNCAO = 2;
    public static final int DESCRICAO = 3;

    /**
     *
     * @param obejeto
     * @param usuario
     * @return
     */
    public boolean confirmarCadastro(Object obejeto, String usuario) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }
        
        try {
            if (obejeto instanceof Disciplina) {
                try {
                    return (super.cadastrarDisciplina(con, (Disciplina) obejeto, usuario) == 1);
                } catch (SQLException | SiseducException ex) {
                    Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                }
            } else if (obejeto instanceof IGenerica) {
                try {
                    return (super.cadastrarFuncao(con, (IGenerica<Funcionario>) obejeto, usuario) == 1);
                } catch (SQLException ex) {
                    Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                }
            }
        } finally {
            Conexao.closeConnection(con);
        }
        return false;
    }

    /**
     *
     * @param tabela
     * @param ativa
     * @return
     */
    public List<String> get(String tabela, boolean ativa) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        final List<String> lista = new ArrayList<>();

        try {
            if (tabela.equals(STR_DISCIPLINA)) {
                try {
                    final List<Disciplina> listaDisciplina = super.getDisciplina(con);
                    listaDisciplina.stream().forEach((disciplina) -> {
                        lista.add(disciplina.getDisciplina());
                    });
                } catch (SQLException ex) {}
            } else if (tabela.equals(STR_CARGO_FUNCAO)) {
                try {
                    final List<IGenerica<Funcionario>> listaFuncao = super.getFuncao(con, ativa);
                    listaFuncao.stream().forEach((funcao) -> {
                        lista.add(funcao.getInstance().getCargoFuncao());
                    });
                } catch (SQLException ex) {}
            }
        } finally {
            Conexao.closeConnection(con);
        }
        return lista;
    }

    /**
     *
     * @param tabela
     * @return
     */
    public List<String> get(String tabela) {
        return this.get(tabela, false);
    }

    /**
     *
     * @param opcao
     * @param opc
     * @return
     */
    public Object get(int opc, String... opcao) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }

        try {
            switch (opc) {
                case DISCIPLINA:
                    final Disciplina d = super.listaDisciplina(con, opcao[0]).get(0);
                    if (d instanceof Disciplina) {
                        return d;
                    }
                    break;
                case CARGO_FUNCAO:
                    final IGenerica<Funcionario> f = super.getFuncao(con, opcao[0]).get(0);
                    if (f instanceof IGenerica) {
                        return f;
                    }
                    break;
                case DESCRICAO:
                    final String desc = super.get(con, opcao[0], opcao[1], opcao[2]);
                    if (desc instanceof String) {
                        return desc;
                    }
                    break;
                default:
                    throw new SiseducException("Valor Inválido!!");
            }

        } catch (SQLException | SiseducException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }

    /**
     *
     * @param tabela
     * @return
     */
    public String getId(String tabela) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return PessoaBO.getCadastro(PessoaDAO.lastInsertId(con, tabela) + 1);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
    
    /**
     * 
     * @param usuario
     */
    public void mostrarNumContrato(String usuario) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        try {
            final String numContrato = super.getNumContrato(con);
            if (numContrato != null) {
                try {
                    final String dataHora = String.format("%1$td %2$s %1$tB %2$s %1$tY %1$tH:%1$tM %1$tp", Calendar.getInstance(), "de");
                    Siseduc.showMessage(Siseduc.TITULO, String.format("Número do Contrato: %s\n\n~::Usuário:...%s\n~::Data:.%s", numContrato, usuario, dataHora), TipoMensagem.MSG_INFORMATION);
                } catch (UnknownFormatConversionException u) {
                    Siseduc.showMessage(Siseduc.TITULO, u.getMessage(), TipoMensagem.MSG_ERROR);
                }
            } else {
                Siseduc.showMessage(Siseduc.TITULO, "Não foi possível recuperar o número do contrato.\nTente novamente mais tarde!", TipoMensagem.MSG_ERROR);
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }
    
    /**
     * 
     * @return 
     */
    public String mostrarNumContrato() {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        try {
            return (super.getNumContrato(con));
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return null;
    }
    
    /**
     * 
     * @param jcb
     * @param idProfessor 
     */
    public void listarDisciplinas(JComboBox jcb, int idProfessor) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        try {
           List<String> disciplinas = get(con, idProfessor);
           if (disciplinas.isEmpty()) {
               throw new SiseducException("Erro ao selecionar a disciplina.");
           }
           
           jcb.setEnabled(true);
           jcb.removeAllItems();
           jcb.addItem("Escolha a disciplina");
           disciplinas.stream().forEach((disciplina) -> {
                jcb.addItem(disciplina);
            });            
        } catch (SQLException | SiseducException e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }        
    }
    
    /**
     * 
     * @return 
     */
    public static String[] notas() {
        String[] notas = new String[102];
        notas[0] = "";
        double value = -0.1;
        
        for (int i = 1; i <= 101; i++) {
            notas[i] = new DecimalFormat("0.0").format(value += 0.1).replace(',', '.');
        }
        return notas;
    }
    
    /**
     * 
     * @return 
     */
    public boolean hasEscola() {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            Siseduc.showMessage(Siseduc.TITULO, "Não foi possivel fazer a conexão com o banco de dados.\nChame o suporte para resolver o problema.", TipoMensagem.MSG_ERROR);
            Siseduc.finalizarSistema();
        }
        
        try {
            return super.hasEscola(con);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        
        return false;
    }
    
    /**
     * 
     * @param escola 
     * @return  
     */
    public boolean cadastrarEscola(List<String> escola) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }
        try {
            if (super.cadastrarEscola(con, escola)) {
                Logger.logger("", "Escola " + escola.get(0) + " cadastrada", "");
                return true;
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return false;
    }
}
