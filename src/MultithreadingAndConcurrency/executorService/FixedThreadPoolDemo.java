package MultithreadingAndConcurrency.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
            for (int i=0 ; i<7 ; i++){
                executorService.execute(new Task(i));
            }
        }
    }
}


