package org.modernjavarecipes.comparatorsandcollectors;

import java.util.*;

import static java.util.stream.Collectors.*;

public class CreatingImmutableCollectionsDemo {
    public static void main(String[] args) {
        //Creating an immutable Map
        Map<String, Integer> map = Collections.unmodifiableMap(
                new HashMap<String, Integer>() {{
                    put("have", 1);
                    put("the", 2);
                    put("high", 3);
                    put("ground", 4);
                }});
    }

    @SafeVarargs
    public static final <T> List<T> createImmutableListJava7(T... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    @SafeVarargs
    public static final <T> Set<T> createImmutableSetJava7(T... elements) {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(elements)));
    }

    @SafeVarargs
    public final <T> List<T> createImmutableList(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));
    }
    @SafeVarargs
    public final <T> Set<T> createImmutableSet(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toSet(),
                        Collections::unmodifiableSet));
    }
}
