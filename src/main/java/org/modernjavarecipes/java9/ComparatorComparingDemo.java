package org.modernjavarecipes.java9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class ComparatorComparingDemo {
    public static void main(String[] args) {
        /*The signature of comparing is:
        static <T,U extends Comparable<? super U>> Comparator<T> comparing(
                Function<? super T,? extends U> keyExtractor)*/

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Seth Curry"),
                new Employee(2, "Kevin Durant"),
                new Employee(3, "Draymond Green"),
                new Employee(4, "Klay Thompson"));
        Employee maxId = employees.stream()
                .max(comparingInt(Employee::getId))
                .orElse(Employee.DEFAULT_EMPLOYEE);
        Employee maxName = employees.stream()
                .max(comparing(Object::toString))
                .orElse(Employee.DEFAULT_EMPLOYEE);
        System.out.println(maxId);
        System.out.println(maxName);

        // Add employees to a map using id as key
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        //Sorting Map elements by key and printing
        employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        System.out.println("Sorted by key:");
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                });

        // Sort employees by name and print them (DOES NOT COMPILE)
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())  // should implement Comparable<Employee>
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                });

        // Sort employees by name and print them
        System.out.println("Sorted by name:");
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName)))
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                });
    }
}
