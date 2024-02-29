# Library-QA

1. [Introducción](#introducción)
2. [Requisitos](#requisitos)
3. [Instalación](#instalacion)
4. [Uso](#uso)
5. [Alcance](#alcance)
6. [Contribución](#contribución)

## introducción

TODO: Breve descripción del proyecto, su contexto dentro de la empresa y su importancia.

## Requisitos

TODO: Lista de requisitos necesarios para ejecutar el proyecto, incluyendo software, librerias, y configuracion especificas.

## Instalacion

> La version actual: **0.1-SNAPSHOT**

### Maven

Comando de instalacion CMD
```CMD
mvn install:install-file -Dfile=<Ruta libreria>\Library-QA.jar -DgroupId=com.periferia -DartifactId=Library-QA -Dversion=<Version Library-QA> -Dpackaging=jar
```

Llamado dependencia en pom.xml
``` Maven
<dependencies>
	<dependency>
		<groupId>com.periferia</groupId>
	    <artifactId>Library-QA</artifactId>
	    <version>${Library-QA.version}</version>
	</dependency>
</dependencies>
```

## Uso

TODO: Explicación de cómo utilizar el proyecto, incluyendo ejemplos de comandos o configuración

## Alcance

### V0.1
* 

## Contribución

TODO: Directrices para contribuir al proyecto, incluyendo informacion sobre cómo reportar problemas y enviar pull requests.
