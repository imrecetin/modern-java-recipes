package org.modernjavarecipes.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcatenatingStreams {

    public static void main(String[] args) {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        List<String> strings = Stream.concat(first, second)
                .collect(Collectors.toList());
        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z");

        first = Stream.of("a", "b", "c").parallel();
        second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        strings = Stream.concat(Stream.concat(first, second), third)
                .collect(Collectors.toList());
        stringList = Arrays.asList("a", "b", "c",
                "X", "Y", "Z", "alpha", "beta", "gamma");

        Stream<String> fourth = Stream.empty();
        strings = Stream.of(first, second, third, fourth)
                .reduce(Stream.empty(), Stream::concat).collect(Collectors.toList());
        //Using reduce with an empty stream and a binary operator

        strings = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        stringList = Arrays.asList("a", "b", "c",
                "X", "Y", "Z", "alpha", "beta", "gamma");

        //you can always make the stream parallel if you want by calling the parallel
        //method, as long as you have not yet processed the data

        //Using concat creates a parallel stream if
        //any of the input streams are parallel, but flatMap does not
        Stream<String> total = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity());
        //assertFalse(total.isParallel());
        total = total.parallel(); //to make flatmap parallel
        //assertTrue(total.isParallel());

        //In short, the concat method is effective for two streams, and can be used as part of a
        //general reduction operation, but flatMap is a natural alternative.

    }
}
