package org.modernjavarecipes.generic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    }

    //Unbounded List as a method arg
    private static void printList(List<?> list) {
        System.out.println(list);
    }

}
