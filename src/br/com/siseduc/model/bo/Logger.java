/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.bo;

import br.com.siseduc.model.dao.Arquivo;
import br.com.siseduc.model.vo.Log;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Juan Soares
 */
public class Logger {

    /** 
     * Inicio da String a ser colorida 
     * 
     * @see #colorir(int, java.lang.String, java.lang.String, java.awt.Color, boolean, javax.swing.JTextPane)
     * @see #styleFont(javax.swing.JTextPane, boolean, boolean, boolean, java.lang.String, java.awt.Color, int, int, int, int) 
     */
    private int inicio = 0;
    /** 
     * Fim da String a ser colorida 
     * 
     * @see #colorir(int, java.lang.String, java.lang.String, java.awt.Color, boolean, javax.swing.JTextPane)
     * @see #styleFont(javax.swing.JTextPane, boolean, boolean, boolean, java.lang.String, java.awt.Color, int, int, int, int) 
     */
    private int fim = 0;
    /**
     * @see #getLogger(java.lang.String)
     */
    private static int i = 0;

    /**
     * Usada para verificar se o arquivo a ser recuperado
     * será um arquivo de LOG.
     * 
     * @see #logger(java.lang.String...)
     * @see #logger(int, java.lang.String...)
     */
    public static final int LOG = 0;
    /**
     * Usada para verificar se o arquivo a ser recuperado
     * será um arquivo de Informação.
     * 
     * @see #logger(int, java.lang.String...)
     */
    public static final int INFO = 1;
    /**
     * Usada para verificar se o arquivo a ser recuperado
     * será um arquivo de Sessão.
     * 
     * @see #logger(java.lang.String, java.lang.String)
     * @see #logger(int, java.lang.String...)
     */
    public static final int SESSION = 2;

    /**
     *
     * @param options
     */
    public static void logger(String... options) {
        logger(LOG, options);
    }

    /**
     *
     * @param ip
     * @param usuario
     */
    public static void logger(String usuario, String ip) {
        logger(SESSION, usuario, "", ip);
    }

    /**
     *
     * @param opc
     * @param options
     */
    public static void logger(int opc, String... options) {
        final Log log = new Log();
        String sUrl = (opc == LOG)
                ? Arquivo.DIR_LOG.concat(Log.LOG)
                : (opc == SESSION)
                        ? Arquivo.DIR_LOG.concat(Log.SESSION)
                        : Arquivo.DIR_LOG.concat(Log.INFO);

        log.setInfo(options[0]);
        if (options.length > 1) {
            log.setAcao(options[1]);
            log.setOpcao(options[2]);
        }
        log.setData(String.format("%1$tA, %1$td de %1$tB de %1$tY %1$tI:%1$tM %1$tp", Calendar.getInstance()));
        Arquivo.serializar(sUrl, log, opc);
    }

    /**
     *
     * @param url
     * @return
     */
    public static StringBuilder getLogger(final String url) throws NullPointerException {

        final List<Log> interator = (List<Log>) Arquivo.getSer(url);
        final StringBuilder text = new StringBuilder();

        interator.stream().forEach((log) -> {
            if (log.getAcao() != null) {
                text.append(String.format("%s - %s @@%s %s [%s]\n", String.format("/*!%s", PessoaBO.getCadastro(++i)), log.getAcao(), log.getInfo(), log.getOpcao(), log.getData()));
            } else if (log.getInfo() == null) {
                text.append(String.format("%s - %s\n", String.format("/*!%s", PessoaBO.getCadastro(++i)), log.getOpcao()));
            } else {
                text.append(String.format("%s - %s [%s] ::--\n", String.format("/*!%s", PessoaBO.getCadastro(++i)), log.getInfo(), log.getData()));
            }
        });

        i = 0;
        return text;
    }

    /**
     *
     * @param start
     * @param tipo
     * @param pattener
     * @param color
     * @param italic
     * @param txt
     */
    public void colorir(int start, String tipo, String pattener, Color color, boolean italic, JTextPane txt) {
        inicio = txt.getText().indexOf(tipo, start);
        fim = txt.getText().indexOf(pattener, inicio);

        if (inicio == -1) {
            return;
        }

        styleFont(txt, false, false, italic, "Tahoma", color, 12, 1, inicio, fim);
        colorir(++fim, tipo, pattener, color, italic, txt);
    }

    /**
     *
     * @param txt
     * @param bold
     * @param under
     * @param italic
     * @param fontFamily
     * @param color
     * @param size
     * @param tipo
     * @param inicio
     * @param fim
     */
    public void styleFont(JTextPane txt, boolean bold, boolean under, boolean italic, String fontFamily, Color color, int size, int tipo, int inicio, int fim) {
        String textSelected = null;
        int count = 0;
        int start = 0;
        int end = 0;
        try {
            txt.setSelectionStart(inicio);
            txt.setSelectionEnd(fim);

            start = txt.getSelectionStart();
            end = txt.getSelectionEnd();
            count = txt.getSelectedText().length();
            textSelected = txt.getSelectedText();
        } catch (NullPointerException e) {}

        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setBold(atributos, bold);
        StyleConstants.setUnderline(atributos, under);
        StyleConstants.setItalic(atributos, italic);
        StyleConstants.setFirstLineIndent(atributos, 400);
        if (tipo == 1) {
            StyleConstants.setFontFamily(atributos, fontFamily);
            StyleConstants.setForeground(atributos, color);
            StyleConstants.setFontSize(atributos, size);
        }
        if (textSelected != null) {
            try {
                txt.getStyledDocument().remove(start, count);
            } catch (BadLocationException b) {}
            try {
                txt.getStyledDocument().insertString(start, textSelected, atributos);
                txt.select(start, end);
                txt.setSelectedTextColor(color);
            } catch (BadLocationException bl) {}
        } else {
            try {
                txt.getStyledDocument().insertString(txt.getStyledDocument().getLength(), textSelected, atributos);
            } catch (BadLocationException ex) {}
        }
    }
}
