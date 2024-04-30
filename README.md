# Library-QA

- [Library-QA](#library-qa)
	- [Introducción](#introducción)
	- [Instalación](#instalación)
		- [Requisitos](#requisitos)
		- [Uso en proyectos maven](#uso-en-proyectos-maven)
	- [Ejemplo test](#ejemplo-test)
	- [Métodos](#métodos)
	- [Contribuciones](#contribuciones)

## Introducción

Libray-QA se crea con la idea de mejorar dia a dia, basado en el código y necesidades que satisfacía las Shared Libraries se diseño eh implemento el proyecto de Library-QA haciendo uso de tecnologías como Java, Selenium, TestNG, log4j entre otras. Cuenta con métodos similares a los existentes de Shared Libraries provenientes de Selenium como click, sendKeys o clear pero aumentando sus capacidades como tiempos dinámicos de espera, fácil manejo de la generación de evidencias, mientras su complejidad disminuía favoreciendo su uso dentro de la fabrica QA - Squad Automatización.

## Instalación

> La version actual: **1.0-SNAPSHOT**

### Requisitos

- Java JDK versión 17 o superior
  - Establece la variable de entorno `JAVA_HOME` en la ubicación del ejecutable de Java (el JDK, no el JRE).
  - Para probar esto, intenta ejecutar el comando ```javac```. Este comando no existirá si solo tienes instalado el JRE. Si te encuentras con una lista de opciones de línea de comandos, estás referenciando correctamente al JDK.

### Uso en proyectos maven

Comando de instalación desde CMD

``` CMD
mvn install:install-file -Dfile=< ruta-jar >\Library-QA.jar -DgroupId=com.periferiaitgroup -DartifactId=Library-QA -Dversion=< Version-Library-QA > -Dpackaging=jar
```

Llamado dependencia en pom.xml

``` MAVEN
<dependencies>
 <dependency>
  <groupId>com.periferiaitgroup</groupId>
     <artifactId>Library-QA</artifactId>
     <version><Library-QA.version></version>
 </dependency>
</dependencies>
```

## Ejemplo test

TODO: Documentar caso completo con DataProvider, métodos Before y After.

``` JAVA
@Test
public void casoDePrueba000000(String user, String password) {
 String url = "https://google.com";
 String analista = "Analista Periferia";
 String nameTest = Thread.currentThread().getStackTrace()[1].getMethodName();
 
 driver = Periferia.setUp(Navegador.CHROME)
 Periferia.goUrl(url);

 GenerarEvidencia.iniciarEvidencia(nameTest, analista, url);

 testPage.iniciarSesion(user, password);
 testPage.ingresoModuloCompras();
 testPage.procesoPago();
 testPage.verificarFactura();
 testPage.cerrarSesion();

 GenerarEvidencia.finalizarEvidencia();
}
```

```JAVA
public class TestPage extends TestMap {
	public void iniciarSesion(String user, String password) {
		Periferia.sendKeys(inputUser, user, "Ingreso usuario");
		Periferia.sendKeys(inputPwd, password, "Ingreso contraseña");
		Periferia.click(btnLogin)
		Periferia.capturarEvidencia("Evidencia login correcto con credenciales, usuario: " + user" y contraseña: " + password)
	}
}
```

```JAVA
public class TestMap {
    public TestMap(SelfHealingDriver driver){
        super();
    }

    protected By inputUser = By.xpath("//input[@id='user']");
    protected By inputPwd = By.xpath("//input[@id='password']");
    protected By btnLogin = By.xpath("//button[@id='login']");
}
```

## Métodos

Los principales métodos de interacción cuentan con la misma estructura de sobrecarga, todos requerirán siempre de un localizador, y si se desea se puede variar el tiempo de espera, un mensaje para la evidencia o ambos parámetros.

> Los métodos no generan el paso dentro del archivo pdf si no se le indica un mensaje para la misma.

``` JAVA
import com.periferiaitgroup.Periferia;

public class test {
	By button = By.xpath("xpathExpression");
	int tiempoEspera = 10;

	Periferia.click(button);
	Periferia.click(button, tiempoEspera);
	Periferia.click(button, "Mensaje para la evidencia");
	Periferia.click(button, tiempoEspera, "Mensaje para la evidencia");	
}
```

También se encuentran implementados métodos para obtener información de la pagina web.

``` JAVA
import com.periferiaitgroup.Periferia;

public class test {
	By locator = By.xpath("xpathExpression")

	String texto_1 = Periferia.getText(locator);
	String texto_2 = Periferia.getText(locator, tiempoEspera);
	String texto_3 = Periferia.getText(locator, "Mensaje para la evidencia");
	String texto_4 = Periferia.getText(locator, tiempoEspera, "Mensaje para la evidencia");
}
```

Hay métodos que pueden devolver valores como obtenerTexto o obtenerUrl, que mas adelante se mostrar como se implementan.

TODO: Completar documentación detallada de las clases y métodos implementados.

## Contribuciones

TODO: Directrices para contribuir al proyecto, incluyendo información sobre cómo reportar problemas y enviar pull requests.
