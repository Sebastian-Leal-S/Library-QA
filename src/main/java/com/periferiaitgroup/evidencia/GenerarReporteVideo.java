package com.periferiaitgroup.evidencia;

import com.periferiaitgroup.utilities.HoraSistema;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MIME_AVI;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

/**
 * Clase para generar reportes de video mediante la grabación de la pantalla.
 * Extiende la funcionalidad de ScreenRecorder para personalizar la creación del archivo de video.
 */
public class GenerarReporteVideo extends ScreenRecorder {

    private static ScreenRecorder screenRecorder;
    private final String nameFile;
    
    /**
     * Constructor privado para inicializar los parámetros de grabación de video.
     * 
     * @param cfg            la configuración gráfica utilizada para la grabación.
     * @param captureArea    el área de la pantalla a capturar.
     * @param fileFormat     el formato del archivo de video.
     * @param screenFormat   el formato de video de la pantalla.
     * @param mouseFormat    el formato del video del mouse.
     * @param audioFormat    el formato del audio.
     * @param movieFolder    la carpeta donde se guardará el video.
     * @param nameFile       el nombre del archivo de video.
     * @throws IOException   si ocurre un error de entrada/salida.
     * @throws AWTException  si ocurre un error relacionado con AWT.
     */
    private GenerarReporteVideo(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String
            nameFile) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.nameFile = nameFile;
    }

    /**
     * Crea el archivo de video con el nombre y formato especificados.
     * 
     * @param fileFormat el formato del archivo de video.
     * @return el archivo de video creado.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        String horaActual = HoraSistema.currentDate("HH-mm-ss");

        return new File(movieFolder, nameFile + "_" + horaActual + "." + Registry.getInstance().getExtension(fileFormat));
    }

    /**
     * Inicia la grabación de la pantalla y guarda el archivo de video en la ruta especificada.
     * 
     * @param filePath la ruta donde se guardará el archivo de video.
     * @param fileName el nombre del archivo de video.
     */
    public static void startRecording(File filePath, String fileName) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        try {
            screenRecorder = new GenerarReporteVideo(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, filePath, fileName);
        } catch (IOException e) {
            System.err.println("Falla de entrada/salida \n" + e);
        } catch (AWTException e) {
            System.err.println("Falla con \n" + e);
        }

        try {
            screenRecorder.start();
        } catch (IOException e) {
            System.err.println("Fallas al inciaiar la grabacion" + e);
        }

    }

    /**
     * Detiene la grabación de la pantalla.
     */
    public static void stopRecording(){
        try {
            screenRecorder.stop();
        }catch (IOException e) {
            System.err.println(e);
        }
    }
}