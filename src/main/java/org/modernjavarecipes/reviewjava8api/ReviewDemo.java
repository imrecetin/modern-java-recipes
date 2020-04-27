package org.modernjavarecipes.reviewjava8api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class ReviewDemo {
    public static void main(String[] args) {
        //Finding the max Employee
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Seth Curry"),
                new Employee(2, "Kevin Durant"),
                new Employee(3, "Draymond Green"),
                new Employee(4, "Klay Thompson"));

        Employee maxId = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee e1, Employee e2) {
                        return e1.getId() - e2.getId();
                    }
                }).orElse(Employee.DEFAULT_EMPLOYEE);

        Employee maxName = employees.stream()
                .max(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return o1.toString().compareTo(o2.toString());
                    }
                }).orElse(Employee.DEFAULT_EMPLOYEE);

        System.out.println(maxId);
        System.out.println(maxName);

        //Idiomatic approach to finding the max Employee
        maxId = employees.stream()
                .max(comparingInt(Employee::getId))
                .orElse(Employee.DEFAULT_EMPLOYEE);
        maxName = employees.stream()
                .max(comparing(Object::toString))
                .orElse(Employee.DEFAULT_EMPLOYEE);
        System.out.println(maxId);
        System.out.println(maxName);
    }

    static class Employee {
        public static final Employee DEFAULT_EMPLOYEE = new Employee();
        private int id;
        private String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private Employee() { }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        // ... other methods ...
    }
}
