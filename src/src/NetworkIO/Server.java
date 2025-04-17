package NetworkIO;

import Objects.*;
import Database.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Server Class: Contains all methods and fields pertaining to server objects
 *  @author Ana Farmus, Saahil Kajarekar, Sarah Stone Lab sec 02
 *  @version Apr 20, 2025
 */
public class Server implements Runnable {
    private static final int PORT = 888;
    private static ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Database DB = Database.getInstance();
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Server server = new Server(socket);
                    new Thread(server).start();
                } catch (IOException e) {
                    System.err.println("[SERVER] Error connecting to socket");
                }
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Could not listen on port: " + PORT);
        }
    }

    public void run() {
        try {
            if (socket != null && !socket.isClosed()) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                readFromClient();
            } else {
                System.err.println("[SERVER] Unable to read: Socket is closed");
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Error: Unable to read from server");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("[SERVER] Error closing resources");
            }
        }
    }

    public synchronized void readFromClient() throws IOException {
        String response;
        while ((response = in.readLine()) != null) {
            System.out.println("[SERVER] Client Response: " + response);
            //echoing for now but will add logic for what to do for each possible response
            out.println(response);
        }
    }

}