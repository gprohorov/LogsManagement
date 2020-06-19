package pro.edu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {


        LocalDateTime start = LocalDateTime.now();

        String myString = new String(Files.readAllBytes(Paths
                .get("/home/george/Desktop/logs.txt")));

        String[] lines = myString.split("\\n");
        System.out.println(lines.length);

        int errorLinesCount = 0;

        for (int i = 0; i < lines.length ; i++) {

            if (lines[i].contains("ERROR")){
                errorLinesCount++;
            }

        }
        System.out.println(errorLinesCount);
         LocalDateTime finish = LocalDateTime.now();

        System.out.println(ChronoUnit.MILLIS.between(start, finish) + " ms");

                //--------------------   Java 8 streams
        System.out.println("-------------------------------");

        start = LocalDateTime.now();
        final long errors = Files.lines(Paths.get("/home/george/Desktop/logs.txt"))
                .filter(line -> line.contains("ERROR"))
                .count();

        System.out.println(errors);
        finish = LocalDateTime.now();

        System.out.println(ChronoUnit.MILLIS.between(start, finish) + " ms");

    }
}
