package MultithreadingAndConcurrency.basicMultithreading;

public class ThreadPriorityExample {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getPriority());
//        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//        System.out.println(Thread.currentThread().getPriority());

        /*At the start, the main thread has priority as 5, but it will have the privilege to be executed first because it is a parent thread, and all the threads have to be started from here. Therefore, at the very beginning, the main thread is privileged to be started first. */
        System.out.println(Thread.currentThread().getName() + " says Hi!");

        Thread one = new Thread(()->{
            System.out.println("Thread one also says Hi!");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();

    }
}
