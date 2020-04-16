package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.Arrays;
import java.util.List;

public class LambdasandEffectivelyFinalDemo {

    public static void main(String[] args) {
        //Sum the values in a List
        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9);
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        total = 0;
        nums.forEach(n -> total += n);
        total = nums.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
