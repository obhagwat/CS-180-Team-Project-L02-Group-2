package Objects;
import Interfaces.ChatInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Chat Class: Contains all methods and fields pertaining to chat objects
 *
 *  @author Ana Farmus, Lab sec 02
 *  @version Apr 6, 2025
 */
public class Chat implements Serializable, ChatInterface {
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

    /**
     * Verify whether a message is valid, and if so add it to the chat
     * @param message   The message to be sent in the chat
     * @return whether the message was successfully sent
     */
    public boolean addMessage(Message message) {
        if (message.verifyMessage()) {
            messages.add(message);
            return true;
        }
        return false;
    }

    /**
     * Check if the chat is between two specific users
     * @param userA   One of the users to check
     * @param userB   The other user to check
     * @return whether the chat is between those two users
     */
    public boolean isChatBetween(User userA, User userB) {
        return (user1.equals(userA) && user2.equals(userB)) ||
                (user1.equals(userB) && user2.equals(userA));
    }

    @Override
    public String toString() {
        return String.format("%s, %s", user1.getUsername(), user2.getUsername());
    }

}
