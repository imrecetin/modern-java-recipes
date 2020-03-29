package org.modernjavarecipes.lambda;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Person {
    private String name;
    public Person() {}
    public Person(Person p) {
        this.name = p.name;
    }
    public Person(String... names) {
        this.name = Arrays.stream(names)
                .collect(Collectors.joining(" "));
    }
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
