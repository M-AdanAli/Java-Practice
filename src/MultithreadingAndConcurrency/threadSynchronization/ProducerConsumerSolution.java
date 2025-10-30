package MultithreadingAndConcurrency.threadSynchronization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerSolution {
    public static void main(String[] args) {
        Worker worker = new Worker(5,0);
        Thread producer = new Thread(()->{
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(()->{
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker{
    private int sequence = 0;
    private final int top;
    private final int bottom;
    private List<Integer> container;
    private final Object lock = new Object();

    public Worker(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock){
            while (sequence < 100){
                if (container.size() == top){
                    System.out.println("Container is full. Waiting for items to be removed");
                    lock.wait();
                }else {
                    System.out.println(sequence + " added to the Container!");
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            while (sequence < 100){
                if (container.size() == bottom){
                    System.out.println("Container is empty. Waiting for items to be added.");
                    lock.wait();
                }else {
                    System.out.println(container.removeFirst() + " removed from the Container!");

                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
