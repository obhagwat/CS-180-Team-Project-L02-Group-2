package Test;

import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import Database.Database;
import Objects.User;
import Objects.Chat;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * A program that tests User class
 *
 * <p>Purdue University -- CS18000 -- Spring 2025</p>
 *
 * @author Ovi Bhagwat
 * @version April 5, 2025
 */

@RunWith(JUnit4.class)
public class TestDatabase {
    private Database database;
    private ArrayList<User> testUsers;
    private ArrayList<Chat> testChats;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
//    public void setUp() {
//        testUsers = new ArrayList<>();
//        testUsers.add(new User("user1", "pass1", 0.0, "USA", "123 St", "user1@test.com", "1111111111"));
//        testUsers.add(new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222"));
//
//        testChats = new ArrayList<>();
//        testChats.add(new Chat(testUsers.get(0), testUsers.get(1)));
//
//        database = new Database(testUsers, testChats);
//    }

    @Test
    public void singletonInstanceTest() {
        Database firstInstance = Database.getInstance();
        Database secondInstance = Database.getInstance();
        assertSame("getInstance() should return the same instance", firstInstance, secondInstance);
    }

    @Test
    public void threadSafeSingletonTest() throws InterruptedException {
        class SingletonThread extends Thread {
            public Database db;

            @Override
            public void run() {
                db = Database.getInstance();
            }
        }

        SingletonThread[] threads = new SingletonThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SingletonThread();
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Database firstInstance = threads[0].db;
        for (int i = 1; i < threads.length; i++) {
            assertSame("All threads should get same instance", firstInstance, threads[i].db);
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDatabase.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}