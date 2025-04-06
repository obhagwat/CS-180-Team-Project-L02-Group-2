package Database;
import Objects.*;
import java.util.ArrayList;

/**
 * Database.Database Class: Contains all methods and fields pertaining to database objects
 *  @author Ana Farmus, Lab sec 02
 *  @version Apr 6, 2025
 */
public interface DatabaseInterface {
    void initializeDatabase();
    void closeDatabase();
    void SetDataFileToStore(String filename);
    void addContractor(Contractor newContractor);
    void addSolicitor(Solicitor newSolicitor);
    Contractor getContractor(String username);
    Solicitor getSolicitor(String username);
    void deleteContractor(String username);
    void deleteSolicitor(String username);
    boolean contractorExists(String username);
    boolean solicitorExists(String username);
    ArrayList<Contractor> searchContractor(String search);
    ArrayList<Solicitor> searchSolicitor(String search);
    void addChat(Chat chat);
    Chat getChatBetweenUsers(User user1, User user2);
    ArrayList<Chat> getChatsForUser(String username);
    ArrayList<Chat> getChats();
    boolean chatExists(User user1, User user2);
    boolean deleteChat(User user1, User user2);
    void serializeDatabase();
    void loadDatabase();
    void clearDatabase();
}

