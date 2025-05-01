package Database;

import Objects.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Database.Database Class: Contains all methods and fields pertaining to database objects
 *
 * @author Ana Farmus, Lab sec 02
 * @author Ovi Bhagwat
 * @version Apr 6, 2025
 */
public class Database implements DatabaseInterface {
    private static Database instance;
    private ArrayList<Chat> chats;
    private ArrayList<Contractor> contractors;
    private ArrayList<Solicitor> solicitors;
    private static final String DIRECTORY = "data/";
    private static String solicitorDataFile = DIRECTORY + "solicitorData.dat";
    private static String contractorDataFile = DIRECTORY + "contractorData.dat";
    private static String chatDataFile = DIRECTORY + "chatData.dat";
    private static final Object GATEKEEPER = new Object();

    /**
     * Constructs a Database object initialized with a given list of users.
     * This constructor is used internally for singleton instantiation.
     *
     * @param contractors the list of contractors to initialize the database with
     * @param solicitors  the list of solicitors to intialize the database with
     * @param chats       the list of chats to initialize the database with
     */
    public Database(ArrayList<Solicitor> solicitors, ArrayList<Contractor> contractors, ArrayList<Chat> chats) {
        synchronized (GATEKEEPER) {
            this.solicitors = solicitors;
            this.contractors = contractors;
            this.chats = chats;
        }
    }

    /**
     * Constructs an empty Database and loads data from the specified file
     * into the users list.
     */
    public Database() {
        synchronized (GATEKEEPER) {
            this.solicitors = new ArrayList<>();
            this.contractors = new ArrayList<>();
            this.chats = new ArrayList<>();
            loadDatabase();
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

    /**
     * Initializes the database by loading user data from the file into the users list.
     */
    @Override
    public void initializeDatabase() {
        loadDatabase();
    }

    /**
     * Closes the database by serializing all user data to the file.
     */
    @Override
    public void closeDatabase() {
        serializeDatabase();
    }

    /**
     * Sets the file path for storing serialized user data.
     *
     * @param baseName the name of the file to store serialized data
     */
    public void setDataFileToStore(String baseName) {
        contractorDataFile = DIRECTORY + baseName + ".contractorData.dat";
        solicitorDataFile = DIRECTORY + baseName + ".solicitorData.dat";
        chatDataFile = DIRECTORY + baseName + ".chatData.dat";
    }

    /**
     * Adds a new contractor to the database, then serializes the data to the file.
     *
     * @param newContractor the Contractor object to add to the database
     */
    @Override
    public synchronized boolean addContractor(Contractor newContractor) {
        if (!contractorExists(newContractor.getUsername())) {
            contractors.add(newContractor);
            serializeDatabase();
            return true;
        }
        return false;
    }

    /**
     * Adds a new solicitor to the database, then serializes the data to the file.
     *
     * @param newSolicitor the solicitor object to add to the database
     */
    @Override
    public synchronized boolean addSolicitor(Solicitor newSolicitor) {
        if (!solicitorExists(newSolicitor.getUsername())) {
            solicitors.add(newSolicitor);
            serializeDatabase();
            return true;
        }
        return false;
    }

    /**
     * Retrieves a contractor with a specified username from the database.
     *
     * @param username the username of the contractor to retrieve
     * @return the Contractor object if found, otherwise null
     */
    @Override
    public Contractor getContractor(String username) {
        for (Contractor contractor : contractors) {
            if (contractor.getUsername().equals(username)) {
                return contractor;
            }
        }
        return null;
    }

    /**
     * Retrieves list of all contractors from database.
     *
     * @return Contractor arraylist
     */
    @Override
    public ArrayList<Contractor> getContractors() {
        return new ArrayList<>(contractors);
    }

    /**
     * Retrieves a solicitor with a specified username from the database.
     *
     * @param username the username of the solicitor to retrieve
     * @return the Solicitor object if found, otherwise null
     */
    @Override
    public Solicitor getSolicitor(String username) {
        for (Solicitor solicitor : solicitors) {
            if (solicitor.getUsername().equals(username)) {
                return solicitor;
            }
        }
        return null;
    }

    /**
     * Retrieves list of all solicitors from database.
     *
     * @return Solicitor arraylist
     */
    @Override
    public ArrayList<Solicitor> getSolicitors() {
        return new ArrayList<>(solicitors);
    }

    /**
     * Deletes a contractor with a specified username from the database and
     * serializes the updated contractor list to the file.
     *
     * @param username the username of the contractor to delete
     */
    @Override
    public synchronized boolean deleteContractor(String username) {
        boolean success = false;
        for (Contractor contractor : contractors) {
            if (contractor.getUsername().equals(username)) {
                contractors.remove(contractor);
                success = true;
            }
        }
        serializeDatabase();
        return success;
    }

    /**
     * Deletes a solicitor with a specified username from the database and
     * serializes the updated solicitor list to the file.
     *
     * @param username the username of the solicitor to delete
     */
    @Override
    public synchronized boolean deleteSolicitor(String username) {
        boolean success = false;
        for (Solicitor solicitor : solicitors) {
            if (solicitor.getUsername().equals(username)) {
                solicitors.remove(solicitor);
                success = true;
            }
        }
        serializeDatabase();
        return success;
    }

    /**
     * Checks if a contractor with the specified username exists in the database.
     *
     * @param username the username to check for
     * @return true if the contractor exists, otherwise false
     */
    @Override
    public synchronized boolean contractorExists(String username) {
        for (Contractor contractor : contractors) {
            if (contractor.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a solicitor with the specified username exists in the database.
     *
     * @param username the username to check for
     * @return true if the solicitor exists, otherwise false
     */
    @Override
    public synchronized boolean solicitorExists(String username) {
        for (Solicitor solicitor : solicitors) {
            if (solicitor.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for contractors.
     *
     * @param search entered string in search bar
     * @return a list of Contractor objects matching the keyword
     */
    @Override
    public synchronized ArrayList<Contractor> searchContractor(String search) {
        ArrayList<Contractor> matchedContractors = new ArrayList<>();
        for (Contractor contractor : contractors) {
            if (contractor.getContractorName().contains(search)) {
                matchedContractors.add(contractor);
            }
        }
        return matchedContractors;
    }

    /**
     * Searches for solicitors.
     *
     * @param search entered string in search bar
     * @return a list of Solicitor objects matching the keyword
     */
    @Override
    public synchronized ArrayList<Solicitor> searchSolicitor(String search) {
        ArrayList<Solicitor> matchedSolicitors = new ArrayList<>();
        for (Solicitor solicitor : solicitors) {
            if (solicitor.getSolicitorName().contains(search)) {
                matchedSolicitors.add(solicitor);
            }
        }
        return matchedSolicitors;
    }

    /**
     * Adds a new chat to the database and serializes the data to ensure persistence.
     *
     * @param chat the Chat object to add to the database
     */
    @Override
    public synchronized void addChat(Chat chat) {
        if (!chatExists(chat.getUser1(), chat.getUser2())) {
            chats.add(chat);
            serializeDatabase(); // Ensures that chat data is saved
        }
    }

    /**
     * Retrieves a specific chat between two users, if it exists.
     *
     * @param user1 the first user in the chat
     * @param user2 the second user in the chat
     * @return the Chat object if found, otherwise null
     */
    @Override
    public Chat getChatBetweenUsers(User user1, User user2) {
        for (Chat chat : chats) {
            if (chat.isChatBetween(user1, user2)) {
                return chat;
            }
        }
        return null;
    }

    /**
     * Retrieves all chats that a specific user is part of.
     *
     * @param username the username of the user
     * @return a list of Chat objects that the user is part of
     */
    @Override
    public ArrayList<Chat> getChatsForUser(String username) {
        ArrayList<Chat> userChats = new ArrayList<>();
        for (Chat chat : chats) {
            if (chat.getUser1().getUsername().equals(username) ||
                    chat.getUser2().getUsername().equals(username)) {
                userChats.add(chat);
            }
        }
        return userChats;
    }

    /**
     * Returns a list of all User objects in the users list.
     *
     * @return ArrayList<User> - a list of all users
     */
    @Override
    public ArrayList<Chat> getChats() {
        return new ArrayList<>(chats);
    }

    /**
     * Checks if a chat exists between two specified users.
     *
     * @param user1 the first user
     * @param user2 the second user
     * @return true if a chat exists between the users, otherwise false
     */
    @Override
    public boolean chatExists(User user1, User user2) {
        return getChatBetweenUsers(user1, user2) != null;
    }


    /**
     * Deletes a chat between two specified users from the database and serializes the updated data.
     *
     * @param user1 the first user in the chat
     * @param user2 the second user in the chat
     * @return true if the chat was deleted, false otherwise
     */
    @Override
    public synchronized boolean deleteChat(User user1, User user2) {
        Chat chatToRemove = getChatBetweenUsers(user1, user2);
        if (chatToRemove != null) {
            chats.remove(chatToRemove);
            serializeDatabase(); // Ensures that the change is saved
            return true;
        }
        return false;
    }

    /**
     * Serializes the contractor data, solicitor data and chat data to the specified files.
     * Each user and chat is written
     * as an object to the file, allowing data persistence.
     */
    @Override
    public void serializeDatabase() {
        try (ObjectOutputStream oosContractor = new ObjectOutputStream(new FileOutputStream(contractorDataFile));
             ObjectOutputStream oosSolicitor = new ObjectOutputStream(new FileOutputStream(solicitorDataFile));
             ObjectOutputStream oosChat = new ObjectOutputStream(new FileOutputStream(chatDataFile))) {
            for (Contractor contractor : contractors) {
                oosContractor.writeObject(contractor);
            }
            for (Solicitor solicitor : solicitors) {
                oosSolicitor.writeObject(solicitor);
            }
            for (Chat chat : chats) {
                oosChat.writeObject(chat);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads contractor, solicitor and chat data from the file into the contractor,
     * solicitor and chats list respectively.
     * Each user and chat object
     * is read and added to its list, allowing for persistence between sessions.
     */
    @Override
    public void loadDatabase() {
        try (ObjectInputStream oisContractor = new ObjectInputStream(new FileInputStream(contractorDataFile));
             ObjectInputStream oisSolicitor = new ObjectInputStream(new FileInputStream(solicitorDataFile));
             ObjectInputStream oisChat = new ObjectInputStream(new FileInputStream(chatDataFile))) {
            ArrayList<Contractor> loadedContractors = new ArrayList<>();
            ArrayList<Solicitor> loadedSolicitors = new ArrayList<>();
            ArrayList<Chat> loadedChats = new ArrayList<>();

            try {
                while (true) {
                    loadedContractors.add((Contractor) oisContractor.readObject());
                }
            } catch (EOFException ignored ) {
            }

            try {
                while (true) {
                    loadedSolicitors.add((Solicitor) oisSolicitor.readObject());
                }
            } catch (EOFException ignored) {
            }

            try {
                while (true) {
                    loadedChats.add((Chat) oisChat.readObject());
                }
            } catch (EOFException ignored) {
            }

            this.contractors = loadedContractors;
            this.solicitors = loadedSolicitors;
            this.chats = loadedChats;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears all data from the users list and serializes the empty list to the file.
     */
    @Override
    public void clearDatabase() {
        contractors.clear();
        solicitors.clear();
        chats.clear();
        serializeDatabase();
    }
}
