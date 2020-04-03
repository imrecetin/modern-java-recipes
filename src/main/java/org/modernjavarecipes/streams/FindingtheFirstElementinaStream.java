package org.modernjavarecipes.streams;

import java.util.Optional;
import java.util.stream.Stream;

public class FindingtheFirstElementinaStream {

    public static void main(String[] args) {

        //Finding the first even integer
        Optional<Integer> firstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).filter(n -> n % 2 == 0).findFirst();

        //Using findFirst on an empty stream
        Optional<Integer> firstEvenGT10 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n > 10)
                .filter(n -> n % 2 == 0)
                .findFirst();

        //Using firstEven in parallel
        firstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .parallel()
                .filter(n -> n % 2 == 0)
                .findFirst();
        FindingtheFirstElementinaStream demo=new FindingtheFirstElementinaStream();
        Optional<Integer> any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .parallel()
                .map(demo::delay)
                .findAny();
        System.out.println("Any: " + any);

        //Using findAny on sequential and parallel streams
        any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .map(demo::delay)
                .findAny();
        System.out.println("Sequential Any: " + any);
        any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .parallel()
                .map(demo::delay)
                .findAny();
        System.out.println("Parallel Any: " + any);

        /*the key concept is that of encounter order with streams. If the stream has an
        encounter order, then findFirst will always return the same value. The findAny
        method is allowed to return any element, making it more appropriate for parallel
        operations.*/
    }

    public Integer delay(Integer n) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException ignored) {
        }
        return n;
    }

}
