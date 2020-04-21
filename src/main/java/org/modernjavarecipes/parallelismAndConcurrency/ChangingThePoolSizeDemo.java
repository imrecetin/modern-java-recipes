package org.modernjavarecipes.parallelismAndConcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ChangingThePoolSizeDemo {
    public static void main(String[] args) {
        //By default, the size of the common thread pool equals the number of processors on
        //your machine, computed from Runtime.getRuntime().availableProcessors().

        //pecifying the common pool size programmatically
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        long total = LongStream.rangeClosed(1, 3_000_000)
                .parallel()
                .sum();
        int poolSize = ForkJoinPool.commonPool().getPoolSize();
        System.out.println("Pool size: " + poolSize);

        /* java -cp build/classes/main -Djava.util.concurrent.ForkJoinPool.common.parallelism=10 concurrency.CommonPoolSize
        Pool size: 10 */

        //Creating your own ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool(15);
        ForkJoinTask<Long> task = pool.submit(
                () -> LongStream.rangeClosed(1, 3_000_000)
                        .parallel()
                        .sum());

        try {
            total = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        poolSize = pool.getPoolSize();
        System.out.println("Pool size: " + poolSize);
    }
}
