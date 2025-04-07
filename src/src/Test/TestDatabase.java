package Test;

import Database.Database;
import Objects.*;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * A program that tests the Database class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 6, 2025
 */

@RunWith(JUnit4.class)
public class TestDatabase {
    private Database db;
    private Solicitor legalEagle;
    private Contractor bobTheBuilder;
    private Chat chatEagleBob;
    private Contract buildingProjct;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setup() throws Exception {
        db = Database.getInstance();
        db.setDataFileToStore(tempFolder.newFile("testDB.dat").getPath());
        db.clearDatabase();

        legalEagle = new Solicitor(
                "legalEagle", "law123", 0.0, "USA",
                "123 Court St", "eagle@law.com", "2025550101",
                "Legal Eagles LLC", "National", "Corporate", "Mergers", 10000.0
        );

        bobTheBuilder = new Contractor(
                "buildy", "hammerTime", 0.0, "USA",
                "456 Construction Ave", "buildy@build.com", "1045550202",
                "Buildy McBuildface", "LLC", "700", 1992, Industry.CONSTRUCTION, "USA"
        );

        db.addSolicitor(legalEagle);
        db.addContractor(bobTheBuilder);

        buildingProjct = new Contract(
                legalEagle,
                "Build a new office space for our employees",
                true,
                LocalDateTime.now().plusDays(7),
                new ArrayList<>()
        );

        chatEagleBob = new Chat(legalEagle, bobTheBuilder);
        db.addChat(chatEagleBob);
    }

    @After
    public void closing() {
        db.closeDatabase();
    }

    @Test
    public void testOneDatabase() {
        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();
        assertSame("There should only be one database instance", db1, db2);
    }

    @Test
    public void testAddSolicitor() {
        Solicitor newSolicitor = new Solicitor(
                "newLawyer", "pass123", 0.0, "UK",
                "1 Lawyer Lane", "law@law.com", "555-0303",
                "Law & Order", "International", "Criminal", "Defense", 5000.0
        );

        db.addSolicitor(newSolicitor);
        assertTrue("New solicitor should exist", db.solicitorExists("newLawyer"));
    }

    @Test
    public void testGetSolicitor() {
        Solicitor found = db.getSolicitor("legalEagle");
        assertNotNull("Should find our legal eagle", found);
        assertEquals("Should be our legal eagle", "Legal Eagles LLC", found.getSolicitorName());
    }

    @Test
    public void testDeleteSolicitor() {
        db.deleteSolicitor("legalEagle");
        assertFalse("Legal eagle should be gone", db.solicitorExists("legalEagle"));
    }

    @Test
    public void testAddContractor() {
        Contractor newBuilder = new Contractor(
                "bobTheBuilder", "yesWeCan", 0.0, "Canada",
                "789 Builder Blvd", "bob@build.com", "555-0404",
                "Bob the Builder", "Inc", "100", 2000, Industry.CONSTRUCTION, "Canada"
        );

        db.addContractor(newBuilder);
        assertTrue("Bob should be in the database", db.contractorExists("bobTheBuilder"));
    }

    @Test
    public void testGetContractor() {
        Contractor found = db.getContractor("buildy");
        assertNotNull("Should find our builder", found);
        assertEquals("Should be Buildy McBuildface", "Buildy McBuildface", found.getContractorName());
    }

    @Test
    public void testDeleteContractor() {
        db.deleteContractor("buildy");
        assertFalse("Buildy should be gone", db.contractorExists("buildy"));
    }

    @Test
    public void testAddChat() {
        Chat newChat = new Chat(legalEagle, bobTheBuilder);
        db.addChat(newChat);

        ArrayList<Chat> chats = db.getChatsForUser("legalEagle");
        assertFalse("Should find our chat", chats.isEmpty());
    }

    @Test
    public void testGetUserChats() {
        ArrayList<Chat> chats = db.getChatsForUser("legalEagle");
        assertEquals("Legal eagle should have 1 chat", 1, chats.size());
    }

    @Test
    public void testDeleteChat() {
        boolean deleted = db.deleteChat(legalEagle, bobTheBuilder);
        assertTrue("Chat should be deleted", deleted);
        assertEquals("No chats should remain", 0, db.getChatsForUser("legalEagle").size());
    }

    @Test
    public void testSearchSolicitors() {
        ArrayList<Solicitor> results = db.searchSolicitor("Legal");
        assertEquals("Should find our legal eagle", 1, results.size());
    }

    @Test
    public void testSearchContractors() {
        ArrayList<Contractor> results = db.searchContractor("Build");
        assertEquals("Should find buildy", 1, results.size());
    }

    @Test
    public void testLoadDatabase() {
        db.closeDatabase();
        Database newDb = Database.getInstance();
        newDb.loadDatabase();
        assertNotNull("Legal eagle should still exist", newDb.getSolicitor("legalEagle"));
        assertNotNull("Buildy should still exist", newDb.getContractor("buildy"));
        assertEquals("Chat should still exist", 1, newDb.getChatsForUser("legalEagle").size());
    }

    @Test
    public void testClearDatabase() {
        db.clearDatabase();
        assertEquals("No solicitors should remain", 0, db.searchSolicitor("").size());
        assertEquals("No contractors should remain", 0, db.searchContractor("").size());
        assertEquals("No chats should remain", 0, db.getChats().size());
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