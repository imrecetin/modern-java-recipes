package org.modernjavarecipes.comparatorsandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DownstreamCollectorsDemo {
    public static void main(String[] args) {
        //Partitioning strings by even or odd lengths
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key,value) -> System.out.printf("%5s: %s%n", key, value));

        //Counting the partitioned strings
        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0,
                        Collectors.counting()));
        numberLengthMap.forEach((k,v) -> System.out.printf("%5s: %d%n", k, v));
    }
}
