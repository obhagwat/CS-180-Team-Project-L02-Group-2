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
 * A program that tests Solicitor class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */
@RunWith(JUnit4.class)
public class TestSolicitor {
    private Solicitor testUser;
    private LocalDateTime contractDue;

    @Before
    public void setUp() {
        testUser = new Solicitor("user123", "mypassword", 0.0, "USA", "456 College Ave",
                "student@email.com", "9876543210", "Alex Johnson",
                "Federal", "NASA", "SpaceTech", 5000.0);
        contractDue = LocalDateTime.now().plusDays(7);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Solicitor object should be created", testUser);
        assertEquals("Solicitor name should match", "Alex Johnson", testUser.getSolicitorName());
        assertEquals("Agency level should match", "Federal", testUser.getAgencyLevel());
        assertEquals("Branch should match", "NASA", testUser.getBranch());
        assertEquals("Sub-branch should match", "SpaceTech", testUser.getSubBranch());
        assertTrue("Contracts solicited list should be empty", testUser.getContractsSolicited().isEmpty());
        assertTrue("Open contracts list should be empty", testUser.getOpenContracts().isEmpty());
    }

    @Test
    public void gettersAndSettersTest() {
        testUser.setSolicitorName("Jamie Lee");
        testUser.setAgencyLevel("Local");
        testUser.setBranch("ParksDept");
        testUser.setSubBranch("Forestry");

        assertEquals("Solicitor name should be updated", "Jamie Lee", testUser.getSolicitorName());
        assertEquals("Agency level should be updated", "Local", testUser.getAgencyLevel());
        assertEquals("Branch should be updated", "ParksDept", testUser.getBranch());
        assertEquals("Sub-branch should be updated", "Forestry", testUser.getSubBranch());
    }

    @Test
    public void postContractTest() {
        testUser.postContract("Fixing trails in local park", contractDue, new ArrayList<>());
        assertEquals("Contracts solicited list should have 1 contract",
                1, testUser.getContractsSolicited().size());
        assertEquals("Open contracts list should have 1 contract",
                1, testUser.getOpenContracts().size());

        Contract postedContract = testUser.getContractsSolicited().getFirst();
        assertEquals("Contract description should match", "Fixing trails in local park", postedContract.getContractDescription());
        assertEquals("Contract deadline should match", contractDue, postedContract.getDeadline());
        assertTrue("Contract should be open", postedContract.isContractStatus());
    }

    @Test
    public void makePaymentTest() {
        Contractor worker = new Contractor("worker123", "pw123", 0.0, "USA",
                "789 Campus Ln", "worker@sample.com", "3216549870",
                "Taylor Smith", "Inc", "15", 1500, Industry.CONSTRUCTION, "USA");
        Contract contract = new Contract(testUser, "Paint city benches", true, contractDue, new ArrayList<>());
        Bid newBid = new Bid(worker, contract, 1200.0, "Accepted");

        double startingBalance = testUser.getBalance();
        testUser.makePayment(newBid);
        assertEquals("Balance should decrease by bid amount",
                startingBalance - newBid.getRequestedPay(), testUser.getBalance(), 0.001);
    }

    @Test
    public void closeContractTest() {
        testUser.postContract("Set up lighting in gym", contractDue, new ArrayList<>());
        Contract openOne = testUser.getOpenContracts().getFirst();

        testUser.closeContract(openOne);
        assertFalse("Contract should be closed", openOne.isContractStatus());
        assertTrue("Open contracts list should be empty", testUser.getOpenContracts().isEmpty());
        assertEquals("Contracts solicited list should still have 1 contract",
                1, testUser.getContractsSolicited().size());
    }

    @Test
    public void toStringTest() {
        String expected = "Solicitor [Name: Alex Johnson, Agency Level: Federal, Branch: NASA, " +
                "Sub-Branch: SpaceTech, Open Contracts: 0, Total Contracts: 0]";
        assertEquals("toString should match expected format", expected, testUser.toString());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSolicitor.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}
