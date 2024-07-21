package com.periferiaitgroup.utilities;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Clase utilitaria para medir el tiempo de ejecución de un proceso.
 */
public class TiempoEjecucion {
    private static LocalTime startTime;

    /**
     * Constructor privado para prevenir la instanciación de la clase utilitaria.
     */
    private TiempoEjecucion(){
    	throw new IllegalStateException("Utility class");
    }

    /**
     * Inicia el cronómetro registrando la hora actual.
     */
    public static void start() {
        startTime = LocalTime.now();
    }

    /**
     * Calcula y retorna el tiempo de ejecución desde que se inició el cronómetro.
     * 
     * @return una cadena de texto que representa el tiempo de ejecución en el formato "HH:mm:ss".
     */
    public static String getRunTime() {
        LocalTime endTime = LocalTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
