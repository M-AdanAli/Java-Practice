package MultithreadingAndConcurrency.threadSynchronization.countdownLatch;

import java.util.concurrent.CountDownLatch;

public class Restauraunt {
    public static void main(String[] args) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);
        new Thread(new Chef("Chef A","Pasta",latch)).start();
        new Thread(new Chef("Chef A","Pizza",latch)).start();
        new Thread(new Chef("Chef A","Custard",latch)).start();

        latch.await();

        System.out.println("All dishes are ready! Let's start serving customers");
    }
}
