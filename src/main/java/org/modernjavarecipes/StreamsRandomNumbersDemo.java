package org.modernjavarecipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsRandomNumbersDemo {
    public static void main(String[] args) {
        //Generating streams of random numbers
        Random r = new Random();
        r.ints(5).sorted().forEach(System.out::println);

        r.doubles(5, 0, 0.5).sorted()
                .forEach(System.out::println);

        List<Long> longs = r.longs(5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);

        List<Integer> listOfInts = r.ints(5, 10, 20)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println(listOfInts);
    }
}
