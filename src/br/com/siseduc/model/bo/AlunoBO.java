/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.AlunoDAO;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.TurmaDAO;
import br.com.siseduc.model.vo.Aluno;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import br.com.siseduc.model.vo.Relatorio;
import br.com.siseduc.model.vo.Responsavel;
import br.com.siseduc.model.vo.Turma;
import br.com.siseduc.view.TelaCadastroAluno;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public class AlunoBO extends AlunoDAO {
    /**
     *
     */
    private final TurmaDAO turmaDao = new TurmaDAO();
    /**
     *
     */
    private Aluno aluno;
    /**
     *
     */
    private List<Responsavel> listaResponsavel = new ArrayList<>();


    /**
     *
     * @param aluno
     * @param usuario
     * @param tipo
     * @param responsaveis
     * @return 
     */
    public boolean confirmarCadastro(Aluno aluno, String usuario, int tipo, Responsavel... responsaveis) {
        try {
            if (!PessoaBO.validarNome(aluno.getNome())) {
                return false;
            }
            if (!PessoaBO.validarFormatoFoto(aluno.getFoto())) {
                return false;
            }
            
            if (tipo == Pessoa.ATUALIZAR) {
                if (responsaveis.length == 1 && listaResponsavel.size() == 2) {
                    throw new SiseducException("Você não pode excluir um responsável");
                }
            }
                       
            if (super.confirmarCadastroAtualizacao(this, tipo, aluno, tipo, responsaveis)) {                
                String acao = (tipo == Pessoa.CADASTRAR) 
                        ? String.format("Aluno(a) %s cadastrado por:", aluno.getNome()) 
                        : String.format("Cadastro do(a) aluno(a) %s atualizado por:", aluno.getNome());

                Logger.logger(usuario, acao, "");
                
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
     * @param modalidade
     * @param serie
     * @return
     */
    public String descricaoSerie(String modalidade, String serie) {
        con = Conexao.getConnection(con);        
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            final String desc = turmaDao.getDescricaoSerie(con, modalidade, serie);
            if (desc != null) {
                return desc;
            }
        } catch (Exception e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }

    /**
     *
     * @param modalidade
     * @param serie
     * @param turno
     * @return
     */
    public String[] turmas(String modalidade, String serie, String turno) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }

        try {
            final List<Turma> listaTurma = turmaDao.getTurma(con, modalidade, serie, turno);
            final String[] turmas = new String[listaTurma.size()];

            if (listaTurma.isEmpty()) {
                return new String[0];
            }

            for (int i = 0; i < turmas.length; i++) {
                turmas[i] = listaTurma.get(i).getTurma();
            }

            return turmas;

        } catch (Exception e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
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
    public Turma turma(String turma) {

        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return null;
            }

            final Turma getTurma = turmaDao.getTurma(con, turma, true).get(0);

            return (getTurma != null)
                    ? getTurma
                    : null;
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
    public String matricula() {
        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return null;
            }

            final int id = lastInsertId(con, "aluno") + 1;
            final String getMatricula = PessoaBO.getCadastro(id);

            final String matricula = String.format("%tY", Calendar.getInstance()).concat(getMatricula);

            if (matricula != null) {
                return matricula;
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
     * @param aluno
     * @param responsaveis
     * @return
     */
    public StringBuilder confirmarDados(Aluno aluno, Responsavel... responsaveis) {

        final StringBuilder dados = new StringBuilder();

        dados.append("\n\t---- Confirmar Informações Aluno ----")
                .append("\n    Nome:.................".concat(aluno.getNome()))
                .append("\n    Sexo:..................".concat(aluno.getSexo()))
                .append("\n    Data Nascimento:.".concat(aluno.getDataNascimento()))
                .append("\n    Turma:................".concat(aluno.getTurma().getTurma()))
                .append("\n    Repetente:...........".concat((aluno.isRepetente()) ? "Sim" : "Não"))
                .append("\n");

        for (Responsavel responsavel : responsaveis) {
            dados.append("\n\t---- Confirmar Informações ".concat(responsavel.getParentesco()).concat(" ----"))
                    .append("\n    Nome:........".concat(responsavel.getNome()))
                    .append("\n    Identidade:.".concat(responsavel.getIdentidade()))
                    .append("\n    CPF:..........".concat(responsavel.getCpf()))
                    .append("\n    Telefone:....".concat(responsavel.getTelefone()))
                    .append("\n");
        }

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
     * @param nome
     * @param ano
     * @return
     */
    public boolean preencherTabela(JTable table, DefaultTableModel model, String nome, String ano) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return Boolean.FALSE;
        }

        try {
            final List<Aluno> alunos = super.listaAluno(con, new String[]{nome, ano});
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            if (!nome.isEmpty()) {
                int linha = 0;
                for (Aluno aluno : alunos) {

                    model.addRow(new String[1]);

                    table.setValueAt(aluno.getMatricula(), linha, 0);
                    table.setValueAt(aluno.getNome(), linha, 1);
                    table.setValueAt(aluno.getTurma().getTurma(), linha, 2);
                    table.setValueAt(aluno.getTurma().getSerie(), linha, 3);
                    table.setValueAt(aluno.getTurma().getModalidade(), linha, 4);
                    table.setValueAt(aluno.getTurma().getTurno(), linha, 5);

                    linha++;
                }
                table.repaint();
                table.revalidate();

                return Boolean.TRUE;
            }
        } catch (SQLException e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return Boolean.FALSE;
    }

    /**
     *
     * @param table
     * @param model
     * @param alunos
     * @return
     */
    public boolean preencherTabelaAluno(JTable table, DefaultTableModel model, List<Aluno> alunos) {

        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return false;
            }

            final List<Aluno> listaAluno = (alunos == null)
                    ? super.listaAluno(con)
                    : alunos;

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            
            if (!listaAluno.isEmpty()) {
                int linha = 0;
                for (Aluno aluno : listaAluno) {

                    model.addRow(new String[1]);

                    table.setValueAt(aluno.getMatricula(), linha, 0);
                    table.setValueAt(aluno.getNome(), linha, 1);
                    table.setValueAt(aluno.getTelefone(), linha, 2);
                    table.setValueAt(aluno.getCelular(), linha, 3);
                    table.setValueAt(aluno.getEmail(), linha, 4);

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
    public boolean preencherTabelaAluno(JTable table, DefaultTableModel model) {
        return this.preencherTabelaAluno(table, model, null);
    }
    
    /**
     *
     * @param tela
     * @param matricula
     * @return
     */
    public Aluno atualizarCadastro(TelaCadastroAluno tela, int matricula) {
        try {
            con = Conexao.getConnection(con);
            if (!Conexao.isConectado(con)) {
                return null;
            }

            Map<Aluno, List<Responsavel>> mapAluno = listaAluno(con, matricula);

            if (mapAluno != null) {
                Set<Aluno> alunos = mapAluno.keySet();
                alunos.stream().map((Aluno a) -> {
                    AlunoBO.this.aluno = a;
                    return a;
                }).forEach((a) -> {
                    this.listaResponsavel = mapAluno.get(a);
                });

                tela.getLbFotoAluno().setName(aluno.getFoto());
                tela.getLbFotoAluno().setIcon(new ImageIcon(Arquivo.DIR_FOTOS.concat(aluno.getFoto())));

                tela.getTxtNomeAluno().setText(aluno.getNome());
                tela.getTxtNomeAlunoDesabilitado().setText(aluno.getNome());
                tela.getTxtMatricula().setText(aluno.getMatricula());
                tela.getRbSexoMasculino().setSelected(aluno.getSexo().equals("Masculino"));
                tela.getRbSexoFeminino().setSelected(aluno.getSexo().equals("Feminino"));
                tela.getTxtNacionalidadeAluno().setText(aluno.getNacionalidade());
                tela.getTxtDataNascimento().setDate(DateFormat.getDateInstance().parse(PessoaBO.data(aluno.getDataNascimento(), '-', '/')));
                tela.getTxtNaturalidadeAluno().setText(aluno.getNaturalidade());
                tela.getJcbUfNaturalidadeAluno().setSelectedItem(aluno.getUfNaturalidade());
                tela.getRbEdFisicaSim().setSelected(aluno.isPraticaEdFisica());
                tela.getRbIrmaoSim().setSelected(aluno.isIrmaoNaEscola());
                tela.getRbRepetenteSim().setSelected(aluno.isRepetente());
                tela.getTxtTelefoneAluno().setText(aluno.getTelefone());
                tela.getTxtCelularAluno().setText(aluno.getCelular());
                tela.getTxtEmailAluno().setText(aluno.getEmail());

                tela.getJcbModalidadeAluno().setSelectedItem(aluno.getTurma().getModalidade());
                tela.getJcbSerieAluno().setSelectedItem(aluno.getTurma().getSerie());
                tela.getJcbTurnoAluno().setSelectedItem(aluno.getTurma().getTurno());
                tela.getJcbTurmaAluno().setSelectedItem(aluno.getTurma().getTurma());

                tela.getTxtEnderecoAluno().setText(aluno.getEndereco());
                tela.getTxtComplementoEnderecoAluno().setText(aluno.getComplemento());
                tela.getTxtBairroAluno().setText(aluno.getBairro());
                tela.getTxtCidadeAluno().setText(aluno.getCidade());
                tela.getJcbUfResidenciaAluno().setSelectedItem(aluno.getUf());
                tela.getTxtCepAluno().setText(aluno.getCep());

                tela.getCheckPaiDeclarado().setSelected(!aluno.isPaiDeclarado());

                listaResponsavel.stream().forEach((responsavel) -> {
                    if (responsavel.getParentesco().equals("Mãe")) {
                        tela.getTxtNomeMae().setText(responsavel.getNome());
                        tela.getTxtIdentidadeMae().setText(responsavel.getIdentidade());
                        tela.getTxtCpfMae().setText(responsavel.getCpf());
                        tela.getTxtTelefoneMae().setText(responsavel.getTelefone());
                        tela.getTxtCelularMae().setText(responsavel.getCelular());
                        tela.getTxtEmailMae().setText(responsavel.getEmail());

                        tela.getTxtEnderecoMae().setText(responsavel.getEndereco());
                        tela.getTxtComplementoEnderecoMae().setText(responsavel.getComplemento());
                        tela.getTxtBairroMae().setText(responsavel.getBairro());
                        tela.getTxtCidadeMae().setText(responsavel.getCidade());
                        tela.getJcbUfMae().setSelectedItem(responsavel.getUf());
                        tela.getTxtCepMae().setText(responsavel.getCep());
                        tela.getRbMoraComFilhoSimMae().setSelected(responsavel.isMoraComFilho());
                        tela.getRbMoraComFilhoNaoMae().setSelected(!responsavel.isMoraComFilho());
                    } else {
                        tela.getTxtNomePai().setText(responsavel.getNome());
                        tela.getTxtIdentidadePai().setText(responsavel.getIdentidade());
                        tela.getTxtCpfPai().setText(responsavel.getCpf());
                        tela.getTxtTelefonePai().setText(responsavel.getTelefone());
                        tela.getTxtCelularPai().setText(responsavel.getCelular());
                        tela.getTxtEmailPai().setText(responsavel.getEmail());

                        tela.getTxtEnderecoPai().setText(responsavel.getEndereco());
                        tela.getTxtComplementoEnderecoPai().setText(responsavel.getComplemento());
                        tela.getTxtBairroPai().setText(responsavel.getBairro());
                        tela.getTxtCidadePai().setText(responsavel.getCidade());
                        tela.getJcbUfPai().setSelectedItem(responsavel.getUf());
                        tela.getTxtCepPai().setText(responsavel.getCep());
                        tela.getRbMoraComFilhoSimPai().setSelected(responsavel.isMoraComFilho());
                        tela.getRbMoraComFilhoNaoPai().setSelected(!responsavel.isMoraComFilho());
                    }
                });
            }
            return aluno;
        } catch (SQLException | ParseException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }
    
    /**
     *
     * @param order
     * @param nome
     * @return
     */
    public List<Aluno> ordenarAluno(String order, String nome) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return (nome.isEmpty())
                    ? super.listaAluno(con, order)
                    : super.listaAluno(con, order, nome);

        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return null;
    }

    /**
     *
     * @param fields
     * @param values
     * @param nome
     * @param usuario
     */
    public void gerarBoletim(String[] fields, String[] values, String nome, String usuario) {

        try {
            final String titulo = "Visualizar Boletim de ".concat(nome);
            final String url = Arquivo.DIR_REPORT.concat(Relatorio.BOLETIM.value);
            Arquivo.gerarRelatorio(url, fields, values, titulo);

            String acao = "Boletim gerado por:";
            String opcao = "para o(a) aluno(a) ".concat(nome);

            Logger.logger(usuario, acao, opcao);
        } catch (Exception e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
        }
    }
    
    /**
     * 
     * @param idAluno
     * @param idProfessor
     * @param idTurma
     * @param idDisciplina
     * @return 
     */
    public List<Double> getNotas(int idAluno, int idProfessor, int idTurma, int idDisciplina) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            return super.listaNotas(con, idAluno, idProfessor, idTurma, idDisciplina);
           
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        
        return null;
    }
    
    /**
     * 
     * @param hasNota
     * @param idAluno
     * @param idProfessor
     * @param idTurma
     * @param idDisciplina
     * @param notas
     * @return 
     */
    public boolean lancarNotas(boolean hasNota, int idAluno, int idProfessor, int idTurma, int idDisciplina, List<Double> notas) {
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }
        
        if (notas.isEmpty() || notas.size() < 6) {
            return false;
        }
        
        final double aval_1 = notas.get(0);
        final double aval_2 = notas.get(1);
        final double aval_3 = notas.get(2);
        final double aval_4 = notas.get(3);

        final double recu_1  = notas.get(4);
        final double recu_2  = notas.get(5);
        
        final int idNota = (hasNota) ? Integer.parseInt(String.valueOf(notas.get(6)).replace('.', ',').split(",")[0].trim()) : 0;

        try {
            super.lancarNota(con, hasNota, aval_1, aval_2, aval_3, aval_4, recu_1, recu_2, idAluno, idProfessor, idDisciplina, idTurma, idNota);
            final TipoMensagem resultado = Siseduc.showMessage(Siseduc.TITULO, "Notas inseridas com sucesso!!", TipoMensagem.MSG_INFORMATION);
            return (resultado == TipoMensagem.OPCAO_OK);
        } catch (SQLException e) {
            Siseduc.showMessage(Siseduc.TITULO, e.getMessage(), TipoMensagem.MSG_ERROR);
            return Boolean.FALSE;
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param bin
     * @return
     */
    public static int binarioToDecimal(String bin) {

        bin = bin.trim();

        if (bin.isEmpty()) {
            throw new IllegalArgumentException("Número inválido");
        }

        final LinkedList<Integer> binario = new LinkedList<>();
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1') {
                throw new IllegalArgumentException("Número inválido: " + bin.charAt(i));
            }

            binario.addFirst(Integer.parseInt(String.valueOf(bin.charAt(i))));
        }

        int decimal = 0;
        for (int i = 0; i < binario.size(); i++) {
            if (binario.get(i).equals(1)) {
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    /**
     *
     */
    public static void numeroPerfeito() {
        //6
        //1+2+3 = 6
        int soma;
        List<Integer> perfeitos = new ArrayList<>();
        for (int i = 1; i < 5000; i++) {
            soma = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    soma += j;
                }
            }
            if ((soma - i) == i) {
                perfeitos.add(i);
            }
        }
        System.out.println(perfeitos.toString());
    }

    /**
     *
     * @param x
     * @return
     */
    public static String numeroPerfeito(int x) {

        if (x <= 0) {
            return "Informe valores naturais maiores que zero!!";
        }

        int somatoria = 0;
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= x; i++) {
            if (x % i == 0 && i < x) {
                somatoria += i;
                s.append(String.format("%d + %d = %d\n", (somatoria - i), i, somatoria));
            }
        }
        s.append(String.format("\nValor = %d", x));
        s.append(String.format("\nSomatória = %d", somatoria));

        s.append(String.format((somatoria == x) ? "\n\n%d é um número perfeito!!\n\n" : "\n\n%d não é um número perfeito!!\n\n", x));

        return s.toString();
    }

    /**
     *
     * @param x
     * @return
     */
    public static boolean numeroPrimo(int x) {
        return numeroPrimo(x, x, x);
    }

    /**
     * 
     * @param x
     * @param n
     * @param primo
     * @return
     */
    private static boolean numeroPrimo(int x, int n, int primo) {
        if (n == 0) {
            return (primo == 2);
        } else {
            if (x % n != 0) {
                --primo;
            }
            return numeroPrimo(x, --n, primo);
        }
    }
}