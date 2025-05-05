package Test;

import Objects.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * A program that tests Contract class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestContract {
    private Solicitor builidingsSolicitor;
    private Contract govtDeal;
    private LocalDateTime deadline;

    @Before
    public void setUp() {
        builidingsSolicitor = new Solicitor("johnsmith", "securePass123", 0.0, "USA", "1600 Pennsylvania Ave",
                "john.smith@gsa.gov", "2025550100", "John Smith",
                "Federal", "GSA", "Public Buildings Service", 250000.0);
        deadline = LocalDateTime.now().plusDays(30);
        govtDeal = new Contract(builidingsSolicitor, "New Federal Courthouse Construction", "Need to build a 7 story building with numerous court rooms and additional facilities.",
                true, deadline, new ArrayList<>());
    }

    @Test
    public void constructorTest() {
        assertNotNull("Contract object should be created", govtDeal);
        assertEquals("Solicitor should match", builidingsSolicitor, govtDeal.getSolicitor());
        assertEquals("Description should match", "New Federal Courthouse Construction",
                govtDeal.getContractDescription());
        assertTrue("Contract should be open", govtDeal.isContractStatus());
        assertEquals("Deadline should match", deadline, govtDeal.getDeadline());
        assertTrue("Bids list should be empty", govtDeal.getBids().isEmpty());
        assertNull("Winning bid should be null initially", govtDeal.getWinningBid());
    }

    @Test
    public void gettersAndSettersTest() {
        Solicitor newOfficer = new Solicitor("janedoe", "anotherPass456", 0.0, "USA", "1800 F St NW",
                "jane.doe@gsa.gov", "2025550200", "Jane Doe",
                "Federal", "GSA", "Office of Acquisition", 300000.0);
        LocalDateTime newDeadline = LocalDateTime.now().plusDays(45);

        govtDeal.setSolicitor(newOfficer);
        govtDeal.setContractDescription("Post Office Renovation Project");
        govtDeal.setDeadline(newDeadline);

        assertEquals("Solicitor should be updated", newOfficer, govtDeal.getSolicitor());
        assertEquals("Description should be updated", "Post Office Renovation Project",
                govtDeal.getContractDescription());
        assertEquals("Deadline should be updated", newDeadline, govtDeal.getDeadline());
    }

    @Test
    public void contractStatusTest() {
        govtDeal.setContractStatus(false);
        assertFalse("Contract should be closed", govtDeal.isContractStatus());
    }

    @Test
    public void addBidToOpenContractTest() {
        Contractor constructionCo = new Contractor("turnerconstruction", "build2024", 0.0, "USA",
                "500 Construction Way", "bids@turner.com", "2125553000",
                "Turner Construction", "Inc", "5000", "100000", Industry.CONSTRUCTION);
        Bid proposal = new Bid(constructionCo, govtDeal, 15000000.0, "Submitted");

        govtDeal.addBid(proposal);
        assertEquals("Bids list should have 1 bid", 1, govtDeal.getBids().size());
        assertEquals("Added bid should match", proposal, govtDeal.getBids().getFirst());
    }

    @Test(expected = IllegalStateException.class)
    public void addBidToClosedContractTest() {
        govtDeal.setContractStatus(false);
        Contractor constructionCo = new Contractor("turnerconstruction", "build2024", 0.0, "USA",
                "500 Construction Way", "bids@turner.com", "2125553000",
                "Turner Construction", "Inc", "5000", "100000", Industry.CONSTRUCTION);
        Bid proposal = new Bid(constructionCo, govtDeal, 15000000.0, "Submitted");

        govtDeal.addBid(proposal);
    }

    @Test
    public void winningBidTest() {
        Contractor constructionCo = new Contractor("turnerconstruction", "build2024", 0.0, "USA",
                "500 Construction Way", "bids@turner.com", "2125553000",
                "Turner Construction", "Inc", "5000", "100000", Industry.CONSTRUCTION);
        Bid winningProposal = new Bid(constructionCo, govtDeal, 14500000.0, "Accepted");

        govtDeal.setWinningBid(winningProposal);
        assertEquals("Winning bid should be set", winningProposal, govtDeal.getWinningBid());
    }

    @Test
    public void toStringTest() {
        String expected = String.format("Contract [Solicitor: %s, Description: %s, Status: %s, Deadline: %s, Bids: %s]",
                builidingsSolicitor, "New Federal Courthouse Construction", "Open", deadline, "No bids");
        assertEquals("toString should match expected format", expected, govtDeal.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestContract.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}