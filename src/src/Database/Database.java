package Database;
import Interfaces.DatabaseInterface;
import  Objects.*;

import java.util.ArrayList;

/**
 * Database.Database Class: Contains all methods and fields pertaining to database objects
 *  @author Ana Farmus, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class Database implements DatabaseInterface {
    private ArrayList<User> users;
    private ArrayList<Chat> chats;
    private static final Object GATEKEEPER = new Object();

/**
 * Constructs a Database object initialized with a given list of users.
 * This constructor is used internally for singleton instantiation.
 *
 * @param users the list of users to initialize the database with
 * @param chats the list of chats to initialize the database with
 */
    public Database(ArrayList<User> users, ArrayList<Chat> chats) {
        synchronized (GATEKEEPER) {
            this.users = users;
            this.chats = chats;
        }
    }

    /**
     * Constructs an empty Database and loads data from the specified file
     * into the users list.
     */
    public Database() {
        this.users = new ArrayList<>();
        this.chats = new ArrayList<>();
//        loadDatabase();
    }
}
