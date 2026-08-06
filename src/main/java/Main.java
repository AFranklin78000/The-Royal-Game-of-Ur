package main.java;

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
        LOG("Init System", "Wait complete, start writing logs to files. " +
                "Final message: Farewell!");

        try (FileWriter writer = new FileWriter("logs/" +
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("MM-dd-uuuu HH:mm:ss")) + ".txt")) {
            writer.write(LOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writerLatest = new FileWriter("logs/latest.txt")) {
            writerLatest.write(LOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
