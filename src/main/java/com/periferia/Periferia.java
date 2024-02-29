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
    //TODO: Implementar evidencia de las excepcioens

    /**
     * Navigates to the specified URL.
     * 
     * @param url the URL to navigate to
     */
    public static void goUrl(String url) {
        //TODO: Implementar logger: Se navega a la url
        driver.get(url);
    }

    /**
     * Sets up the SelfHealingDriver with the specified browser.
     *
     * @param navegador the browser to use
     * @return the initialized SelfHealingDriver
     */
    public static SelfHealingDriver setUp(Navegador navegador) {
        //TODO: Implementar logger: Driver {Navegador.type} inicializado
        driver = SeleniumDriver.initDriver(navegador);
        return driver;
    }

    public static SelfHealingDriver setUp(String navegador) {
        //TODO: Implementar logger: Driver {Navegador.type} inicializado
        Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());
        driver = SeleniumDriver.initDriver(navegadorEmun);
        return driver;
    }

    /**
     * Sets up the SelfHealingDriver with the specified browser and navigates to the specified URL.
     *
     * @param navegador the browser to use
     * @param url the URL to navigate to
     * @return the initialized SelfHealingDriver
     */
    public static SelfHealingDriver
    setUp(Navegador navegador, String url) {
        //TODO: Implementar logger: Driver {Navegador.type} inicializado y url proyecto
        driver = SeleniumDriver.initDriver(navegador);
        goUrl(url);
        return driver;
    }

    public static SelfHealingDriver setUp(String navegador, String url) {
        //TODO: Implementar logger: Driver {Navegador.type} inicializado
        Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());
        driver = SeleniumDriver.initDriver(navegadorEmun);
        goUrl(url);
        return driver;
    }

    /**
     * Closes the driver.
     */
    public static void closeDriver() {
        if (driver != null){
            driver.quit();
        }
    }

    /**
     * Clicks the element located by the given locator.
     *
     * @param locator the locator of the element to click
     */
    public static void click(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).click();
        logger.info("Clic sobre el elemento: {} ", locator);
    }

    /**
     * Click the element located by the given locator and captures evidence with the specified text.
     *
     * @param locator the locator of the element to click
     * @param textoEvidencia the text for capturing evidence
     */
    public static void click(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).click();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Clic sobre el elemento: {} ", locator);
    }

    /**
     * Sends the input text to the element located by the given locator.
     *
     * @param locator the locator of the element to send the text
     * @param inputText the text to be sent to the element
     */
    public static void sendkey(By locator, String inputText) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).sendKeys(inputText);
        logger.info("Se envia el texto: {}, al elemento {}", inputText, locator);
    }

    /**
     * Sends the input text to the element located by the given locator and captures evidence with the specified text.
     *
     * @param locator the locator of the element to send the text
     * @param inputText the text to be sent to the element
     * @param textoEvidencia the text for capturing evidence
     */
    public static void sendkey(By locator, String inputText, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).sendKeys(inputText);
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se envia el texto: {}, al elemento {}", inputText, locator);
    }

    /**
     * Clears the input field located by the given locator.
     *
     * @param locator the locator of the input field to clear
     */
    public static void clean(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).clear();
        logger.info("Se limpia el elemento: {}", locator);
    }

    /**
     * Clears the input field located by the given locator and captures evidence with the specified text.
     *
     * @param locator the locator of the input field to clear
     * @param textoEvidencia the text for capturing evidence
     */
    public static void clean(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        driver.findElement(locator).clear();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se limpia el elemento: {}", locator);
    }

    /**
     * Clears the input field located by the given locator.
     *
     * @param locator the locator of the input field to clear
     */
    public static String getText(By locator) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        String textoExtraido = driver.findElement(locator).getText();
        logger.info("Se extrajo el texto: {}, del elemento: {}", textoExtraido, locator);
        return textoExtraido;
    }

    /**
     * Clears the input field located by the given locator and captures evidence with the specified text.
     *
     * @param locator the locator of the input field to clear
     * @param textoEvidencia the text for capturing evidence
     */
    public static String getText(By locator, String textoEvidencia) {
        //TODO: Colocar una funcion para tener tiempo de espera dinamico
        String textoExtraido = driver.findElement(locator).getText();
        GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
        logger.info("Se extrajo el texto: {}, del elemento: {}", textoExtraido, locator);
        return textoExtraido;
    }

    public static boolean isDisplayed(By locator) {
    	//TODO: Implementar logger
    	try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
    
    public static boolean isDisplayed(By locator, String textoEvidencia) {
    	//TODO: Implementar logger
    	try {
    		GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
    
    public static boolean isEnabled(By locator) {
    	//TODO: Implementar logger
    	try {
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
    }

    public static boolean isEnabled(By locator, String textoEvidencia) {
    	//TODO: Implementar logger
    	try {
    		GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
    		return driver.findElement(locator).isEnabled();
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    public static boolean isSelected(By locator) {
    	//TODO: Implementar logger
    	try {
			return driver.findElement(locator).isSelected();
		} catch (Exception e) {
			return false;
		}
    }
    
    public static boolean isSelected(By locator, String textoEvidencia) {
    	//TODO: Implementar logger
    	try {
    		GenerarEvidencia.capturarEvidencia(driver, textoEvidencia);
    		return driver.findElement(locator).isSelected();
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    /**
     * Prints the object to the console.
     *
     * @param obj the object to be printed
     */
    public static void printConsole(Object obj) {
        System.out.println(obj);
    }
}
