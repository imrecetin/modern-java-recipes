package org.modernjavarecipes.generic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class GenericDemo {
    //Java 10, known as Project Valhalla, has proposed adding primitive types to collections
    public static void main(String[] args) {
        //Simple generics demo
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        // strings.add(new Date());
        // Integer i = strings.get(0);
        for (String s : strings) {
            System.out.printf("%s has length %d%n", s, s.length());
        }

        //Using primitives in generic collections
        List<Integer> ints = new ArrayList<>();
        ints.add(3); ints.add(1); ints.add(4);
        ints.add(1); ints.add(9); ints.add(2);
        System.out.println(ints);
        for (int i : ints) {
            System.out.println(i);
        }

        //Using a List<Object>
        List<Object> objects = new ArrayList<Object>();
        objects.add("Hello");
        objects.add(LocalDate.now());
        objects.add(3);
        System.out.println(objects);

        //A wildcard is a type argument that uses a question mark, ?, which may or may not
        //have an upper or lower bound

        //A List with an unbounded wildcard
        List<?> stuff = new ArrayList<>();
        // stuff.add("abc");
        // stuff.add(new Object());
        // stuff.add(3);
        int numElements = stuff.size();

        //Upper Bounded Wildcards
        //A List with an upper bound
        List<? extends Number> numbers = new ArrayList<>();
        // numbers.add(3);
        // numbers.add(3.14159);
        // numbers.add(new BigDecimal("3"));

        ints = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<BigDecimal> bigDecimals = Arrays.asList(
                new BigDecimal("1.0"),
                new BigDecimal("2.0"),
                new BigDecimal("3.0"),
                new BigDecimal("4.0"),
                new BigDecimal("5.0")
        );
        System.out.printf("ints sum is %s%n", sumList(ints));
        System.out.printf("doubles sum is %s%n", sumList(doubles));
        System.out.printf("big decimals sum is %s%n", sumList(bigDecimals));

        //Lower Bounded Wildcards
        //Using the numsUpTo method
        ArrayList<Integer> integerList = new ArrayList<>();
        ArrayList<Number> numberList = new ArrayList<>();
        ArrayList<Object> objectList = new ArrayList<>();
        numsUpTo(5, integerList);
        numsUpTo(5, numberList);
        numsUpTo(5, objectList);

        //PECS
        /*It means that if a parameterized type represents a producer, use
        extends. If it represents a consumer, use super. If the parameter is both, don’t use
        wildcards at all—the only type that satisfies both requirements is the explicit type
        itself.*/

        //The advice boils down to:
        //• Use extends when you only get values out of a data structure
        //• Use super when you only put values into a data structure
        //• Use an explicit type when you plan to do both


    }

    //A method to populate a given list
    public static void numsUpTo(Integer num, List<? super Integer> output) {
        IntStream.rangeClosed(1, num)
                .forEach(output::add);
    }

    //Unbounded List as a method arg
    private static void printList(List<?> list) {
        System.out.println(list);
    }

    //Using an upper bound
    private static double sumList(List<? extends Number> list) {
        return list.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    //Extracting a value from an upper bound reference
    private static double sumListExtract(List<? extends Number> list) {
        Number num = list.get(0);
        // ... from before ...
        return num.doubleValue();
    }

}
