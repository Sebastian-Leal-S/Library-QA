package com.periferiaitgroup.driver_manager;

import com.epam.healenium.SelfHealingDriver;
import com.periferiaitgroup.constantes.Navegador;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Clase utilitaria para inicializar y gestionar un `SelfHealingDriver` con diferentes navegadores.
 * Utiliza la biblioteca WebDriverManager para gestionar los controladores de los navegadores.
 */
public class SeleniumDriver {
    protected static SelfHealingDriver healingDriver;

    /**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     * 
     * @throws IllegalStateException si se intenta instanciar la clase.
     */
    private SeleniumDriver() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Inicializa y retorna un `SelfHealingDriver` basado en el navegador especificado.
     * 
     * @param navegador el navegador a utilizar (CHROME, FIREFOX, EDGE).
     * @return el `SelfHealingDriver` inicializado, o null si el navegador no es válido.
     */
    public static @Nullable SelfHealingDriver initDriver(@NotNull Navegador navegador) {
        switch (navegador) {
            case CHROME -> {
                return useChrome();
            }
            case FIREFOX -> {
                return useFirefox();
            }
            case EDGE -> {
                return useEdge();
            }
        }
        return null;
    }

    /**
     * Inicializa un `SelfHealingDriver` utilizando Google Chrome.
     * 
     * @return el `SelfHealingDriver` configurado para Chrome.
     */
    private static SelfHealingDriver useChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--windows-size=1920,1080");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");

        WebDriver delegate = WebDriverManager.chromedriver().capabilities(options).create();
        healingDriver = SelfHealingDriver.create(delegate);

        return healingDriver;
    }

    /**
     * Inicializa un `SelfHealingDriver` utilizando Mozilla Firefox.
     * 
     * @return el `SelfHealingDriver` configurado para Firefox.
     */
    private static SelfHealingDriver useFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--windows-size=1920,1080");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        WebDriver delegate = WebDriverManager.firefoxdriver().capabilities(options).create();
        healingDriver = SelfHealingDriver.create(delegate);
        return healingDriver;
    }

    /**
     * Inicializa un `SelfHealingDriver` utilizando Microsoft Edge.
     * 
     * @return el `SelfHealingDriver` configurado para Edge.
     */
    private static SelfHealingDriver useEdge() {
        EdgeOptions options = new EdgeOptions();

        WebDriver delegate = WebDriverManager.edgedriver().capabilities(options).create();
        healingDriver = SelfHealingDriver.create(delegate);
        return healingDriver;
    }
}
