package pagesObjects;

import com.periferiaitgroup.Periferia;

import mapsObjects.TestMap;

public class TestPage extends TestMap {

    public TestPage() {}

    public void completeTest(String usuario, String contrasenna) {
        Periferia.revisarOrtografia(btnIniciarSesion);
        Periferia.sendKeys(btnIniciarSesion, usuario, 1, "Se ingresa usuario");
        Periferia.sendKeys(txtContrasenna, contrasenna, 1, "ingresar contrasenna");
        Periferia.click(btnIniciarSesion, 1);
        Periferia.revisarOrtografia(txtHomePeriferia);
    }

}
