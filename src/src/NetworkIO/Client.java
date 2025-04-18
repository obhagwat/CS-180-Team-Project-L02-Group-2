package NetworkIO;

import Objects.*;
import Database.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Client Class: Contains all methods and fields pertaining to client objects
 *  @author Ana Farmus, Lab sec 02
 *  @version Apr 20, 2025
 */
public class Client implements Runnable, ClientInterface {
    public static final int PORT = 8888;
    public static final String HOST = "localhost";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Solicitor solicitor;
    private Contractor contractor;
    private User user;
    public static Thread clientThread;
    private Database db = Database.getInstance();

    /**
     * Starts client by creating client object and running it in client thread.
     */
    public static void startClient() {
        Client client = new Client();
        clientThread = new Thread(client);
        clientThread.start();
    }

    /**
     * runs client thread, connects to server, allows communications, then disconnecting.
     */
    public void run() {
        if (connectToServer()) {
            System.out.println("BUILD INCOMPLETE -- GUI Infrastructure does not yet exist.");
        }
    }

    /**
     * Connects to server by establishing socket on specified host and port.
     *
     * @return true if the connection is successful, false if not.
     */
    @Override
    public boolean connectToServer() {
        try {
            socket = new Socket(HOST, PORT);
            System.out.println("[CLIENT] Client started");
            return true;
        } catch (IOException e) {
            System.err.println("[CLIENT] Could not connect to server");
            return false;
        }
    }

    /**
     * Disconnects from the server, closes os streams the socket
     */
    @Override
    public void disconnect() {
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
            System.out.println("[CLIENT] Client disconnected");
        } catch (IOException e) {
            System.err.println("[CLIENT] Could not close input stream");
        }
    }

    /**
     * Fetches username from solicitor client-user
     *
     * @return solicitor client's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * returns client's password
     *
     * @return client user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the industry of the the client
     * @return the client of the industry
     */
    @Override
    public Industry getIndustry() {
        if (user instanceof Contractor) {
            return ((Contractor) user).getIndustry();
        } else if (user instanceof Solicitor) {
            return (Industry.GOVERNMENT);
        }
        return null;
    }

    /**
     * Fetches Contractor object
     *
     * @return Contractor object associated with client
     */
    @Override
    public Contractor getContractor() {
        return contractor;
    }

    /**
     * Fetches Solicitor object
     *
     * @return Solicitor object associated with client
     */
    @Override
    public Solicitor getSolicitor() {
        return solicitor;
    }

    /**
     * Sets contractor object for this client
     *
     * @param contractor
     */
    @Override
    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    /**
     * Sets solicitor object for this client
     *
     * @param solicitor
     */
    @Override
    public void setSolicitor(Solicitor solicitor) {
        this.solicitor = solicitor;
    }

    /**
     * Retrieves array of chats associated with the User object of this client.
     *
     * @return arrayList of user's Chat objects
     */
    public ArrayList<Chat> getChats() {
        return db.getChatsForUser(user.getUsername());
    }

    /**
     * Sends message to the server thrpugh socket.
     * Ensures message is written and flushed to server instantly.
     *
     * @param message to be sent to server
     */
    @Override
    public void sendToServer(String message) {
        try {
            if (socket != null && !socket.isClosed()) {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
                out.flush();
            } else {
                System.err.println("[CLIENT] Unable to send: Socket is closed");
            }
        } catch (IOException e) {
            System.err.println("[CLIENT] Unable to send message: " + e.getMessage());
        }
    }

    /**
     * Continuously reads server response and prints to terminal until connection closes.
     */
    @Override
    public synchronized String readFromServer() {
        try {
            if (socket != null && !socket.isClosed()) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println("[CLIENT] Server Response: " + response);
                    return response;
                }
            } else {
                System.err.println("[CLIENT] Unable to read: Socket is closed");
            }
        } catch (IOException e) {
            System.err.println("[CLIENT] Error: Unable to read from server");
        }
        return null;
    }
}

