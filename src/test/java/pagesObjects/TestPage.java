package pagesObjects;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.Periferia;
import mapsObjects.TestMap;

public class TestPage extends TestMap {

    public TestPage(SelfHealingDriver driver) {
        super(driver);
    }

    public void loginWithEvidence(String user, String password) {
        Periferia.sendkey(inputUser, user, "Ingreso usuario");
        Periferia.sendkey(inputPassword, password, "Ingreso contraseña");

        Periferia.click(btnLogin, "Clic boton login");
    }

    public void loginWithoutEvidence(String user, String password){
        Periferia.sendkey(inputUser, user);
        Periferia.sendkey(inputPassword, password);

        Periferia.click(btnLogin);
    }

    public void logout(){
        // Automatizacion para cerrar sesion
    }
}
