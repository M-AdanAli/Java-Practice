package MultithreadingAndConcurrency.basicMultithreading;

public class DemonUserThreadDemo {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread usrThread = new Thread(new UserThread());

        bgThread.setDaemon(true);

        bgThread.start();
        usrThread.start();
    }
}

class DaemonHelper implements Runnable{

    @Override
    public void run() {
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Demon Helper Running ...");
        }
    }
}

class UserThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User Thread done with execution!");
    }
}