package MultithreadingAndConcurrency.basicMultithreading;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for(int i=1 ; i<=5; i++){
                System.out.println("Thread one : "+i);
            }
        });

        Thread two = new Thread(()->{
            for(int i=1 ; i<=25; i++){
                System.out.println("Thread two : "+i);
            }
        });

        /*one.start();
        two.start();
        System.out.println("Done Executing the threads!");*/

        one.start();
        two.start();
        one.join();
        System.out.println("Done Executing the threads!");
    }
}
