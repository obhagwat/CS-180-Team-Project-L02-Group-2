package Test;

import Objects.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * A program that tests Contractor class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestContractor {
    private Contractor contractor;
    private Contract contract;
    private Bid bid;

    @Before
    public void setUp() {
        contractor = new Contractor("greenfieldconstruction", "securePass123", 0.0, "USA", "789 Elm St",
                "contact@greenfield.com", "5551234567",
                "Greenfield Construction Inc.", "LLC", "75", 2010, Industry.CONSTRUCTION, "USA");
        contract = new Contract(null, "New Government Building Contract", true, null, new ArrayList<>());
        bid = new Bid(contractor, contract, 250000.0, "Under Review");
    }

    @Test
    public void constructorTest() {
        assertNotNull("Contractor object should be created", contractor);
        assertEquals("Contractor name should match", "Greenfield Construction Inc.", contractor.getContractorName());
        assertEquals("Company type should match", "LLC", contractor.getCompanyType());
        assertEquals("Number of employees should match", "75", contractor.getNumEmployees());
        assertEquals("Year founded should match", 2010, contractor.getYearFounded());
        assertEquals("Industry should match", Industry.CONSTRUCTION, contractor.getIndustry());
        assertTrue("Contracts won list should be empty", contractor.getContractsWon().isEmpty());
        assertTrue("All bids list should be empty", contractor.getAllBids().isEmpty());
    }

    @Test
    public void gettersAndSettersTest() {
        contractor.setContractorName("Greenfield Construction Enterprises");
        contractor.setCompanyType("Corporation");
        contractor.setNumEmployees("120");
        contractor.setYearFounded(2005);
        contractor.setIndustry(Industry.IT_SERVICES);

        assertEquals("Contractor name should be updated", "Greenfield Construction Enterprises", contractor.getContractorName());
        assertEquals("Company type should be updated", "Corporation", contractor.getCompanyType());
        assertEquals("Number of employees should be updated", "120", contractor.getNumEmployees());
        assertEquals("Year founded should be updated", 2005, contractor.getYearFounded());
        assertEquals("Industry should be updated", Industry.IT_SERVICES, contractor.getIndustry());
    }

    @Test
    public void replyWithBidTest() {
        contractor.replyWithBid(contract, 250000.0);
        assertEquals("All bids list should have 1 bid", 1, contractor.getAllBids().size());
        Bid addedBid = contractor.getAllBids().getFirst();
        assertEquals("Bid contractor should match", contractor, addedBid.getContractor());
        assertEquals("Bid contract should match", contract, addedBid.getContract());
        assertEquals("Bid amount should match", 250000.0, addedBid.getRequestedPay(), 0.001);
        assertEquals("Bid status should be Under Consideration", "Under Consideration", addedBid.getStatus());
    }

    @Test
    public void addToContractsWonTest() {
        contractor.addToContractsWon(contract);
        assertEquals("Contracts won list should have 1 contract", 1, contractor.getContractsWon().size());
        assertEquals("Added contract should match", contract, contractor.getContractsWon().getFirst());
    }

    @Test
    public void receivePaymentTest() {
        double initialBalance = contractor.getBalance();
        contractor.recievePayment(bid);
        assertEquals("Balance should increase by bid amount",
                initialBalance + bid.getRequestedPay(), contractor.getBalance(), 0.001);
    }

    @Test
    public void bidsUnderReviewTest() {
        contractor.replyWithBid(contract, 250000.0); // Under Review
        Bid rejectedBid = new Bid(contractor, contract, 200000.0, "Rejected");
        contractor.getAllBids().add(rejectedBid);

        ArrayList<Bid> underReview = contractor.bidsUnderReview();
        assertEquals("Should return only bids under review", 1, underReview.size());
        assertEquals("Should return the correct bid", 250000.0, underReview.getFirst().getRequestedPay(), 0.001);
    }

    @Test
    public void toStringTest() {
        String expected = "Contractor [Name: Greenfield Construction Inc., Company Type: LLC, Employees: 75, " +
                "Industry: CONSTRUCTION, Contracts Won: 0]";
        assertEquals("toString should match expected format", expected, contractor.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestContractor.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}
