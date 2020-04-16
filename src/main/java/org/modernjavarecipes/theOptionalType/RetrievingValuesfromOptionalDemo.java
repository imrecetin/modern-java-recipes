package org.modernjavarecipes.theOptionalType;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class RetrievingValuesfromOptionalDemo {
    public static void main(String[] args) {
        //by invoking the get method. If the Optional is empty, however, then
        //the get method throws a NoSuchElementException.

        //Retrieving the first even-length string
        Optional<String> firstEven =
                Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 == 0)
                .findFirst();
        System.out.println(firstEven.get()); // Don't do this, even if it works

        //Retrieving the first odd-length string
        Optional<String> firstOdd =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 != 0)
                        .findFirst();
        System.out.println(firstOdd.get()); // throws NoSuchElementException

        //Retrieving the first even-length string with a protected get
        firstEven = Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        System.out.println(firstEven.isPresent() ? firstEven.get() : "No even length strings");

        //Using orElse
        firstOdd = Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 != 0)
                        .findFirst();
        System.out.println(firstOdd.orElse("No odd length strings"));

        //Using a Supplier in orElseGet
        Stream<ComplexObject> values = Stream.of(new ComplexObject());
        Optional<ComplexObject> val = values.findFirst();
        val.orElse(new ComplexObject());
        val.orElseGet(() -> new ComplexObject());

        //Using orElserow as a Supplier
        Optional<String> first =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        System.out.println(first.orElseThrow(NoSuchElementException::new));

        //the ifPresent method allows you to provide a Consumer that is only executed
        //when the Optional contains a value
        //Using the ifPresent method
        first = Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        first.ifPresent(v -> System.out.println("Found an even-length string : "+v));

        first = Stream.of("five", "even", "length", "string", "values")
                .filter(s -> s.length() % 2 != 0)
                .findFirst();
        first.ifPresent(v -> System.out.println("Found an odd-length string"));
    }

    public static class ComplexObject{

    }
}
