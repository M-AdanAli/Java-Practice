package MultithreadingAndConcurrency.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WorkLoadSplitter extends RecursiveAction {

    private final int workLoad;

    public WorkLoadSplitter(int workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if (workLoad > 16){
            System.out.println("Workload too big. Splitting tasks for workload : "+workLoad);

            int firstWorkLoad = workLoad / 2;
            int secondWorkLoad = workLoad - firstWorkLoad;

            WorkLoadSplitter work1 = new WorkLoadSplitter(firstWorkLoad);
            WorkLoadSplitter work2 = new WorkLoadSplitter(secondWorkLoad);

            work1.fork();
            work2.fork();
        }else {
            System.out.println("Work load within limits. Executing task for workload : "+workLoad);
        }
    }
}

class workLoadSplitDemo{
    public static void main(String[] args) {
        int workLoad = 128;
        try(ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())){
            WorkLoadSplitter workLoadSplitter = new WorkLoadSplitter(workLoad);
            pool.execute(workLoadSplitter);
        }
    }
}