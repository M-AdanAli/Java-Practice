package MultithreadingAndConcurrency.threadSynchronization;

public class WaitAndNotifyDemo {

    /*The difference between `wait()` and `sleep()` method is that `wait()` and `notify()` are used for inter-thread communication, while `sleep()` is used just to pause the execution of a given thread for a particular time. */

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one  = new Thread(()->{
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(()->{
            two();
        });

        one.start();
        two.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from method 1");
            LOCK.wait();
            System.out.println("Back again in method 1");
        }
    }

    private static void two(){
        synchronized (LOCK){
            /*Another important point to note is that even if we call the notify() here, the program will still execute the lines in the method after the notify(), and then the control will be handed over to the method one, and it will be notified for further execution. */
            System.out.println("Hello from method 2 ...");
            LOCK.notify();
            System.out.println("Hello again from method 2 even after notifying");
        }
    }
}
