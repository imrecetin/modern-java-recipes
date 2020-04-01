package org.modernjavarecipes.functions;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicatesDemo {

    public static void main(String[] args) {
        ImplementPredicate demo = new ImplementPredicate();
        String[] names= Stream.of("Mal", "Wash", "Kaylee", "Inara", "Zoë",
                "Jayne", "Simon", "River", "Shepherd Book")
                .sorted()
                .toArray(String[]::new);
        getNamesOfLength(5,names);
        getNamesStartingWith("S",names);

        demo.getNamesSatisfyingCondition(s -> s.length() == 5, names);
        demo.getNamesSatisfyingCondition(s -> s.startsWith("S"),names);
        demo.getNamesSatisfyingCondition(LENGTH_FIVE, names);
        demo.getNamesSatisfyingCondition(STARTS_WITH_S, names);

        demo.getNamesSatisfyingCondition(LENGTH_FIVE.and(STARTS_WITH_S), names);
        demo.getNamesSatisfyingCondition(LENGTH_FIVE.or(STARTS_WITH_S), names);
        demo.getNamesSatisfyingCondition(LENGTH_FIVE.negate(), names);

    }

    //Finding strings of a given length
    public static String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.length() == length)
                .collect(Collectors.joining(", "));
    }

    //Finding strings that start with a given string
    public static String getNamesStartingWith(String s, String... names) {
        return Arrays.stream(names)
                .filter(t -> t.startsWith(s))
                .collect(Collectors.joining(", "));
    }

    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> STARTS_WITH_S = s -> s.startsWith("S");

    //Finding strings that satisfy an arbitrary predicate
    public static class ImplementPredicate {
        public String getNamesSatisfyingCondition(Predicate<String> condition, String... names) {
            return Arrays.stream(names)
                    .filter(condition)
                    .collect(Collectors.joining(", "));
        }

    }
    // ... other methods ...

}
