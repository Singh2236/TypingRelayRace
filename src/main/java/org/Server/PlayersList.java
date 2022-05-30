package org.Server;

import java.util.ArrayList;

public class PlayersList {
    private static PlayersList instance;
    ArrayList<Thread> playersListArray = new ArrayList<>();

    public static PlayersList getInstance() {
        if (instance == null) {
            instance = new PlayersList();

        }
        return instance;
    }
    private PlayersList(){

    }


    public synchronized void addPlayers(Thread currentThread) {
        playersListArray.add(currentThread);
    }

    public int getNoOfPlayersLoggedIn() {
        return playersListArray.size();
    }

    public boolean isTeamBuild() {
        if (playersListArray.size() == 2) {
            return true;
        }
        else if (playersListArray.size() < 2) {
            return false;
        }
        else return false;
    }


    public boolean emptyList() {
        return playersListArray.removeAll(playersListArray);
    }
    public int getSize(){
        return playersListArray.size();
    }

    @Override
    public String toString() {
        return "PlayersList{" +
                "playersListArray=" + playersListArray +
                '}';
    }
}
