package RunPruebas;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.Periferia;
import com.periferia.constantes.Navegador;
import com.periferia.evidencia.GenerarEvidencia;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pagesObjects.TestPage;

public class TestPrincipal {

    static SelfHealingDriver driver;
    static TestPage testPage;

    @DataProvider(name = "dpGeneral")
    public Object[][] dpGeneral() {
        //TODO: Extraer informacion de un archivo facil de editar
        return null;
    }

    @BeforeTest
    public void beforeTest() {
        // Configurar propiedades
            // Leer propiedades
            // Cargar propiedades

        // Instaciación de las pageObj
        testPage = new TestPage(driver);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
	@Story("Pruebas library-QA")
	@Description("Test para probar Libreria-QA con evidencia")
    public void testWithEvidence() {
        String nameTest = Thread.currentThread().getStackTrace()[1].getMethodName();

        driver = Periferia.setUp(Navegador.CHROME, "https://demoqa.com/login");

        GenerarEvidencia.iniciarEvidencia(nameTest, "Nombre Analista", "Url page");

        testPage.loginWithEvidence("User", "Password");

        GenerarEvidencia.finalizarEvidencia();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Pruebas library-QA")
    @Description("test para probar la nueva libreria QA sin evidencia")
    public void testWithoutEvidence() {
        String nameTest = Thread.currentThread().getStackTrace()[1].getMethodName();

        driver = Periferia.setUp(Navegador.CHROME, "https://demoqa.com/login");

        GenerarEvidencia.iniciarEvidencia(nameTest, "Nombre Analista", "Url Page");

        testPage.loginWithoutEvidence("User", "Password");

        GenerarEvidencia.finalizarEvidencia();
    }

    @AfterTest
    public void afterTest() {
        Periferia.closeDriver();
    }

}
