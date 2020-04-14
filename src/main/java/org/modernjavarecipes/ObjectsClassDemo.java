package org.modernjavarecipes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectsClassDemo {

    public static void main(String[] args) {
        //Returning a collection and filtering out nulls
        List<String> strings = Arrays.asList(
                "this", null, "is", "a", null, "list", "of", "strings", null);
        List<String> nonNullStrings = strings.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        assertTrue(Objects.deepEquals(strings, nonNullStrings);
    }

    //Filtering nulls from a generic list
    public <T> List<T> getNonNullElements(List<T> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
