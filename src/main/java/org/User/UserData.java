package org.User;

import java.util.HashMap;
import java.util.Map;

///////////////////////////////////////////////////////////////////////////
//                              Singleton class
///////////////////////////////////////////////////////////////////////////


public class UserData {

    private static UserData instance;

    //Map of Usernames to their passwords
    Map<String,String> userDataMap = new HashMap<>();

    //create getInstance method instead of Constructor

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }


    //private default constructor
    private UserData() {
    }


    //create methods -> isUsernameTaken ,registerUser,isLogInCorrect

    public synchronized boolean isUsernameTaken(String name) {
        return userDataMap.containsKey(name);
    }

    public synchronized void registerUser(String name, String pass) {
        userDataMap.put(name, pass);
    }

    public synchronized boolean isLogInCorrect(String name, String pass) {
        if (!userDataMap.containsKey(name)) {
            return false;
        }
        String temp = userDataMap.get(name);
        return temp.equals(pass);
    }
}

/*Purpose of Singleton Class
The primary purpose of a Single class is to restrict the limit of the number of object creation to only one.
This often ensures that there is access control to resources, for example, socket or database connection.

The memory space wastage does not occur with the use of the singleton class because it restricts the instance
 creation. As the object creation will take place only once instead of creating it each time a new request is made.

We can use this single object repeatedly as per the requirements. This is the reason why the multi-threaded
and database applications mostly make use of the Singleton pattern in Java for caching, logging, thread pooling,
configuration settings, and much more.

For example, there is a license with us, and we have only one database connection or suppose if our JDBC driver
does not allow us to do multithreading, then Singleton class comes into the picture and makes sure that at a time,
only a single connection or a single thread can access the connection.*/
