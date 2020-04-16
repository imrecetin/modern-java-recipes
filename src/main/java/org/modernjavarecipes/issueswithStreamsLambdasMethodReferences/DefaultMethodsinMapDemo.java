package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DefaultMethodsinMapDemo {
    public static void main(String[] args) {

        //Calling the countWords method
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' Agent says, 'heard it'.";
        Map<String, Integer> counts = countWords(passage, "NSA", "agent", "joke");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));
        // Output is agent=1, NSA=2, joke=1

    }

    //Fibonacci calculation with a cache
    private Map<Long, BigInteger> cache = new HashMap<>();
    public BigInteger fib(long i) {
        if (i == 0) return BigInteger.ZERO;
        if (i == 1) return BigInteger.ONE;
        return cache.computeIfAbsent(i, n -> fib(n - 2).add(fib(n - 1)));
    }

    //Update the word counts only for specific words
    public static Map<String,Integer> countWords(String passage, String... strings) {
        Map<String, Integer> wordCounts = new HashMap<>();
        Arrays.stream(strings).forEach(s -> wordCounts.put(s, 0));
        Arrays.stream(passage.split(" ")).forEach(word ->
                wordCounts.computeIfPresent(word, (key, val) -> val + 1));
        return wordCounts;
    }

    //Using the merge method
    public Map<String, Integer> fullWordCounts(String passage) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String testString = passage.toLowerCase().replaceAll("\\W"," ");
        Arrays.stream(testString.split("\\s+")).forEach(word ->
                wordCounts.merge(word, 1, Integer::sum));
        return wordCounts;
    }
}
