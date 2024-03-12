package com.periferia;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.constantes.Navegador;
import com.periferia.driver_manager.SeleniumDriver;
import com.periferia.evidencia.GenerarEvidencia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Librería interna Equipo Automatización para pruebas QA en Periferia IT Group.
 *
 * @author Periferia IT Group - Automation Equipment
 */
public class Periferia {

    protected static SelfHealingDriver driver;
    protected static Logger log = LogManager.getLogger(Periferia.class);

    private Periferia() {
        throw new IllegalStateException("Utility class");
    }

    // getDriver
    public static SelfHealingDriver getDriver() {
        return driver;
    }

    // Set up
    /**
     * Configura y inicializa el controlador de Selenium.
     *
     * @param navegador El navegador que se utilizará para la prueba.
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     */
    public static SelfHealingDriver setUp(Navegador navegador) {
        try {
            log.info("Inicializando el driver... con el navegador {}", navegador);
            driver = SeleniumDriver.initDriver(navegador);
            log.info("Driver inicializado correctamente");
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    public static SelfHealingDriver setUp(Navegador navegador, String url) {
        try {
            log.info("Inicializando el driver... con el navegador {}", navegador);
            driver = SeleniumDriver.initDriver(navegador);
            log.info("Driver inicializado correctamente");
            Periferia.goUrl(url);
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    /**
     * Configura y inicializa el controlador de Selenium con el navegador especificado.
     *
     * @param navegador El nombre del navegador que se utilizará para la prueba (por ejemplo, "CHROME", "FIREFOX" o "EDGE").
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws IllegalArgumentException Si el nombre del navegador no es válido.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     */
    public static SelfHealingDriver setUp(String navegador) {
        try {
            Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());

            log.info("Inicializando el driver... con el navegador {}", navegadorEmun);
            driver = SeleniumDriver.initDriver(navegadorEmun);
            log.info("Driver inicializado correctamente");
        }catch (IllegalArgumentException e){
            log.fatal("El navegador no es valido: {}", e.getMessage());
            throw new IllegalArgumentException("El navegador no es valido", e);
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    public static SelfHealingDriver setUp(String navegador, String url) {
        try {
            Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());

            log.info("Inicializando el driver... con el navegador {}", navegadorEmun);
            driver = SeleniumDriver.initDriver(navegadorEmun);
            log.info("Driver inicializado correctamente");
            Periferia.goUrl(url);
        }catch (IllegalArgumentException e){
            log.fatal("El navegador no es valido: {}", e.getMessage());
            throw new IllegalArgumentException("El navegador no es valido", e);
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    // Tear down
    /**
     * Cierra el controlador de Selenium si está inicializado.
     */
    public static void tearDown() {
        if (driver != null){
            driver.quit();
            log.info("Driver cerrado correctamente");
        }
    }

    // Go url
    public static void goUrl(String url) {
        try {
            driver.get(url);
            log.info("Navegando a la url: {}", url);
        }catch (WebDriverException e){
            log.fatal("Error al navegar a la url: {}", e.getMessage());
            throw new WebDriverException("No se pudo navegar a la url", e);
        }
    }

    // Find element
    public static WebElement findElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.debug("Elemento encontrado: {}", locator);
            return element;
        } catch (Exception e) {
            log.fatal("No se encontro el elemento: {}, dentro del tiempo de 3 seg. Falla {}", locator, e.getMessage());
            throw new NoSuchElementException("EL elemento no fue encontrado entre del tiempo de espera de 3 seg: " + locator);
        }
    }
    public static WebElement findElement(By locator, int tiempoEspera) {
        WebDriverWait wait = new WebDriverWait(driver, tiempoEspera);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.debug("Elemento encontrado: {}", locator);
            return element;
        } catch (Exception e) {
            log.fatal("No se encontro el elemento: {}, dentro del tiempo de {} seg. Falla {}", locator, tiempoEspera, e.getMessage());
            throw new NoSuchElementException("EL elemento "+ locator +" no fue encontrado entre del tiempo de espera de " + tiempoEspera + " seg");
        }
    }

    // Click
    public static void click(By locator) {
        try {
            WebElement element = findElement(locator);
            element.click();
            log.info("Click en el elemento: {}", locator);
        } catch (ElementNotInteractableException e) {
            log.error("No fue posible realizar clic sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }
    public static void click(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.click();
            log.info("Click en el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
        } catch (ElementNotInteractableException e) {
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }
    public static void click(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            element.click();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.info("Click en el elemento: {} y correcto guardo de evidencia", locator);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }
    public static void click(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.click();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.info("Click en el elemento: {}, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }
    public static void printConsole(Object message){
        System.out.println(message);
    }
}
