package org.modernjavarecipes.streams;

import org.modernjavarecipes.lambda.PalindromeChecker;

import java.util.stream.Stream;

public class StreamsForStringAndBack {

    public static void main(String[] args) {
        PalindromeChecker demo=new PalindromeChecker();
        Stream.of("Madam, in Eden, I'm Adam",
                "Go hang a salami; I'm a lasagna hog",
                "Flee to me, remote elf!",
                "A Santa pets rats as Pat taps a star step at NASA")
                .allMatch(demo::isPalindromeFor8);

    }

    public static class PalindromeChecker {
        //Checking for palindromes in Java 7 or earlier
        public boolean isPalindrome(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    sb.append(c);
                }
            }
            String forward = sb.toString().toLowerCase();
            String backward = sb.reverse().toString().toLowerCase();
            return forward.equals(backward);
        }

        //Checking for palindromes using Java 8 streams
        public boolean isPalindromeFor8(String s) {
            String forward = s.toLowerCase().codePoints()
                    .filter(Character::isLetterOrDigit)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
            String backward = new StringBuilder(forward).reverse().toString();
            return forward.equals(backward);
        }
    }
}
