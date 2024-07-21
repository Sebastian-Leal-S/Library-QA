package mapsObjects;

import org.openqa.selenium.By;

public class TestMap {
    protected By txtUsuario = By.id("username");
    protected By txtContrasenna = By.id("password");
    protected By btnIniciarSesion = By.xpath("//*[@class='btn btn-primary btn-block']");

    //DICCIONARIO
    protected By txtHome = By.xpath("//*[@class='card-body login-card-body']");
    protected By txtHomePeriferia = By.xpath("//*[@data-id='55f4399']");
}
