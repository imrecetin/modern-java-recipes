package org.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private Queue<T> queue;
    private int size=16;
    private ReentrantLock lock=new ReentrantLock(true);
    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();

    public MyBlockingQueue(int size){
        queue=new LinkedList<>();
        this.size=size;
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size()==this.size){ //will ensure correctness of program double check queue size
                notFull.await();
            }
            queue.add(t);
            notEmpty.notifyAll();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try{
            while(queue.size()==0){ //will ensure correctness of program double check queue size
                notEmpty.await();
            }
            final T item = queue.remove();
            notFull.notifyAll();
            return item;
        }finally {
            lock.unlock();
        }
    }

}
