package MultithreadingAndConcurrency.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newCachedThreadPool()){
            for (int i=0 ; i<1000 ; i++){
                executorService.execute(new Task(i));
            }
        }
    }
}
