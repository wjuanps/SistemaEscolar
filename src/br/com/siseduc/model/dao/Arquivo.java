/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.dao;

import br.com.siseduc.Siseduc;
import br.com.siseduc.model.bo.Logger;
import br.com.siseduc.model.bo.SiseducException;
import br.com.siseduc.model.vo.Grade;
import br.com.siseduc.model.vo.Mensagem.TipoMensagem;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Juan Soares
 */
public abstract class Arquivo implements Diretorio {
    
    /**
     * Conjunto de caracteres padrão
     */
    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    
    /**
     * 
     */
    public static final boolean ABRIR = Boolean.TRUE;
    
    /**
     * 
     */
    public static final boolean SALVAR = !ABRIR;

    /**
     *
     * Cria os diretórios, caso não
     * existão, onde ficam
     * armazenados os arquivos.
     * 
     * @return
     */
    public static boolean criarDiretorios() {
        try {
            if (!Files.isDirectory(Paths.get(ROOT.concat("/Siseduc")))) {
                Files.createDirectory(Paths.get(ROOT.concat("/Siseduc")));
            }
            if (!Files.isDirectory(Paths.get(ROOT.concat("/Siseduc/src")))) {
                Files.createDirectory(Paths.get(ROOT.concat("/Siseduc/src")));
            }
            if (!Files.isDirectory(Paths.get(DIR_FOTOS))) {
                Files.createDirectory(Paths.get(DIR_FOTOS));
            }
            if (!Files.isDirectory(Paths.get(DIR_TEMP))) {
                Files.createDirectory(Paths.get(DIR_TEMP));
            }
            if (!Files.isDirectory(Paths.get(DIR_DIPLOMAS))) {
                Files.createDirectory(Paths.get(DIR_DIPLOMAS));
            }
            if (!Files.isDirectory(Paths.get(DIR_LOG))) {
                Files.createDirectory(Paths.get(DIR_LOG));
            }
            if (!Files.isDirectory(Paths.get(DIR_GRADE))) {
                Files.createDirectory(Paths.get(DIR_GRADE));
            }
            
            copiarArquivo(System.getProperty("user.dir").concat("\\src\\images\\default.png"), DIR_FOTOS.concat("default.png"));
            
            if (!Files.exists(Paths.get(DIR_GRADE.concat(Grade.EJA_FILE)))) {
                copiarArquivo(DIR_FILES.concat(Grade.EJA_FILE), DIR_GRADE.concat(Grade.EJA_FILE));
            }
            
            if (!Files.exists(Paths.get(DIR_GRADE.concat(Grade.FUNDAMENTAL_1_FILE)))) {
                copiarArquivo(DIR_FILES.concat(Grade.FUNDAMENTAL_1_FILE), DIR_GRADE.concat(Grade.FUNDAMENTAL_1_FILE));
            }
            
            if (!Files.exists(Paths.get(DIR_GRADE.concat(Grade.FUNDAMENTAL_2_FILE)))) {
                copiarArquivo(DIR_FILES.concat(Grade.FUNDAMENTAL_2_FILE), DIR_GRADE.concat(Grade.FUNDAMENTAL_2_FILE));
            }
            
            if (!Files.exists(Paths.get(DIR_GRADE.concat(Grade.MEDIO_FILE)))) {
                copiarArquivo(DIR_FILES.concat(Grade.MEDIO_FILE), DIR_GRADE.concat(Grade.MEDIO_FILE));
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * @param abrir
     * @return um objeto do tipo <code>Path</code> com o caminho do arquivo
     */
    public static File selecionarArquivo(boolean abrir) {
        final JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle(Siseduc.TITULO);
        
        int resp;        
        if (abrir) {
            resp = jFileChooser.showOpenDialog(null);
        } else {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF do Acrobat(*.pdf)", "pdf");
            jFileChooser.addChoosableFileFilter(filter);
            jFileChooser.setFileFilter(filter);
            resp = jFileChooser.showSaveDialog(null);
        }
        
        return (
            (resp == JFileChooser.APPROVE_OPTION) ? jFileChooser.getSelectedFile() : null
        );
    }

    /**
     *
     * @param file
     * @param fileName
     * @param width
     * @param height
     */
    public static void salvarImagem(File file, String fileName, int width, int height) {

        final BufferedImage imagem;
        try {
            imagem = ImageIO.read(file);

            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newImg.createGraphics();
            g.drawImage(imagem, 0, 0, width, height, null);

            ImageIO.write(newImg, "PNG", new File(DIR_TEMP.concat(fileName)));

        } catch (IOException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
    }

    /**
     *
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean excluirArquivo(String path, String fileName) throws IOException {

        if (path == null) {
            throw new IOException("Caminho inválido");
        }

        if (fileName == null) {
            throw new IOException("Arquivo inválido");
        }

        if (!Files.exists(Paths.get(path.concat(fileName)))) {
            return false;
        }

        return Files.deleteIfExists(Paths.get(path.concat(fileName)));
    }

    /**
     *
     * @param origem
     * @param destino
     * @return
     * @throws IOException
     */
    public static Path moverArquivo(String origem, String destino) throws IOException {

        if (!Files.exists(Paths.get(origem))) {
            return null;
        }

        return Files.move(Paths.get(origem), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     *
     * @param origem
     * @param destino
     * @return
     * @throws IOException
     */
    public static Path copiarArquivo(String origem, String destino) throws IOException {

        if (!Files.exists(Paths.get(origem))) {
            return null;
        }

        return Files.copy(Paths.get(origem), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     *
     * Cria um relatório
     * 
     * @param url informa o caminho aonde está salvo o arquivo
     * @param fields campos a serem comparados no banco de dados
     * @param values valores para comparação
     * @param titulo titulo da janela
     * @return true se o relatório for criado com sucesso
     *         false se o relatório não for criado
     */
    public static boolean gerarRelatorio(String url, String[] fields, String[] values, String titulo) {

        Connection con = null;
        con = Conexao.getConnection(con);
        if (!Conexao.isConectado(con)) {
            return false;
        }

        try {
            if (fields.length != values.length) {
                return false;
            }

            if (!Files.exists(Paths.get(url))) {
                return false;
            }

            final Map<String, Object> filter = new HashMap();

            for (int i = 0; i < values.length && i < fields.length; i++) {
                filter.put(fields[i], values[i]);
            }

            final JasperPrint print = JasperFillManager.fillReport(url, filter, con);
            if (print.getPages().size() <= 0) {
                throw new SiseducException("Desculpe, mas não foi possível abrir o arquivo");
            }
            
            JasperViewer viewer = new JasperViewer(print, false);

            viewer.setIconImage(new ImageIcon(viewer.getClass().getResource("/images/icone.png")).getImage());
            viewer.setTitle(titulo);
            viewer.setResizable(false);
            viewer.setVisible(true);
        } catch (JRException | SiseducException ex) {
            Siseduc.showMessage("Erro Relatório", ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            Conexao.closeConnection(con);
        }
        return true;
    }

    /**
     *
     * Recupera um objeto serializado.
     * 
     * @param url informa o caminho do arquivo
     * @return o objeto seriazado
     */
    public static Object getSer(final String url) {
        try (FileInputStream fis = new FileInputStream(url)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return ois.readObject();
            } catch (ClassNotFoundException | StreamCorruptedException ex) {
                Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
            }
        } catch (FileNotFoundException fe) {
            try {
                Files.createFile(Paths.get(url));
            } catch (IOException ex) {}
        } catch (IOException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return null;
    }

    /**
     *
     * Serializa um objeto
     * 
     * @param url informa o caminho aonde o arquivo será salvo.
     * @param obj o objeto que será serializado
     * @param info o tipo de serialização
     *             1 se for apenas um objeto
     *             0 se for uma lista de objetos
     */
    public static void serializar(final String url, Object obj, int info) {

        List<Object> iterator = (Files.exists(Paths.get(url)) && info == Logger.LOG) 
                ? (List<Object>) getSer(url) 
                : new ArrayList<>();
        try {
            iterator.add(obj);
        } catch (NullPointerException ne) {}

        try (FileOutputStream fos = new FileOutputStream(url)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject((info == Logger.LOG) ? iterator : obj);
            }
        } catch (IOException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
    }
    
    /**
     * 
     * @param path
     * @return 
     */
    public static List<Object> getObjects(String path) {
        final List<Object> objects = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                objects.add(line);
            }
        } catch (IOException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        }
        return objects;
    }
    
    /**
     * 
     * @param file
     * @param text 
     */
    private static void writeAsPDF(File file, String text) {
        final Document document = new Document();
        document.addAuthor("Juan Soares");
        document.addTitle(Siseduc.TITULO);
        document.addSubject("Numero do contrato");
        document.addCreationDate();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            
            Paragraph paragraph = new Paragraph();
            paragraph.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLDITALIC, BaseColor.BLUE));
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            paragraph.add(String.format("%1$td de %1$tB de %1$tY", Calendar.getInstance()));
            document.add(paragraph);
            
            final Image image = Image.getInstance("icone.png");
            image.scalePercent(20);
            image.setAlignment(Image.ALIGN_CENTER);
            document.add(image);
            
            paragraph = new Paragraph();
            paragraph.setFont(new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC, BaseColor.BLACK));
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            paragraph.add(Siseduc.TITULO);
            document.add(paragraph);
            
            paragraph = new Paragraph();
            paragraph.setFont(new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD, BaseColor.BLACK));
            paragraph.setSpacingBefore(30);
            paragraph.add(text);
            document.add(paragraph);
            
            paragraph = new Paragraph();
            paragraph.setSpacingBefore(30);
            Phrase phrase = new Phrase("Para mais informações acesse: ");
            phrase.setFont(new Font(Font.FontFamily.HELVETICA, 11));
            paragraph.add(phrase);
            paragraph.setFont(new Font(Font.FontFamily.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD, BaseColor.BLUE));
            paragraph.add("http:\\localhost\\getteacher\\");
            document.add(paragraph);  
        } catch (DocumentException | FileNotFoundException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } catch (IOException ex) {
            Siseduc.showMessage(Siseduc.TITULO, ex.getMessage(), TipoMensagem.MSG_ERROR);
        } finally {
            document.close();
        }
    }
    
    /**
     * 
     * @param file
     * @param text 
     */
    public static void saveAsPDF(File file, String text) {
        String fileName = file.getName();
        if (!fileName.endsWith(".pdf")) {
            file = new File(file.toString().concat(".pdf"));
        }
        
        if (file.exists()) {
            TipoMensagem response = Siseduc.showMessage(Siseduc.TITULO, file.getName() + " já existe.\nDeseja substituí-lo?", TipoMensagem.MSG_QUESTION);
            if (response != TipoMensagem.OPCAO_OK) {
                return;
            } 
        }        
        writeAsPDF(file, text);
    }
}
