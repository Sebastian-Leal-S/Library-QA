package com.periferiaitgroup.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.testng.Assert.assertTrue;

public class GestorArchivos {
	
	private GestorArchivos() {}
	
    /**
     * Crea una carpeta en la ruta especificada si la evidencia está habilitada.
     *
     * @param rutaEvidencia    La ruta de la carpeta donde se guardarán las capturas
     * @param nameCarpeta      El nombre del test en ejecución de pantalla.
     * @return El directorio de la carpeta creada.
     */
    public static File crearCarpetaEvidencia(String rutaEvidencia, String nameCarpeta) {
        File directorio = new File(rutaEvidencia + nameCarpeta + "_" + HoraSistema.currentDate("dd-MM-yyyy"));
        directorio.mkdir();

        return directorio;
    }

    /**
     * Elimina un directorio y su contenido.
     *
     * @param directory El directorio que se eliminará.
     * @throws IOException Sí ocurre un error de entrada/salida al eliminar el
     *                     directorio y su contenido.
     */
    public static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            try (Stream<Path> paths = Files.walk(directory.toPath()).sorted(Comparator.reverseOrder())) {
                paths.forEach(path -> {
                    try {
                        if (Files.isDirectory(path)) {
                            Files.delete(path);
                        } else {
                            Files.deleteIfExists(path);
                        }
                    } catch (IOException e) {
                        System.err.println("Error al borrar el archivo: " + path);
                    }
                });
            }
        }
    }

    /**
     * Elimina un archivo en la ruta especificada.
     *
     * @param rutaImagen La ruta del archivo a eliminar.
     * @throws IOException Si ocurre un error de E/S.
     */
    public static void eliminarArchivo(String rutaImagen) {
        File fichero = new File(rutaImagen);
        fichero.delete();
    }

    /**
     * Elimina una carpeta en la ruta especificada si existe.
     *
     * @param ruta La ruta de la carpeta a eliminar.
     * @throws IOException Si ocurre un error de E/S.
     */
    public static void eliminarCarpeta(String ruta) {
        File directory = new File(ruta);
        if (directory.exists()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isDirectory()) {
                    try {
                        GestorArchivos.deleteDirectory(file);
                    }catch (IOException e) {
                        System.err.println("Hubo fallas al momento de eliminar el archivo\n" + e);
                    }
                } else {
                    file.delete();
                }
            }
        }
    }

    /**
     * Elimina archivos segun una expresion regular
     *
     * @param directoryPath - Carpeta en donde se borraron los archivos
     * @param regularExpression - Expresion regular para seleccionar archivos a borrar
     */
    public static void borrarArchivosPorPatron(String directoryPath, String regularExpression) {
        // Crear un objeto File que represente la carpeta donde se encuentran los archivos
        File carpeta = new File(directoryPath);

        // Verificar si la carpeta existe
        if (carpeta.exists()) {
            // Crear una expresión regular para seleccionar los archivos que se desean borrar
            Pattern patron = Pattern.compile(regularExpression);

            // Obtener una lista de archivos que coincidan con la expresión regular
            File[] archivos = carpeta.listFiles((dir, name) -> patron.matcher(name).matches());

            // Recorrer la lista de archivos y borrarlos
            assert archivos != null;
            for (File archivo : archivos) {
                archivo.delete();
            }
        }

    }

    /**
     * Valida si un archivo existe dentro de una carpeta
     *
     * @param fileDirectoryPath - Ruta a donde se encuentra el archivo
     * @param nameFile - nombre del archivo que se quiere validar
     */
    public void validarArchivo(String fileDirectoryPath, String nameFile) {
        File fileToCheck = new File(fileDirectoryPath, nameFile);
        assertTrue(fileToCheck.exists(), "File " + nameFile + " was not found in the folder " + fileDirectoryPath + ".");
    }

    /**
     * Renombra un archivo dentro de una carpeta
     *
     * @param fileDirectoryPath - Ruta del directorio donde se encuentra el archivo
     * @param nameFile - Nombre actual del archivo
     * @param newNameFile - Nombre nuevo del archivo
     * @throws NoSuchFileException
     * @throws FileAlreadyExistsException
     */
    public void renombrarArchivo(String fileDirectoryPath, String nameFile, String newNameFile) throws NoSuchFileException, FileAlreadyExistsException {
        try {
            File file = new File(fileDirectoryPath, nameFile);
            if (!file.exists()) {
                throw new NoSuchFileException("The source file does not exist.");
            }

            File dest = new File(fileDirectoryPath, newNameFile);
            if (dest.exists()) {
                throw new FileAlreadyExistsException("The destination path exists.");
            }

            boolean success = file.renameTo(dest);
            assertTrue(success, "File was not renamed successfully.");
            validarArchivo(fileDirectoryPath, newNameFile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Muestra en pantalla el archivo indicado durante 15 Segundos.
     *
     * Para luego cerrar todos los archivos en excel.
     *
     * @param fileDirectoryPath - Ruta del directorio donde se encuentra el archivo
     * @param nameFile - Nombre del archivo a abrir
     * @throws IOException  - No se encontro el archivo
     */
    public void mostrarArchivosEnPantalla(String fileDirectoryPath, String nameFile) throws IOException {
        try {
            File file = new File(fileDirectoryPath, nameFile);
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()) {
                desktop.open(file);
                Thread.sleep(15000);

                String[] comando = {"cmd", "/c", "taskkill", "/IM", "excel.exe"};
                Process proceso = Runtime.getRuntime().exec(comando);
                proceso.waitFor();
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra en pantalla un archivo durante el tiempo proporcionado
     *
     * Para luego cerrar todos los archivos en excel.
     *
     * @param fileDirectoryPath - Ruta del directorio donde se encuentra el archivo
     * @param nameFile - Nombre del archivo a abrir
     * @param secondsOnScreen - Tiempo en segundos para mostrar el archivo en pantalla
     * @throws IOException - No se encontro el archivo
     */
    public void mostrarArchivosEnPantalla(String fileDirectoryPath, String nameFile, int secondsOnScreen) throws IOException{
        try {
            File file = new File(fileDirectoryPath, nameFile);
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()) {
                desktop.open(file);
                Thread.sleep(secondsOnScreen * 1000L);

                String[] comando = {"cmd", "/c", "taskkill", "/IM", "excel.exe"};
                Process proceso = Runtime.getRuntime().exec(comando);
                proceso.waitFor();
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
