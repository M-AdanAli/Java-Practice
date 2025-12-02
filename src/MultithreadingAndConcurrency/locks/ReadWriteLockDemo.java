package MultithreadingAndConcurrency.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        for (int i = 0; i < 2; i++) {
            Thread readerthread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.getValue();
                }
            });
            readerthread.setName("Reader Thread "+i);
            readerthread.start();
        }

        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
            }
        });
        writerThread.setName("Writer Thread");
        writerThread.start();
    }
}

class SharedResource{
    private int counter;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment(){
        lock.writeLock().lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName()+" writes : "+counter);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void getValue(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" reads : "+counter);
        }finally {
            lock.readLock().unlock();
        }
    }
}