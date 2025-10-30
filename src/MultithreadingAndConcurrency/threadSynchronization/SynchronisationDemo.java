package MultithreadingAndConcurrency.threadSynchronization;

public class SynchronisationDemo {
    public static int counter = 0;

    public static void main(String[] args) {
        /*So when we have a shared resource and two threads are working on the same resource concurrently, for example here we are incrementing the counter variable that happens in the three steps:
1. The counter is loaded
2. The counter is incremented
3. The value is set back to the variable
So what happens if for example is thread one has loaded the counter, and it sees that the counter is 0, it will increment it to 1. So before setting the value back to 1, thread 2 comes in picture, it sees that the counter is 0, and he increments it to 1. So in this way there comes inconsistency that the value of the counter thread two should see should be 1, but it sees 0. So in this way the inconsistency comes here, so after these two operations our counter variable remains 1 by incrementing by both two threads.*/

        /*Actually, the operation we are doing is a non-atomic operation in technical terms. A non-atomic operation is the one that can be broken down into smaller steps, and while doing those steps step-by-step, these steps can be interleaved by other threads that can lead to inconsistent results. The phenomena are referred to as a race condition. */

        Thread one = new Thread(()->{
            for (int i=0; i<10000 ; i++){
                increment();
            }
        });

        Thread two = new Thread(()->{
            for (int i=0; i<10000 ; i++){
                increment();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Counter Value : "+counter);

    }

    /*One way to fix this is to provide only one thread access to the critical section, so that the other thread cannot intervene while the operation is not completed. This can be achieved by using the synchronized keyword. This is a concurrency control mechanism that is called mutual exclusion, in which we make sure that our shared resource is only accessed by one thread at a time. And such a piece of data, or code block, is known as "Critical Section". */

    private synchronized static void increment(){
        counter = counter + 1;
    }

    /*While it seems to solve the issue, there are certain problems connected to using synchronized keywords at the method level. To understand them, first, we understand how the synchronized keyword actually works in Java.

So, what we have is that every object in Java has a monitor associated with it. When a thread enters a synchronized block, it tries to hold the monitor lock (sometimes called intrinsic lock) of the object associated with the synchronized block if it is available. What is the meaning of available? Sometimes, the monitor lock is acquired by another thread and it is executing, so the other thread needs to wait, and it goes to the block state. */
    /*The problem exactly here is that it is a kind of coarse-grained locking. Actually, the single lock is protecting a large group of related resources. But the thing is that when we apply it on the method level, it blocks the whole method while we only have two or three lines that need that control. */
    /*So in this way, we are losing the fine-grained control in which we only lock the specific or a smaller part or you can say the critical sections that are necessary and that are required to be locked. */
    /*Also, when a subclass overrides the synchronized method from the superclass. It must explicitly declare it as synchronized as well if it wants to retain the synchronization control. */

    /*In Java, when discussing synchronization at the class level using synchronized static methods, there is indeed only one lock associated with the entire class. This means that if a thread is executing any synchronized static method of a class, no other thread can execute any other synchronized static method of the same class concurrently. They will be blocked until the first thread releases the class-level lock.*/


}
