package SocketProgrammingJavaNIO;



import java.util.Scanner;

public class Main {
    private static final int SERVER_PORT_NUMBER  = 12345;

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)){
            System.out.println("Is this a Server? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("y")){
                new NioServer().start(SERVER_PORT_NUMBER);
            }else {
                new NioClient().start(SERVER_PORT_NUMBER,scanner);
            }
        }
    }
}
