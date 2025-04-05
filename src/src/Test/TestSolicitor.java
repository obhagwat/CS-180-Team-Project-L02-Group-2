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
    private Solicitor solicitor;
    private LocalDateTime deadline;

    @Before
    public void setUp() {
        solicitor = new Solicitor("solicitor1", "pass123", 0.0, "USA", "123 St",
                "solicitor@test.com", "1234567890", "Test Solicitor",
                "Federal", "DOD", "Army", 10000.0);
        deadline = LocalDateTime.now().plusDays(7);
    }

    @Test
    public void constructorTest() {
        assertNotNull("Solicitor object should be created", solicitor);
        assertEquals("Solicitor name should match", "Test Solicitor", solicitor.getSolicitorName());
        assertEquals("Agency level should match", "Federal", solicitor.getAgencyLevel());
        assertEquals("Branch should match", "DOD", solicitor.getBranch());
        assertEquals("Sub-branch should match", "Army", solicitor.getSubBranch());
        assertTrue("Contracts solicited list should be empty", solicitor.getContractsSolicited().isEmpty());
        assertTrue("Open contracts list should be empty", solicitor.getOpenContracts().isEmpty());
    }

    @Test
    public void gettersAndSettersTest() {
        solicitor.setSolicitorName("Updated Solicitor");
        solicitor.setAgencyLevel("State");
        solicitor.setBranch("DOE");
        solicitor.setSubBranch("Energy");

        assertEquals("Solicitor name should be updated", "Updated Solicitor", solicitor.getSolicitorName());
        assertEquals("Agency level should be updated", "State", solicitor.getAgencyLevel());
        assertEquals("Branch should be updated", "DOE", solicitor.getBranch());
        assertEquals("Sub-branch should be updated", "Energy", solicitor.getSubBranch());
    }

    @Test
    public void postContractTest() {
        solicitor.postContract("Test contract", deadline, new ArrayList<>());
        assertEquals("Contracts solicited list should have 1 contract",
                1, solicitor.getContractsSolicited().size());
        assertEquals("Open contracts list should have 1 contract",
                1, solicitor.getOpenContracts().size());

        Contract postedContract = solicitor.getContractsSolicited().get(0);
        assertEquals("Contract description should match", "Test contract", postedContract.getContractDescription());
        assertEquals("Contract deadline should match", deadline, postedContract.getDeadline());
        assertTrue("Contract should be open", postedContract.isContractStatus());
    }

    @Test
    public void makePaymentTest() {
        Contractor contractor = new Contractor("contractor1", "pass123", 0.0, "USA",
                "123 St", "contractor@test.com", "1234567890",
                "Test Contractor", "LLC", "50", 2000, Industry.CONSTRUCTION, "USA");
        Contract contract = new Contract(solicitor, "Test contract", true, deadline, new ArrayList<>());
        Bid bid = new Bid(contractor, contract, 1000.0, "Accepted");

        double initialBalance = solicitor.getBalance();
        solicitor.makePayment(bid);
        assertEquals("Balance should decrease by bid amount",
                initialBalance - bid.getRequestedPay(), solicitor.getBalance(), 0.001);
    }

    @Test
    public void closeContractTest() {
        solicitor.postContract("Test contract", deadline, new ArrayList<>());
        Contract contract = solicitor.getOpenContracts().get(0);

        solicitor.closeContract(contract);
        assertFalse("Contract should be closed", contract.isContractStatus());
        assertTrue("Open contracts list should be empty", solicitor.getOpenContracts().isEmpty());
        assertEquals("Contracts solicited list should still have 1 contract",
                1, solicitor.getContractsSolicited().size());
    }

    @Test
    public void toStringTest() {
        String expected = "Solicitor [Name: Test Solicitor, Agency Level: Federal, Branch: DOD, " +
                "Sub-Branch: Army, Open Contracts: 0, Total Contracts: 0]";
        assertEquals("toString should match expected format", expected, solicitor.toString());
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