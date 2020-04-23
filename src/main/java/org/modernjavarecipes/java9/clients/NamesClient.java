package org.modernjavarecipes.java9.clients;

import org.modernjavarecipes.java9.suppliers.NamesSupplier;

import java.util.stream.Stream;

public class NamesClient {
    public static void main(String[] args) {
        NamesSupplier supplier = new NamesSupplier();
        try (Stream<String> lines = supplier.get()) {
            lines.forEach(line -> System.out.printf("Hello, %s!%n", line));
        } //try-with-resources auto-closes stream
    }
}
