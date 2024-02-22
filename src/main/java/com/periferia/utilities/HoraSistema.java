package com.periferia.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HoraSistema {
    private  HoraSistema(){}

    /**
     * Genera una cadena de texto con la hora actual del sistema en formato "dd-MM-yyyy" Ejemplo: 06-09-2003
     *
     * @return La hora actual del sistema en el formato "dd-MM-yyyy".
     *
     */
    public static String currentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTimeFormatter.format(currentTime);
    }

    /**
     * Obtiene la hora actual del sistema en el formato especificado y la devuelve como una cadena de texto.
     *
     * @param formatter 	Formato de la hora deseado (por ejemplo, "HH:mm:ss").
     * @return La hora actual del sistema en el formato especificado.
     *
     * @example ejemplo1 La siguiente línea muestra cómo usar el método con el formato "HH:mm:ss":<br/>
     * // String fecha = tiempoSistema("HH:mm:ss");
     *
     * @example ejemplo2 La siguiente línea muestra cómo usar el método con el formato "yyyy-MM-dd HH:mm:ss":<br/>
     * // String formato = "yyyy-MM-dd HH:mm:ss"
     * // String fecha = tiempoSistema(formato);
     */
    public static String currentDate(String formatter) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return dateTimeFormatter.format(currentTime);
    }
}
