package RunPruebas;

import com.epam.healenium.SelfHealingDriver;
import com.periferia.Periferia;
import com.periferia.constantes.Navegador;
import com.periferia.evidencia.GenerarEvidencia;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagesObjects.TestPage;

public class TestPrincipal {

    static SelfHealingDriver driver;
    static TestPage testPage;

    static String url;
    static String nombreAnalista;

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
        url = "D:\\Test-Page\\index.html";
        nombreAnalista = "Nombre Analista";

        // Instaciación de las pageObj
        testPage = new TestPage(driver);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
	@Story("Pruebas library-QA")
    @Description("Test para probar Libreria-QA")
    public void testMethods() {
        String nameTest = Thread.currentThread().getStackTrace()[1].getMethodName();

        driver = Periferia.setUp(Navegador.CHROME, url);

        GenerarEvidencia.iniciarEvidencia(nameTest, nombreAnalista, url);

        testPage.clickTest();
        testPage.sendKeyTest();
        testPage.clearTest();
        testPage.getTextTest();

        GenerarEvidencia.finalizarEvidencia();
    }

    @AfterTest
    public void afterTest() {
        Periferia.tearDown();
    }

}
