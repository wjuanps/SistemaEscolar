/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.TurmaDAO;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Grade;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Turma;
import br.com.siseduc.view.TelaAlterarProfTurma;
import br.com.siseduc.view.TelaCadastroTurma;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class TurmaBO extends TurmaDAO {

    private static final String SERIE_FUNDAMENTAL[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String SERIE_MEDIO[] = {"1", "2", "3"};
    private static final String SERIE_EJA[] = {"5-6", "7-8"};
    private static final String TURNO[] = {"", "M", "T", "N"};
    private static final String MODALIDADE[] = {"", "F", "M", "E"};

    private static final int FUNDAMENTAL = 1;
    private static final int MEDIO = 2;
    private static final int EJA = 3;
    
    private final EscolaBO escolaBo = new EscolaBO();

    /**
     *
     * @param turma
     * @param usuario
     * @param professores
     * @param tipo
     * @return 
     */
    public boolean cadastrarTurma(Turma turma, String usuario, List<String> professores, int tipo) {
        if (turma instanceof Turma) {
            if (super.confirmarCadastroAtualizacao(this, tipo, turma, tipo, professores)) {
                final String acao = (tipo == Pessoa.CADASTRAR)
                        ? String.format("Turma %s cadastrada por", turma.getTurma())
                        : String.format("Turma %s atualizada por", turma.getTurma());
                Logger.logger(usuario, acao, "");
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param jcb
     */
    public void modalidade(JComboBox... jcb) {
        jcb[0].setSelectedIndex(0);
        jcb[1].removeAllItems();
        jcb[1].addItem("Escolha a Série");
        switch (jcb[2].getSelectedIndex()) {
            case 1:
                for (String serie : SERIE_FUNDAMENTAL) {
                    jcb[1].addItem(serie);
                }
                break;
            case 2:
                for (String serie : SERIE_MEDIO) {
                    jcb[1].addItem(serie);
                }
                break;
            case 3:
                for (String serie : SERIE_EJA) {
                    jcb[1].addItem(serie);
                }
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param jcb
     * @param txt
     * @throws SiseducException
     */
    public void descricao(JTextField txt, JComboBox... jcb) throws SiseducException {

        if (txt == null || jcb == null) {
            throw new SiseducException("Campo inválido");
        }

        if (jcb.length < 2) {
            throw new SiseducException("Argumentos inválidos");
        }

        if (jcb[0].getSelectedIndex() == 0) {
            txt.setText(null);
            jcb[1].setSelectedIndex(0);
            return;
        }

        if (jcb[1].getSelectedIndex() == 0) {
            txt.setText(null);
            return;
        }

        switch (jcb[0].getSelectedIndex()) {
            case FUNDAMENTAL:
                txt.setText(String.format("%sª Série | Fundamental", jcb[1].getSelectedItem().toString()));
                break;
            case MEDIO:
                txt.setText(String.format("%sº Ano | Médio", jcb[1].getSelectedItem().toString()));
                break;
            case EJA:
                txt.setText(String.format("%sª | EJA", jcb[1].getSelectedItem().toString()));
                break;
        }
    }

    /**
     *
     * @param serie
     * @param t
     * @param m
     * @return
     */
    public String gerarTurma(String serie, int t, int m) {

        String turma = null;
        try {
            final String ano = String.format("%tY", Calendar.getInstance());
            final String turno = TURNO[t];
            final String modalidade = MODALIDADE[m];
            
            turma = String.format("%s%s%s%s1", ano, turno, serie, modalidade);
            
            if (!super.getTurma().isEmpty()) {
                if (super.getTurma().contains(turma)) {
                    int aux = Integer.parseInt(turma.substring(turma.length() - 1, turma.length()));
                    turma = turma.substring(0, turma.length() - 1).concat(String.valueOf(++aux));
                }
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }

        return turma;
    }

    /**
     *
     * @param model
     * @param table
     */
    public void preencherTabelaTurma(DefaultTableModel model, JTable table) {
        this.preencherTabelaTurma(model, table, null);
    }

    /**
     *
     * @param model
     * @param table
     * @param turma
     */
    public void preencherTabelaTurma(DefaultTableModel model, JTable table, List<Turma> turma) {
        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return;
            }
            
            List<Turma> turmas;
            
            turmas = (turma == null)
                    ? super.getTurma(con, false)
                    : turma;
            
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
                
            if (!turmas.isEmpty()) {
                int linha = 0;
                for (Turma t : turmas) {
                    
                    model.addRow(new String[1]);
                    
                    table.setValueAt(t.getTurma(), linha, 0);
                    table.setValueAt(t.getSerie(), linha, 1);
                    table.setValueAt(t.getModalidade(), linha, 2);
                    table.setValueAt(t.getCountAluno(), linha, 3);
                    table.setValueAt(t.getCountProfessor(), linha, 4);
                    table.setValueAt(t.getStatus(), linha, 5);
                    
                    linha++;
                }
                table.revalidate();
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param tela
     * @param t
     */
    public void editarTurma(TelaCadastroTurma tela, String t) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        
        try {
            tela.getJcbModalidade().setEnabled(false);
            tela.getJcbSerie().setEnabled(false);
            tela.getJcbTurno().setEnabled(false);
            
            final Turma turma = super.getTurma(con, t, false).get(0);
            
            tela.getTxtTurma().setText(turma.getTurma());
            tela.getJcbModalidade().setSelectedItem(turma.getModalidade());
            
            tela.getJcbSerie().removeAllItems();
            tela.getJcbSerie().addItem("Escolha a Série");
            switch (tela.getJcbModalidade().getSelectedIndex()) {
                case FUNDAMENTAL:
                    for (String serie : SERIE_FUNDAMENTAL) {
                        tela.getJcbSerie().addItem(serie);
                    }
                    break;
                case MEDIO:
                    for (String serie : SERIE_MEDIO) {
                        tela.getJcbSerie().addItem(serie);
                    }
                    break;
                case EJA:
                    for (String serie : SERIE_EJA) {
                        tela.getJcbSerie().addItem(serie);
                    }
                    break;
                default:
                    break;
            }
            
            tela.getJcbSerie().setSelectedItem(turma.getSerie());
            
            tela.getTxtDescricaoSerie().setText(turma.getDescricaoSerie());
            tela.getJcbTurno().setSelectedItem(turma.getTurno());
            tela.getTxtDescricaoTurma().setText(turma.getDescricaoTurma());
            
            tela.getTxtDataMatricula().setText(PessoaBO.data(turma.getDataMatricula(), '-', '/'));
            tela.getTxtDataModificacao().setText(PessoaBO.data(turma.getDataModificacao(), '-', '/'));
            tela.getTxtDataInativarTurma().setText(PessoaBO.data(turma.getDataInativacao(), '-', '/'));
            tela.getTxtDataCancelamento().setText(PessoaBO.data(turma.getDataCancelamento(), '-', '/'));
            
            tela.getRbAtivaSim().setSelected(turma.isTurmaAtiva());
            tela.getRbAtivaNao().setSelected(!turma.isTurmaAtiva());
            
            tela.getRbCanceladaSim().setSelected(turma.isTurmaCancelada());
            tela.getRbCanceladaNao().setSelected(!turma.isTurmaCancelada());
            
            final List<String> professores = this.getProfessores(turma.getTurma());
            if (null != professores && !professores.isEmpty()) {
                int i = 0;
                for (String professor : professores) {
                    professores.set(i++, String.format("%d - %s - %s", i, professor.split("-")[1].trim(), professor.split("-")[2].trim()));
                }
                tela.getJlListaProfessores().setListData(PessoaBO.listaToVetor(professores));
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param order
     * @param turma
     * @return
     */
    public List<Turma> ordernarTurma(String order, String turma) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return (turma.equals(""))
                    ? super.getTurma(con, order)
                    : super.getTurma(con, order, turma);

        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
    
    /**
     * 
     * @param turma
     * @return 
     */
    public int getId(String turma) {
        int id = 0;
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return id;
        }
        
        try {
            return super.getId(con, turma);
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }        
        return 0;
    }
    
    /**
     * 
     * @param serie
     * @param modalidade
     * @return 
     */
    public String[] getGrade(int serie, String modalidade) throws NullPointerException {
        
        String file = null;
        
        if (null != modalidade) {
            switch (modalidade) {
                case Grade.MEDIO:
                    file = Grade.MEDIO_FILE;
                    break;
                case Grade.EJA:
                    file = Grade.EJA_FILE;
                    break;
                case Grade.FUNDAMENTAL:
                    if (serie > 0 && serie < 6) {
                        file = Grade.FUNDAMENTAL_1_FILE;
                    } else {
                        file = Grade.FUNDAMENTAL_2_FILE;
                    }
                    break;
                default:
                    break;
            }
        }
        
        String path = Arquivo.DIR_GRADE.concat(file);
        final List<Object> list = Arquivo.getObjects(path);
        
        String values[] = new String[list.size()];
        int i = 0;
        for (Object value : list) {
            values[i] = String.valueOf(i + 1 + " - ").concat((String) value);
            i++;
        }
        
        return values;
    }
    
    /**
     * 
     * @param turma
     * @return 
     */
    public List<String> getProfessores(String turma) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            final List<String> lista = super.getProfTurma(con, turma);
            if (!lista.isEmpty()) {
                return lista;
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }        
        return null;
    }
    
    /**
     * 
     * @return 
     */
    public boolean hasTurma() {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }
        
        try {
            final List<Turma> turma = super.getTurma(con);
            return (!turma.isEmpty());
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }  finally {
            Conexao.closeConnection(con);
        }      
        return false;
    }
    
    /**
     * 
     * @param listaProfessores
     * @param tela
     * @param index
     * @param turma
     * @param tipo
     * @return 
     */
    public boolean hasData(List<String> listaProfessores, TelaAlterarProfTurma tela, int index, String turma, int tipo) {        
        if (tipo == Turma.ATUALIZAR) {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return false;
            }
            try {
                final String disciplina = listaProfessores.get(index).split("-")[2].trim();
                final Disciplina d = (Disciplina) escolaBo.get(EscolaBO.DISCIPLINA, disciplina);

                final int idProfessor  = Integer.valueOf(listaProfessores.get(index).split("-")[0].trim());
                final int idDisciplina = d.getId();
                
                if (super.hasData(con, idProfessor, idDisciplina, turma)) {
                    final TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, "Não é possível excluir esse professor!\nVocê deseja substituí-lo:", TipoMensagem.MSG_QUESTION);
                    if (response == TipoMensagem.OPCAO_OK) {
                        
                        final List<String> lista = super.getProf(con, idProfessor, idDisciplina);
                        if (lista.isEmpty()) {
                            Siseduc.showMessage(Siseduc.TITULO, "Não existe outro professor habilitado para ministrar a seguinte disciplina.\n\n::" + disciplina, TipoMensagem.MSG_ERROR);
                            return true;
                        } else {
                            tela.getTxtProfessorAnterior().setText(listaProfessores.get(index).split("-")[1].trim());
                            tela.getTxtTurma().setText(turma);
                            tela.getTxtDisciplina().setText(disciplina);

                            for (String string : lista) {
                                tela.getJcbNovoProfessor().addItem(string);
                            }

                            tela.setVisible(true);
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            } catch (SQLException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            } finally {
                Conexao.closeConnection(con);
            }
        }        
        return false;
    }
    
    /**
     * 
     * @param idNovoProfessor
     * @param idProfessorAntigo
     * @param idDisciplina
     * @param turma 
     * @return  
     */
    public boolean alterarProfeTurma(int idNovoProfessor, int idProfessorAntigo, int idDisciplina, String turma) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }
        try {
            return (
                super.alterarProfeTurma(con, idNovoProfessor, idProfessorAntigo, idDisciplina, turma)
            );
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return false;
    }
}
