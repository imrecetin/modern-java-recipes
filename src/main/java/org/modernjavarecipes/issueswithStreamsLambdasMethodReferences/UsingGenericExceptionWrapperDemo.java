package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UsingGenericExceptionWrapperDemo {
    public static void main(String[] args) {

    }

    //Using a generic static wrapper method
    public List<String> encodeValuesWithWrapper(String... values) {
        return Arrays.stream(values)
                .map(wrapper(s -> URLEncoder.encode(s, "UTF-8")))
                .collect(Collectors.toList());
    }

    //A functional interface based on Function that throws Exception
    @FunctionalInterface
    public interface FunctionWithException<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    //A wrapper method to deal with exceptions
    private static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
