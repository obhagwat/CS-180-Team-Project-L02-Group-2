package Test;

import Objects.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.Assert.*;

/**
 * A program that tests Bid class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */

@RunWith(JUnit4.class)
public class TestBid {
    private Contractor contractor;
    private Contract contract;
    private Bid bid;

    @Before
    public void setUp() {
        contractor = new Contractor("contractor1", "pass123", 0.0, "USA",
                "123 St", "contractor@test.com", "1234567890",
                "Test Contractor", "LLC", "50", 2000, Industry.CONSTRUCTION, "USA");
        contract = new Contract(null, "Test contract", true, null, new ArrayList<>());
        bid = new Bid(contractor, contract, 1000.0, "Under Consideration");
    }

    @Test
    public void constructorTest() {
        assertNotNull("Bid object should be created", bid);
        assertEquals("Contractor should match", contractor, bid.getContractor());
        assertEquals("Contract should match", contract, bid.getContract());
        assertEquals("Requested pay should match", 1000.0, bid.getRequestedPay(), 0.001);
        assertEquals("Status should match", "Under Consideration", bid.getStatus());
    }

    @Test
    public void gettersAndSettersTest() {
        Contractor newContractor = new Contractor("contractor2", "pass456", 0.0, "UK",
                "456 St", "contractor2@test.com", "0987654321",
                "New Contractor", "Inc", "100", 1990, Industry.IT_SERVICES, "UK");
        Contract newContract = new Contract(null, "New contract", true, null, new ArrayList<>());

        bid.setContractor(newContractor);
        bid.setContract(newContract);
        bid.setRequestedPay(1500.0);

        assertEquals("Contractor should be updated", newContractor, bid.getContractor());
        assertEquals("Contract should be updated", newContract, bid.getContract());
        assertEquals("Requested pay should be updated", 1500.0, bid.getRequestedPay(), 0.001);
    }

    @Test
    public void statusTest() {
        bid.setStatus("Accepted");
        assertEquals("Status should be updated to Accepted", "Accepted", bid.getStatus());

        bid.setStatus("Rejected");
        assertEquals("Status should be updated to Rejected", "Rejected", bid.getStatus());

        bid.setStatus("Under Consideration");
        assertEquals("Status should be updated to Under Consideration",
                "Under Consideration", bid.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidStatusTest() {
        bid.setStatus("Invalid Status");
    }

    @Test
    public void isAcceptedTest() {
        bid.setStatus("Accepted");
        assertTrue("isAccepted should return true for Accepted status", bid.isAccepted());

        bid.setStatus("Rejected");
        assertFalse("isAccepted should return false for Rejected status", bid.isAccepted());
    }

    @Test
    public void toStringTest() {
        String expected = String.format("Bid [Contractor: %s, Contract: %s, Requested Pay: $%.2f, Status: %s]",
                contractor, contract, 1000.0, "Under Consideration");
        assertEquals("toString should match expected format", expected, bid.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestBid.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}