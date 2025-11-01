package MultithreadingAndConcurrency.threadSynchronization.countdownLatch;

import java.util.concurrent.CountDownLatch;

public class Chef implements Runnable{
    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(name+" is preparing "+dish);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name+" has finished preparing "+dish);
        latch.countDown();
    }
}
