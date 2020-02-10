/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.dao.Conexao;
import br.com.siseduc.model.dao.PessoaDAO;
import br.com.siseduc.model.vo.IGenerica;
import br.com.siseduc.model.vo.Mensagem;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import br.com.siseduc.model.vo.Pessoa;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Soares
 */
public abstract class PessoaBO extends PessoaDAO {
 
    /**
     *
     * @param nome
     * @return
     * @throws SiseducException
     */
    public static boolean validarNome(String nome) throws SiseducException {
        if (nome.isEmpty()) {
            throw new SiseducException(Mensagem.NOME_INVALIDO);
        }
        return true;
    }

    /**
     *
     * @param foto
     * @return
     * @throws SiseducException
     */
    public static boolean validarFormatoFoto(String foto) throws SiseducException {

        /**/
        if (foto.isEmpty()) {
            throw new SiseducException("");
        }

        /**/
        final String[] formatoEsperado = {"png", "jpg", "jpeg"};
        String formatoRecebido = "";

        /**/
        String aux[] = foto.replace('.', ',').split(",");
        for (String f : aux) {
            formatoRecebido = f;
        }

        /**/
        String extensao = "";
        if (!formatoRecebido.equals("")) {
            extensao = formatoRecebido;
        }

        /**/
        for (String formato : formatoEsperado) {
            if (extensao.equals(formato)) {
                return true;
            }
        }
        throw new SiseducException(String.format("%s%s",Mensagem.FOTO_INVALIDA, Arrays.toString(formatoEsperado)));
    }

    /**
     *
     * @param data
     * @param formatoEntrada
     * @param formatoSaida
     * @return
     */
    public static String data(String data, char formatoEntrada, char formatoSaida) {
        if (data == null) {
            return null;
        }
        //
        final String aux[] = data.split(String.format("%c", formatoEntrada));
        //
        return ((formatoSaida == '-')
                ? String.format("%s-%s-%s", aux[2], aux[1], aux[0])
                : String.format("%s/%s/%s", aux[2], aux[1], aux[0]));

    }

    /**
     *
     * @param string
     * @return
     */
    public static String criptografar(String string) {

        String aux = string;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte messageDigest[] = md5.digest(aux.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            aux = hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Siseduc.showMessage(Siseduc.TITULO, Mensagem.ERRO_CRIPTOGRAFIA, TipoMensagem.MSG_ERROR);
        }

        return aux;
    }

    /**
     *
     * @param id
     * @return
     */
    public static String getCadastro(int id) {

        final String[] vector = {"", "0000", "000", "00", "0"};
        final String matricula = String.valueOf(id);

        return (matricula.length() < 5)
                ? vector[matricula.length()].concat(matricula)
                : matricula;
    }

    /**
     *
     * @param label
     * @param foto
     * @return
     */
    public static String alterarFoto(JLabel label, String foto) {
        //
        final File path = Arquivo.selecionarArquivo(Arquivo.ABRIR);
        String fileName;

        try {
            if (path != null && validarFormatoFoto(path.toString())) {
                //
                fileName = criptografar(path.toString() + new Random(0xFFAFBF) + foto).concat(".png");

                try {
                    foto = (foto != null) ? foto : "null.png";
                    Arquivo.excluirArquivo(Arquivo.DIR_TEMP, foto);
                } catch (IOException ex) {
                    Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
                }
                //
                Arquivo.salvarImagem(path, fileName, 100, 100);

                //
                label.setIcon(new ImageIcon(Arquivo.DIR_TEMP.concat(fileName)));

                return fileName;
            }
        } catch (SiseducException ex) {
            Siseduc.showMessage("Foto inválida", ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return foto;
    }

    /**
     * 
     * @param table
     * @param model
     * @param tabela 
     */
    public static void preencherTabela(JTable table, DefaultTableModel model, String tabela) {
        preencherTabela(table, model, tabela, null);
    }

    /**
     *
     * @param table
     * @param model
     * @param tabela
     * @param nome
     */
    public static void preencherTabela(JTable table, DefaultTableModel model, String tabela, String nome) {

        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return;
        }
        
        try {
            final List<IGenerica<Pessoa>> lista = (nome == null)
                    ? PessoaDAO.getPessoa(con, tabela)
                    : PessoaDAO.getPessoa(con, tabela, nome);

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            if (!lista.isEmpty()) {

                int linha = 0;
                for (IGenerica<Pessoa> pessoa : lista) {
                    model.addRow(new String[1]);

                    table.setValueAt(PessoaBO.getCadastro(Integer.parseInt(pessoa.getInstance().getMatricula())), linha, 0);
                    table.setValueAt(pessoa.getInstance().getNome(), linha, 1);
                    table.setValueAt(pessoa.getInstance().getTelefone(), linha, 2);
                    table.setValueAt(pessoa.getInstance().getEmail(), linha, 3);

                    linha++;
                }
                table.revalidate();
            }
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, Mensagem.ERRO_BANCO_DE_DADOS, TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
    }

    /**
     *
     * @param palavra
     * @return
     */
    public static String removerAcentos(String palavra) {

        if (palavra.isEmpty()) {
            return null;
        }

        char letra[][] = {
            {
                'á', 'à', 'â', 'ã',
                'é', 'è', 'ê',
                'í', 'ì', 'î',
                'ó', 'ò', 'ô', 'õ',
                'ú', 'ù', 'û', 'ç'
            },
            {
                'a', 'a', 'a', 'a',
                'e', 'e', 'e',
                'i', 'i', 'i',
                'o', 'o', 'o', 'o',
                'u', 'u', 'u', 'c'
            }
        };

        char aux[] = palavra.toCharArray();
        int length = aux.length;

        int i = 0;
        do {
            int n = letra[0].length;
            int j = 0;
            while (n-- != 0) {
                final char c1 = aux[i];
                final char c2 = letra[0][j];
                if (equals(c1, c2)) {
                    palavra = palavra.replace(c1, letra[1][j]);
                }
                j++;
            }
        } while (++i < length);

        return palavra;
    }

    /**
     *
     * @param c1
     * @param c2
     * @return
     */
    private static boolean equals(char c1, char c2) {
        return (c1 == c2);
    }

    /**
     * 
     * @param table
     * @param where
     * @return 
     */
    public static String getId(String table, String where) {
        
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return null;
        }
        
        try {
            String id = String.valueOf(PessoaDAO.listaId(con, "id_".concat(table), table, table, where).get(0));
            if (id != null)
                return id;
        } catch (SQLException ex) {
            Siseduc.showMessage(Siseduc.TITULO, Mensagem.ERRO_BANCO_DE_DADOS, TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }        
        return null;
    }
    
    /**
     * 
     * @param lista
     * @return 
     */
    public static String[] listaToVetor(List<String> lista) {
        String aux[] = new String[lista.size()];

        for (int i = 0; i < aux.length; i++) {
            aux[i] = lista.get(i);
        }        
        return aux;
    }
}
