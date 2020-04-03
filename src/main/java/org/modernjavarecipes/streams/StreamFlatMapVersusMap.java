package org.modernjavarecipes.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamFlatMapVersusMap {

    public static void main(String[] args) {
        /*Use map if each element is transformed into a single value. Use flatMap if each ele‐
        ment will be transformed to multiple values and the resulting stream needs to be
        “flattened.*/

        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");
        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        List<Customer> customers = Arrays.asList(sheridan, ivanova, garibaldi);

        //Using map on Customer to name
        customers.stream()
                .map(Customer::getName)
                .forEach(System.out::println);

        //Using map on Customer to orders
        customers.stream()
                .map(Customer::getOrders)
                .forEach(System.out::println);
        customers.stream()
                .map(customer -> customer.getOrders().stream())
                .forEach(System.out::println);

        //Using flatMap on Customer orders
        customers.stream()
                .flatMap(customer -> customer.getOrders().stream()) //generate Stream<Order>
                .forEach(System.out::println);
        //The Function argument to flatMap produces a Stream of output values
    }

    public static class Customer {
        private String name;
        private List<Order> orders = new ArrayList<>();
        public Customer(String name) {
            this.name = name;
        }
        public String getName() { return name; }
        public List<Order> getOrders() { return orders; }
        public Customer addOrder(Order order) {
            orders.add(order);
            return this;
        }
    }
    public static class Order {
        private int id;
        public Order(int id) {
            this.id = id;
        }
        public int getId() { return id; }
    }

}
