package com.periferiaitgroup.evidencia;

import com.periferiaitgroup.utilities.CaptureScreen;
import com.periferiaitgroup.utilities.GestorArchivos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Clase para la creacion de evidencias
 */
public class GenerarEvidencia {
    private static final String EVIDENCE_PATH = "./outputData/";
    private static File rutaCarpetaEvidencia;
    protected static Logger log = LogManager.getLogger(GenerarEvidencia.class);

    /**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     * 
     * @throws IllegalStateException si se intenta instanciar la clase.
     */
    private GenerarEvidencia() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Metodo para iniciar la generacion de evidencias se genera un reporte en pdf y un video de la ejecucion.
     *
     * @param nameTest Nombre del test ejecutado
     * @param nameAnalyst Nombre del analista al que queda la ejecucion
     * @param urlPage Url de la pagina a la cual se le realizara el test automatizado
     */
    public static void iniciarEvidencia(String nameTest, String nameAnalyst, String urlPage) {

        GestorArchivos.eliminarCarpeta(EVIDENCE_PATH);
        rutaCarpetaEvidencia = GestorArchivos.crearCarpetaEvidencia(EVIDENCE_PATH, nameTest);
        log.info("La evidencia se guardo en {} ", rutaCarpetaEvidencia.getPath());

        GenerarReportePDF.createTemplate(rutaCarpetaEvidencia, nameTest, nameAnalyst, urlPage);
        GenerarReporteVideo.startRecording(rutaCarpetaEvidencia, nameTest);

        log.info("Se inicio la creacion de las evidencias");
    }

    /**
     * Metodo para finalizar la generacion de evidencias se cierra el reporte en pdf y el video de la ejecucion.
     */
    public static void finalizarEvidencia() {
        GenerarReportePDF.closeTemplate();
        GenerarReporteVideo.stopRecording();

        log.info("Se finalizo la creacion de las evidencias");
    }

    /**
     * Metodo para capturar evidencia en el reporte en pdf
     *
     * @param driver           WebDriver para tomar captura de pantalla
     * @param mensajeEvidencia Mensaje que se mostrara en el reporte
     */
    public static void capturarEvidencia(WebDriver driver, String mensajeEvidencia) {
        String rutaImg = CaptureScreen.captureScreen(driver, rutaCarpetaEvidencia);
        GenerarReportePDF.createBody(rutaImg, mensajeEvidencia);
        GestorArchivos.eliminarArchivo(rutaImg);
    }

    /**
     * Metodo para capturar evidencia en el reporte en pdf
     *
     * @param driver WebDriver para tomar captura de pantalla
     * @param mensajeError Mensaje que se mostrara en el reporte
     * @param locator Localizador del elemento que genero el error
     */
    public static void capturarEvidencia(WebDriver driver, String mensajeError, By locator) {
        String rutaImg = CaptureScreen.captureScreen(driver, rutaCarpetaEvidencia);
        GenerarReportePDF.createErrorBody(rutaImg, mensajeError, locator);
        GestorArchivos.eliminarArchivo(rutaImg);
    }

    /**
     * Captura evidencia de un error ortografico y su sugerencia correspondiente en un reporte PDF.
     * 
     * @param error la descripción del error ortográfico encontrado.
     * @param sugerencia la sugerencia de corrección para el error ortográfico.
     */
    public static void capturarEvidencia(String error, String sugerencia) {
        GenerarReportePDF.addSugerenciaOrtografia(error, sugerencia);
    }
}
