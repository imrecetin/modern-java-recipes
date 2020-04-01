package org.modernjavarecipes.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);

        //Stream.forEach(Consumer<? super T> action);
        //Optional.ifPresent(Consumer<? super T> consumer)
        //Stream.peek(Consumer<? super T> action)
    }
}
