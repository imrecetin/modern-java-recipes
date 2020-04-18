package org.modernjavarecipes.fileInputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessFilesDemo {
    public static void main(String[] args) {
        //Finding the 10 longest words in the web2 dictionary
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Determining number of words of each length
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.println(len + ": " + num));
        }catch (IOException e) {
            e.printStackTrace();
        }

        //Number of words of each length, in descending order
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n",
                            e.getKey(), e.getValue()));
        }catch (IOException e) {
            e.printStackTrace();
        }

        //Using BufferedReader.lines method
        try (Stream<String> lines =
                     new BufferedReader(
                             new FileReader("/usr/share/dict/words")).lines()) {
            // ... same as previous example ...
        }catch (IOException e) {
            e.printStackTrace();
        }

        //since Stream implements AutoCloseable, when the try-with-resources
        //block closes the stream, it will then close the underlying BufferedReader

    }
}
