package org.modernjavarecipes.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAndStaticMethodsInterfacesDemo {

    public static void main(String[] args) {

        //Using default methods
        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9);
        boolean removed = nums.removeIf(n -> n <= 0);
        System.out.println("Elements were " + (removed ? "" : "NOT") + " removed");
        nums.forEach(System.out::println);

        //Sorting strings
        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore",
                "Dalton", "Brosnan", "Craig");
        List<String> sorted = bonds.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]
        sorted = bonds.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        // [Moore, Lazenby, Dalton, Craig, Connery, Brosnan]
        sorted = bonds.stream()
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]
        sorted = bonds.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        // [Moore, Craig, Dalton, Connery, Lazenby, Brosnan]
        sorted = bonds.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        // [Craig, Moore, Dalton, Brosnan, Connery, Lazenby]

    }
}
