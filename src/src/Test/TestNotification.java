package Test;

import Objects.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

/**
 * A program that tests Notification class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestNotification {
    private User recipient;
    private Notification notification;

    @Before
    public void setUp() {
        recipient = new User("user1", "pass1", 0.0, "USA", "123 St", "user1@test.com", "1111111111");
        notification = new Notification("Test message", recipient);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Notification object should be created", notification);
        assertEquals("Message should match", "Test message", notification.getMessage());
        assertNotNull("Timestamp should be set", notification.getTimestamp());
        assertEquals("Recipient should match", recipient, notification.getRecipient());
    }

    @Test
    public void timestampTest() {
        LocalDateTime beforeCreation = LocalDateTime.now().minusSeconds(1);
        LocalDateTime afterCreation = LocalDateTime.now().plusSeconds(1);

        assertTrue("Timestamp should be after beforeCreation",
                notification.getTimestamp().isAfter(beforeCreation));
        assertTrue("Timestamp should be before afterCreation",
                notification.getTimestamp().isBefore(afterCreation));
    }

    @Test
    public void gettersTest() {
        assertEquals("Message should be accessible", "Test message", notification.getMessage());
        assertNotNull("Timestamp should be accessible", notification.getTimestamp());
        assertEquals("Recipient should be accessible", recipient, notification.getRecipient());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestNotification.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}