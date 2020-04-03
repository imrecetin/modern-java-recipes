package org.modernjavarecipes.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UsingAnyMatchAllMatchNoneMatch {

    public static void main(String[] args) {
        /*
        * The signatures of the anyMatch, allMatch, and noneMatch methods on Stream are:
        boolean anyMatch(Predicate<? super T> predicate)
        boolean allMatch(Predicate<? super T> predicate)
        boolean noneMatch(Predicate<? super T> predicate)
        * */
        UsingAnyMatchAllMatchNoneMatch calculator=new UsingAnyMatchAllMatchNoneMatch();

        IntStream.of(2, 3, 5, 7, 11, 13, 17, 19).allMatch(calculator::isPrime);
        Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20).anyMatch(calculator::isPrime);

        Stream.empty().allMatch(e -> false);
        Stream.empty().noneMatch(e -> true);
        Stream.empty().anyMatch(e -> true);
        //For both allMatch and noneMatch, the Javadocs say, “if the stream is empty then true
        //is returned and the predicate is not evaluated
    }

    public boolean isPrime(int num) {
        int limit = (int) (Math.sqrt(num) + 1);
        return num == 2 || num > 1 && IntStream.range(2, limit).noneMatch(divisor -> num % divisor == 0);
    }

}
