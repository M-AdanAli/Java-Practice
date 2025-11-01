package MultithreadingAndConcurrency.executorService;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){

            Future<Integer> result = executorService.submit(new returnValueTask());
            System.out.println("Is Cancelled : "+result.isCancelled());
            System.out.println(result.get(6,TimeUnit.SECONDS));
            result.cancel(false);
            System.out.println("Is done : "+result.isDone());

//            Future<?> submit = executorService.submit(new Task(1));
//            System.out.println(submit.get());

            System.out.println("Main thread executed successfully!");
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}

class returnValueTask implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 12;
    }
}