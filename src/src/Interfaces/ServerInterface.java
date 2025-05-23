package Interfaces;

import NetworkIO.Server;
import Objects.*;
import Exceptions.*;

import java.io.IOException;
import java.util.*;
/**
 * Server Interface Class: Contains all methods and fields pertaining to server objects
 * Note: Some of these will be one line because they are handled by the database
 * and we just need to propagate it up
 *
 * @author Saahil Kajarekar, Sarah Stone Lab sec 02
 * @version Apr 16, 2025
 */
public interface ServerInterface {

    /**
     * called for each new client to read input and call corresponding methods
     */
    void handleClient() throws IOException;

    /**
     * accepts a message from the Client
     * @return the message from the client
     */
    String readFromClient();

    /**
     * sends a string to the Client
     * @param message the message to be sent
     */
    void sendToClient(String message);

    /**
     * authenticates a user through their username and password
     * @return true if the User fits the role, username, and passwords else false
     * @throws InvalidUserException if the user is not authenticated
     */
    boolean authenticateUser(String role, String username, String password) throws InvalidUserException;

    /**
     * Sends a message from one client to another
     * @param message the message to be sent
     * @return true if the message successfully sent, else false
     */
    boolean deliverBetweenClients(String message, Server receiver);

    //Database based
    /**
     * Adds the user to the list of contractors or solicitors in the database
     * Note: We will need to prompt to create a user object before this method is called
     * @param user the user to be added
     * @return true if the user is successfully added else false
     */
    boolean createUser(User user);
    /**
     * deletes a user from the database
     * @return true if the user was deleted successfully else false
     *
     */
    boolean deleteUser(User user);

    //GUI based, commented out for now
    /**
     * displays all contracts for the industry of the contractor
     */
    //void displayPossibleContracts();
    /**
     * display contractors on the GUI of our choosing
     */
    //void displayContractors();
    /**
     * Allows the client to modify their profile including username and password
     */
    //void editProfile();
    /**
     *
     */

}
