# Library-QA

## Instalacion

> La version actual: **0.1-SNAPSHOT**

### Maven

Comando de instalacion desde CMD
```CMD
mvn install:install-file -Dfile=<Ruta libreria>\Library-QA.jar -DgroupId=com.periferia -DartifactId=Library-QA -Dversion=<Version Library-QA> -Dpackaging=jar
```

Llamado dependencia desde pom.xml
``` Maven
<dependency>
	<groupId>com.periferia</groupId>
    <artifactId>Library-QA</artifactId>
    <version>${Library-QA.version}</version>
</dependency>
```