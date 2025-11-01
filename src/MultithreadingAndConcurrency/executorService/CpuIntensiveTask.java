package MultithreadingAndConcurrency.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveTask {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        System.out.println("Created Thread pool with " + cores + " threads.");

        for (int i=0; i<20 ; i++){
            executorService.execute(new CpuTask());
        }
    }
}

class CpuTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Some CPU intensive task being executed by : "+Thread.currentThread().getName());
    }
}
