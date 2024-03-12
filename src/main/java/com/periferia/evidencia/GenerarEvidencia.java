package com.periferia.evidencia;

import com.periferia.Periferia;
import com.periferia.utilities.CaptureScreen;
import com.periferia.utilities.GenerarReportePDF;
import com.periferia.utilities.GenerarReporteVideo;
import com.periferia.utilities.GestorArchivos;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Clase para la creacion de evidencias
 */
public class GenerarEvidencia {
    private static final String EVIDENCE_PATH = "./outputData/";

    private static File rutaCarpeta;

    //FIXME: Condicional para la generacion de evidencia desde un archivo externo

    private GenerarEvidencia() {
        throw new IllegalStateException("Utility class");
    }

    public static void capturarEvidencia(WebDriver driver, String texto) {
        String rutaImg = CaptureScreen.captureScreen(driver, rutaCarpeta);
        GenerarReportePDF.createBody(rutaImg, texto);
        GestorArchivos.eliminarArchivo(rutaImg);
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
        Periferia0.printConsole("La evidencia se guardo en: " + rutaPngEvidencia.getPath());

        GenerarReportePDF.createTemplate(rutaCarpeta, nameTest, nameAnalyst, urlPage);
        GenerarReporteVideo.startRecording(rutaCarpeta, nameTest);

        //TODO: Implementar logger: Se inicio la creacion de las evidencias
    }

    public static void finalizarEvidencia() {
        GenerarReportePDF.closeTemplate();
        GenerarReporteVideo.stopRecording();

        //TODO: Implementar logger: Se finalizo la creacion de las evidencias
    }
}
