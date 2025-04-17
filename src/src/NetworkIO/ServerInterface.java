package NetworkIO;

import Objects.*;
import Exceptions.*;
import java.util.*;
/**
 * Server Interface Class: Contains all methods and fields pertaining to server objects
 * Note: Some of these will be one line because they are handled by the database
 * and we just need to propogate it up
 *
 * @author Saahil Kajarekar, Lab sec 02
 * @version Apr 16, 2025
 */
public interface ServerInterface{

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
     * @throws InvalidUserException if the user is not authenticated
     */
    boolean authenticateUser();

    /**
     *Sends a message from one client to another
     * @param message the message to be sent
     */
    void delieverToClient(String message);
    //Database based
    /**
     * creates a user that will be stored in the database
     * @return true if the user was sucessfully created else false
     * @throws InvalidUserException  if the use object is invalid
     */
    boolean createUser();
    /**
     * deletes a user from the database
     * @return true if the user was deleted sucessfully else false
     * @throws InvalidUserException if the user object is invalid
     * @throws InvalidChatException if the chat involves a deleted user
     */
    boolean deleteUser();

    //GUI based
    /**
     * displays all contracts for the industry of the contractor
     */
    void displayPossibleContracts();
    /**
     * display contractors on the GUI of our choosing
     */
    void displayContractors();
    /**
     * Allows the client to modify their profile including username and password
     */
    void editProfile();
    /**
     *
     */


}
