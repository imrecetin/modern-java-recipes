package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class IteratingOverCollectionsMapsDemo {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        integers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        integers.forEach((Integer n) -> {
            System.out.println(n);
        });
        integers.forEach(n -> System.out.println(n));
        integers.forEach(System.out::println);

        //Iterating over a Map
        java.util.Map<Long, String> map = new HashMap<>();
        map.put(86L, "Don Adams (Maxwell Smart)");
        map.put(99L, "Barbara Feldon");
        map.put(13L, "David Ketchum");
        map.forEach((num, agent) ->
                System.out.printf("Agent %d, played by %s%n", num, agent));
    }

}
