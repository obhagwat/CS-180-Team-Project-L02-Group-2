package Interfaces;

import Objects.Message;
import Objects.User;

import java.util.ArrayList;

/**
 *  Chat Interface: Interface for Chat class
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface ChatInterface {
    User getUser1();
    User getUser2();
    String getChatId();
    ArrayList<User> getUsers();
    ArrayList<Message> getMessages();

    boolean addMessage(Message message);
    boolean isChatBetween(User userA, User userB);
}
