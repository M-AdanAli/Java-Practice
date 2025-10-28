package MultithreadingAndConcurrency.basicMultithreading;

public class ExtendsThreadExample {
    public static void main(String[] args) {
        Thread one = new Thread1();
        Thread two = new Thread2();

        one.start();
        two.start();
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i=1; i<=15; i++){
            System.out.println("Thread One : "+ i);
        }
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        for (int i=1; i<=15; i++){
            System.out.println("Thread Two : "+ i);
        }
    }
}