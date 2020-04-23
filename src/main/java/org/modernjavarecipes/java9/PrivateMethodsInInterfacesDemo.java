package org.modernjavarecipes.java9;


import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PrivateMethodsInInterfacesDemo {
    public static void main(String[] args) {

    }

    class PrivateDemo implements SumNumbers {}

    public class SumNumbersTest {
        private SumNumbers demo = new PrivateDemo();
        @Test
        public void addEvens() throws Exception {
            assertEquals(2 + 4 + 6, demo.addEvens(1, 2, 3, 4, 5, 6));
        }

        @Test
        public void addOdds() throws Exception {
            assertEquals(1 + 3 + 5, demo.addOdds(1, 2, 3, 4, 5, 6));
        }
    }

    //Private method in an interface
    public interface SumNumbers {
        default int addEvens(int... nums) {
            return add(n -> n % 2 == 0, nums);
        }
        default int addOdds(int... nums) {
            return add(n -> n % 2 != 0, nums);
        }
        private int add(IntPredicate predicate, int... nums) {
            return IntStream.of(nums)
                    .filter(predicate)
                    .sum();
        }
    }
}
