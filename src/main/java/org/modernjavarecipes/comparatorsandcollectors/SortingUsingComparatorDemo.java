package org.modernjavarecipes.comparatorsandcollectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

public class SortingUsingComparatorDemo {

    public static void main(String[] args) {

    }

    //Sorting strings lexicographically
    private List<String> sampleStrings =
            Arrays.asList("this", "is", "a", "list", "of", "strings");
    public List<String> defaultSort() {
        Collections.sort(sampleStrings);
        return sampleStrings;
    }
    public List<String> defaultSortUsingStreams() {
        return sampleStrings.stream()
                .sorted()
                .collect(toList());
    }

    //Sorting strings by length
    public List<String> lengthSortUsingSorted() {
        return sampleStrings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(toList());
    }
    public List<String> lengthSortUsingComparator() {
        return sampleStrings.stream()
                .sorted(comparingInt(String::length))
                .collect(toList());
    }

    //Sorting by length, then equal lengths lexicographically
    public List<String> lengthSortThenAlphaSort() {
        return sampleStrings.stream()
                .sorted(comparing(String::length)
                        .thenComparing(naturalOrder()))
                .collect(toList());
    }


    public static class Golfer {
        private String first;
        private String last;
        private int score;

        private Golfer() {
        }

        public static Golfer createGolfer(String first,String last,int score) {
            Golfer g=new Golfer();
            return g;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    private List<Golfer> golfers = Arrays.asList(
            Golfer.createGolfer("Jack", "Nicklaus", 68),
            Golfer.createGolfer("Tiger", "Woods", 70),
            Golfer.createGolfer("Tom", "Watson", 70),
            Golfer.createGolfer("Ty", "Webb", 68),
            Golfer.createGolfer("Bubba", "Watson", 70)
    );

    public List<Golfer> sortByScoreThenLastThenFirst() {
        return golfers.stream()
                .sorted(comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(toList());
    }
}
