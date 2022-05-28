package org.Server;

import org.User.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
    Socket connection;

    public ClientHandler(Socket socket) {
        this.connection = socket;
    }


    public void run() {
        try {





            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);

            while (true) {

                String msgFromClient = in.readLine(); // Place A
                System.out.println(msgFromClient);
                if (msgFromClient.equals("1")) {
                    //run authentication method to check if the details given by user is correct
                    //return of this authentication function should be boolean
                    //write boolean to the client
                    String clientCredentials = in.readLine();  //B
                    System.out.println(clientCredentials);
                    String[] clientDataArray = clientCredentials.split(",");
                    String clientName = clientDataArray[0];
                    String clientPass = clientDataArray[1];

                    UserData userData = UserData.getInstance();
                    if (userData.isLogInCorrect(clientName, clientPass)) {
                        out.println(true);  //C
                    }
                    else {
                        out.println("Credentials are not correct please try Again");
                    }


                }
                else if (msgFromClient.equals("2")) {
                    //read from the client
                    //run the signUp method to add the details of the user to the database
                    //write success to the client and return him to main menu at the client side
                    //singleton class for data registration

                    String clientCredentials = in.readLine();       //D
                    String[] clientDataArray = clientCredentials.split(",");
                    String clientName = clientDataArray[0];
                    String clientPass = clientDataArray[1];

                    //storing data in userDataMap
                    UserData userData = UserData.getInstance();
                    if (userData.isUsernameTaken(clientName)) {
                        out.println("User Name taken");               //E
                    }
                    else {
                        userData.registerUser(clientName, clientPass);
                        out.println("User Registered(" + clientName + ")"); //E

                    }

                }
                else if (msgFromClient.equals("3")) {
                    break;
                }
            }
            in.close();
            out.close();
            connection.close();


        } catch (SocketException socketException) {
            System.out.println("Lost" + connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
