package org.modernjavarecipes.streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class LazyStreams {

    public static void main(String[] args) {
        //Streams are already lazy and do not process elements until a terminal condition is
        //reached. Then each element is processed individually. If there is a short-circuiting
        //operation at the end, the stream processing will terminate whenever all the conditions
        //are satisfied.

        //First double between 200 and 400 divisible by 3
        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .findFirst();
        System.out.println(firstEvenDoubleDivBy3);

        //Streams are lazy, in that no
        //work is done until the terminal condition is reached, and then each element is pro‐
        //cessed through the pipeline individually.
    }

}
