package org.Server;

import org.TheGame.TypingRaceAlgo;
import org.User.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {
    Socket connection;
    static PlayersList playersList;

    public ClientHandler(Socket socket) {
        this.connection = socket;
    }


    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);

            label:
            while (true) {

                String msgFromClient = in.readLine(); // Place A

                switch (msgFromClient) {
                    case "1": {
                        System.out.println(Thread.currentThread().getName() + " Chosen to Login.");
                        //run authentication method to check if the details given by user is correct
                        //return of this authentication function should be boolean
                        //write boolean to the client
                        String clientCredentials = in.readLine();  //B

                        String[] clientDataArray = clientCredentials.split(",");
                        String clientName = clientDataArray[0];
                        String clientPass = clientDataArray[1];
                        System.out.println(Thread.currentThread().getName() + " Credentials : " + clientName + " " + clientPass);

                        UserData userData = UserData.getInstance();

                        if (userData.isLogInCorrect(clientName, clientPass)) {
                            out.println(true);  //C

                            System.out.println(Thread.currentThread().getName() + "Logged In");

                            if (in.readLine().equals("play")) {     //place H
                                playersList = PlayersList.getInstance();
                                playersList.addPlayers(Thread.currentThread());

                                ///
                                out.println("wait");                // place G

                                System.out.println("Waiting for team to build...");
                                //maybe current thread to wait until we have two players
                                // in the array list
                            }

                            //crucial point has to be modified
                            while (true) {
                                System.out.println("Checking team build");
                                if (playersList.isTeamBuild()) {
                                    out.println("Team");            //Invokes ClientSideGame
                                    TypingRaceAlgo game = new TypingRaceAlgo();
                                    game.run();
                                }
                                try {
                                    Thread.sleep(5000);  //need to change this code
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }


                        }
                        else {
                            out.println("Credentials are not correct please try Again");
                        }


                        break;
                    }
                    case "2": {
                        System.out.println("Client Chosen to Register.");
                        //read from the client
                        //run the signUp method to add the details of the user to the database
                        //write success to the client and return him to main menu at the client side
                        //singleton class for data registration

                        String clientCredentials = in.readLine();       //D

                        String[] clientDataArray = clientCredentials.split(",");
                        String clientName = clientDataArray[0];
                        String clientPass = clientDataArray[1];
                        System.out.println(Thread.currentThread().getName() + " Credentials : " + clientName + " " + clientPass);

                        //storing data in userDataMap
                        UserData userData = UserData.getInstance();
                        if (userData.isUsernameTaken(clientName)) {
                            out.println("User Name taken");               //E

                            System.out.println("User Name Taken");
                        }
                        else {
                            userData.registerUser(clientName, clientPass);
                            out.println("User Registered(" + clientName + ")"); //E


                        }

                        break;
                    }
                    case "3":
                        break label;
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
