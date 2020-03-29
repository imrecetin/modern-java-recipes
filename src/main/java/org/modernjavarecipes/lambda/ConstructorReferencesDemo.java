package org.modernjavarecipes.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructorReferencesDemo {

    public static void main(String[] args) {

        //Transforming strings into Person instances
        List<String> names =
                Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace", "Karen Spärck Jones");
        List<Person> people = names.stream()
                .map(name -> new Person(name))
                .collect(Collectors.toList());
        // or, alternatively,
        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());

        //Converting a list of people to a list of names
        names = people.stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());
        // or, alternatively,
        names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        //Converting a list to a stream and back
        Person before = new Person("Grace Hopper");
        people = Stream.of(before).collect(Collectors.toList());
        Person after = people.get(0);
        assertTrue(before == after);
        before.setName("Grace Murray Hopper");
        assertEquals("Grace Murray Hopper", after.getName());

        //Using the copy constructor
        people = Stream.of(before)
                .map(Person::new)
                .collect(Collectors.toList());
        after = people.get(0);
        assertFalse(before == after);
        assertEquals(before, after);
        before.setName("Rear Admiral Dr. Grace Murray Hopper");
        assertFalse(before.equals(after));

        //Using the varargs constructor
        names.stream()
                .map(name -> name.split(" "))
                .map(Person::new)
                .collect(Collectors.toList());

        //Creating an array of Person references
        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);


    }
}
