package MultithreadingAndConcurrency.otherConcepts;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count.incrementAndGet();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count.incrementAndGet();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(count);
    }
}
