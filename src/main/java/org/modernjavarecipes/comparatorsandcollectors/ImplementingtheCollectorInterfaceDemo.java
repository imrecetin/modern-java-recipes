package org.modernjavarecipes.comparatorsandcollectors;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImplementingtheCollectorInterfaceDemo {
    public static void main(String[] args) {

    }

    //Using collect to return a List
    public List<String> evenLengthStrings(String... strings) {
        return Stream.of(strings)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
    }

    //Using collect to return an unmodifiable SortedSet
    public SortedSet<String> oddLengthStringSet(String... strings) {
        Collector<String, ?, SortedSet<String>> intoSet =
                Collector.of(TreeSet<String>::new,
                        SortedSet::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        },
                        Collections::unmodifiableSortedSet);
        return Stream.of(strings)
                .filter(s -> s.length() % 2 != 0)
                .collect(intoSet);
    }
}
