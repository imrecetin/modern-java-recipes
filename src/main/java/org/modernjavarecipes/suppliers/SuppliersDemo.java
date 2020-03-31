package org.modernjavarecipes.suppliers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SuppliersDemo {

    public static void main(String[] args) {
        //Using Math.random() as a Supplier
        Logger logger = Logger.getLogger(SuppliersDemo.class.toString());
        DoubleSupplier randomSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };
        randomSupplier = () -> Math.random();
        randomSupplier = Math::random;

        //logger.info(randomSupplier);

        //Finding a name from a collection
        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara",
                "Zoë", "Jayne", "Simon", "River", "Shepherd Book");
        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("C"))
                .findFirst();
        System.out.println(first);
        System.out.println(first.orElse("None"));
        System.out.println(first.orElse(String.format("No result found in %s",
                names.stream().collect(Collectors.joining(", ")))));
        System.out.println(first.orElseGet(() ->
                String.format("No result found in %s",
                        names.stream().collect(Collectors.joining(", ")))));
    }
}
