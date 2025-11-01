package MultithreadingAndConcurrency.concurrentCollection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}

class Simulation{
    private final List<Integer> list;

    Simulation(){
        this.list = new CopyOnWriteArrayList<>();
        list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0));
    }

    public void simulate(){
        Thread one  = new Thread(new Writer(list));
        Thread two  = new Thread(new Writer(list));
        Thread three  = new Thread(new Writer(list));
        Thread four = new Thread(new Reader(list));

        one.start();
        two.start();
        three.start();
        four.start();
    }
}

class Reader implements Runnable{
    private final List<Integer> list;

    Reader(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(list);
        }
    }
}

class Writer implements Runnable{
    private final List<Integer> list;
    private final Random randomGenerator;

    Writer(List<Integer> list) {
        this.list = list;
        this.randomGenerator = new Random();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            list.set(randomGenerator.nextInt(list.size()), randomGenerator.nextInt(10));
        }
    }
}