package RunPruebas;

import com.epam.healenium.SelfHealingDriver;
import com.periferiaitgroup.Periferia;
import com.periferiaitgroup.constantes.Navegador;
import com.periferiaitgroup.evidencia.GenerarEvidencia;
import com.periferiaitgroup.utilities.GestorArchivosExcel;
import com.periferiaitgroup.utilities.PropertyFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagesObjects.TestPage;

import java.util.Properties;

public class TestPrincipal {

    static SelfHealingDriver driver;
    static TestPage testPage;

    static String url;
    static String nombreAnalista;

    @DataProvider(name = "dpExcel")
    public Object[][] dpGeneralExcel() {
        return GestorArchivosExcel.getTableArray("./Data/Data.xlsx", "Inicio");
    }

    @BeforeTest
    public void beforeTest() {
        Properties properties = PropertyFileReader.readProperty();
        url = properties.getProperty("URL");
        nombreAnalista = properties.getProperty("ANALISTA");

        // Instaciación de las pageObj
        testPage = new TestPage(driver);
    }

    @Test(dataProvider = "dpExcel")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pruebas library-QA")
    @Description("Test para una prueba completa de Libreria-QA")
    public void testComplete(String parameterOne, String paramaterTwo) {
        String nameTest = Thread.currentThread().getStackTrace()[1].getMethodName();

        driver = Periferia.setUp(Navegador.CHROME, url);

        GenerarEvidencia.iniciarEvidencia(nameTest, nombreAnalista, url);

        testPage.completeTest(parameterOne);

        GenerarEvidencia.finalizarEvidencia();
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
