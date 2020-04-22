package org.modernjavarecipes.parallelismAndConcurrency;

import java.util.concurrent.*;

public class TheFutureInterfaceDemo {
    public static void main(String[] args) {
        //Submitting a Callable and returning the Future
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return "Hello, World!";
            }
        });
        System.out.println("Processing...");
        getIfNotCancelled(future);

        //Using a lambda expression and checking if the Future is done
        future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });
        System.out.println("More processing...");
        while (!future.isDone()) {
            System.out.println("Waiting...");
        }
        getIfNotCancelled(future);

        //Using isDone in a loop is called busy waiting and is not generally a
        //good idea because of the potentially millions of calls it generates.
        //The CompletableFuture class, discussed in the rest of this chapter,
        //provides a better way to react when a Future completes.

        //Cancelling the Future
        future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });
        future.cancel(true);
        System.out.println("Even more processing...");
        getIfNotCancelled(future);
    }

    //Retrieving a value from a Future
    public static void getIfNotCancelled(Future<String> future) {
        try {
            if (!future.isCancelled()) {
                System.out.println(future.get());
            } else {
                System.out.println("Cancelled");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
