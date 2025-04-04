package Database;

import Objects.*;

import java.util.ArrayList;

public interface DatabaseInterface {
    void initializeDatabase();
    void closeDatabase();
    void addUser(User newUser);
    User getUser(String username);
    void deleteUser(String username);
    boolean UserExists(String username);
    ArrayList<User> searchUsers();
    void addChat(Chat chat);
    Chat getChatBetweenUsers(User user1, User user2);
    ArrayList<Chat> getChatsforUser(String username);
    ArrayList<Chat> getChats();
    boolean ChatExists(String username, Chat chat);
    void deleteChat(Chat chat); //or User user1, User user2 as params??
    void serializeDatabase();
    void loadDatabase();
    void clearDatabase();


}

