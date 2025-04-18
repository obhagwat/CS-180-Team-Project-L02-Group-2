package NetworkIO;

import Objects.*;
import Database.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

import Exceptions.*;

/**
 * Server Class: Contains all methods and fields pertaining to server objects
 *  @author Ana Farmus, Saahil Kajarekar, Sarah Stone Lab sec 02
 *  @version Apr 20, 2025
 */
public class Server implements Runnable {
    private static final int PORT = 888;
    private static final ArrayList<Server> CLIENTSOCKETS = new ArrayList<>();
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
                synchronized (CLIENTSOCKETS) {
                    CLIENTSOCKETS.add(this);
                }
                handleClient();
            } else {
                System.err.println("[SERVER] Unable to read: Socket is closed");
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Error: Unable to read from client");
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

    public void handleClient() throws IOException {
        String response;
        while ((response = in.readLine()) != null) {
            System.out.println("[SERVER] Client Response: " + response);
            //echoing for now but will add logic for what to do for each possible response
            out.println(response);
        }

    }

    public void sendToClient(String message) {
            if (socket != null && !socket.isClosed()) {
                out.println(message);
            } else {
                System.err.println("[SERVER] Unable to write: Socket is closed.");
            }
    }

    public String readFromClient() {
        String response = "";
        try {
            if (socket != null && !socket.isClosed()) {
                response = in.readLine();
            } else {
                System.err.println("[SERVER] Unable to read: Socket is closed.");
            }
        } catch (IOException e) {
            System.err.println("[SERVER] Error: unable to read from client");
        }
        return response;
    }

    /**
     *
     * @param user the user to be deleted
     * @return true if the user was deleted false otherwise
     */
    public boolean deleteUser(User user) {
        if(user instanceof Solicitor) {
            return DB.deleteSolicitor(user.getUsername());
        } else if (user instanceof Contractor) {
            return DB.deleteContractor(user.getUsername());
        }
        return false;
    }

    /**
     * Adds the user to the list of contractors or solicitors in the database
     * Note: We will need to prompt to create a user object before this method is called
     * @param user the user to be added
     * @return true if the user is successfully added else false
     */
    public boolean createUser(User user) {
        if(user instanceof Solicitor) {
            return DB.addSolicitor((Solicitor) user);
        } else if (user instanceof Contractor) {
            return DB.addContractor((Contractor) user);
        }
        return false;
    }

    /**
     * authenticates a user through their username and password
     * @return true if the User fits the role, username, and passwords else false
     * @throws InvalidUserException if the user is not authenticated
     */
    public boolean authenticateUser(String role, String username, String password) throws InvalidUserException {
        if(username == null || password == null || role == null) {
            throw new InvalidUserException("A parameter is null");
        }
        if(role.equalsIgnoreCase("solicitor")) {
            Solicitor temp = DB.getSolicitor(username);
            if(temp.getPassword().equals(password)) {
                return true;
            }
        } else if (role.equalsIgnoreCase("contractor")) {
            Contractor temp = DB.getContractor(username);
            if(temp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sends a message from one client to another
     * @param message the message to be sent
     * @return true if the message successfully sent, else false
     */
    public boolean deliverBetweenClients(String message, Server receiver) {
        synchronized (CLIENTSOCKETS) {
            if(receiver != null) {
                for (Server s : CLIENTSOCKETS) {
                    if (s.equals(receiver)) {
                        receiver.out.println(message);
                        return true;
                    }
                }
            }
            return false;
        }
    }

}