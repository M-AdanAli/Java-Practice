package MultithreadingAndConcurrency.forkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SummingElementsTask extends RecursiveTask<Integer> {

    int[] arr;
    int start;
    int end;

    public SummingElementsTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int size = (end - start) + 1;
        if (size > 100){
            int mid = (start + end)/2;
            SummingElementsTask task1 = new SummingElementsTask(arr,start,mid);
            SummingElementsTask task2 = new SummingElementsTask(arr,mid+1,end);

            task1.fork();
            task2.fork();

            return task1.join() + task2.join();
        }else {
            return sumElements();
        }
    }

    Integer sumElements(){
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }
}

class SumArrayElementsUsingForkJoinPool{
    public static void main(String[] args) {
        int[] arr = new int[10000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10)+1;
        }
        try(ForkJoinPool pool = new ForkJoinPool( Runtime.getRuntime().availableProcessors() )){
            Integer sum = pool.invoke(new SummingElementsTask(arr, 0, arr.length - 1));
            System.out.println("Sum is : "+sum);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}