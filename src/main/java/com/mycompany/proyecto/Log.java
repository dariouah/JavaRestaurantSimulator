package com.mycompany.proyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    
    private static final String LOG_FILE_PATH = "evolucionRestaurante.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Log instance;
    private BufferedWriter writer;

    private Log() {
        try {
            writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true));
        } catch (IOException e) {}
    }

    public synchronized void escribir(String event) {
        try {
            //Le añadimos la fecha y hora al evento
            LocalDateTime currentTime = LocalDateTime.now();
            String formattedTime = currentTime.format(DATE_FORMATTER);
            String logMessage = formattedTime + " - " + event;
            //Escribimos en el archivo log
            writer.write(logMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
        }
    }

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();   
        }
        return instance;
    }
}