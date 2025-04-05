package Test;

import Objects.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * A program that tests Message class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestMessage {
    private User sender;
    private User recipient;
    private Message message;
    private Date timestamp;
    private SimpleDateFormat testDateFormat; // Local date format for testing

    @Before
    public void setUp() {
        sender = new User("user1", "pass1", 0.0, "USA", "123 St", "user1@test.com", "1111111111");
        recipient = new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222");
        timestamp = new Date();
        message = new Message(timestamp, "Hello", sender, recipient);
        testDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm"); // Create our own formatter
    }

    @Test
    public void constructorTest() {
        assertNotNull("Message object should be created", message);
        assertEquals("Timestamp should match", timestamp, message.getTimeStamp());
        assertEquals("Text should match", "Hello", message.getText());
        assertEquals("Sender should match", sender, message.getSender());
        assertEquals("Recipient should match", recipient, message.getRecipient());
        assertNull("Bid should be null when not provided", message.getBid());
    }

    @Test
    public void constructorWithBidTest() {
        Contract contract = new Contract(null, "Test contract", true, null, new ArrayList<>());
        Bid bid = new Bid(null, contract, 1000.0, "Under Consideration");
        Message messageWithBid = new Message(timestamp, "Hello with bid", sender, recipient, bid);

        assertEquals("Bid should be set", bid, messageWithBid.getBid());
    }

    @Test
    public void verifyValidMessageTest() {
        assertTrue("Valid message should verify", message.verifyMessage());
    }

    @Test
    public void verifyInvalidMessageTest() {
        Message nullSender = new Message(timestamp, "Hello", null, recipient);
        assertFalse("Message with null sender should not verify", nullSender.verifyMessage());

        Message nullRecipient = new Message(timestamp, "Hello", sender, null);
        assertFalse("Message with null recipient should not verify", nullRecipient.verifyMessage());

        Message emptyText = new Message(timestamp, "", sender, recipient);
        assertFalse("Message with empty text should not verify", emptyText.verifyMessage());

        Message nullText = new Message(timestamp, null, sender, recipient);
        assertFalse("Message with null text should not verify", nullText.verifyMessage());

        String longText = new String(new char[501]).replace('\0', 'a');
        Message tooLong = new Message(timestamp, longText, sender, recipient);
        assertFalse("Message with >500 chars should not verify", tooLong.verifyMessage());
    }

    @Test
    public void toStringTest() {
        String expected = String.format("[%s] From: %s To: %s - %s",
                testDateFormat.format(timestamp),
                "user1", "user2", "Hello");
        assertEquals("toString should match expected format", expected, message.toString());
    }

    @Test
    public void toStringWithoutRecipientTest() {
        Message noRecipient = new Message(timestamp, "Hello", sender, null);
        String expected = String.format("[%s] From: %s - %s",
                testDateFormat.format(timestamp),
                "user1", "Hello");
        assertEquals("toString without recipient should match expected format",
                expected, noRecipient.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestMessage.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}