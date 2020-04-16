package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MappingOptionalsDemo {
    public static void main(String[] args) {

    }

    //Finding Employees by ID
    public List<Employee> findEmployeesByIds(List<Integer> ids) {
        return ids.stream()
                .map(this::findEmployeeById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    //Using Optional.map
    public List<Employee> findEmployeesByIdsWithMap(List<Integer> ids) {
        return ids.stream()
                .map(this::findEmployeeById)
                .flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }

    public Optional<Employee> findEmployeeById(int id){
        return Optional.ofNullable(new Employee());
    }

    public static class Employee{

    }
}
