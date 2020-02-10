/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.FuncionarioDAO;
import br.com.siseduc.model.vo.Funcionario;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Usuario;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class FuncionarioBO extends FuncionarioDAO {

    /**
     * 
     */
    private final UsuarioBO usuarioBO = new UsuarioBO();
    /**
     * 
     */
    private final StringBuilder dados = new StringBuilder();

    /**
     *
     * @param funcionario
     * @param usuario
     * @param user
     * @param tipo
     * @return
     */
    public boolean confirmarCadastro(Funcionario funcionario, Usuario usuario, String user, int tipo) {
        try {
            if (usuario != null) {
                if (tipo == 1 && usuarioBO.isUsuario(usuario.getUsuario())) {
                    throw new SiseducException("Usuário já existe.");
                } else if (!usuarioBO.confirmarSenha(usuario.getSenha(), usuario.getrSenha())) {
                    throw new SiseducException("Senhas são diferentes.");
                }
            }

            if (super.confirmarCadastroAtualizacao(this, tipo, funcionario, usuario, tipo)) {
                String acao = (tipo == Pessoa.CADASTRAR)
                        ? String.format("Funcionário(a) %s cadastrado(a) por:", funcionario.getNome())
                        : String.format("Cadastrado do(a) funcionário(a) %s atualizado por:", funcionario.getNome());

                Logger.logger(user, acao, "");
                return true;
            }
        } catch (SiseducException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            return false;
        }
        return false;
    }

    /**
     *
     * @param funcionario
     * @param user
     * @param tipo
     * @return
     */
    public boolean confirmarCadastro(Funcionario funcionario, String user, int tipo) {
        return this.confirmarCadastro(funcionario, null, user, tipo);
    }

    /**
     *
     * @param table
     * @param model
     * @param listaFuncionario
     * @return
     */
    public boolean preencherTabela(JTable table, DefaultTableModel model, List<Usuario> listaFuncionario) {

        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return false;
            }

            List<Usuario> lista = (listaFuncionario == null)
                    ? super.getFuncionario(con)
                    : listaFuncionario;

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            if (!lista.isEmpty()) {

                int linha = 0;
                for (Funcionario funcionario : lista) {
                    model.addRow(new String[1]);

                    table.setValueAt(funcionario.getMatricula(), linha, 0);
                    table.setValueAt(funcionario.getNome(), linha, 1);
                    table.setValueAt(funcionario.getCargoFuncao(), linha, 2);
                    table.setValueAt(funcionario.getTelefone(), linha, 3);
                    table.setValueAt(funcionario.getEmail(), linha, 4);

                    linha++;
                }

                table.revalidate();
                return true;
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return false;
    }

    /**
     *
     * @param table
     * @param model
     * @return
     */
    public boolean preencherTabela(JTable table, DefaultTableModel model) {
        return this.preencherTabela(table, model, null);
    }

    /**
     * 
     * @param order
     * @param nome
     * @return 
     */
    public List<Usuario> ordernarFuncionario(String order, String nome) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return (nome.isEmpty())
                    ? super.getFuncionario(con, order)
                    : super.getFuncionario(con, order, nome);
            
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }

    /**
     *
     * @param funcionario
     * @param usuario
     * @return
     */
    public StringBuilder dadosFuncionario(Funcionario funcionario, Usuario usuario) {

        dados.delete(0, dados.length());

        dados.append("\n\t---- Confirmar informações Funcionário ----")
                .append("\n    Nome:\t  ".concat(funcionario.getNome()))
                .append("\n    Sexo:\t  ".concat(funcionario.getSexo()))
                .append("\n    Função:  ".concat(funcionario.getCargoFuncao()));

        if (funcionario.isUsuario()) {
            dados.append("\n\n\t---- Confirmar informações Usuário ----")
                    .append("\n    Usuário: ".concat(usuario.getUsuario()))
                    .append("\n\n\t---- Privilégios ----");
            if (usuario.isUsuarioMaster()) {
                dados.append("\n    Usuário máster: Sim");
            } else {
                dados.append("\n    Usuário máster: \t\t\tNão\n")
                        .append("\n    Cadastrar/Atualizar Aluno:\t\t".concat((usuario.isCadastrarAtualizarAluno()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Professor:\t".concat((usuario.isCadastrarAtualizarProfessor()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Funcionário:\t".concat((usuario.isCadastrarAtualizarFuncionario()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar Usuário:\t\t\t".concat((usuario.isCadastrarUsuario()) ? "Sim" : "Não"))
                        .append("\n    Controle Fincanceiro:\t\t".concat((usuario.isControleFinanceiro()) ? "Sim" : "Não"))
                        .append("\n    Excluir Aluno:\t\t\t".concat((usuario.isExcluirAluno()) ? "Sim" : "Não"))
                        .append("\n    Excluir Professor:\t\t\t".concat((usuario.isExcluirProfessor()) ? "Sim" : "Não"))
                        .append("\n    Excluir Funcionário:\t\t".concat((usuario.isExcluirFuncionario()) ? "Sim" : "Não"))
                        .append("\n    Excluir Usuário:\t\t\t".concat((usuario.isExcluirUsuario()) ? "Sim" : "Não"))
                        .append("\n    Emitir Relatórios:\t\t\t".concat((usuario.isEmitirRelatorios()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Turma:\t\t".concat((usuario.isCadastrarAlterarTurma()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Disciplina:\t".concat((usuario.isCadastrarAlterarDisciplina()) ? "Sim" : "Não"))
                        .append("\n    Excluir Turma:\t\t\t".concat((usuario.isExcluirTurma()) ? "Sim" : "Não"))
                        .append("\n    Excluir Disciplina:\t\t\t".concat((usuario.isExcluirDisciplina()) ? "Sim" : "Não"))
                        .append("\n    Emitir Documentos:\t\t".concat((usuario.isEmitirDocumentos()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Fornecedor:\t".concat((usuario.isCadastrarAtualizarFornecedores()) ? "Sim" : "Não"))
                        .append("\n    Cadastrar/Atualizar Bens Patrimoniais:   ".concat((usuario.isCadastrarAtualizarBensPatrimoniais()) ? "Sim" : "Não"))
                        .append("\n    Excluir Fornecedor:\t\t".concat((usuario.isExcluirFornecedores()) ? "Sim" : "Não"))
                        .append("\n    Excluir Bens Patrimoniais:\t\t".concat((usuario.isExcluirBensPatrimoniais()) ? "Sim" : "Não"));
            }
        }

        dados.append("\n\n    ----------------------------------------------")
                .append("\n    ---")
                .append("\n    --- Finalizado em: ".concat(String.format("%1$tA, %1$td de %1$tB de %1$tY %1$tI:%1$tM %1$tp", Calendar.getInstance())))
                .append("\n    ---");

        return dados;
    }
    
    /**
     * 
     * @param matricula
     * @return 
     */
    public Usuario getFuncionario(int matricula) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            final Usuario usuario = super.getFuncionario(con, matricula).get(0);
            return (usuario != null) ? usuario : null;
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
}