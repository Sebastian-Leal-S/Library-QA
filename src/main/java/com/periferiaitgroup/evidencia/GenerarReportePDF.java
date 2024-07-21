package com.periferiaitgroup.evidencia;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.periferiaitgroup.utilities.HoraSistema;
import com.periferiaitgroup.utilities.TiempoEjecucion;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase utilitaria para generar reportes PDF con diferentes secciones, incluyendo
 * encabezados, cuerpo del documento, errores y sugerencias ortográficas.
 */
public class GenerarReportePDF {

    private static final String MARCA_DE_AGUA_PNG = "./imagenes/marcaDeAgua.png";
    private static final String LOGO_PNG = "./imagenes/LogoClient.png";
    private static final Document documento = new Document();
    private static boolean documentoCreado = false;
    private static int imgContador = 0;

    private static final Font FONT_HEADER = FontFactory.getFont("ITALIC", 12, BaseColor.GRAY);
    private static final Font FONT_MAIN = FontFactory.getFont("ARIAL", 12, BaseColor.BLACK);
    private static final Font FONT_PAGINATION = FontFactory.getFont("ARIAL", 12, BaseColor.GRAY);
    private static final Font FONT_ERROR = FontFactory.getFont("TIMES", 12, Font.NORMAL, BaseColor.RED);

    private static final String FORMATO_FECHA = "HH:mm:ss";

    /**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     * 
     * @throws IllegalStateException si se intenta instanciar la clase.
     */
    private GenerarReportePDF() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Crea la plantilla del reporte PDF con los datos iniciales.
     * 
     * @param filePath la ruta donde se guardará el archivo PDF.
     * @param nameTest el nombre de la prueba.
     * @param nameAnalyst el nombre del analista.
     * @param url la URL de la aplicación de prueba.
     */
    public static void createTemplate(File filePath, String nameTest, String nameAnalyst, String url) {
        try {
            TiempoEjecucion.start();
            String nameFile = "\\" + nameTest + "_" + HoraSistema.currentDate("HH-mm-ss") + ".pdf";
            FileOutputStream archivo = new FileOutputStream(filePath + nameFile);
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);

            Image logoHeader = Image.getInstance(LOGO_PNG);
            logoHeader.scalePercent(50);
            logoHeader.setWidthPercentage(100);

            Paragraph titulo = new Paragraph(
                    "Caso de Prueba: " + nameTest + "\n\nFecha Ejecucion: " + HoraSistema.currentDate("dd/MM/yyyy")
                            + "\nAnalista: " + nameAnalyst + "\nTipo de Prueba: Automatizada",
                    FONT_HEADER);
            titulo.setAlignment(1);

            Paragraph urlApp = new Paragraph("Url de prueba: " + url);
            urlApp.setAlignment(Element.ALIGN_LEFT);
            urlApp.setFont(FONT_MAIN);

            Paragraph parrafoHoraInicio = new Paragraph("Hora de inicio: " + HoraSistema.currentDate(FORMATO_FECHA));
            parrafoHoraInicio.setAlignment(Element.ALIGN_LEFT);
            parrafoHoraInicio.setFont(FONT_MAIN);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            PdfPCell p1 = new PdfPCell(titulo);
            p1.setHorizontalAlignment(1);
            p1.setVerticalAlignment(2);
            p1.setBorderColor(BaseColor.GRAY);

            PdfPCell p2 = new PdfPCell(logoHeader);
            p2.setHorizontalAlignment(1);
            p2.setVerticalAlignment(2);
            p2.setBorderColor(BaseColor.GRAY);
            table.addCell(p2);
            table.addCell(p1);

            documento.setMargins(60, 40, 30, 30);
            documento.open();
            documento.add(table);

            Footer headerFooter = new Footer();

            writer.setPageEvent(headerFooter);

            documento.add(urlApp);
            documento.add(parrafoHoraInicio);

            documentoCreado = true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega el cuerpo del documento con una imagen y un mensaje.
     * 
     * @param rutaImagen la ruta de la imagen a agregar.
     * @param mensaje el mensaje a incluir en el documento.
     */
    public static void createBody(String rutaImagen, String mensaje) {
        if (!documentoCreado) {
            return;
        }

        try {
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Element.ALIGN_LEFT);
            parrafo.setFont(FONT_MAIN);
            parrafo.add("\n\nAccion: " + mensaje + "\n");
            documento.add(parrafo);

            Image imagen = Image.getInstance(rutaImagen);
            imagen.scalePercent(26, 26);
            imagen.setAlignment(Element.ALIGN_CENTER);
            imagen.setBorder(Rectangle.BOX);
            imagen.setBorderWidth(3);
            imagen.setBorderColor(BaseColor.BLACK);
            documento.add(imagen);
            imgContador = imgContador + 1;
            if (imgContador == 2) {
                documento.newPage();
                imgContador = 0;
            }
        }catch (DocumentException e) {
            System.err.println("Falla al añadir el parrafo al pdf\n" + e);
        }catch (IOException e) {
            System.err.println("Algo fallo no fue posible obtener la imagen de la evidecia\n" + e);
        }
    }

    /**
     * Agrega el cuerpo del documento con una imagen, un mensaje de error y un localizador.
     * 
     * @param rutaImagen la ruta de la imagen a agregar.
     * @param errorMessage el mensaje de error a incluir en el documento.
     * @param locator el localizador del elemento donde ocurrió el error.
     */
    public static void createErrorBody(String rutaImagen, String errorMessage, By locator) {
        if (!documentoCreado) {
            return;
        }

        try {
            //TODO: Comprobrar la redundancia de este parrafo
            Paragraph localizador = new Paragraph();
            localizador.setAlignment(Element.ALIGN_LEFT);
            localizador.setFont(FONT_MAIN);
            localizador.add("Acción: " + locator);
            documento.add(localizador);
            Image imagen = null;
            imagen = Image.getInstance(rutaImagen);
            imagen.scalePercent(25, 35);
            imagen.scaleToFit(500, 500);
            imagen.setAlignment(Element.ALIGN_CENTER);
            documento.add(imagen);
            imgContador = imgContador + 1;
            if (imgContador == 2) {
                documento.newPage();
                imgContador = 0;
            }
            Paragraph elementoError = new Paragraph();
            elementoError.setAlignment(Element.ALIGN_LEFT);
            elementoError.setFont(FONT_ERROR);
            elementoError.add("\nFallo al momento de interactuar con: " + locator + "/n");

            Paragraph parrafoError = new Paragraph();
            parrafoError.setAlignment(Element.ALIGN_LEFT);
            parrafoError.setFont(FONT_ERROR);
            parrafoError.add("\nMensaje de la accion fallida: " + errorMessage + "/n");
            documento.add(elementoError);
            documento.add(parrafoError);
            documento.close();
        }catch (DocumentException e) {
            System.err.println("Falla al añadir el parrafo al pdf\n" + e);
        }catch (IOException e) {
            System.err.println("Algo fallo no fue posible obtener la imagen de la evidecia\n" + e);
        }
    }

    /**
     * Agrega sugerencias ortográficas al documento PDF.
     * 
     * @param palabraConError la palabra con error ortográfico.
     * @param sugerencias las sugerencias de corrección ortográfica.
     */
    public static void addSugerenciaOrtografia(String palabraConError, String sugerencias) {
        try {
            Paragraph title = new Paragraph();
            title.setAlignment(Element.ALIGN_LEFT);
            title.setFont(FONT_ERROR);
            title.add("Posible falta ortográfica!!\n");
            documento.add(title);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Element.ALIGN_LEFT);
            parrafo.setFont(FONT_MAIN);
            parrafo.add(palabraConError + "\n" + sugerencias);

            documento.add(parrafo);
        } catch (DocumentException e) {
            System.err.println("Falla al añadir el parrafo al pdf\n" + e);
        }
    }

    /**
     * Cierra la plantilla del reporte PDF agregando la hora de finalización y el tiempo de ejecución.
     */
    public static void closeTemplate() {
        try {
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Element.ALIGN_RIGHT);
            parrafo.setFont(FONT_MAIN);
            parrafo.add("Hora de Finalización: " + HoraSistema.currentDate(FORMATO_FECHA) + "\n");
            parrafo.add("Tiempo de Ejecución: " + TiempoEjecucion.getRunTime() + "\n");
            documento.add(Chunk.NEWLINE);
            documento.add(parrafo);
            documento.close();

            documentoCreado = false;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase interna para agregar encabezados y pies de página al documento PDF.
     */
    private static class Footer extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            float pageWidthHalf = (document.getPageSize().getLeft() + document.getPageSize().getRight()) / 2;

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase("Pagina: " + writer.getPageNumber(), FONT_PAGINATION), pageWidthHalf, 20, 0);
            PdfContentByte cb = writer.getDirectContentUnder();
            PdfGState trans = new PdfGState();

            try {
                Image logoFooter = Image.getInstance(MARCA_DE_AGUA_PNG);
                logoFooter.setAbsolutePosition(0, 0);
                logoFooter.scalePercent(23);
                trans.setFillOpacity(1f);
                cb.setGState(trans);
                cb.addImage(logoFooter);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Convierte un archivo PDF en una lista de cadenas de texto.
     * 
     * @param file la ruta del archivo PDF.
     * @return una lista de cadenas de texto que representa el contenido del PDF.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public static ArrayList<String> pdfATexto(String file) throws IOException {
        ArrayList<String> lineasPDF = new ArrayList<>();
        // LEEMOS EL ARCHIVO
        PdfReader readerPDF = new PdfReader(file);
        // LO CONVERTIMOS EN DOCUMENTO EN UNA VARIABLE
        try (PdfDocument documentPDF = new PdfDocument(readerPDF)) {
            // LEE LAS LINEAS DEL PDF
            int num = documentPDF.getNumberOfPages();
            // RECORRE CADA LINEA Y LAS AGREGA A UN ARRAY
            for (int i = 1; i <= num; i++) {
                String linea = PdfTextExtractor.getTextFromPage(documentPDF.getPage(i));
                lineasPDF.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineasPDF;
    }
}
