package NetworkIO;

import Objects.*;
import Database.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Server Class: Contains all methods and fields pertaining to server objects
 *  @author Ana Farmus, Saahil Kajarekar, Lab sec 02
 *  @version Apr 20, 2025
 */
abstract class Server implements ServerInterface,Runnable {
    private final int PORT = 888;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Database DB = Database.getInstance();

    /**
     * Creates the server and waits for the client to be ran
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
        }
    }

    public void run(){
        Server server = this;
        //server.acceptConnection();
    }


}
