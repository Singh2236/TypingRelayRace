package org.Client;

import java.net.Socket;

public class ClientSideGame {
    public void run(Socket connection){
        System.out.println("We are inside Game now");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);

        }

    }
}
