package SocketProgrammingJavaNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {
    void start(int portNumber, Scanner scanner){
        try(SocketChannel serverChannel = SocketChannel.open()){
            serverChannel.connect(new InetSocketAddress(portNumber));
            System.out.println("Connected to the Server!");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true){
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("quit")){
                    break;
                }
                if (!userInput.isBlank()){
                    userInput += System.lineSeparator();
                    buffer.clear().put(userInput.getBytes()).flip();
                    while (buffer.hasRemaining()){
                        serverChannel.write(buffer);
                    }
                }
                buffer.clear();
                int bytesRead = serverChannel.read(buffer);
                if (bytesRead > 0){
                    buffer.flip();
                    String message = new String(buffer.array(), buffer.position(), bytesRead);
                    System.out.print("SERVER Says :- "+message);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
