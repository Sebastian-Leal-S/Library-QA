package com.periferia.evidencia;

import com.periferia.Periferia;
import com.periferia.utilities.CaptureScreen;
import com.periferia.utilities.GenerarReportePDF;
import com.periferia.utilities.GenerarReporteVideo;
import com.periferia.utilities.GestorArchivos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Clase para la creacion de evidencias
 */
public class GenerarEvidencia {
    private static final String EVIDENCE_PATH = "./outputData/";
    private static File rutaPngEvidencia;

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
        rutaPngEvidencia = GestorArchivos.crearCarpetaEvidencia(EVIDENCE_PATH, nameTest);
        //FIXME: Cambiar a logger
        Periferia.printConsole("La evidencia se guardo en: " + rutaPngEvidencia.getPath());

        GenerarReportePDF.createTemplate(rutaPngEvidencia, nameTest, nameAnalyst, urlPage);
        GenerarReporteVideo.startRecording(rutaPngEvidencia, nameTest);

        //TODO: Implementar logger: Se inicio la creacion de las evidencias
    }

    public static void finalizarEvidencia() {
        GenerarReportePDF.closeTemplate();
        GenerarReporteVideo.stopRecording();

        //TODO: Implementar logger: Se finalizo la creacion de las evidencias
    }

    public static void capturarEvidencia(WebDriver driver, String mensajeEvidencia) {
        String rutaImg = CaptureScreen.captureScreen(driver, rutaPngEvidencia);
        GenerarReportePDF.createBody(rutaImg, mensajeEvidencia);
        GestorArchivos.eliminarArchivo(rutaImg);
    }

    public static void capturarEvidencia(WebDriver driver, String mensajeError, By locator) {
        String rutaImg = CaptureScreen.captureScreen(driver, rutaPngEvidencia);
        GenerarReportePDF.createErrorBody(rutaImg, mensajeError, locator);
        GestorArchivos.eliminarArchivo(rutaImg);
    }
}
