package SocketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void start(final int portNumber, final Scanner scanner){
        try (Socket socket = new Socket("localhost",portNumber);
             PrintWriter writer  = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Socket created..!");
            for (String userInput; !(userInput=scanner.nextLine()).isEmpty(); ){
                writer.println(userInput);
                System.out.println("Response : "+reader.readLine());
            }
        }catch (IOException e){
            //todo: Handle exceptions later
            System.out.println(e);
        }
    }
}
