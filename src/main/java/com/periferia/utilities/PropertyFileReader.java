package com.periferia.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private PropertyFileReader() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Lee las propiedades desde un archivo de configuración y devuelve un objeto Properties.
     *
     * @return Un objeto Properties que contiene las propiedades cargadas desde el archivo de configuración.
     */
    public static Properties readProperty() {
        Properties properties =  new Properties();
        try (FileInputStream input = new FileInputStream("./configFiles/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }

    /**
     * Lee las propiedades desde un archivo de configuración en la ruta especificada y devuelve un objeto Properties.
     *
     * @param rutaArchivoPropiedades La ruta del archivo de propiedades desde donde se cargarán las propiedades.
     * @return Un objeto Properties que contiene las propiedades cargadas desde el archivo de configuración en la ruta especificada.
     */
    public static Properties readProperty(String rutaArchivoPropiedades) {
        Properties properties =  new Properties();
        try (FileInputStream input = new FileInputStream(rutaArchivoPropiedades)) {
            properties.load(input);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }
}
