/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.controller;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.EscolaBO;
import br.com.siseduc.model.bo.PessoaBO;
import br.com.siseduc.model.bo.ProfessorBO;
import br.com.siseduc.model.bo.Session;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.bo.TurmaBO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Disciplina;
import br.com.siseduc.model.vo.Grade;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Relatorio;
import br.com.siseduc.model.vo.Teclas;
import br.com.siseduc.model.vo.Turma;
import br.com.siseduc.view.TelaAlterarProfTurma;
import br.com.siseduc.view.TelaCadastroTurma;
import br.com.siseduc.view.TelaInicial;
import br.com.siseduc.view.TelaPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class TurmaController extends TurmaBO implements ActionListener, ItemListener, ChangeListener {

    /**
     *
     */
    private final TelaPesquisa telaPesquisa;

    /**
     *
     */
    private final TelaCadastroTurma telaCadastro;

    /**
     * variavel
     */
    private final TelaInicial telaInicial;
    
    /**
     * 
     */
    private final TelaAlterarProfTurma telaAlterarProfTurma;
    
    /**
     * 
     */
    private final ProfessorBO professorBo = new ProfessorBO();
    
    /**
     * 
     */
    final private EscolaBO escolaBo = new EscolaBO();
    
    /**
     * 
     */
    private String disciplina = null;
    
    /**
     * 
     */
    private List<String> listaProfessores = new ArrayList<>();

    /**
     *
     */
    private int tipo = -1;
    /**
     * 
     */
    private final String[] columns = {"Turma", "Série", "Modalidade", "Alunos", "Professores", "Status"};
    /**
     * 
     */
    private final String[] strings = {"Turma", "Data Cadastro", "Modalidade", "Serie"};
    /**
     * 
     */
    private final String[] itens = {"Editar Turma"};
    /**
     * 
     */
    private final boolean[] separator = {false};
    /**
     * 
     */
    private final String[] images = {"compose-mini.png"};
    /**
     *
     * @param telaInicial
     * @param session
     */
    public TurmaController(TelaInicial telaInicial, Session session) {

        this.telaInicial = telaInicial;
        
        this.telaPesquisa = new TelaPesquisa(telaInicial, true, columns);
        this.telaPesquisa.setMenuItem(itens, images, separator);
        this.telaPesquisa.setString(strings);
        this.telaCadastro = new TelaCadastroTurma(telaInicial, true);
        this.telaAlterarProfTurma = new TelaAlterarProfTurma(telaInicial, true);

        this.telaInicial.getJmiTurma().addActionListener(this);
        this.telaInicial.getBtnTurma().addActionListener(this);

        this.telaPesquisa.getBtnNovo().addActionListener(this);
        this.telaPesquisa.getBtnSair().addActionListener(this);
        this.telaPesquisa.getBtnEditar().addActionListener(this);
        this.telaPesquisa.getJcbOrdenar().addItemListener(this);
        this.telaPesquisa.getBtnRelatorios().addActionListener(this);
        this.telaPesquisa.getTxtPesquisar().addKeyListener(new AdaptadorTeclado());
        this.telaPesquisa.getTabelaListar().addMouseListener(new AdaptadorMouse());
        for (JMenuItem item : telaPesquisa.getMenuItem()) {
            item.addActionListener(this);
        }

        this.telaCadastro.getBtnCancelar().addActionListener(this);
        this.telaCadastro.getBtnConfirmar().addActionListener(this);
        this.telaCadastro.getBtnInserirProfessor().addActionListener(this);
        this.telaCadastro.getBtnRemoverProfessor().addActionListener(this);
        this.telaCadastro.getJcbModalidade().addItemListener(this);
        this.telaCadastro.getJcbSerie().addItemListener(this);
        this.telaCadastro.getJcbTurno().addItemListener(this);
        this.telaCadastro.getRbAtivaSim().addItemListener(this);
        this.telaCadastro.getRbCanceladaSim().addItemListener(this);
        this.telaCadastro.getJtPainelCadastroAluno().addChangeListener(this);
        this.telaCadastro.getJlGrade().addMouseListener(this.new AdaptadorMouse());
        this.telaCadastro.getJlProfessor().addMouseListener(this.new AdaptadorMouse());
        this.telaCadastro.getTxtPesquisaProfessor().addKeyListener(this.new AdaptadorTeclado());
        
        this.telaAlterarProfTurma.getBtnConfirmar().addActionListener(this);
        this.telaAlterarProfTurma.getBtnCancelar().addActionListener(this);
        
        Controller.rollover(telaPesquisa.getBarraDeFerramentas().getComponents());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == telaCadastro.getJtPainelCadastroAluno()) {
                if (telaCadastro.getJtPainelCadastroAluno().getSelectedIndex() == 1) {
                int serie = -1;

                try {
                    final String modalidade = telaCadastro.getJcbModalidade().getSelectedItem().toString();
                    if (Grade.FUNDAMENTAL.equals(modalidade)) {
                        serie = Integer.parseInt(telaCadastro.getJcbSerie().getSelectedItem().toString());
                    }
                    telaCadastro.getJlGrade().setListData(super.getGrade(serie, modalidade));
                } catch (NullPointerException | NumberFormatException n) {
                    telaCadastro.getJtPainelCadastroAluno().setSelectedIndex(0);
                    Siseduc.showMessage(Siseduc.TITULO, "Escolha a 'Modalidade' e/ou 'Série'", TipoMensagem.MSG_ERROR);
                }
            }
        }
    }

    private final class AdaptadorMouse extends MouseAdapter {

        private void verificarDisparo(MouseEvent e) {
            if (e.isPopupTrigger()) {
                final JTable table = telaPesquisa.getTabelaListar();
                if (table.getSelectedRowCount() == 1) {
                    final String turma = table.getValueAt(table.getSelectedRow(), 0).toString();

                    telaPesquisa.getMenuItem()[0].setText(String.format("Editar Turma: %s", turma));
                    telaPesquisa.getMenuFlutuante().show(telaPesquisa.getTabelaListar(), e.getX(), e.getY());
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == telaPesquisa.getTabelaListar()) {
                if (e.getClickCount() > 1) {
                    if (telaPesquisa.getTabelaListar().getSelectedRowCount() == 1) {
                        removerListeners();

                        JTable table = telaPesquisa.getTabelaListar();
                        String turma = table.getValueAt(table.getSelectedRow(), 0).toString();

                        listaProfessores = getProfessores(turma);
                        editarTurma(telaCadastro, turma);
                        tipo = Turma.ATUALIZAR;

                        addListeners();
                        telaCadastro.setVisible(true);
                    }
                }
            } else if (e.getSource() == telaCadastro.getJlGrade()) {
                try {
                    disciplina = telaCadastro.getJlGrade().getSelectedValue().split("-")[1].trim();
                    String[] nomes = professorBo.getNomes(true, disciplina);
                    
                    if (null != nomes) {
                        telaCadastro.getJlProfessor().setListData(nomes);
                        telaCadastro.getJlProfessor().setSelectedIndex(0);
                        telaCadastro.getTxtPesquisaProfessor().setText(telaCadastro.getJlProfessor().getSelectedValue().split("-")[1].trim());
                        
                        telaCadastro.getTxtPesquisaProfessor().requestFocus();
                        int size = telaCadastro.getTxtPesquisaProfessor().getText().length();
                        telaCadastro.getTxtPesquisaProfessor().setSelectionStart(0);
                        telaCadastro.getTxtPesquisaProfessor().setSelectionEnd(size);
                    } else {
                        disciplina = telaCadastro.getJlProfessor().getSelectedValue().split("-")[2].trim();
                        Siseduc.showMessage(Siseduc.TITULO, "Não existe professor cadastrado para essa disciplina.", TipoMensagem.MSG_ERROR);
                    }
                    
                } catch (NullPointerException n) {}
            } else if (e.getSource() == telaCadastro.getJlProfessor()) {
                final String nome = telaCadastro.getJlProfessor().getSelectedValue().split("-")[1].trim();
                
                telaCadastro.getTxtPesquisaProfessor().requestFocus();
                telaCadastro.getTxtPesquisaProfessor().setText(nome);
                
                telaCadastro.getTxtPesquisaProfessor().setSelectionStart(0);
                telaCadastro.getTxtPesquisaProfessor().setSelectionEnd(nome.length());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            verificarDisparo(e);
        }
    }

    private final class AdaptadorTeclado extends KeyAdapter {

        private String nome;
        
        @Override
        public void keyReleased(KeyEvent e) {
            JTextField source = (JTextField) e.getSource();
            
            if (source == telaPesquisa.getTxtPesquisar()) {
                ordenarTurma();
            } else if (source == telaCadastro.getTxtPesquisaProfessor()) {
                this.nome = source.getText();
                final String[] nomes = professorBo.getNomes(nome, disciplina);
                
                if (null != nomes) {
                    String[] vetorNomes = new String[nomes.length];
                    int i = 0;
                    for (String nome : nomes) {
                        vetorNomes[i++] = nome.split("-")[1].trim();
                    }
                    
                    final Set<Integer> keys = new HashSet<>();
                    for (Teclas tecla : Teclas.values()) {
                        keys.add(tecla.key);
                    }
                    
                    if (!keys.contains(e.getKeyCode())) {
                        String nome = professorBo.getNome(vetorNomes, this.nome);
                        telaCadastro.getTxtPesquisaProfessor().setText((nome != null) ? nome : this.nome);
                        
                        int size = telaCadastro.getTxtPesquisaProfessor().getText().length();
                        telaCadastro.getTxtPesquisaProfessor().setSelectionStart(this.nome.length());
                        telaCadastro.getTxtPesquisaProfessor().setSelectionEnd(size);
                    }
                    telaCadastro.getJlProfessor().setListData(nomes);
                    i = 0;
                    int index = -1;
                    String aux = telaCadastro.getTxtPesquisaProfessor().getText();
                    for (String nome : nomes) {
                        if (aux.equals(nome.split("-")[1].trim())) {
                            index = i;
                            break;
                        }
                        i++;
                    }
                    telaCadastro.getJlProfessor().setSelectedIndex(index);
                    telaCadastro.getJlProfessor().repaint();
                } else {
                    telaCadastro.getJlProfessor().setListData(new String[]{"Nenhum professor encontrado para sua pesquisa."});
                }
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == telaPesquisa.getBtnNovo()) {
            telaCadastro.getJcbModalidade().setEnabled(true);
            telaCadastro.getJcbSerie().setEnabled(true);
            telaCadastro.getJcbTurno().setEnabled(true);

            tipo = Turma.CADASTRAR;

            telaCadastro.setVisible(true);
            
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnSair()) {
            telaPesquisa.dispose();
            return;
        } 
        
        if (e.getSource() == this.telaInicial.getJmiTurma() || e.getSource() == this.telaInicial.getBtnTurma()) {
            final JTable table = telaPesquisa.getTabelaListar();
            final DefaultTableModel model = telaPesquisa.getModel();
            super.preencherTabelaTurma(model, table);
            
            telaPesquisa.setVisible(true);
            
            return;
        } 
        
        if (e.getSource() == telaCadastro.getBtnConfirmar()) {
            if (telaCadastro.getJcbModalidade().getSelectedIndex() == 0) {
                telaCadastro.getJcbModalidade().requestFocus();
                return;
            }
            if (telaCadastro.getJcbSerie().getSelectedIndex() == 0) {
                telaCadastro.getJcbSerie().requestFocus();
                return;
            }
            if (telaCadastro.getJcbTurno().getSelectedIndex() == 0) {
                telaCadastro.getJcbTurno().requestFocus();
                return;
            }

            final Turma turma = new Turma();

            turma.setTurma(telaCadastro.getTxtTurma().getText());
            turma.setModalidade(telaCadastro.getJcbModalidade().getSelectedItem().toString());
            turma.setSerie(telaCadastro.getJcbSerie().getSelectedItem().toString());
            turma.setDescricaoSerie(telaCadastro.getTxtDescricaoSerie().getText());
            turma.setTurno(telaCadastro.getJcbTurno().getSelectedItem().toString());
            turma.setDescricaoTurma(telaCadastro.getTxtDescricaoTurma().getText());
            turma.setDataMatricula(PessoaBO.data(telaCadastro.getTxtDataMatricula().getText(), '/', '-'));
            turma.setDataModificacao(PessoaBO.data(telaCadastro.getTxtDataModificacao().getText(), '/', '-'));
            turma.setTurmaAtiva(telaCadastro.getRbAtivaSim().isSelected());
            turma.setTurmaCancelada(telaCadastro.getRbCanceladaSim().isSelected());
            turma.setDataInativacao(PessoaBO.data(telaCadastro.getTxtDataInativarTurma().getText(), '/', '-'));
            turma.setDataCancelamento(PessoaBO.data(telaCadastro.getTxtDataCancelamento().getText(), '/', '-'));
            
            final List<String> aux = listaProfessores;
            int i = 0;
            for (String professor : aux) {
                final String disciplina = professor.split("-")[2].trim();
                final Disciplina d = (Disciplina) escolaBo.get(EscolaBO.DISCIPLINA, disciplina);
                
                final int idProfessor  = Integer.parseInt(professor.split("-")[0].trim());
                final int idDisciplina = d.getId();
                
                final String string = String.format("%d - %d", idProfessor, idDisciplina);
                listaProfessores.set(i++, string);
            }
            
            if (super.cadastrarTurma(turma, telaInicial.getLbUsuario().getText(), listaProfessores, tipo)) {
                super.preencherTabelaTurma(telaPesquisa.getModel(), telaPesquisa.getTabelaListar());
                this.limparCampos();
            }
            
            return;
        }
        
        if (e.getSource() == telaPesquisa.getBtnEditar() || e.getSource() == telaPesquisa.getMenuItem()[0]) {
            if (telaPesquisa.getTabelaListar().getSelectedRowCount() == 1) {
                removerListeners();

                JTable table = telaPesquisa.getTabelaListar();
                String turma = table.getValueAt(table.getSelectedRow(), 0).toString();

                listaProfessores = super.getProfessores(turma);
                super.editarTurma(telaCadastro, turma);
                tipo = Turma.ATUALIZAR;

                addListeners();
                telaCadastro.setVisible(true);
            }
            
            return;
        } 
        
        if (e.getSource() == this.telaPesquisa.getBtnRelatorios()) {

            final JTable table = this.telaPesquisa.getTabelaListar();

            if (table.getSelectedRowCount() == 1) {
                final String turma = table.getValueAt(table.getSelectedRow(), 0).toString();
                final String url = Arquivo.DIR_REPORT.concat(Relatorio.RELATORIO_TURMA.value);

                this.telaPesquisa.setVisible(false);
                this.telaPesquisa.dispose();

                Arquivo.gerarRelatorio(url, new String[]{"turma"}, new String[]{turma}, "Relatório Turma - ".concat(turma));

                String usuario = this.telaInicial.getLbUsuario().getText();
                String acao = String.format("Relatório turma %s gerado por", turma);
                br.com.siseduc.model.bo.Logger.logger(usuario, acao, "");
            }
            
            return;
        } 
        
        if (e.getSource() == telaCadastro.getBtnCancelar()) {
            limparCampos();
            return;
        } 
        
        if (e.getSource() == telaCadastro.getBtnInserirProfessor()) {
            final String professor = telaCadastro.getJlProfessor().getSelectedValue();
            final String disciplina = professor.split("-")[2].trim();
            
            addProfessor(professor, disciplina);
            return;
        }
        
        if (e.getSource() == telaCadastro.getBtnRemoverProfessor()) {
            final JList origem = telaCadastro.getJlListaProfessores();
            final int index    = origem.getSelectedIndex();
            final String turma = telaCadastro.getTxtTurma().getText();
            
            if (!super.hasData(listaProfessores, telaAlterarProfTurma, index, turma, tipo)) {                
                origem.setListData(PessoaBO.listaToVetor(listaProfessores));
                origem.setSelectedIndex(index);
                listaProfessores = professorBo.remover(origem, listaProfessores);

                String aux[] = new String[listaProfessores.size()];
                for (int i = 0; i < aux.length; i++) {
                    aux[i] = String.format("%s - %s - %s", i+1, listaProfessores.get(i).split("-")[1].trim(), listaProfessores.get(i).split("-")[2].trim());
                }
                origem.setListData(aux);
            }
            
            return;
        }
        
        if (e.getSource() == this.telaAlterarProfTurma.getBtnCancelar()) {
            this.telaAlterarProfTurma.getTxtTurma().setText(null);
            this.telaAlterarProfTurma.getTxtProfessorAnterior().setText(null);
            this.telaAlterarProfTurma.getTxtDisciplina().setText(null);
            this.telaAlterarProfTurma.getJcbNovoProfessor().removeAllItems();
            
            this.telaAlterarProfTurma.setVisible(false);
            this.telaAlterarProfTurma.dispose();
            
            return;
        }
        
        if (e.getSource() == this.telaAlterarProfTurma.getBtnConfirmar()) {
            final String professor  = this.telaAlterarProfTurma.getJcbNovoProfessor().getSelectedItem().toString();
            final String disciplina = professor.split("-")[2].trim();
            final Disciplina d      = (Disciplina) escolaBo.get(EscolaBO.DISCIPLINA, disciplina);
            final int index         = telaCadastro.getJlListaProfessores().getSelectedIndex();
            
            final int idNovoProfessor   = Integer.valueOf(professor.split("-")[0].trim());
            final int idProfessorAntigo = Integer.valueOf(listaProfessores.get(index).split("-")[0].trim());
            final int idDisciplina      = d.getId();
            final String turma          = telaAlterarProfTurma.getTxtTurma().getText();
            
            if (super.alterarProfeTurma(idNovoProfessor, idProfessorAntigo, idDisciplina, turma)) {
                listaProfessores.set(index, professor);
                if (!listaProfessores.isEmpty()) {
                    String professores[] = new String[listaProfessores.size()];
                    int i = 0;
                    for (String p : listaProfessores) {
                        professores[i++] = String.format("%s - %s - %s", i, p.split("-")[1].trim(), p.split("-")[2].trim());
                    }
                    telaCadastro.getJlListaProfessores().setListData(professores);
                }
                
                this.telaAlterarProfTurma.getTxtTurma().setText(null);
                this.telaAlterarProfTurma.getTxtProfessorAnterior().setText(null);
                this.telaAlterarProfTurma.getTxtDisciplina().setText(null);
                this.telaAlterarProfTurma.getJcbNovoProfessor().removeAllItems();

                this.telaAlterarProfTurma.setVisible(false);
                this.telaAlterarProfTurma.dispose();
            } 
        } 
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == telaCadastro.getJcbModalidade()) {
                super.modalidade(telaCadastro.getJcbTurno(), telaCadastro.getJcbSerie(), telaCadastro.getJcbModalidade());
                return;
            }

            if (e.getSource() == telaCadastro.getJcbSerie()) {
                try {
                    super.descricao(telaCadastro.getTxtDescricaoSerie(), telaCadastro.getJcbModalidade(), telaCadastro.getJcbSerie());
                    telaCadastro.getJcbTurno().setSelectedIndex(0);
                } catch (SiseducException ex) {
                    Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                }
                return;
            }

            if (e.getSource() == telaCadastro.getJcbTurno()) {
                if (telaCadastro.getJcbTurno().getSelectedIndex() == 0) {
                    telaCadastro.getTxtTurma().setText(null);
                    return;
                }

                final String serie = telaCadastro.getJcbSerie().getSelectedItem().toString();
                final int turno = telaCadastro.getJcbTurno().getSelectedIndex();
                final int modalidade = telaCadastro.getJcbModalidade().getSelectedIndex();

                final String turma = super.gerarTurma(serie, turno, modalidade);
                telaCadastro.getTxtTurma().setText(turma);

                return;
            }

            if (e.getSource() == telaPesquisa.getJcbOrdenar()) {
                this.ordenarTurma();
                return;
            }
        }

        if (e.getSource() == telaCadastro.getRbAtivaSim()) {
            if (telaCadastro.getRbAtivaSim().isSelected()) {
                telaCadastro.getTxtDataInativarTurma().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
            } else {
                telaCadastro.getTxtDataInativarTurma().setText(null);
            }
            return;
        }

        if (e.getSource() == telaCadastro.getRbCanceladaSim()) {
            if (telaCadastro.getRbCanceladaSim().isSelected()) {
                telaCadastro.getTxtDataCancelamento().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
            } else {
                telaCadastro.getTxtDataCancelamento().setText(null);
            }
        }
    }

    private void limparCampos() {
        telaCadastro.getTxtTurma().setText(null);
        telaCadastro.getJcbModalidade().setSelectedIndex(0);
        telaCadastro.getJcbSerie().setSelectedIndex(0);
        telaCadastro.getTxtDescricaoSerie().setText(null);
        telaCadastro.getJcbTurno().setSelectedIndex(0);
        telaCadastro.getTxtDescricaoTurma().setText(null);
        telaCadastro.getTxtDataMatricula().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
        telaCadastro.getTxtDataModificacao().setText(String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()));
        telaCadastro.getRbAtivaNao().setSelected(true);
        telaCadastro.getRbCanceladaNao().setSelected(true);
        
        telaCadastro.getJlGrade().setListData(new String[]{});
        telaCadastro.getJlProfessor().setListData(new String[]{});
        telaCadastro.getJlListaProfessores().setListData(new String[]{});
        telaCadastro.getTxtPesquisaProfessor().setText(null);
        telaCadastro.getJtPainelCadastroAluno().setSelectedIndex(0);
        
        listaProfessores.removeAll(listaProfessores);

        telaCadastro.dispose();
        tipo = -1;
    }

    private void removerListeners() {
        telaCadastro.getJcbModalidade().removeItemListener(this);
        telaCadastro.getJcbSerie().removeItemListener(this);
        telaCadastro.getJcbTurno().removeItemListener(this);
        this.telaCadastro.getRbAtivaSim().removeItemListener(this);
        this.telaCadastro.getRbCanceladaSim().removeItemListener(this);
    }

    private void addListeners() {
        telaCadastro.getJcbModalidade().addItemListener(this);
        telaCadastro.getJcbSerie().addItemListener(this);
        telaCadastro.getJcbTurno().addItemListener(this);
        this.telaCadastro.getRbAtivaSim().addItemListener(this);
        this.telaCadastro.getRbCanceladaSim().addItemListener(this);
    }

    private void ordenarTurma() {
        final String order = telaPesquisa.getJcbOrdenar().getSelectedItem().toString().replace(' ', '_').toLowerCase();
        final String turma = telaPesquisa.getTxtPesquisar().getText().trim();

        final DefaultTableModel model = telaPesquisa.getModel();
        final JTable table = telaPesquisa.getTabelaListar();

        super.preencherTabelaTurma(model, table, super.ordernarTurma(order, turma));
    }
    
    /**
     * 
     * @param professor
     * @param disciplina 
     */
    public void addProfessor(String professor, String disciplina) {
        if (null == listaProfessores) {
            listaProfessores = new ArrayList<>();
        }

        if (listaProfessores.contains(professor)) {
            final String msg = String.format("Você já adicionou esse professor.\n\n~::%s - %s", professor.split("-")[1].trim(), professor.split("-")[2].trim());
            Siseduc.showMessage(Siseduc.TITULO, msg, TipoMensagem.MSG_ERROR);
            return;
        }

        for (String aux : listaProfessores) {
            if (disciplina.equals(aux.split("-")[2].trim())) {
                Siseduc.showMessage(Siseduc.TITULO, "Já existe um professor cadastrado para essa disciplina.\n\n~::".concat(disciplina), TipoMensagem.MSG_ERROR);
                return;
            }
        }

        listaProfessores.add(professor);
        if (!listaProfessores.isEmpty()) {
            String professores[] = new String[listaProfessores.size()];
            int i = 0;
            for (String p : listaProfessores) {
                professores[i++] = String.format("%s - %s - %s", i, p.split("-")[1].trim(), p.split("-")[2].trim());
            }
            telaCadastro.getJlListaProfessores().setListData(professores);
        }
    }
    
    /**
     * @see TelaCadastroTurma
     * 
     * @return 
     */
    public TelaPesquisa getTelaCadastroTurma() {
        if (this.telaPesquisa != null) {
            return telaPesquisa;
        }
        return null;
    } 
}
