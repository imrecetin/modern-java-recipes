package org.modernjavarecipes.parallelismAndConcurrency;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

public class CoordinatingCompletableFuturesPart1Demo {
    public static void main(String[] args) {
        //You want the completion of one Future to trigger another action.

        CompletableFuture.supplyAsync(() -> {return sleepThenReturnString();})
                .thenApply(n->Integer.parseInt(n))
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println)
                .join();
        System.out.println("Running...");

        //Running CompletableFuture tasks on a separate thread pool
        ExecutorService service = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(() -> {return sleepThenReturnString();}, service)
                .thenApply(Integer::parseInt)
                .thenApply(x -> 2 * x)
                .thenAccept(System.out::println)
                .join();
        System.out.println("Running...");
    }

    //Composing two Futures together
    @Test
    public void compose() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));
        assertTrue(5 == completableFuture.get());
    }

    //Combining two Futures
    @Test
    public void combine() throws Exception {
        int x = 2;
        int y = 3;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCombine(CompletableFuture.supplyAsync(() -> y),
                                (n1, n2) -> n1 + n2);
        assertTrue(5 == completableFuture.get());
    }

    //Using the handle method for Exception
    private CompletableFuture<Integer> getIntegerCompletableFuture(String num) {
        return CompletableFuture.supplyAsync(() -> Integer.parseInt(num))
                .handle((val, exc) -> val != null ? val : 0);
    }
    @Test
    public void handleWithException() throws Exception {
        String num = "abc";
        CompletableFuture<Integer> value = getIntegerCompletableFuture(num);
        assertTrue(value.get() == 0);
    }
    @Test
    public void handleWithoutException() throws Exception {
        String num = "42";
        CompletableFuture<Integer> value = getIntegerCompletableFuture(num);
        assertTrue(value.get() == 42);
    }

    //Coordinating tasks using a CompletableFuture
    private static String sleepThenReturnString() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return "42";
    }
}
