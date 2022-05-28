package org.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 9632;



    public static void main(String[] args) {
        try {
            Socket connection = new Socket(SERVER_IP, PORT);

            System.out.println("Connection established");
            System.out.println();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Press\n1.Login\n2.SignUp\n3.quit");
                String input = scanner.nextLine();
                //sending the choice of user to server
                out.println(input);      //place A
                if (input.equals("1")) {
                    //login
                    //get Credentials Method
                    String userCredentialsToServer = userCredentials();
                    //send to server concatenating both the strings
                    out.println(userCredentialsToServer); //B

                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    String check = in.readLine();

                    if (check.equals("true")) {
                        System.out.println("Successfully Logged ");

                        //if the player is logged in then send him to lobby to wait there for grouping
                    }else {
                        System.out.println(check);
                    }


                }
                else if (input.equals("2")) {
                    //signup
                    //get credentials method
                    String userCredentialsToServer = userCredentials();
                    //send to server
                    out.println(userCredentialsToServer);             //D
                    //reading the msg from the server
                    String msg2FromServer = in.readLine();          //E
                    System.out.println(msg2FromServer);
                }
                else if (input.equals("3")) {
                    break;
                }


            }
            in.close();
            out.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    static String userCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your Name");
        String name = scanner.nextLine();
        System.out.println("Type your Pass");
        String pass = scanner.nextLine();
        return name + "," + pass;
    }


}
