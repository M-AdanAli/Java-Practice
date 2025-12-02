package MultithreadingAndConcurrency.otherConcepts;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();

    public void workerOne(){
        lockA.lock();
        System.out.println("Worker One acquired Lock A");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        lockB.lock();
        System.out.println("Worker One acquired lock B");

        lockA.unlock();
        lockB.unlock();
    }

    public void workerTwo(){
        lockB.lock();
        System.out.println("Worker Two acquired Lock B");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        lockA.lock();
        System.out.println("Worker Two acquired lock A");

        lockB.unlock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        new Thread(demo::workerOne, "Worker One").start();
        new Thread(demo::workerTwo, "Worker Two").start();

        new Thread(()->{
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while (true){
                long[] threadIds = mxBean.findDeadlockedThreads();
                if (threadIds != null){
                    System.out.println("Deadlock detected!");
                    ThreadInfo[] threadInfo = mxBean.getThreadInfo(threadIds);
                    for (long threadId : threadIds){
                        System.out.println("Thread with ID "+threadId+" is in DeadLock");
                    }
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
