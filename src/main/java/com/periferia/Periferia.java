package com.periferia;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.constantes.Navegador;
import com.periferia.driver_manager.SeleniumDriver;
import com.periferia.evidencia.GenerarEvidencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Librería interna Equipo Automatización para pruebas QA en Periferia IT Group.
 *
 * @author Periferia IT Group - Automation Equipment
 */
public class Periferia {
    protected static SelfHealingDriver driver;

    private static final Logger logger = LogManager.getLogger(Periferia.class);

    private Periferia() {
        throw new IllegalStateException("Utility class");
    }

    //FIXME: Implementar correcto manejo de excepsiones

    public static void goUrl(String url) {
        driver.get(url);
    }

    public static SelfHealingDriver setUp(Navegador navegador) {
        driver = SeleniumDriver.initDriver(navegador);
        return driver;
    }

    public static SelfHealingDriver setUp(Navegador navegador, String url) {
        driver = SeleniumDriver.initDriver(navegador);
        goUrl(url);
        return driver;
    }

    public static void closeDriver() {
        if (driver != null){
            driver.quit();
        }
    }

    public static void click(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).click();
        logger.info("Clic sobre el elemento: {} ", locator);
    }

    public static void click(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).click();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Clic sobre el elemento: {} ", locator);
    }

    public static void sendkey(By locator, String inputText) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).sendKeys(inputText);
        logger.info("Se envia el texto: {}, al elemento {}", inputText, locator);
    }

    public static void sendkey(By locator, String inputText, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).sendKeys(inputText);
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se envia el texto: {}, al elemento {}", inputText, locator);
    }

    public static void clean(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).clear();
        logger.info("Se limpia el elemento: {}", locator);
    }

    public static void clean(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).clear();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se limpia el elemento: {}", locator);
    }

    public static String getText(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        String textoExtraido = driver.findElement(locator).getText();
        logger.info("Se extrajo el texto: {}, del elemento: {}", textoExtraido, locator);
        return textoExtraido;
    }

    public static String getText(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        String textoExtraido = driver.findElement(locator).getText();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se extrajo el texto: {}, del elemento: {}", textoExtraido, locator);
        return textoExtraido;
    }

    public static void printConsole(Object obj) {
        System.out.println(obj);
    }
}
