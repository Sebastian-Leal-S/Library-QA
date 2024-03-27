package mapsObjects;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;

public class TestMap {
    public TestMap(SelfHealingDriver driver){
        super();
    }

    protected By buttonColor = By.xpath("//button[@id='button-change-color']");
    protected By textTest = By.xpath("//p[@id='text-visible']");
    protected By inputTest = By.xpath("//input[@id='input-text']");
    protected By buttonText = By.xpath("//button[@id='button-change-text']");
}
