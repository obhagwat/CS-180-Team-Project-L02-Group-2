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
        contractor = new Contractor("ProtectUs", "sheildforall", 0.0, "USA",
                "410 Salisbury Ave", "protectUS@yahoo.com", "5550005050",
                "ProtectUs", "INC", "5000", "2014", Industry.DEFENSE);
        contract = new Contract(null, "Test contract", true, null, new ArrayList<>());
        bid = new Bid(contractor, contract, 1000.0, "Under Consideration");
    }

    @Test
    public void constructorTest() {
        assertNotNull("Bid object should be created", bid);
        assertEquals("Contractor should match", contractor, bid.getContractor());
        assertEquals("Contract should match", contract, bid.getContract());
        assertEquals("Requested pay should match", 1000.0, bid.getRequestedPay(), 0.0001);
        assertEquals("Status should match", "Under Consideration", bid.getStatus());
    }

    @Test
    public void gettersAndSettersTest() {
        Contractor newContractor = new Contractor("farmersedu", "fruitbasket", 0.0, "US",
                "Main St", "farmersedu@outlook.com", "4254254255",
                "Farmer's Edu", "LLC", "3000", "1946", Industry.EDUCATION);
        Contract newContract = new Contract(null, "New contract", true, null, new ArrayList<>());

        bid.setContractor(newContractor);
        bid.setContract(newContract);
        bid.setRequestedPay(1500.0);

        assertEquals("Contractor should be updated", newContractor, bid.getContractor());
        assertEquals("Contract should be updated", newContract, bid.getContract());
        assertEquals("Requested pay should be updated", 1500.0, bid.getRequestedPay(), 0.0001);
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