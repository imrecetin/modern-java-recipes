package org.modernjavarecipes.java9;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalStreamOrIfPresentOrElseDemo {
    public static void main(String[] args) {
        Customer customer = findById(1).orElse(Customer.DEFAULT);
        customer = findById(1).orElseGet(() -> createDefaultCustomer());
    }

    //Using ifPresent to only print nonempty customers
    public void printCustomer(Integer id) {
        findByIdLocal(id).ifPresent(System.out::println);
    }
    public void printCustomers(Integer... ids) {
        Arrays.asList(ids).forEach(this::printCustomer);
    }

    //Printing a customer or a default message
    public void printCustomerWithPresent(Integer id) {
        findByIdLocal(id).ifPresentOrElse(System.out::println, () -> System.out.println("Customer with id=" + id + " not found"));
    }

    private static Customer createDefaultCustomer() {
        return Customer.DEFAULT;
    }

    public Optional<Customer> findByIdWithOr(int id) {
        return findById(id)
                .or(() -> findByIdRemote(id))
                .or(() -> Optional.of(Customer.DEFAULT));
    }

    //Using a filter and a Map on Optionals
    public static Collection<Customer> findAllById(Integer... ids) {
        return Arrays.stream(ids)
                .map(OptionalStreamOrIfPresentOrElseDemo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    //Using flatMap with Optional.stream
    public static Collection<Customer> findAllByIdWithFlatMap(Integer... ids) {
        return Arrays.stream(ids)
                .map(OptionalStreamOrIfPresentOrElseDemo::findById)
                .flatMap(Optional::stream).collect(Collectors.toList());
    }

    //Find a customer by ID
    public static Optional<Customer> findById(int id) {
        return Optional.ofNullable(customers.get(id));
    }

    //Find a customer by ID
    public static Optional<Customer> findByIdRemote(int id) {
        return Optional.ofNullable(customers.get(id));
    }

    //Find a customer by ID
    public static Optional<Customer> findByIdLocal(int id) {
        return Optional.ofNullable(customers.get(id));
    }

    static Map<Integer,Customer> customers=new HashMap<>();

    public static class Customer{
        public static final Customer DEFAULT = new Customer();
    }
}
