package org.modernjavarecipes.theOptionalType;

import java.util.Optional;

public class CreatinganOptionalDemo {

    /*Incidentally, the classes OptionalInt, OptionalLong, and OptionalDouble wrap
    primitives that can never be null, so they only have an of method:
    static OptionalInt of(int value)
    static OptionalLong of(long value)
    static OptionalDouble of(double value)
    Instead of get, the getter methods on those classes are getAsInt, getAsLong, and
    getAsDouble.*/

    public static void main(String[] args) {
        //instances of Optional are immutable
    }

    //Creating an Optional with “of”
    public static <T> Optional<T> createOptionalTheHardWay(T value) {
        return value == null ? Optional.empty() : Optional.of(value);
    }

    //Creating an Optional with “ofNullable”
    public static <T> Optional<T> createOptionalTheEasyWay(T value) {
        return Optional.ofNullable(value);
    }

}
