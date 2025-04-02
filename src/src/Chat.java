import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Chat Class: Contains all methods and fields pertaining to chat objects
 *
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Chat implements Serializable {
    private User user1;
    private User user2;
    private String chatId;
    private ArrayList<Message> messages;

    public Chat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.chatId = generateChatId();
        this.messages = new ArrayList<>();
    }

    private String generateChatId() { return UUID.randomUUID().toString(); }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public String getChatId() {
        return chatId;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", user1.getUsername(), user2.getUsername());
    }

}
