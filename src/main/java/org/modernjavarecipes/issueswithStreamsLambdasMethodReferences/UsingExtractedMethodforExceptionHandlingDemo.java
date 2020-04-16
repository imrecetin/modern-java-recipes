package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsingExtractedMethodforExceptionHandlingDemo {

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(30, 10, 40, 10, 50, 90);
        List<Integer> scaled = UsingExtractedMethodforExceptionHandlingDemo.div(values, 10);
        System.out.println(scaled);
        // prints: [3, 1, 4, 1, 5, 9]
        scaled = UsingExtractedMethodforExceptionHandlingDemo.div(values, 0);
        System.out.println(scaled);
        // throws ArithmeticException: / by zero

    }

    //Extracting a lambda into a method
    private Integer divide(Integer value, Integer factor) {
        try {
            return value / factor;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return value;
    }

    public List<Integer> divUsingMethod(List<Integer> values, Integer factor) {
        return values.stream()
                .map(n -> divide(n, factor))
                .collect(Collectors.toList());
    }

    //Lambda expression with try/catch
    public static List<Integer> divEx(List<Integer> values, Integer factor) throws Exception {
        return values.stream()
                .map( n -> {
                    try {
                        return n / factor;
                    } catch (ArithmeticException e) {
                        e.printStackTrace();
                    }
                    return n / factor;
                }).collect(Collectors.toList());
    }

    //A lambda expression that may throw an unchecked exception
    public static List<Integer> div(List<Integer> values, Integer factor) {
        return values.stream()
                .map(n -> n / factor)
                .collect(Collectors.toList());
    }
}
