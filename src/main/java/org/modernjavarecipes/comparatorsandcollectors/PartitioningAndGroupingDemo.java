package org.modernjavarecipes.comparatorsandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningAndGroupingDemo {
    public static void main(String[] args) {
        //Partitioning strings by even or odd lengths
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key,value) -> System.out.printf("%5s: %s%n", key, value));

        //Grouping strings by length
        strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Integer, List<String>> lengthMap2 = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        lengthMap2.forEach((k,v) -> System.out.printf("%d: %s%n", k, v));
    }
}
