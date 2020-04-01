package org.modernjavarecipes.streams;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ReductionOperationsUsingReduceDemo {

    public static void main(String[] args) {

        //Reduction operations on IntStream
        String[] strings = "this is an array of strings".split(" ");
        long count = Arrays.stream(strings)
                .map(String::length)
                .count();
        System.out.println("There are " + count + " strings");
        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length)
                .sum();
        System.out.println("The total length is " + totalLength);
        OptionalDouble ave = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        System.out.println("The average length is " + ave);
        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.println("The max and min lengths are " + max + " and " + min);

        //Summing numbers using reduce
        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y).orElse(0);

        //Printing the values of x and y
        sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d%n", x, y);
                    return x + y;
                }).orElse(0);

        //Doubling the values during the sum (NOTE: NOT CORRECT)
        int doubleSum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + 2 * y).orElse(0);

        //Doubling the values during the sum (WORKS)
        doubleSum = IntStream.rangeClosed(1, 10)
                .reduce(0, (x, y) -> x + 2 * y);

        //Performing a reduce with a binary operator
        sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        //Finding the max using reduce
        Integer max2 = Stream.of(3, 1, 4, 1, 5, 9)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("The max value is " + max2);

        //Concatenating strings from a stream using reduce
        String s = Stream.of("this", "is", "a", "list")
                .reduce("", String::concat);
        System.out.println(s);

        //Collecting strings using a StringBuilder
        s = Stream.of("this", "is", "a", "list")
                .collect(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();

        //Collecting strings, with method references
        s = Stream.of("this", "is", "a", "list")
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();

        //Joining strings using Collectors
        s = Stream.of("this", "is", "a", "list")
                .collect(Collectors.joining());


        List<Book> books=Stream.of(new Book()).collect(toList());
        //Accumulating Books into a Map
        HashMap<Integer, Book> bookMap = books.stream()
                .reduce(new HashMap<Integer, Book>(),
                        (map, book) -> {
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });
        bookMap.forEach((k,v) -> System.out.println(k + ": " + v));


        //Summing BigDecimals with reduce
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));
        System.out.println("The total is " + total);

        //Sorting strings by length
        List<String> stringList = Arrays.asList(
                "this", "is", "a", "list", "of", "strings");
        List<String> sorted = stringList.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(toList());

        IntStream.rangeClosed(1, 100)
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();

        //Using multiple peek methods
        IntStream.rangeClosed(1, 100)
                    .peek(n -> System.out.printf("original: %d%n", n))
                    .map(n -> n * 2)
                    .peek(n -> System.out.printf("doubled : %d%n", n))
                    .filter(n -> n % 3 == 0)
                    .peek(n -> System.out.printf("filtered: %d%n", n))
                    .sum();

    }


    public static class Book {
        private Integer id;
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
