package SocketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public void start(final int portNumber){
        try(ServerSocket serverSocket  = new ServerSocket(portNumber);
            ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            System.out.println("Waiting for clients...");
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected..  !");
                executorService.submit(()->{
                    String clientIp = clientSocket.getInetAddress().getHostAddress();
                    int clientPort = clientSocket.getPort();
                    try (BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)){
                        for(String inputLine; (inputLine=clientInput.readLine()) != null ;){
                            System.out.println(clientIp+":"+clientPort+" :- "+inputLine);
                            writer.println(new StringBuilder(inputLine).reverse());
                        }
                    }catch (IOException e){
                        System.out.println(e);
                    }
                });
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
