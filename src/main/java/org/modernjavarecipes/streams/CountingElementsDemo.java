package org.modernjavarecipes.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingElementsDemo {

    public static void main(String[] args) {
        //Counting elements in a stream
        long count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).count();
        System.out.printf("There are %d elements in the stream%n", count);

        //Counting the elements using Collectors.counting
        count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .collect(Collectors.counting());
        System.out.printf("There are %d elements in the stream%n", count);

        List<String> strings=Arrays.asList("Madam, in Eden, I'm Adam",
                "Go hang a salami; I'm a lasagna hog",
                "Flee to me, remote elf!",
                "A Santa pets rats as Pat taps a star step at NASA");

        //Counting string partitioned by length
        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.length() % 2 == 0,
                        Collectors.counting()));
        numberLengthMap.forEach((k,v) -> System.out.printf("%5s: %d%n", k, v));
    }

}
