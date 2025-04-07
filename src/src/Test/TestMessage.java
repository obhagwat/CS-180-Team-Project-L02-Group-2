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
    private User agencyRep;
    private User contractor;
    private Message bidMessage;
    private Date messageTimestamp;
    private SimpleDateFormat readableDate;

    @Before
    public void setUp() {
        agencyRep = new User("agencyRep", "safePass123", 0.0, "USA", "101 Gov Lane", "rep@govagency.gov", "9998887777");
        contractor = new User("contractorX", "contract123", 0.0, "USA", "202 Contractor Blvd", "x@firm.com", "8887776666");
        messageTimestamp = new Date();
        bidMessage = new Message(messageTimestamp, "Proposal submitted for review.", agencyRep, contractor);
        readableDate = new SimpleDateFormat("MM/dd/yy HH:mm");
    }

    @Test
    public void constructorTest() {
        assertNotNull("Message object should be created", bidMessage);
        assertEquals("Timestamp should match", messageTimestamp, bidMessage.getTimeStamp());
        assertEquals("Text should match", "Proposal submitted for review.", bidMessage.getText());
        assertEquals("Sender should match", agencyRep, bidMessage.getSender());
        assertEquals("Recipient should match", contractor, bidMessage.getRecipient());
        assertNull("Bid should be null when not provided", bidMessage.getBid());
    }

    @Test
    public void constructorWithBidTest() {
        Contract testContract = new Contract(null, "Secure Facility Upgrade", true, null, new ArrayList<>());
        Bid proposal = new Bid(null, testContract, 250000.0, "Submitted");
        Message messageWithProposal = new Message(messageTimestamp, "Proposal attached for consideration.", agencyRep, contractor, proposal);

        assertEquals("Bid should be set", proposal, messageWithProposal.getBid());
    }

    @Test
    public void verifyValidMessageTest() {
        assertTrue("Valid message should verify", bidMessage.verifyMessage());
    }

    @Test
    public void verifyInvalidMessageTest() {
        Message noSender = new Message(messageTimestamp, "Ping", null, contractor);
        assertFalse("Message with null sender should not verify", noSender.verifyMessage());

        Message noRecipient = new Message(messageTimestamp, "Ping", agencyRep, null);
        assertFalse("Message with null recipient should not verify", noRecipient.verifyMessage());

        Message blankBody = new Message(messageTimestamp, "", agencyRep, contractor);
        assertFalse("Message with empty text should not verify", blankBody.verifyMessage());

        Message nullBody = new Message(messageTimestamp, null, agencyRep, contractor);
        assertFalse("Message with null text should not verify", nullBody.verifyMessage());

        String tooManyChars = "a".repeat(501);
        Message overflowText = new Message(messageTimestamp, tooManyChars, agencyRep, contractor);
        assertFalse("Message with >500 chars should not verify", overflowText.verifyMessage());
    }

    @Test
    public void toStringTest() {
        String expected = String.format("[%s] From: %s To: %s - %s",
                readableDate.format(messageTimestamp),
                "agencyRep", "contractorX", "Proposal submitted for review.");
        assertEquals("toString should match expected format", expected, bidMessage.toString());
    }

    @Test
    public void toStringWithoutRecipientTest() {
        Message broadcast = new Message(messageTimestamp, "General update.", agencyRep, null);
        String expected = String.format("[%s] From: %s - %s",
                readableDate.format(messageTimestamp),
                "agencyRep", "General update.");
        assertEquals("toString without recipient should match expected format", expected, broadcast.toString());
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
