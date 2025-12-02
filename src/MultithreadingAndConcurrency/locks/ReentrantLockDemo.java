package MultithreadingAndConcurrency.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA(){
        lock.lock();
        try {
            sharedData++;
            System.out.println(Thread.currentThread().getName()+" Method A: Shared Data = "+sharedData);
            methodB();
        }finally {
            lock.unlock();
        }
    }

    public void methodB(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"'s lock hold count : "+lock.getHoldCount());
        try {
            sharedData--;
            System.out.println(Thread.currentThread().getName()+" Method B: Shared Data = "+sharedData);
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantLockDemo example = new ReentrantLockDemo();

        for (int i = 0; i < 5; i++) {
            new Thread(example::methodA).start();

        }
    }
}
