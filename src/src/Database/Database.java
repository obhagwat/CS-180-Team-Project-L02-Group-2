package Database;
import Interfaces.DatabaseInterface;
import  Objects.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Database.Database Class: Contains all methods and fields pertaining to database objects
 *  @author Ana Farmus, Lab sec 02
 *  @author Ovi Bhagwat
 *  @version Apr 6, 2025
 */
public class Database implements DatabaseInterface {
    private static Database instance;
    private ArrayList<User> users;
    private ArrayList<Chat> chats;
    private static final String DIRECTORY = "data/";
    private static String userDataFile = DIRECTORY + "userData.dat";
    private static String chatDataFile = DIRECTORY + "chatData.dat";
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
        synchronized (GATEKEEPER) {
            this.users = new ArrayList<>();
            this.chats = new ArrayList<>();
//        loadDatabase();
        }


    }

    /**
     * Returns the singleton instance of the Database. If no instance exists,
     * a new one is created and initialized by loading data from the file.
     *
     * @return the singleton instance of the Database
     */
    public synchronized static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }
}
