package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClosureCompositionDemo {
    public static void main(String[] args) {
        //Using the compose and anden methods
        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, Integer> mult3 = x -> x * 3;
        Function<Integer, Integer> mult3add2 = add2.compose(mult3);
        Function<Integer, Integer> add2mult3 = add2.andThen(mult3);
        System.out.println("mult3add2(1): " + mult3add2.apply(1));
        System.out.println("add2mult3(1): " + add2mult3.apply(1));

        //Parse an integer from a string, then add 2
        add2 = x -> x + 2;
        Function<String, Integer> parseThenAdd2 = add2.compose(Integer::parseInt);
        System.out.println(parseThenAdd2.apply("1"));

        //Add a number, then convert to a string
        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, String> plus2toString = add2.andThen(Object::toString);
        System.out.println(plus2toString.apply(1));
        // prints "3"

        //Composed consumer for printing and logging
        Logger log = Logger.getLogger(ClosureCompositionDemo.class.toString());
        Consumer<String> printer = System.out::println;
        Consumer<String> logger = log::info;
        Consumer<String> printThenLog = printer.andThen(logger);
        Stream.of("this", "is", "a", "stream", "of", "strings").forEach(printThenLog);

        IntPredicate triangular = ClosureCompositionDemo::isTriangular;
        IntPredicate perfect = ClosureCompositionDemo::isPerfect;
        IntPredicate both = triangular.and(perfect);
        IntStream.rangeClosed(1, 10_000)
                .filter(both)
                .forEach(System.out::println);
    }

    //Triangle numbers that are perfect squares
    public static boolean isPerfect(int x) {
        return Math.sqrt(x) % 1 == 0;
    }
    public static boolean isTriangular(int x) {
        double val = (Math.sqrt(8 * x + 1) - 1) / 2;
        return val % 1 == 0;
    }

}
