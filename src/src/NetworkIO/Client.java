package NetworkIO;

import Components.GUIWindow;
import Interfaces.ClientInterface;
import Objects.*;
import Database.*;
import Pages.LandingPage;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Client Class: Contains all methods and fields pertaining to client objects
 *
 * @author Ana Farmus, Lab sec 02
 * @version Apr 20, 2025
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
    public ArrayList<Contract> contractList = new ArrayList<>(); // Added for mock testing

    public static void startClient() {
        Client client = new Client();
        clientThread = new Thread(client);
        clientThread.start();
    }

    @Override
    public void run() {
        if (connectToServer()) {
            Client currentClient = this;
            SwingUtilities.invokeLater(() -> GUIWindow.getInstance().switchPage(new LandingPage(currentClient)));
        }
    }

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

    @Override
    public void disconnect() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
            System.out.println("[CLIENT] Client disconnected");
        } catch (IOException e) {
            System.err.println("[CLIENT] Could not close input stream");
        }
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Industry getIndustry() {
        if (user instanceof Contractor) {
            return ((Contractor) user).getIndustry();
        } else if (user instanceof Solicitor) {
            return Industry.GOVERNMENT;
        }
        return null;
    }

    @Override
    public Contractor getContractor() {
        return contractor;
    }

    @Override
    public Solicitor getSolicitor() {
        return solicitor;
    }

    @Override
    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    @Override
    public void setSolicitor(Solicitor solicitor) {
        this.solicitor = solicitor;
    }

    public ArrayList<Chat> getChats() {
        return db.getChatsForUser(user.getUsername());
    }

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

    // --- Mock Testing Methods ---
    public static Client startMockClient() {
        Client mock = new Client();

        Contractor contractor = new Contractor(
                "testContractor", "pass123", 4.5, "USA", "123 Main St", "test@contractor.com",
                "555-1234", "BuildCo", "LLC", "50", "2010", Industry.CONSTRUCTION
        );
        mock.contractor = contractor;
        mock.user = contractor;

        Solicitor solicitor = new Solicitor(
                "testSolicitor", "pass456", 4.8, "USA", "789 Agency Rd", "test@agency.com",
                "555-5678", "Gov Agency", "Federal", "Procurement", "IT", 10000000
        );
        mock.solicitor = solicitor;

        Contract contract1 = new Contract(solicitor, "Build website for government services", true, java.time.LocalDateTime.now().plusDays(10), null);
        Contract contract2 = new Contract(solicitor, "Design mobile app interface", false, java.time.LocalDateTime.now().minusDays(2), null);

        Bid bid1 = new Bid(contractor, contract1, 9500.0, "Under Consideration");
        Bid bid2 = new Bid(contractor, contract2, 8700.0, "Rejected");

        contractor.getAllBids().add(bid1);
        contractor.getAllBids().add(bid2);
        contract1.getBids().add(bid1);
        contract2.getBids().add(bid2);

        solicitor.getContractsSolicited().add(contract1);
        solicitor.getContractsSolicited().add(contract2);
        solicitor.getOpenContracts().add(contract1);

        mock.contractList.add(contract1);
        mock.contractList.add(contract2);

        return mock;
    }

    public static Client startMockClientContractorOnly() {
        Client mock = startMockClient();
        mock.solicitor = null;
        return mock;
    }

    public static Client startMockClientSolicitorOnly() {
        Client mock = startMockClient();
        mock.contractor = null;
        return mock;
    }
}
