package org.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientLobby {
    public void run(Socket connection){
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (true) {

                System.out.println("Press 1. Play 2. Exit");
                int input = scanner.nextInt();

                if (input == 1) {
                    out.println("play");                    //place H                    //"play" msg has to be read exactly at server side

                    //while loop laona pauga shayad
                    while (true) {
                        String sth = in.readLine();

                        if (sth.equals("wait")) {     //place G
                            System.out.println("Waiting for the Team to build");

                        }
                        else if (sth.equals("Team")) {
                            ClientSideGame csg = new ClientSideGame();
                            csg.run(connection);
                        }
                    }


                }
                else if (input == 2) {
                    break;
                }else System.out.println("Please Press 1 or 2.");
            }
        } catch (IOException e) {
            System.out.println("IOException in ClientLobby");
        }
    }
}
