package org.modernjavarecipes.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoxedStreamsDemo {

    public static void main(String[] args) {
        //Converting a stream of strings to a list
        List<String> strings = Stream.of("this", "is", "a", "list", "of", "strings")
                .collect(Collectors.toList());

        //Using the boxed method
        List<Integer> ints = IntStream.of(3, 1, 4, 1, 5, 9)
                .boxed()
                .collect(Collectors.toList());
        //Using the mapToObj method
        ints = IntStream.of(3, 1, 4, 1, 5, 9)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        //Using the three-argument version of collect
        ints = IntStream.of(3, 1, 4, 1, 5, 9)
                .collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);

        //Convert an IntStream to an int array
        int[] intArray = IntStream.of(3, 1, 4, 1, 5, 9).toArray();



    }

}
