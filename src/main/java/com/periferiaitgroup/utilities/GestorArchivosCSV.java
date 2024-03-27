package com.periferiaitgroup.utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GestorArchivosCSV {
    private final String separator;

    /**
     * Constructor por defecto de la clase Gestor de Archivos CSV.
     * Separa los elementos dentro del CSV con una coma ','.
     */
    public GestorArchivosCSV() {
        this.separator = ",";
    }

    /**
     * Constructor para la clase GestorArchivosCSV.
     * Separa los elementos dentro del CSV con el carácter especificado.
     *
     * @param separator el carácter que se utilizará como separador en el archivo CSV
     */
    public GestorArchivosCSV(String separator) {
        this.separator = separator;
    }

    /**
     * Lee un archivo CSV y devuelve su contenido como una lista de matrices de cadenas.
     *
     * @param archivoCSV - el {@code File} a leer
     * @return una lista de matrices de cadenas que contienen los valores leídos del archivo CSV
     * @throws CsvValidationException si hay un error al validar el archivo CSV
     * @throws IOException            si hay un error al leer el archivo CSV
     */
    public List<String> leerCSV(File archivoCSV) throws CsvValidationException, IOException {
        List<String> valores = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(archivoCSV))) {
            String[] filas;
            while ((filas = csvReader.readNext()) != null) {
                for (String fila : filas) {
                    String[] seccion = fila.split(this.separator);
                    Collections.addAll(valores, seccion);
                }
            }

            csvReader.close();
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo CSV.", e);
        } catch (CsvValidationException e) {
            throw new CsvValidationException("Error al validar el archivo CSV");
        }

        return valores;
    }

    /**
     * Lee un archivo CSV y devuelve su contenido como una lista de matrices de cadenas.
     *
     * @param carpetaPadre  - la ruta del directorio padre
     * @param nombreArchivo - el nombre del archivo a leer
     * @return una lista de matrices de cadenas que contienen los valores leídos del archivo CSV
     * @throws CsvValidationException si hay un error al validar el archivo CSV
     * @throws IOException            si hay un error al leer el archivo CSV
     */
    public List<String> leerCSV(String carpetaPadre, String nombreArchivo) throws CsvValidationException, IOException {
        List<String> valores = new ArrayList<>();

        File file = new File(carpetaPadre, nombreArchivo);
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] filas;
            while ((filas = csvReader.readNext()) != null) {
                for (String fila : filas) {
                    String[] seccion = fila.split(this.separator);
                    Collections.addAll(valores, seccion);
                }
            }

            csvReader.close();
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo encontrado en " + carpetaPadre + nombreArchivo + " CSV.", e);
        } catch (CsvValidationException e) {
            throw new CsvValidationException("Error al validar el archivo encontrado en " + carpetaPadre + nombreArchivo + " CSV");
        }

        return valores;
    }

    public Iterator<Object[]> dataProviderFromCSV(File archivoCSV) throws IOException {
        List<Object[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(archivoCSV))) {
            String[] line;
            boolean firstLine = true;
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                data.add(line);
            }
        } catch (CsvValidationException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data.iterator();
    }

    public Iterator<Object[]> dataProviderFromCSV(String rutaArchivoCSV) throws IOException {
        List<Object[]> data = new ArrayList<>();

        File fileCSV = new File(rutaArchivoCSV);
        try (CSVReader reader = new CSVReader(new FileReader(fileCSV))) {
            String[] line;
            boolean firstLine = true;
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                data.add(line);
            }
        } catch (CsvValidationException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data.iterator();
    }

    public Iterator<Object[]> dataProviderFromCSV(String carpetaPadre, String nombreArchivo) throws IOException {
        List<Object[]> data = new ArrayList<>();

        File csvFile = new File(carpetaPadre, nombreArchivo);
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            boolean firstLine = true;
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                data.add(line);
            }
        } catch (CsvValidationException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data.iterator();
    }
}
