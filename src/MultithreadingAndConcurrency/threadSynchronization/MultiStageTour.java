package MultithreadingAndConcurrency.threadSynchronization;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MultiStageTour {
    private static final int NUM_OF_TOURISTS = 5;
    private static final int NUM_OF_STAGES =3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_OF_TOURISTS, ()->{
        System.out.println("Tour guide started speaking...");
    });

    public static void main(String[] args) {
        for (int touristId = 0; touristId <= NUM_OF_TOURISTS; touristId++) {
            new Thread(new Tourist(touristId)).start();
        }
    }

    static class Tourist implements Runnable{
        private final int touristId;

        Tourist(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for (int stage = 0; stage <= NUM_OF_STAGES; stage++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("Tourist "+touristId+" arrived at stage : "+stage);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}