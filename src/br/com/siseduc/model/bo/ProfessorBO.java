/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.EscolaDAO;
import br.com.siseduc.model.dao.ProfessorDAO;
import br.com.siseduc.model.vo.Formacao;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Professor;
import br.com.siseduc.model.vo.Relatorio;
import br.com.siseduc.model.vo.Turma;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class ProfessorBO extends ProfessorDAO {
    /**
     * 
     */
    private String nome = "";
    /**
     * 
     */
    private List<String> listaDisciplina = new ArrayList<>();
    
    /**
     *
     * @param professor
     * @param usuario
     * @param formacoes
     * @param listaDisciplinas
     * @param tipo
     * @return
     * @throws SiseducException
     */
    public boolean confirmarCadastro(Professor professor, String usuario, List<Formacao> formacoes, List<String> listaDisciplinas, int tipo) throws SiseducException {

        final List<Integer> disciplinas = this.listaDisciplina(listaDisciplinas);

        if (PessoaBO.validarFormatoFoto(professor.getFoto()) && PessoaBO.validarNome(professor.getNome())) {
            if (super.confirmarCadastroAtualizacao(this, tipo, professor, formacoes, disciplinas, tipo)) {
                String acao = (tipo == Pessoa.CADASTRAR)
                        ? String.format("Professor(a) %s cadastrado(a) por:", professor.getNome())
                        : String.format("cadastro do(a) professor(a) %s atualizado por:", professor.getNome());
                Logger.logger(usuario, acao, "");
                
                return true;
            }
        }        
        return false;
    }

    /**
     *
     * @param formacao
     * @return
     * @throws SiseducException
     */
    public boolean validarFormacao(Formacao formacao) throws SiseducException {

        if (formacao.getTitulo().equals("")) {
            throw new SiseducException("Informe o titulo");
        }

        if (formacao.getCurso().equals("")) {
            throw new SiseducException("Informe o curso");
        }

        if (formacao.getInstituicao().equals("")) {
            throw new SiseducException("Informe a Instituição");
        }

        if (formacao.getAnoInicio() == 0) {
            throw new SiseducException("Informe o ano de inicio");
        }

        if (formacao.getAnoTermino() == 0) {
            throw new SiseducException("Informe o ano de término");
        }

        if (formacao.getDiploma().equals("")) {
            throw new SiseducException("Informe o diploma");
        }
        
        if (formacao.getAnoInicio() > formacao.getAnoTermino()) {
            throw new SiseducException("Ano de formacao inválido");
        }
        return true;
    }

    /**
     *
     * @return
     */
    public String matriculaProfessor() {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        String matricula = null;
        try {
            final String[] vector = {"", "0000", "000", "00", "0"};
            matricula = String.valueOf(lastInsertId(con, "professor") + 1);
            
            if (matricula.length() < 5) {
                return vector[matricula.length()].concat(matricula);
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return matricula;
    }
    
    /**
     * 
     * @param jcb
     * @param turma 
     */
    public void listarProfessores(JComboBox jcb, String turma) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        try {
            List<String> professores = super.getProfessores(con, turma);
            
            if (professores.isEmpty()) {
                throw new SiseducException("Não existe professor nessa turma.");
            }
            
            jcb.removeAllItems();
            jcb.addItem("Escolha o Professor");
            professores.stream().forEach((professor) -> {
                jcb.addItem(professor);
            });
        } catch (SQLException | SiseducException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        finally {
            Conexao.closeConnection(con);
        }
    }
    
    /**
     *
     * @return
     */
    public String[] disciplinas() {

        final EscolaDAO ed = new EscolaDAO();

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        String[] vetor = null;
        try {
            final Map<Integer, String> disciplinas = ed.listaDisciplina(con);
            if (disciplinas.isEmpty()) {
                return null;
            }

            int i = 0;
            vetor = new String[disciplinas.size()];
            Set<Integer> keys = disciplinas.keySet();
            for (Integer key : keys) {
                vetor[i++] = String.format("%s - %s", PessoaBO.getCadastro(key), disciplinas.get(key));
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return vetor;
    }

    /**
     *
     * @param origem
     * @param destino
     * @param lista
     * @return
     */
    public List<String> copy(final JList<String> origem, final JList<String> destino, List<String> lista) {

        final String vetorDisciplina[];
        this.listaDisciplina = lista;

        final List<String> aux = origem.getSelectedValuesList();
        for (int i = 0; i < aux.size(); i++) {
            if (!this.listaDisciplina.contains(aux.get(i))) {
                this.listaDisciplina.add(aux.get(i));
            }
        }

        vetorDisciplina = new String[this.listaDisciplina.size()];
        for (int i = 0; i < this.listaDisciplina.size(); i++) {
            vetorDisciplina[i] = this.listaDisciplina.get(i);
        }

        destino.setListData(vetorDisciplina);
        origem.clearSelection();

        return this.listaDisciplina;
    }

    /**
     *
     * @param disciplinas
     * @return
     */
    public List<Integer> listaDisciplina(List<String> disciplinas) {

        final List<Integer> lista = new ArrayList<>();

        if (!disciplinas.isEmpty()) {
            for (String disciplina : disciplinas) {
                lista.add(Integer.parseInt(disciplina.split("-")[0].trim()));
            }
        }
        return lista;
    }

    /**
     *
     * @param origem
     * @param lista
     * @param all
     * @return
     */
    private List<String> remover(final JList<String> origem, List<String> lista, boolean all) {

        final String vetorDisciplina[];
        this.listaDisciplina = lista;

        try {
            if (all) {
                this.listaDisciplina.removeAll(lista);
            } else {
                final List<String> aux = origem.getSelectedValuesList();
                for (int i = 0; i < aux.size(); i++) {
                    this.listaDisciplina.remove(aux.get(i));
                }
            }

            vetorDisciplina = new String[lista.size()];
            for (int i = 0; i < this.listaDisciplina.size(); i++) {
                vetorDisciplina[i] = this.listaDisciplina.get(i);
            }
            
            origem.setListData(vetorDisciplina);

        } catch (ArrayIndexOutOfBoundsException a) {
            Siseduc.showMessage(Siseduc.TITULO, "Selecione uma disciplina", TipoMensagem.MSG_ERROR);
        }
        return this.listaDisciplina;
    }

    /**
     *
     * @param origem
     * @param lista
     * @return
     */
    public List<String> remover(final JList<String> origem, List<String> lista) {
        return this.remover(origem, lista, false);
    }

    /**
     *
     * @param origem
     * @param lista
     * @return
     */
    public List<String> removerTodas(final JList<String> origem, List<String> lista) {
        return this.remover(origem, lista, true);
    }

    /**
     *
     * @param professor
     * @param formacoes
     * @param disciplinas
     * @return
     */
    public StringBuilder confirmarDados(Professor professor, List<Formacao> formacoes, List<String> disciplinas) {
        final StringBuilder dados = new StringBuilder();

        dados.append("\n\t\t --- Confirmar Informações do Professor --- \n")
                .append("    Nome: ".concat(professor.getNome()))
                .append("\n    Sexo: ".concat(professor.getSexo()))
                .append("\n    Indentidade: ".concat(professor.getIdentidade()))
                .append("\n    CPF: ".concat(professor.getCpf()))
                .append("\n\t\t --- Formação Acadêmica ---");

        int i = 0;
        for (Formacao formacao : formacoes) {
            dados.append("\n    --> Formação ".concat(String.format("%d", ++i)))
                    .append("\n\tTitulo: ".concat(formacao.getTitulo()))
                    .append("\n\tCurso: ".concat(formacao.getCurso()))
                    .append("\n\tInstituição: ".concat(formacao.getInstituicao()))
                    .append("\n\tAno início: ".concat(String.valueOf(formacao.getAnoInicio())))
                    .append("\n\tAno Término: ".concat(String.valueOf(formacao.getAnoTermino())));
        }

        dados.append("\n    --> Disciplinas");
        disciplinas.stream().forEach((disciplina) -> {
            dados.append(String.format("\n\t%s", disciplina));
        });

        dados.append("\n\n    ----------------------------------------------")
                .append("\n    ---")
                .append("\n    --- Finalizado em: ".concat(String.format("%1$tA, %1$td de %1$tB de %1$tY %1$tI:%1$tM %1$tp", Calendar.getInstance())))
                .append("\n    ---");

        return dados;
    }

    /**
     *
     * @param table
     * @param model
     * @param order
     * @param text
     * @return
     */
    public boolean preencherTabelaProfessor(JTable table, DefaultTableModel model, String order, String text) {

        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return false;
            }
            
            final List<Professor> professores = super.listaProfessor(con, order, text);
            
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
            if (!professores.isEmpty()) {
                
                int linha = 0;
                for (Professor professor : professores) {
                    
                    model.addRow(new String[1]);
                    
                    table.setValueAt(professor.getMatricula(), linha, 0);
                    table.setValueAt(professor.getNome(), linha, 1);
                    table.setValueAt(professor.getTelefone(), linha, 2);
                    table.setValueAt(professor.getCelular(), linha, 3);
                    table.setValueAt(professor.getEmail(), linha, 4);
                    
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
     * @param id_professor
     * @return
     */
    public Map<Professor, List<Formacao>> getProfessor(int id_professor) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        Map<Professor, List<Formacao>> map = null;
        try {            
            map = new HashMap<>();
            
            final Professor professor = listaProfessor(con, id_professor).get(0);
            final List<Formacao> formacoes = (professor != null) ? professor.getFormacao() : new ArrayList<>();
            
            if (professor == null || formacoes.isEmpty()) {
                return null;
            }
            
            map.put(professor, formacoes);
            
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return map;
    }

    /**
     * 
     * @param nome
     * @param jcb
     * @return 
     */
    public String getProfessor(String nome, JComboBox ... jcb) {
        
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            Map<String, Turma<Set<String>, Set<String>>> map = super.getProfessor(con, nome);
            if (!map.isEmpty()) {

                Set keys = map.keySet();

                keys.stream().forEach((Object key) -> {

                    this.nome = (String) key;

                    jcb[0].removeAllItems();
                    jcb[0].addItem("Disciplinas");

                    jcb[1].removeAllItems();
                    jcb[1].addItem("Turmas");

                    for (Object disciplina : map.get(key).getInstanceA().toArray()) {
                        jcb[0].addItem(disciplina);
                    }

                    for (Object turma : map.get(key).getInstanceP().toArray()) {
                        jcb[1].addItem(turma);
                    }
                });
            }            
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_INFORMATION);
        } finally {
            Conexao.closeConnection(con);
        }
        return this.nome;
    }
    
    /**
     * 
     * @param nome
     * @param disciplina
     * @return 
     */
    public String[] getNomes(String nome, String disciplina) {
        
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            final List<String> lista = super.getNomes(con, nome, disciplina);

            if (!lista.isEmpty()) {
                String[] nomes = new String[lista.size()];
                for (int i = 0; i < nomes.length; i++) {
                    nomes[i] = lista.get(i);
                }
                return nomes;
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
     * @param nome
     * @return 
     */
    public String[] getNomes(String nome) {
        return this.getNomes(nome, null);
    }
    
    /**
     * 
     * @param dis
     * @param disciplina
     * @return 
     */
    public String[] getNomes(boolean dis, String disciplina) {
        if (dis)
            return this.getNomes(null, disciplina);
        
        return null;
    }
    
    /**
     * 
     * @param nomes
     * @param s
     * @return 
     */
    public String getNome(String[] nomes, String s) {
        
        for (String nome : nomes) {
            String aux = nome.substring(0, s.length());
            if (aux.toLowerCase().equals(s.toLowerCase())) {
                return nome;
            }
        }        
        return null;
    }
    
    /**
     * 
     * @param fields
     * @param values 
     * @param titulo 
     */
    public void gerarDiarioClasse(String[] fields, String[] values, String titulo) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        
        try {            
            final String url = Arquivo.DIR_REPORT.concat(Relatorio.DIARIO_CLASSE.value);
            Arquivo.gerarRelatorio(url, fields, values, titulo);            
        } catch (Exception e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }         
    } 
}