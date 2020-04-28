package org.modernjavarecipes.java9;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamMapDemo {
    public static void main(String[] args) {
        //Mapping a List<Employee> to a List<String>
        List<String> names = Arrays.asList(new Employee()).stream()
                .map(Employee::getName)
                .collect(toList());
        java.util.List<String> strings = Arrays.asList(new Employee()).stream()
                .map(Object::toString)
                .collect(toList());
    }
    static class Employee {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
