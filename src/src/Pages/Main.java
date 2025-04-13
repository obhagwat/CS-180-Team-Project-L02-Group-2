package Pages;

import Objects.*;
import Database.*;
import NetworkIO.*;
import Exceptions.*;


public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        System.out.println("INITIALIZING DATABASE...");
        database.initializeDatabase();
        System.out.println("DATABASE INITIALIZED");
        System.out.println("SETTING UP NETWORK...");
        //Server.startServer();
        //Client.startClient();
        System.out.println("NETWORK SETUP COMPLETE");
    }
}