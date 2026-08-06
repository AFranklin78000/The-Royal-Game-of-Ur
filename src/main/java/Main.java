package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();

        LOG("Init System", "Exiting program, system wait...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOG("Init System", "Wait complete, start writing logs to files.");

        File logs = new File("logs/");

        if (!logs.exists()) {
            LOG("Init System", "Logs directory doesn't exist; creating logs directory...");
            logs.mkdir();
            LOG("Init System", "Logs directory created.");
        }

        LOG("Init System", "Final log message: Farewell!");

        File log = new File("logs/" +
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("MM-dd-uuuu HH_mm_ss")) + ".txt");
        File logLatest = new File("logs/latest.txt");

        try (FileWriter writer = new FileWriter(log)) {
            log.createNewFile();
            writer.write(LOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(logLatest)) {
            logLatest.createNewFile();
            writer.write(LOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("<Base System> Program complete, shutting down.");
    }

    private void run() {

    }



    private static String LOG = "";

    public static void LOG(String messenger, String message) {
        System.out.println("[" + LocalTime.now() + "] <" + messenger + "> " + message);
        LOG = LOG.concat("[" + LocalTime.now() + "] <" + messenger + "> " + message + "\n");
    }

    public static void LOG(String messenger, Object message) {
        System.out.println("[" + LocalTime.now() + "] <" + messenger + "> " + message);
        LOG = LOG.concat("[" + LocalTime.now() + "] <" + messenger + "> " + message.toString() + "\n");
    }

    public static void LOG(String message) {
        System.out.println("[" + LocalTime.now() + "] <Basic System> " + message);
        LOG = LOG.concat("[" + LocalTime.now() + "] <Basic System> " + message + "\n");
    }

    public static void LOG(Object message) {
        System.out.println("[" + LocalTime.now() + "] <Basic System> " + message);
        LOG = LOG.concat("[" + LocalTime.now() + "] <Basic System> " + message.toString() + "\n");
    }

}
