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
    private User testGovUser;
    private Notification bidNotification;

    @Before
    public void setUp() {
        testGovUser = new User("govUser22", "securePass1", 0.0, "USA",
                "456 Contract Ave", "govuser@agency.org", "5559876543");
        bidNotification = new Notification("New bid submitted on your contract.", testGovUser);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Notification object should be created", bidNotification);
        assertEquals("Message should match", "New bid submitted on your contract.", bidNotification.getMessage());
        assertNotNull("Timestamp should be set", bidNotification.getTimestamp());
        assertEquals("Recipient should match", testGovUser, bidNotification.getRecipient());
    }

    @Test
    public void timestampTest() {
        LocalDateTime beforeNotif = LocalDateTime.now().minusSeconds(1);
        LocalDateTime afterNotif = LocalDateTime.now().plusSeconds(1);

        assertTrue("Timestamp should be after beforeNotif", bidNotification.getTimestamp().isAfter(beforeNotif));
        assertTrue("Timestamp should be before afterNotif", bidNotification.getTimestamp().isBefore(afterNotif));
    }

    @Test
    public void gettersTest() {
        assertEquals("Message should be accessible", "New bid submitted on your contract.",
                bidNotification.getMessage());
        assertNotNull("Timestamp should be accessible", bidNotification.getTimestamp());
        assertEquals("Recipient should be accessible", testGovUser, bidNotification.getRecipient());
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
