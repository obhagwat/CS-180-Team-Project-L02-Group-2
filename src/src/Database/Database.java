package Database;
import  Objects.*;

import java.util.ArrayList;

/**
 * Database.Database Class: Contains all methods and fields pertaining to database objects
 *  @author Ana Farmus, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class Database {
    private ArrayList<User> users;
    private ArrayList<Chat> chats;
    private static final Object GATEKEEPER = new Object();

    public Database(ArrayList<User> users, ArrayList<Chat> chats) {
        synchronized (GATEKEEPER) {
            this.users = users;
            this.chats = chats;
        }
    }
}
