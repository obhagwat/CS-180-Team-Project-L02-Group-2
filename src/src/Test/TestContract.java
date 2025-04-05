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
    private Solicitor solicitor;
    private Contract contract;
    private LocalDateTime deadline;

    @Before
    public void setUp() {
        solicitor = new Solicitor("solicitor1", "pass123", 0.0, "USA", "123 St",
                "solicitor@test.com", "1234567890", "Test Solicitor",
                "Federal", "DOD", "Army", 10000.0);
        deadline = LocalDateTime.now().plusDays(7);
        contract = new Contract(solicitor, "Test contract", true, deadline, new ArrayList<>());
    }

    @Test
    public void constructorTest() {
        assertNotNull("Contract object should be created", contract);
        assertEquals("Solicitor should match", solicitor, contract.getSolicitor());
        assertEquals("Description should match", "Test contract", contract.getContractDescription());
        assertTrue("Contract should be open", contract.isContractStatus());
        assertEquals("Deadline should match", deadline, contract.getDeadline());
        assertTrue("Bids list should be empty", contract.getBids().isEmpty());
        assertNull("Winning bid should be null initially", contract.getWinningBid());
    }

    @Test
    public void gettersAndSettersTest() {
        Solicitor newSolicitor = new Solicitor("solicitor2", "pass456", 0.0, "UK", "456 St",
                "solicitor2@test.com", "0987654321", "New Solicitor",
                "State", "DOE", "Energy", 5000.0);
        LocalDateTime newDeadline = LocalDateTime.now().plusDays(14);

        contract.setSolicitor(newSolicitor);
        contract.setContractDescription("Updated contract");
        contract.setDeadline(newDeadline);

        assertEquals("Solicitor should be updated", newSolicitor, contract.getSolicitor());
        assertEquals("Description should be updated", "Updated contract", contract.getContractDescription());
        assertEquals("Deadline should be updated", newDeadline, contract.getDeadline());
    }

    @Test
    public void contractStatusTest() {
        contract.setContractStatus(false);
        assertFalse("Contract should be closed", contract.isContractStatus());
    }

    @Test
    public void addBidToOpenContractTest() {
        Contractor contractor = new Contractor("contractor1", "pass123", 0.0, "USA",
                "123 St", "contractor@test.com", "1234567890",
                "Test Contractor", "LLC", "50", 2000, Industry.CONSTRUCTION, "USA");
        Bid bid = new Bid(contractor, contract, 1000.0, "Under Consideration");

        contract.addBid(bid);
        assertEquals("Bids list should have 1 bid", 1, contract.getBids().size());
        assertEquals("Added bid should match", bid, contract.getBids().getFirst());
    }

    @Test(expected = IllegalStateException.class)
    public void addBidToClosedContractTest() {
        contract.setContractStatus(false);
        Contractor contractor = new Contractor("contractor1", "pass123", 0.0, "USA",
                "123 St", "contractor@test.com", "1234567890",
                "Test Contractor", "LLC", "50", 2000, Industry.CONSTRUCTION, "USA");
        Bid bid = new Bid(contractor, contract, 1000.0, "Under Consideration");

        contract.addBid(bid);
    }

    @Test
    public void winningBidTest() {
        Contractor contractor = new Contractor("contractor1", "pass123", 0.0, "USA",
                "123 St", "contractor@test.com", "1234567890",
                "Test Contractor", "LLC", "50", 2000, Industry.CONSTRUCTION, "USA");
        Bid bid = new Bid(contractor, contract, 1000.0, "Under Consideration");

        contract.setWinningBid(bid);
        assertEquals("Winning bid should be set", bid, contract.getWinningBid());
    }

    @Test
    public void toStringTest() {
        String expected = String.format("Contract [Solicitor: %s, Description: %s, Status: %s, Deadline: %s, Bids: %s]",
                solicitor, "Test contract", "Open", deadline, "No bids");
        assertEquals("toString should match expected format", expected, contract.toString());
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