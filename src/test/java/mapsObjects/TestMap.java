package mapsObjects;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;

public class TestMap {
    public TestMap(SelfHealingDriver driver){
        super();
    }

    protected By inputUser = By.xpath("//input[@id='userName']");
    protected By inputPassword = By.xpath("//input[@id='password']");
    protected By btnLogin = By.xpath("//button[@id='login']");
}
