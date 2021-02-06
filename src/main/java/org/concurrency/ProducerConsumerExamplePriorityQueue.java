package org.concurrency;

import java.util.PriorityQueue;
import java.util.Queue;

public class ProducerConsumerExamplePriorityQueue {

    private static long counter=0;

    public static void main(String[] args) throws InterruptedException {
        Queue<String> priorityQueue=new PriorityQueue<>();

        final Runnable producerRunnable=()->{
            while(true){
                priorityQueue.add("Random Value "+counter++);
        }};

        new Thread(producerRunnable).start();
        new Thread(producerRunnable).start();

        final Runnable consumerRunnable=()->{
            while(true){
               System.out.println(priorityQueue.peek());
        }};

        new Thread(consumerRunnable).start();
        new Thread(consumerRunnable).start();

        Thread.sleep(1000);

    }
}
