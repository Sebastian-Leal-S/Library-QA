package pagesObjects;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.Periferia;
import mapsObjects.TestMap;
import org.openqa.selenium.By;

public class TestPage extends TestMap {

    public TestPage(SelfHealingDriver driver) {
        super(driver);
    }

    public void clickTest() {
        By buttonTest = By.xpath("//button[@id='button-test']");

        Periferia.click(buttonTest);

        Periferia.click(buttonTest, 5);

        Periferia.click(buttonTest, "Click en el boton test.");

        Periferia.click(buttonTest, 5, "Click en el boton test, dentro del tiempo de espera proporcionado.");
    }

    public void sendKeyTest() {
        By inputTest = By.xpath("//input[@id='input-test']");

        Periferia.sendKeys(inputTest, "Texto a enviar");

        Periferia.sendKeys(inputTest, "Texto a enviar", 5);

        Periferia.sendKeys(inputTest, "Texto a enviar", "Envio de texto");

        Periferia.sendKeys(inputTest, "Texto a enviar", 5, "Envio de texto");
    }

    public void getTextTest() {
        By textTest = By.xpath("//p[@id='text-test']");

        Periferia.getText(textTest);

        Periferia.getText(textTest, 5);

        Periferia.getText(textTest, "Obtener texto");

        Periferia.getText(textTest, 5, "Obtener texto");
    }

    public void clearTest() {
        By inputTest = By.xpath("//input[@id='input-test']");

        Periferia.clear(inputTest);

        Periferia.clear(inputTest, 5);

        Periferia.clear(inputTest, "Limpiar campo");

        Periferia.clear(inputTest, 5, "Limpiar campo");
    }
}
