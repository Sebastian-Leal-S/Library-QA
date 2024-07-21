package com.periferiaitgroup;

import com.epam.healenium.SelfHealingDriver;
import com.periferiaitgroup.constantes.Navegador;
import com.periferiaitgroup.driver_manager.SeleniumDriver;
import com.periferiaitgroup.evidencia.GenerarEvidencia;
import com.periferiaitgroup.utilities.RevisorOrtografico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.languagetool.rules.RuleMatch;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Librería interna Equipo Automatización para pruebas QA en Periferia IT Group.
 *
 * @author Periferia IT Group - Automation Equipment
 */
public class Periferia {

    protected static SelfHealingDriver driver;
    protected static final int TIMEOUT = 3;
    protected static Logger log = LogManager.getLogger(Periferia.class);

    private Periferia() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Obtiene el controlador de Selenium.
     *
     * @return El controlador de Selenium.
     */
    public static SelfHealingDriver getDriver() {
        return driver;
    }

    /**
     * Configura y inicializa el controlador de Selenium.
     *
     * @param navegador El navegador que se utilizará para la prueba.
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     *<pre>
	 * {@code
	 * Periferia.setUp(Navegador.CHROME);
	 * }
	 * </pre>
     */
    public static SelfHealingDriver setUp(Navegador navegador) {
        try {
            driver = SeleniumDriver.initDriver(navegador);
            log.info("Driver iniciado correctamente con el navegador {}", navegador);
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    /**
     * Configura y inicializa el controlador de Selenium con el navegador especificado y navega a la URL proporcionada.
     *
     * @param navegador El navegador que se utilizará para la prueba.
     * @param url       La URL a la que se desea navegar.
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     * 
     *<pre>
	 * {@code
	 * String url = "www.prueba.com"
	 * Periferia.setUp(Navegador.CHROME, url);
	 * }
	 * </pre>
     */
    public static SelfHealingDriver setUp(Navegador navegador, String url) {
        try {
            driver = SeleniumDriver.initDriver(navegador);
            log.info("Driver iniciado correctamente con el navegador {}", navegador);

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
     * @param navegador El nombre del navegador que se utilizará para la prueba ("CHROME", "FIREFOX" o "EDGE").
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws IllegalArgumentException Si el nombre del navegador no es válido.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     */
    public static SelfHealingDriver setUp(String navegador) {
        try {
            Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());
            driver = SeleniumDriver.initDriver(navegadorEmun);
            log.info("Driver inciado correctamente con el navegador {}", navegadorEmun);
        }catch (IllegalArgumentException e){
            log.fatal("El navegador no es valido: {}", e.getMessage());
            throw new IllegalArgumentException("El navegador no es valido", e);
        }catch (WebDriverException e){
            log.fatal("Error al inicializar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo inicializar el driver", e);
        }
        return driver;
    }

    /**
     * Configura y inicializa el controlador de Selenium con el navegador especificado y navega a la URL proporcionada.
     *
     * @param navegador El nombre del navegador que se utilizará para la prueba (por ejemplo, "CHROME", "FIREFOX" o "EDGE").
     * @param url La URL a la que se desea navegar.
     * @return El controlador de Selenium configurado y listo para usar.
     * @throws IllegalArgumentException Si el nombre del navegador no es válido.
     * @throws WebDriverException Si ocurre un error al inicializar el controlador de Selenium.
     */
    public static SelfHealingDriver setUp(String navegador, String url) {
        try {
            Navegador navegadorEmun = Navegador.valueOf(navegador.toUpperCase());
            driver = SeleniumDriver.initDriver(navegadorEmun);
            log.info("Driver iniciado correctamente con el navegador {}", navegadorEmun);

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

    /**
     * Cierra el controlador de Selenium si está inicializado.
     */
    public static void tearDown() {
        try {
            if (driver != null) {
                Thread.sleep(2000);
                driver.close();
                driver.quit();
                log.info("Driver cerrado correctamente");
            }
        } catch (WebDriverException | InterruptedException e) {
            log.fatal("Error al cerrar el controlador: {}", e.getMessage());
            throw new WebDriverException("No se pudo cerrar el driver", e);
        }
    }

    /**
     * Navega a la URL proporcionada.
     *
     * @param url La URL a la que se desea navegar.
     * @throws WebDriverException Si ocurre un error al navegar a la URL.
     */
    public static void goUrl(String url) {
        try {
            driver.get(url);
            log.info("Navegando a la url: {}", url);
        }catch (WebDriverException e){
            log.fatal("Error al navegar a la url: {}", e.getMessage());
            throw new WebDriverException("No se pudo navegar a la url", e);
        }
    }

    /**
     * Encuentra un elemento web utilizando el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @return El elemento web encontrado.
     * @throws NoSuchElementException Si el elemento web no se encuentra en el tiempo de espera de 3 segundos.
     */
    public static WebElement findElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.debug("Elemento encontrado: {}", locator);
            return element;
        } catch (Exception e) {
            log.fatal("No se encontro el elemento: {}, dentro del tiempo de 3 seg. Falla {}", locator, e.getMessage());
            throw new NoSuchElementException("EL elemento no fue encontrado entre del tiempo de espera de 3 seg: " + locator);
        }
    }

    /**
     * Encuentra un elemento web utilizando el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @return El elemento web encontrado.
     * @throws NoSuchElementException Si el elemento web no se encuentra en el tiempo de espera proporcionado.
     */
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

    /**
     * Encuentra todos los elementos web que coinciden con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar los elementos web.
     * @return Una lista de elementos web encontrados.
     * @throws NoSuchElementException Si los elementos web no se encuentran en el tiempo de espera de 3 segundos.
     */
    public static List<WebElement> findElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            log.debug("Elementos encontrados: {}", locator);
            return elements;
        } catch (Exception e) {
            log.fatal("No se encontraron elementos: {}, dentro del tiempo de 3 seg. Falla {}", locator, e.getMessage());
            throw new NoSuchElementException("Los elementos no fueron encontrados entre del tiempo de espera de 3 seg: " + locator);
        }
    }

    /**
     * Encuentra todos los elementos web que coinciden con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar los elementos web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar los elementos web.
     * @return Una lista de elementos web encontrados.
     * @throws NoSuchElementException Si los elementos web no se encuentran en el tiempo de espera proporcionado.
     */
    public static List<WebElement> findElements(By locator, int tiempoEspera) {
        WebDriverWait wait = new WebDriverWait(driver, tiempoEspera);
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            log.debug("Elementos encontrados: {}", locator);
            return elements;
        } catch (Exception e) {
            log.fatal("No se encontraron elementos: {}, dentro del tiempo de {} seg. Falla {}", locator, tiempoEspera, e.getMessage());
            throw new NoSuchElementException("Los elementos " + locator + " no fueron encontrados entre del tiempo de espera de " + tiempoEspera + " seg");
        }
    }

    /**
     * Realiza clic en el elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera de 3 segundos.
     */
    public static void click(By locator) {
        try {
            WebElement element = findElement(locator);
            element.click();
            log.debug("Click en el elemento: {}", locator);
        } catch (ElementNotInteractableException e) {
            log.error("No fue posible realizar clic sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza clic en el elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera proporcionado.
     */
    public static void click(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.click();
            log.debug("Click en el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
        } catch (ElementNotInteractableException e) {
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza clic en el elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera de 3 segundos.
     */
    public static void click(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            element.click();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Click en el elemento: {} y correcto guardo de evidencia", locator);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza clic en el elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera proporcionado.
     */
    public static void click(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.click();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Click en el elemento: {}, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar clic sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Envía texto al elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param texto El texto que se desea enviar al elemento web.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera de 3 segundos.
     */
    public static void sendKeys(By locator, String texto) {
        try {
            WebElement element = findElement(locator);
            element.sendKeys(texto);
            log.debug("Envio de texto '{}' sobre el elemento: {}", texto, locator);
        } catch (Exception e) {
            log.fatal("No fue posible realizar el envio de texto sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Envía texto al elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param texto El texto que se desea enviar al elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera proporcionado.
     */
    public static void sendKeys(By locator, String texto, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.sendKeys(texto);
            log.debug("Envio de texto '{}' sobre el elemento: {}, dentro del tiempo {} seg", texto, locator, tiempoEspera);
        } catch (Exception e) {
            log.fatal("No fue posible realizar el envio de texto sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Envía texto al elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param texto El texto que se desea enviar al elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera de 3 segundos.
     */
    public static void sendKeys(By locator, String texto, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            element.sendKeys(texto);
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Envio de texto '{}' sobre el elemento: {} y correcto guardo de evidencia", texto, locator);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar el envio de texto sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Envía texto al elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @param texto El texto que se desea enviar al elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @throws ElementNotInteractableException Si el elemento web no es interactuable en el tiempo de espera proporcionado.
     */
    public static void sendKeys(By locator, String texto, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.sendKeys(texto);
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Envio de texto '{}' sobre el elemento: {}, dentro del tiempo {} seg y correcto guardo de evidencia", texto, locator, tiempoEspera);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible realizar el envio de texto sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el texto del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @return El texto del elemento web.
     * @throws NoSuchElementException Si el texto del elemento web no se encuentra en el tiempo de espera de 3 segundos.
     */
    public static String getText(By locator) {
        try {
            WebElement element = findElement(locator);
            String text = element.getText();
            log.debug("Se obtuvo el texto '{}' sobre el elemento: {}", text, locator);
            return text;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el texto sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el texto del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator      El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @return El texto del elemento web.
     * @throws NoSuchElementException Si el texto del elemento web no se encuentra en el tiempo de espera proporcionado.
     */
    public static String getText(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            String text = element.getText();
            log.debug("Se obtuvi texto sobre el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
            return text;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el texto sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el texto del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator          El localizador utilizado para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @return El texto del elemento web.
     * @throws NoSuchElementException Si el texto del elemento web no se encuentra en el tiempo de espera de 3 segundos.
     */
    public static String getText(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            String text = element.getText();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Obtener texto sobre el elemento: {} y correcto guardo de evidencia", locator);
            return text;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible obtener el texto sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el texto del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator          El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera     El tiempo de espera en segundos para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @return El texto del elemento web.
     * @throws NoSuchElementException Si el texto del elemento web no se encuentra en el tiempo de espera proporcionado.
     */
    public static String getText(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            String text = element.getText();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Obtener texto sobre el elemento: {}, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
            return text;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible obtener el texto sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Limpia el contenido del campo identificado por el locator especificado.
     *
     * @param locator El locator del elemento cuyo campo se desea limpiar.
     * @throws NoSuchElementException Si no es posible limpiar el campo del elemento.
     */
    public static void clear(By locator) {
        try {
            WebElement element = findElement(locator);
            element.clear();
            log.debug("Limpiar campo sobre el elemento: {}", locator);
        } catch (Exception e) {
            log.fatal("No fue posible limpiar el campo sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Limpia el contenido del campo identificado por el locator especificado.
     *
     * @param locator El locator del elemento cuyo campo se desea limpiar.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @throws NoSuchElementException Si no es posible limpiar el campo del elemento.
     */
    public static void clear(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.clear();
            log.debug("Limpiar campo sobre el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
        } catch (Exception e) {
            log.fatal("No fue posible limpiar el campo sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Limpia el contenido del campo identificado por el locator especificado.
     *
     * @param locator El locator del elemento cuyo campo se desea limpiar.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     * @throws NoSuchElementException Si no es posible limpiar el campo del elemento.
     */
    public static void clear(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            element.clear();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Limpiar campo sobre el elemento: {} y correcto guardo de evidencia", locator);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible limpiar el campo sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Limpia el contenido del campo identificado por el locator especificado.
     *
     * @param locator El locator del elemento cuyo campo se desea limpiar.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @param mensajeEvidencia El mensaje que se desea capturar en la evidencia.
     */
    public static void clear(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            element.clear();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("Limpiar campo sobre el elemento: {}, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("No fue posible limpiar el campo sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está siendo <b>mostrado</b> en la interfaz de usuario.
     *
     * @param locator el localizador del elemento
     * @return true si el elemento está siendo mostrado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su visibilidad
     */
    public static boolean isDisplayed(By locator) {
        try {
            WebElement element = findElement(locator);
            boolean isDisplayed = element.isDisplayed();
            log.debug("El elemento: {} se encuentra visible", locator);
            return isDisplayed;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra visible, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está siendo <b>mostrado</b> en la interfaz de usuario.
     *
     * @param locator      el localizador del elemento
     * @param tiempoEspera el tiempo de espera en segundos para encontrar el elemento web
     * @return true si el elemento está siendo mostrado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su visibilidad
     */
    public static boolean isDisplayed(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isDisplayed = element.isDisplayed();
            log.debug("El elemento: {} se encuentra visible", locator);
            return isDisplayed;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra visible, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está siendo <b>mostrado</b> en la interfaz de usuario.
     *
     * @param locator          el localizador del elemento
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está siendo mostrado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su visibilidad
     */
    public static boolean isDisplayed(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            boolean isDisplayed = element.isDisplayed();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra visible y correcto guardo de evidencia", locator);
            return isDisplayed;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra visible, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está siendo <b>mostrado</b> en la interfaz de usuario.
     *
     * @param locator          el localizador del elemento
     * @param tiempoEspera     el tiempo de espera en segundos para encontrar el elemento web
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está siendo mostrado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su visibilidad
     */
    public static boolean isDisplayed(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isDisplayed = element.isDisplayed();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra visible, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
            return isDisplayed;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra visible, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>habilitado</b> en la interfaz de usuario.
     *
     * @param locator el localizador del elemento
     * @return true si el elemento está habilitado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su habilitación
     */
    public static boolean isEnable(By locator) {
        try {
            WebElement element = findElement(locator);
            boolean isEnable = element.isEnabled();
            log.debug("El elemento: {} se encuentra habilitado", locator);
            return isEnable;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra habilitado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>habilitado</b> en la interfaz de usuario.
     *
     * @param locator      el localizador del elemento
     * @param tiempoEspera el tiempo de espera en segundos para encontrar el elemento web
     * @return true si el elemento está habilitado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su habilitación
     */
    public static boolean isEnable(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isEnable = element.isEnabled();
            log.debug("El elemento: {} se encuentra habilitado", locator);
            return isEnable;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra habilitado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>habilitado</b> en la interfaz de usuario.
     *
     * @param locator          el localizador del elemento
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está habilitado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su habilitación
     */
    public static boolean isEnable(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            boolean isEnable = element.isEnabled();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra habilitado y correcto guardo de evidencia", locator);
            return isEnable;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra habilitado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>habilitado</b> en la interfaz de usuario.
     *
     * @param locator          el localizador del elemento
     * @param tiempoEspera     el tiempo de espera en segundos para encontrar el elemento web
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está habilitado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su habilitación
     */
    public static boolean isEnable(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isEnable = element.isEnabled();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra habilitado, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
            return isEnable;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra habilitado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>seleccionado</b> en la interfaz de usuario.
     * <br/>
     * Este método se utiliza con elementos de entrada como casillas de verificación (checkbox), botones de radio (radio buttons),
     * elementos de entrada (input) y elementos de opción (option).
     *
     * @param locator el localizador del elemento
     * @return true si el elemento está seleccionado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su selección
     */
    public static boolean isSelected(By locator) {
        try {
            WebElement element = findElement(locator);
            boolean isSelected = element.isSelected();
            log.debug("El elemento: {} se encuentra seleccionado", locator);
            return isSelected;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra seleccionado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>seleccionado</b> en la interfaz de usuario.
     * <br/>
     * Este método se utiliza con elementos de entrada como casillas de verificación (checkbox), botones de radio (radio buttons),
     * elementos de entrada (input) y elementos de opción (option).
     *
     * @param locator      el localizador del elemento
     * @param tiempoEspera el tiempo de espera en segundos para encontrar el elemento web
     * @return true si el elemento está seleccionado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su selección
     */
    public static boolean isSelected(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isSelected = element.isSelected();
            log.debug("El elemento: {} se encuentra seleccionado", locator);
            return isSelected;
        } catch (Exception e) {
            log.fatal("El elemento: {} no se encuentra seleccionado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>seleccionado</b> en la interfaz de usuario.
     * <br/>
     * Este método se utiliza con elementos de entrada como casillas de verificación (checkbox), botones de radio (radio buttons),
     * elementos de entrada (input) y elementos de opción (option).
     *
     * @param locator          el localizador del elemento
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está seleccionado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su selección
     */
    public static boolean isSelected(By locator, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator);
            boolean isSelected = element.isSelected();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra seleccionado y correcto guardo de evidencia", locator);
            return isSelected;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra seleccionado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Método para verificar si un elemento está <b>seleccionado</b> en la interfaz de usuario.
     * <br/>
     * Este método se utiliza con elementos de entrada como casillas de verificación (checkbox), botones de radio (radio buttons),
     * elementos de entrada (input) y elementos de opción (option).
     *
     * @param locator          el localizador del elemento
     * @param tiempoEspera     el tiempo de espera en segundos para encontrar el elemento web
     * @param mensajeEvidencia el mensaje que se desea capturar en la evidencia
     * @return true si el elemento está seleccionado, false de lo contrario
     * @throws RuntimeException si el elemento no puede ser encontrado o si ocurre un error al intentar determinar su selección
     */
    public static boolean isSelected(By locator, int tiempoEspera, String mensajeEvidencia) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            boolean isSelected = element.isSelected();
            GenerarEvidencia.capturarEvidencia(driver, mensajeEvidencia);
            log.debug("El elemento: {} se encuentra seleccionado, dentro del tiempo {} seg y correcto guardo de evidencia", locator, tiempoEspera);
            return isSelected;
        } catch (Exception e) {
            GenerarEvidencia.capturarEvidencia(driver, e.getMessage(), locator);
            log.fatal("El elemento: {} no se encuentra seleccionado, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el tag del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator El localizador utilizado para encontrar el elemento web.
     * @return El tag del elemento web.
     * @throws NoSuchElementException Si el tag del elemento web no se encuentra en el tiempo de espera de 3 segundos.
     */
    public static String obtenerTag(By locator) {
        try {
            WebElement element = findElement(locator);
            String tag = element.getTagName();
            log.debug("Se obtuvo el tag '{}' sobre el elemento: {}", tag, locator);
            return tag;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el tag sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el tag del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator      El localizador utilizado para encontrar el elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @return El tag del elemento web.
     * @throws NoSuchElementException Si el tag del elemento web no se encuentra en el tiempo de espera proporcionado.
     */
    public static String obtenerTag(By locator, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            String tag = element.getTagName();
            log.debug("Se obtuvo el tag sobre el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
            return tag;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el tag sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el atributo del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator  El localizador utilizado para encontrar el elemento web.
     * @param atributo El atributo que se desea obtener del elemento web.
     * @return El valor del atributo del elemento web.
     * @throws NoSuchElementException Si el atributo del elemento web no se encuentra en el tiempo de espera de 3 segundos.
     */
    public static String obtenerAtributo(By locator, String atributo) {
        try {
            WebElement element = findElement(locator);
            String attribute = element.getAttribute(atributo);
            log.debug("Se obtuvo el atributo '{}' sobre el elemento: {}", attribute, locator);
            return attribute;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el atributo sobre el elemento {}, en el tiempo 3 seg, por el error {}", locator, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el atributo del elemento web que coincide con el localizador proporcionado.
     *
     * @param locator      El localizador utilizado para encontrar el elemento web.
     * @param atributo     El atributo que se desea obtener del elemento web.
     * @param tiempoEspera El tiempo de espera en segundos para encontrar el elemento web.
     * @return El valor del atributo del elemento web.
     * @throws NoSuchElementException Si el atributo del elemento web no se encuentra en el tiempo de espera proporcionado.
     */
    public static String obtenerAtributo(By locator, String atributo, int tiempoEspera) {
        try {
            WebElement element = findElement(locator, tiempoEspera);
            String attribute = element.getAttribute(atributo);
            log.debug("Se obtuvo el atributo sobre el elemento: {}, dentro del tiempo {} seg", locator, tiempoEspera);
            return attribute;
        } catch (Exception e) {
            log.fatal("No fue posible obtener el atributo sobre el elemento {}, en el tiempo {}, por el error {}", locator, tiempoEspera, e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el título de la página actual.
     *
     * @return El título de la página actual como una cadena de caracteres.
     * @throws IllegalStateException Si no es posible obtener el título de la página.
     */
    public static String obtenerTitulo() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            log.fatal("No fue posible obtener el titulo de la pagina, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo obtener el título de la página.", e);
        }
    }

    /**
     * Obtiene la URL actual de la página.
     *
     * @return La URL actual de la página como una cadena de caracteres.
     * @throws IllegalStateException Si no es posible obtener la URL actual de la página.
     */
    public static String obtenerUrlActual() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            log.fatal("No fue posible obtener la url actual de la pagina, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo obtener la URL actual de la página.", e);
        }
    }

    /**
     * Navega a la página anterior en el historial del navegador.
     *
     * @throws IllegalStateException Si no es posible navegar a la página anterior.
     */
    public static void paginaAtras() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            log.fatal("No fue posible navegar a la pagina anterior, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo navegar a la página anterior.", e);
        }
    }

    /**
     * Navega a la página siguiente en el historial del navegador.
     *
     * @throws IllegalStateException Si no es posible navegar a la página siguiente.
     */
    public static void paginaAdelante() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            log.fatal("No fue posible navegar a la pagina siguiente, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo navegar a la página siguiente.", e);
        }
    }

    /**
     * Actualiza la página actual.
     *
     * @throws IllegalStateException Si no es posible actualizar la página.
     */
    public static void actualizarPagina() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            log.fatal("No fue posible actualizar la pagina, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo actualizar la página.", e);
        }
    }

    /**
     * Cambia el foco del controlador de Selenium al frame especificado.
     *
     * @param frameID El ID del marco al que se desea cambiar el foco.
     * @throws IllegalStateException Si no es posible cambiar el foco al marco especificado.
     */
    public static void cambiarFrame(String frameID) {
        try {
            driver.switchTo().frame(frameID);
        } catch (Exception e) {
            log.fatal("No fue posible cambiar de frame, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo cambiar de frame.", e);
        }
    }

    /**
     * Cambia el foco del controlador de Selenium al frame especificado.
     *
     * @param index El índice del marco al que se desea cambiar el foco.
     * @throws IllegalStateException Si no es posible cambiar el foco al marco especificado.
     */
    public static void cambiarFrame(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            log.fatal("No fue posible cambiar de frame, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo cambiar de frame.", e);
        }
    }

    /**
     * Cambia el foco del controlador de Selenium al frame especificado.
     *
     * @param nombreFrame El nombre del marco al que se desea cambiar el foco.
     * @throws IllegalStateException Si no es posible cambiar el foco al marco especificado.
     */
    public static void cambiarFrame(By nombreFrame) {
        try {
            driver.switchTo().frame(findElement(nombreFrame));
        } catch (Exception e) {
            log.fatal("No fue posible cambiar de frame, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo cambiar de frame.", e);
        }
    }

    /**
     * Sale del frame actual y regresa al frame padre.
     *
     * @throws IllegalStateException Si no es posible salir del frame.
     */
    public static void salirFrame() {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            log.fatal("No fue posible salir del frame, por el error {}", e.getMessage());
            throw new IllegalStateException("No se pudo salir del frame.", e);
        }
    }

    /**
     * Revisa la ortografía del texto obtenido mediante el localizador especificado.
     * Obtiene el texto del elemento identificado por el localizador y luego realiza una verificación ortográfica.
     * Para cada error ortográfico encontrado, registra el error y sus sugerencias de corrección en el archivo PDF y registro de depuración.
     *
     * @param locator El localizador que identifica el elemento del cual se obtendrá el texto para la revisión ortográfica.
     */
    public static void revisarOrtografia(By locator) {
        String texto = getText(locator);
        List<RuleMatch> matches = RevisorOrtografico.spellCheck(texto);
        assert matches != null;
        for (RuleMatch match : matches) {
            int inicio = match.getFromPos();
            int fin = match.getToPos();
            String error = texto.substring(inicio, fin);
            log.debug("Palabra potencialmente mal escrita: {}", error);
            log.debug("Sugerencias de correciones: {}", match.getSuggestedReplacements());
            GenerarEvidencia.capturarEvidencia("Palabra potencialmente mal escrita: " + error, "Sugerencias de correciones: " + match.getSuggestedReplacements());
        }
    }

    /**
     * Imprime un mensaje en la consola utilizando el nivel de registro de información.
     *
     * @param message El mensaje a imprimir en la consola.
     */
    public static void printConsole(Object message){
        log.info(message);
    }
}
