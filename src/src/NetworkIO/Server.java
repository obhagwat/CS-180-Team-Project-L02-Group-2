package NetworkIO;

import Interfaces.ServerInterface;
import Objects.*;
import Database.*;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import Exceptions.*;

/**
 * Server Class: Contains all methods and fields pertaining to server objects
 *
 * @author Ana Farmus, Saahil Kajarekar, Sarah Stone Lab sec 02
 * @version Apr 20, 2025
 */
public class Server implements Runnable, ServerInterface {
    private static final int PORT = 8888;
    private static final ArrayList<Server> CLIENTSOCKETS = new ArrayList<>();
    private static ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Database dB = Database.getInstance();
    private Socket socket;
    public Database database = Database.getInstance();

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * creates server socket and then creates new socket and thread for each client
     */
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

    /**
     * called for each new client to read input and call corresponding methods
     */
    public void handleClient() throws IOException {
        String response;
        while ((response = in.readLine()) != null) {
            System.out.println("[SERVER] Client Response: " + response);
            //echoing for now but will add logic for what to do for each possible response
            String[] message = response.split(": ");
            String first = message[0];
            String[] rest;
            if (message.length > 1) {
                rest = message[1].split(", ");
            } else {
                rest = new String[0];
            }
            switch (first) {
                case "CONTRACTOR LOGIN" -> clogin(rest);
                case "CREATE_CONTRACTOR" -> createc(rest);
                case "CREATE_SOLICITOR" -> creates(rest);
                case "SOLICITOR LOGIN" -> slogin(rest);
            }
        }

    }

    public synchronized void clogin(String[] message) {
        System.out.println("[SERVER] clogin");
        String username = message[0];
        String password = message[1];
        System.out.println(username);
        System.out.println(password);
        Contractor contractor = database.getContractor(username);
        if (contractor != null && contractor.getPassword().equals(password)) {
            System.out.println("[SERVER] Login success " + contractor.getUsername());
            sendToClient("SUCCESS");
        } else {
            System.out.println("[SERVER] Login failed " + username);
            sendToClient("FAILURE");
        }
    }

    public synchronized void slogin(String[] message) {
        System.out.println("[SERVER] slogin");
        String username = message[0];
        String password = message[1];
        System.out.println(username);
        System.out.println(password);
        Solicitor solicitor = database.getSolicitor(username);
        if (solicitor != null && solicitor.getPassword().equals(password)) {
            System.out.println("[SERVER] Login success " + solicitor.getUsername());
            sendToClient("SUCCESS");
        } else {
            System.out.println("[SERVER] Login failed " + username);
            sendToClient("FAILURE");
        }
    }

    public synchronized void createc(String[] message) {
        System.out.println("[SERVER] creating contractor");
        String username = message[0];
        String password = message[1];
        String profileName = message[2];
        String organizationName = message[3];
        String country = message[4];
        String physicalAddress = message[5];
        String emailAddress = message[6];
        String phoneNumber = message[7];
        String organizationType = message[8];
        String numEmployees = message[9];
        String foundingYr = message[10];
        if ((database.getContractor(username) != null) || (database.getSolicitor(username) != null)) {
            System.out.println("[SERVER] User already exists " + username);
            sendToClient("FAILURE");
        } else {
            Contractor newContractor = new Contractor(username, password, 0.0, country, physicalAddress, emailAddress, phoneNumber, profileName, organizationType, numEmployees, foundingYr, null);
            database.addContractor(newContractor);
            System.out.println("[SERVER] Contractor created " + username);
            database.serializeDatabase();
            database.loadDatabase();
            sendToClient("SUCCESS");
        }
    }

    public synchronized void creates(String[] message) {
        System.out.println("[SERVER] creating solicitor");
        String username = message[0];
        String password = message[1];
        String agencyLevel = message[2];
        String branch = message[3];
        String subBranch = message[4];
        String physicalAddress = message[5];
        String emailAddress = message[6];
        String phoneNumber = message[7];
        if ((database.getSolicitor(username) != null) || (database.getContractor(username) != null)) {
            System.out.println("[SERVER] User already exists " + username);
            sendToClient("FAILURE");
        } else {
            Solicitor newSolicitor = new Solicitor(username, password, 0.0, "", physicalAddress, emailAddress, phoneNumber, username, agencyLevel, branch, subBranch, 0.0);
            database.addSolicitor(newSolicitor);
            System.out.println("[SERVER] Solicitor created " + username);
            database.serializeDatabase();
            database.loadDatabase();
            sendToClient("SUCCESS");
        }
    }

    /**
     * sends a string to the Client
     *
     * @param message the message to be sent
     */
    public void sendToClient(String message) {
        if (socket != null && !socket.isClosed()) {
            out.println(message);
            System.out.println("[SERVER] sent message to client: " + message);
        } else {
            System.err.println("[SERVER] Unable to write: Socket is closed.");
        }
    }

    /**
     * accepts a message from the Client
     *
     * @return the message from the client
     */
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
     * @param user the user to be deleted
     * @return true if the user was deleted false otherwise
     */
    public boolean deleteUser(User user) {
        if (user instanceof Solicitor) {
            return dB.deleteSolicitor(user.getUsername());
        } else if (user instanceof Contractor) {
            return dB.deleteContractor(user.getUsername());
        }
        return false;
    }

    /**
     * Adds the user to the list of contractors or solicitors in the database
     * Note: We will need to prompt to create a user object before this method is called
     *
     * @param user the user to be added
     * @return true if the user is successfully added else false
     */
    public boolean createUser(User user) {
        if (user instanceof Solicitor) {
            return dB.addSolicitor((Solicitor) user);
        } else if (user instanceof Contractor) {
            return dB.addContractor((Contractor) user);
        }
        return false;
    }

    /**
     * authenticates a user through their username and password
     *
     * @return true if the User fits the role, username, and passwords else false
     * @throws InvalidUserException if the user is not authenticated
     */
    public boolean authenticateUser(String role, String username, String password) throws InvalidUserException {
        if (username == null || password == null || role == null) {
            throw new InvalidUserException("A parameter is null");
        }
        if (role.equalsIgnoreCase("solicitor")) {
            Solicitor temp = dB.getSolicitor(username);
            if (temp.getPassword().equals(password)) {
                return true;
            }
        } else if (role.equalsIgnoreCase("contractor")) {
            Contractor temp = dB.getContractor(username);
            if (temp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sends a message from one client to another
     *
     * @param message the message to be sent
     * @return true if the message successfully sent, else false
     */
    public boolean deliverBetweenClients(String message, Server receiver) {
        synchronized (CLIENTSOCKETS) {
            if (receiver != null) {
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