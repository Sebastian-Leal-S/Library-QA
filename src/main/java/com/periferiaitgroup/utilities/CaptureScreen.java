package com.periferiaitgroup.utilities;

import com.epam.healenium.SelfHealingDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Clase utilitaria para capturar pantallas durante la ejecución de pruebas automatizadas con Selenium WebDriver.
 */
public class CaptureScreen {
	
	 /**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     * 
     * @throws IllegalStateException si se intenta instanciar la clase.
     */
	private CaptureScreen() {
        throw new IllegalStateException("Utility class");
    }
	
	/**
     * Captura una imagen de la pantalla actual del navegador y la guarda en la ruta especificada.
     * 
     * @param driver el WebDriver que se está utilizando para la automatización.
     * @param rutaCarpeta la carpeta donde se guardará la imagen de la captura de pantalla.
     * @return la ruta completa del archivo de imagen capturado.
     */
    public static String captureScreen(WebDriver driver, File rutaCarpeta) {
        String hora = HoraSistema.currentDate("HH-mm-ss");

        WebDriver delegatedDriver = ((SelfHealingDriver) driver).getDelegate();

        File srcFile = ((TakesScreenshot) delegatedDriver).getScreenshotAs(OutputType.FILE);

        String pathImage = rutaCarpeta + "\\" + hora + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(pathImage));
        }catch (IOException e){
            System.out.println("Falla al tomar la captura de pantalla\n" + e);
        }

        return new File(pathImage).toString();
    }
}