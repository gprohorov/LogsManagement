package pro.edu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileName = "/home/george/Desktop/logs.txt";

        LocalDateTime start = LocalDateTime.now();
       // System.out.println(start);
        //LocalDateTime ttt = LocalDateTime.parse("2020-11-08T22:01:46.042");
       // System.out.println(ttt.plusDays(1));

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

        List<String> the14thOctoberLogs =  Files.lines(Paths.get("/home/george/Desktop/logs.txt"))
                .filter(line -> line.contains("2019-10-13"))
                .collect(Collectors.toList());

        String logs3 = "";

        for (String line :the14thOctoberLogs){
            logs3 += line + System.lineSeparator();
        }

        Path path = Paths.get("/home/george/Desktop/logs5.txt");

        Files.write(path, logs3.getBytes());

        System.out.println("-------------   stream ------------------");
        start = LocalDateTime.now();

        System.out.println(Files.lines(Paths.get(fileName))
                .filter(line -> line.contains("ERROR"))
                .count());


        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " ms");


        System.out.println("-------------   scanner ------------------");
        start = LocalDateTime.now();




        Scanner scanner = new Scanner(new File(fileName));
        errorLinesCount = 0;
        while (scanner.hasNext()){
            if(scanner.nextLine().contains("ERROR")){
                errorLinesCount++;
            }
        }
        System.out.println(errorLinesCount);
        System.out.println(ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " ms");
    }
}
