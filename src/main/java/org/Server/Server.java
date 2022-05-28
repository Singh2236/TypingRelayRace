package org.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {

        Socket connection = null;
        try {


            int port = 4566;
            ServerSocket serverSocket = new ServerSocket(9632);
            System.out.println("Waiting for connection");
            int userNumber = 0;
            while (true) {
                connection = serverSocket.accept();
                userNumber++;
                String username = "User" + userNumber;
                System.out.println("connection with " + connection + " established.");
                ClientHandler clientHandler = new ClientHandler(connection);

                Thread runClient = new Thread(clientHandler, username);
                runClient.start();

                System.out.println("Number of threads running "+ (Thread.activeCount()-2));
                System.out.println("Connected with " );
            }
        } catch (SocketException socketException) {
            System.out.println("Lost" + connection);
        } catch (IOException e) {
            System.out.println("IOExecption");
        }


    }


}
