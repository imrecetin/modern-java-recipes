package org.modernjavarecipes.comparatorsandcollectors;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class FindingMaxMinValuesDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Cersei", 250_000, "Lannister"),
                new Employee("Jamie", 150_000, "Lannister"),
                new Employee("Tyrion", 1_000, "Lannister"),
                new Employee("Tywin", 1_000_000, "Lannister"),
                new Employee("Jon Snow", 75_000, "Stark"),
                new Employee("Robb", 120_000, "Stark"),
                new Employee("Eddard", 125_000, "Stark"),
                new Employee("Sansa", 0, "Stark"),
                new Employee("Arya", 1_000, "Stark"));
        Employee defaultEmployee = new Employee("A man (or woman) has no name", 0, "Black and White");

        Optional<Employee> optionalEmp = employees.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(Employee::getSalary)));
        System.out.println("Emp with max salary: " +
                optionalEmp.orElse(defaultEmployee));

        //Using Stream.max
        optionalEmp = employees.stream()
                .max(Comparator.comparingInt(Employee::getSalary));

        //Finding the highest salary
        OptionalInt maxSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .max();
        System.out.println("The max salary is " + maxSalary);

        //Using Collectors.maxBy
        optionalEmp = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));

        //Using Collectors.maxBy as a downstream collector
        Map<String, Optional<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(
                                Comparator.comparingInt(Employee::getSalary))));
        map.forEach((house, emp) ->
                System.out.println(house + ": " + emp.orElse(defaultEmployee)));

    }

    public static class Employee {
        private String name;
        private Integer salary;
        private String department;

        public Employee(String s, int i, String black_and_white) {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }
}
