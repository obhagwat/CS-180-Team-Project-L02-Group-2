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
    private User jake;
    private User jane;
    private Chat chat;

    @Before
    public void setUp() {
        jake = new User("jakedoe", "password123", 5.0, "USA",
                "123 Elm St, Springfield", "jake.doe@example.com", "5051234567");
        jane = new User("janesmith", "securePassword456", 3.0, "Canada",
                "456 Maple Ave, Toronto", "jane.smith@example.com", "7685678901");
        chat = new Chat(jake, jane);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Chat object should be created", chat);
        assertEquals("User1 should match", jake, chat.getUser1());
        assertEquals("User2 should match", jane, chat.getUser2());
        assertNotNull("Chat ID should be generated", chat.getChatId());
        assertTrue("Messages list should be empty", chat.getMessages().isEmpty());
    }

    @Test
    public void getUsersTest() {
        ArrayList<User> users = chat.getUsers();
        assertEquals("Should contain 2 users", 2, users.size());
        assertTrue("Should contain user1", users.contains(jake));
        assertTrue("Should contain user2", users.contains(jane));
    }

    @Test
    public void addValidMessageTest() {
        Message message = new Message(new Date(), "Hello, Jane!", jake, jane);
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
                chat.isChatBetween(jake, jane));
        assertTrue("Should recognize chat between user2 and user1",
                chat.isChatBetween(jane, jake));

        User tony = new User("tonyjohnson", "password789", 2.0, "Australia",
                "789 Pine St, Melbourne", "tinyjohn@apple.com", "1949986483");
        assertFalse("Should not recognize chat with non-participant",
                chat.isChatBetween(jake, tony));
    }

    @Test
    public void toStringTest() {
        assertEquals("toString should show usernames", "jakedoe, janesmith", chat.toString());
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
