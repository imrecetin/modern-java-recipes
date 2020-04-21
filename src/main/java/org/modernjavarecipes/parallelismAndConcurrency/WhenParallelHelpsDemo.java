package org.modernjavarecipes.parallelismAndConcurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class WhenParallelHelpsDemo {
    public static void main(String[] args) {
        Runtime.getRuntime().availableProcessors();
        Instant before = Instant.now();
        Integer total = IntStream.of(3, 1, 4, 1, 5, 9)
                .map(WhenParallelHelpsDemo::doubleIt)
                .sum();
        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");

        //Using a parallel stream
        before = Instant.now();
        total = IntStream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .map(WhenParallelHelpsDemo::doubleIt)
                .sum();
        after = Instant.now();
        duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");


    }

    //Adding integers in a sequential stream
    public static int doubleIt(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
        return n * 2;
    }

    //Summing generic streams
    public long sequentialStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(Integer.MAX_VALUE)
                .reduce(0L, Long::sum);
    }

    public long parallelStreamSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(Integer.MAX_VALUE)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /*The approaches that use
    Stream<Long> instead of LongStream are much slower, especially combined with the
    fact that using a fork-join pool with iterate is not easy to divide. Using LongStream
    with a rangeClosed method is so fast that there is very little difference between
    sequential and parallel performance at all.*/

    //Using LongStream
    public long sequentialLongStreamSum() {
        return LongStream.rangeClosed(1, Integer.MAX_VALUE)
                .sum();
    }
    public long parallelLongStreamSum() {
        return LongStream.rangeClosed(1, Integer.MAX_VALUE)
                .parallel()
                .sum();
    }

    //Iteratively summing numbers in a loop
    public long iterativeSum() {
        long result = 0;
        for (long i = 1L; i <= Integer.MAX_VALUE; i++) {
            result += i;
        }
        return result;
    }

}
