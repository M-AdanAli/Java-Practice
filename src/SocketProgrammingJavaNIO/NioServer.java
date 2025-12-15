package SocketProgrammingJavaNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;

public class NioServer {
    public void start(final int portNumber){
        HashSet<SocketChannel> clients = new HashSet<>();
        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(portNumber));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true){
                if (selector.select() == 0){
                    continue;
                }

                for (SelectionKey key:selector.selectedKeys()){
                    if (key.isAcceptable()){
                        if (key.channel() instanceof ServerSocketChannel channel){
                            SocketChannel clientSocketChannel = channel.accept();
                            Socket clientSocket = clientSocketChannel.socket();
                            String hostAddress = clientSocket.getInetAddress().getHostAddress();
                            int port = clientSocket.getPort();
                            String clientInfo = hostAddress+":"+port;
                            System.out.println("CONNECTED  -  "+clientInfo);
                            clientSocketChannel.configureBlocking(false);
                            clientSocketChannel.register(selector, SelectionKey.OP_READ);
                            clients.add(clientSocketChannel);
                        }else {
                            throw new RuntimeException("Unknown Channel");
                        }
                    }else if(key.isReadable()){
                        if (key.channel() instanceof SocketChannel clientChannel){
                            int bytesRead = clientChannel.read(buffer);
                            Socket clientSocket = clientChannel.socket();
                            String hostAddress = clientSocket.getInetAddress().getHostAddress();
                            int port = clientSocket.getPort();
                            String clientInfo = hostAddress+":"+port;
                            if (bytesRead == -1){
                                System.out.println("DISCONNECTED  -  "+clientInfo);
                                clientChannel.close();
                                clients.remove(clientChannel);
                            }
                            buffer.flip();
                            String message = new String(buffer.array(), buffer.position(), bytesRead);
                            System.out.print(clientInfo+"  Says :- "+message);
                            while (buffer.hasRemaining()){
                                clientChannel.write(buffer);
                            }
                            buffer.clear();
                        }else {
                            throw new RuntimeException("Unknown Channel");
                        }
                    }
                }

                selector.selectedKeys().clear();
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            for (SocketChannel client: clients){
                try {
                    client.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }
    }
}
