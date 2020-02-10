/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.LembreteDAO;
import br.com.siseduc.model.vo.Lembrete;
import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import com.toedter.calendar.JCalendar;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Sophia
 */
public class LembreteBO extends LembreteDAO {

    private Connection con = null;
    private JMenuItem[] itens;

    /**
     *
     * @param lembrete
     * @param tipo
     * @param idUsuario
     * @return
     */
    public boolean confirmarCadastro(Lembrete lembrete, int idUsuario, int tipo) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }

        try {
            return (super.cadastrarAtualizar(con, lembrete, idUsuario, tipo));
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), Mensagem.TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }

        return false;
    }

    /**
     *
     * @param lembrete
     * @return
     */
    public boolean validarCampos(Lembrete lembrete) {
        try {
            if (lembrete.getTitulo().isEmpty()) {
                throw new SiseducException("Informe um titulo.");
            }

            final String hora = lembrete.getData().split("\\s")[1];
            if (!hora.matches("\\d{2}:\\d{2}:\\d{2}")) {
                throw new SiseducException("Hora inválida.");
            }

            int i = 0;
            for (String h : hora.split(":")) {
                int aux = Integer.parseInt(h);
                if (i == 0 && aux > 24) {
                    throw new SiseducException("Hora inválida!!\nValor máximo para hora é 24.");
                } else if (aux > 60) {
                    throw new SiseducException("Minuto inválido!!\nValor máximo para minuto é 60.");
                }
                i++;
            }
        } catch (SiseducException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), Mensagem.TipoMensagem.MSG_ERROR);
            return false;
        }

        return (Siseduc.showMessage(
                Siseduc.TITULO,
                "Você tem certeza que deseja confirmar\nesse lembrete?",
                TipoMensagem.MSG_QUESTION) == TipoMensagem.OPCAO_OK);
    }

    public void meusLembretes(int idUsuario, JMenu menu) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        try {
            menu.removeAll();
            final List<Lembrete> lembretes = super.getLembretes(con, idUsuario);
            menu.setEnabled(!lembretes.isEmpty());
            if (!lembretes.isEmpty()) {
                int i = 0;
                itens = new JMenuItem[lembretes.size()];
                for (Lembrete lembrete : lembretes) {
                    final String data = lembrete.getData().replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3-$2-$1");
                    final String icon = String.format("/images/record-%s.png", (lembrete.isAtivo()) ? "ativo" : "inativo");
                    final ImageIcon image = new ImageIcon(getClass().getResource(icon));

                    itens[i] = new JMenuItem(String.format("%s %s %s", lembrete.getTitulo(), data, (lembrete.isAtivo()) ? "Ativo" : "Não Ativo"), image);
                    menu.add(itens[i++]);
                }
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), Mensagem.TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param idUsuario
     * @param menuExcluir
     * @param menuEditar
     * @param data
     */
    public void hasLembrete(int idUsuario, JMenuItem menuExcluir, JMenuItem menuEditar, String data) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }

        try {
            final boolean hasLembrete = super.hasLembrete(con, idUsuario, data);
            menuExcluir.setEnabled(hasLembrete);
            menuEditar.setEnabled(hasLembrete);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), Mensagem.TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param calendario
     * @param idUsuario
     */
    public void setIconLembretes(JCalendar calendario, int idUsuario) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }

        try {
            String data = String.format("%1$tY-%1$tm-%1$td", calendario.getDate());
            final Component[] dias = calendario.getDayChooser().getDayPanel().getComponents();
            for (Component dia : dias) {
                final JButton botao = (JButton) dia;
                botao.setIcon(null);
            }
            for (Component dia : dias) {
                final JButton botao = (JButton) dia;
                String d = botao.getText();
                if (d.matches("\\d{1,2}")) {
                    if (d.length() == 1) {
                        d = String.format("0%s", d);
                    }
                    
                    final String date = data.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$1-$2-" + d);
                    if (super.hasLembrete(con, idUsuario, date)) {
                        botao.setIcon(new ImageIcon(getClass().getResource("/images/document-text.png")));
                    }
                }
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), Mensagem.TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    public JMenuItem[] getItens() {
        return itens;
    }

    protected final class TempoRestante implements Runnable {

        @Override
        public void run() {

        }
    }
}
