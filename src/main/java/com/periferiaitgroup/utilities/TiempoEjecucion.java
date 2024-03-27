package com.periferiaitgroup.utilities;

import java.time.Duration;
import java.time.LocalTime;

public class TiempoEjecucion {
    private static LocalTime startTime;

    private TiempoEjecucion() {}

    public static void start() {
        startTime = LocalTime.now();
    }

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
