package Pages;

import Objects.*;
import Database.*;
import NetworkIO.*;
import Exceptions.*;

/**
 * -serves as the entry point for starting the server-client application.
 * -initializes the database and sets up the network by starting the server and client components.
 *
 * @author Ana Farmus, Lab sec 02
 * @version April 20, 2024
 */
public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        database.initializeDatabase();
        System.out.println("DATABASE INITIALIZED");

        System.out.println("CURRENT SOLICITORS: \n");
        for (Solicitor solicitor : database.getSolicitors()) {
            System.out.println(solicitor);
        }

        System.out.println("CURRENT CONTRACTORS: \n");
        for (Contractor contractor : database.getContractors()) {
            System.out.println(contractor);
        }

        System.out.println("SETTING UP NETWORK...");
        Server.startServer();
        Client.startClient();
        System.out.println("NETWORK SETUP SUCCESSFUL");
    }
}