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

public class SeleniumDriver {
    protected static SelfHealingDriver healingDriver;

    private SeleniumDriver() {
        throw new IllegalStateException("Utility class");
    }

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

    private static SelfHealingDriver useEdge() {
        EdgeOptions options = new EdgeOptions();

        WebDriver delegate = WebDriverManager.edgedriver().capabilities(options).create();
        healingDriver = SelfHealingDriver.create(delegate);
        return healingDriver;
    }
}
