package org.modernjavarecipes.comparatorsandcollectors;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortingMaps {
    public static void main(String[] args) {
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> lines = Files.lines("dictionary")) {
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(
                            String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sorting the map by key
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> lines = Files.lines(dictionary)) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(
                            String::length, Collectors.counting()));
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %2d words%n",
                            e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
