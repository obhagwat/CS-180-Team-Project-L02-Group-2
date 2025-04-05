package Test;

import Objects.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * A program that tests Chat class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestChat {
    private User user1;
    private User user2;
    private Chat chat;

    @Before
    public void setUp() {
        user1 = new User("user1", "pass1", 0.0, "USA", "123 St", "user1@test.com", "1111111111");
        user2 = new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222");
        chat = new Chat(user1, user2);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Chat object should be created", chat);
        assertEquals("User1 should match", user1, chat.getUser1());
        assertEquals("User2 should match", user2, chat.getUser2());
        assertNotNull("Chat ID should be generated", chat.getChatId());
        assertTrue("Messages list should be empty", chat.getMessages().isEmpty());
    }

    @Test
    public void getUsersTest() {
        ArrayList<User> users = chat.getUsers();
        assertEquals("Should contain 2 users", 2, users.size());
        assertTrue("Should contain user1", users.contains(user1));
        assertTrue("Should contain user2", users.contains(user2));
    }

    @Test
    public void addValidMessageTest() {
        Message message = new Message(new Date(), "Hello", user1, user2);
        assertTrue("Valid message should be added", chat.addMessage(message));
        assertEquals("Messages list should have 1 message", 1, chat.getMessages().size());
    }

    @Test
    public void addInvalidMessageTest() {
        Message nullMessage = new Message(null, null, null, null);
        assertFalse("Invalid message should not be added", chat.addMessage(nullMessage));
        assertTrue("Messages list should still be empty", chat.getMessages().isEmpty());
    }

    @Test
    public void isChatBetweenTest() {
        assertTrue("Should recognize chat between user1 and user2",
                chat.isChatBetween(user1, user2));
        assertTrue("Should recognize chat between user2 and user1",
                chat.isChatBetween(user2, user1));

        User user3 = new User("user3", "pass3", 0.0, "CA", "789 St", "user3@test.com", "3333333333");
        assertFalse("Should not recognize chat with non-participant",
                chat.isChatBetween(user1, user3));
    }

    @Test
    public void toStringTest() {
        assertEquals("toString should show usernames", "user1, user2", chat.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestChat.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}