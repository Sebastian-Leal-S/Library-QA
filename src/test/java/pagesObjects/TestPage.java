package pagesObjects;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.Periferia;
import mapsObjects.TestMap;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TestPage extends TestMap {

    public TestPage(SelfHealingDriver driver) {
        super(driver);
    }

    public void clickTest() {
        By buttonTest = By.xpath("//button[@id='button-change-color']");

        Periferia.click(buttonTest);

        Periferia.click(buttonTest, 5);

        Periferia.click(buttonTest, "Click en el boton test.");

        Periferia.click(buttonTest, 5, "Click en el boton test, dentro del tiempo de espera proporcionado.");
    }

    public void sendKeyTest() {
        By inputTest = By.xpath("//input[@id='input-text']");

        Periferia.sendKeys(inputTest, "Texto a enviar");

        Periferia.sendKeys(inputTest, "Texto a enviar", 5);

        Periferia.sendKeys(inputTest, "Texto a enviar", "Envio de texto");

        Periferia.sendKeys(inputTest, "Texto a enviar", 5, "Envio de texto");
    }

    public void getTextTest() {
        By textTest = By.xpath("//p[@id='text-visible']");

        String texto = Periferia.getText(textTest);
        Periferia.printConsole(texto);

        String text2 = Periferia.getText(textTest, 5);
        Periferia.printConsole(text2);

        String text3 = Periferia.getText(textTest, "Obtener texto");
        Periferia.printConsole(text3);

        String text4 = Periferia.getText(textTest, 5, "Obtener texto");
        Periferia.printConsole(text4);
    }

    public void clearTest() {
        By inputTest = By.xpath("//input[@id='input-text']");

        Periferia.clear(inputTest);

        Periferia.clear(inputTest, 5);

        Periferia.clear(inputTest, "Limpiar campo");

        Periferia.clear(inputTest, 5, "Limpiar campo");
    }

    public void completeTest() {
        By buttonColor = By.xpath("//button[@id='button-change-color']");
        By textTest = By.xpath("//p[@id='text-visible']");
        By inputTest = By.xpath("//input[@id='input-text']");
        By buttonText = By.xpath("//button[@id='button-change-text']");

        Periferia.click(buttonColor, "Clic para cambiar color");
        Periferia.sendKeys(inputTest, "este texto tiene errores", "Envio de texto");
        Periferia.click(buttonText);

        Periferia.revisarOrtografia(textTest);

        Assert.assertEquals("este texto tiene errores", Periferia.getText(textTest));
        Periferia.clear(inputTest);

        Periferia.sendKeys(inputTest, "Como estan?");
        Periferia.click(buttonColor);
        Periferia.capturarEvidencia("Esto es para la evidencia");

        Periferia.isSelected(buttonColor);
    }
}
