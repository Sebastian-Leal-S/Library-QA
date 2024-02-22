package com.periferia.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private PropertyFileReader() {
        throw new IllegalStateException("Utility class");
    }

    public static Properties readProperty() {
        Properties properties =  new Properties();
        try (FileInputStream input = new FileInputStream("./configFiles/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }

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
