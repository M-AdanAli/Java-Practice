package MultithreadingAndConcurrency.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            for (int i=0 ; i<5 ; i++){
                executorService.execute(new Task(i));
            }
        }

    }
}