package org.modernjavarecipes.parallelismAndConcurrency;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConvertingFromSequentialToParallelStreamsDemo {
    public static void main(String[] args) {

        //Switching from parallel to sequential (NOT WHAT YOU MIGHTEXPECT)
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> nums = numbers.parallelStream()
                .map(n -> n * 2)
                .peek(n -> System.out.printf("%s processing %d%n",
                        Thread.currentThread().getName(), n))
                .sequential()
                .sorted()
                .collect(Collectors.toList());

        /*When executing, a stream can be either parallel or sequential. The
        parallel or sequential methods effectively set or unset a
        boolean, which is checked when the terminal expression is reached.*/

    }

    //Creating sequential streams (parts of a JUnit test)
    @Test
    public void sequentialStreamOf() throws Exception {
        assertFalse(Stream.of(3, 1, 4, 1, 5, 9).isParallel());
    }
    @Test
    public void sequentialIterateStream() throws Exception {
        assertFalse(Stream.iterate(1, n -> n + 1).isParallel());
    }
    @Test
    public void sequentialGenerateStream() throws Exception {
        assertFalse(Stream.generate(Math::random).isParallel());
    }
    @Test
    public void sequentialCollectionStream() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.stream().isParallel());
    }

    //Using the parallelStream method
    @Test
    public void parallelStreamMethodOnCollection() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertTrue(numbers.parallelStream().isParallel());
    }

    //Using the parallel method on a stream
    @Test
    public void parallelMethodOnStream() throws Exception {
        assertTrue(Stream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .isParallel());
    }

    //Converting a parallel stream to sequential
    @Test
    public void parallelStreamThenSequential() throws Exception {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.parallelStream()
                .sequential()
                .isParallel());
    }
}
