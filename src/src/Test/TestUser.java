package Test;

import Objects.*;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
public class TestUser {
    private User user;

    @Before
    public void setUp() {
        user = new User("user1", "pass1", 0.0, "USA", "123 St", "user1@test.com", "1111111111");
    }

    @Test
    public void constructorTest() {
        assertNotNull("User object should be created", user);
        assertEquals("Username should match", "user1", user.getUsername());
        assertEquals("Password should match", "pass1", user.getPassword());
        assertEquals("Rating should be 0 initially", 0.0, user.getRating(), 0.001);
        assertEquals("Country should match", "USA", user.getCountryOfOrigin());
        assertEquals("Address should match", "123 St", user.getAddress());
        assertEquals("Email should match", "user1@test.com", user.getEmail());
        assertEquals("Phone number should match", "1111111111", user.getPhoneNumber());
        assertEquals("Balance should be 0 initially", 0.0, user.getBalance(), 0.001);
    }

    @Test
    public void gettersAndSettersTest() {
        user.setUsername("newUser");
        user.setPassword("newPass");
        user.setCountryOfOrigin("UK");
        user.setAddress("456 St");
        user.setEmail("new@test.com");
        user.setPhoneNumber("2222222222");
        user.setBalance(100.0);

        assertEquals("Username should be updated", "newUser", user.getUsername());
        assertEquals("Password should be updated", "newPass", user.getPassword());
        assertEquals("Country should be updated", "UK", user.getCountryOfOrigin());
        assertEquals("Address should be updated", "456 St", user.getAddress());
        assertEquals("Email should be updated", "new@test.com", user.getEmail());
        assertEquals("Phone number should be updated", "2222222222", user.getPhoneNumber());
        assertEquals("Balance should be updated", 100.0, user.getBalance(), 0.001);
    }

    @Test
    public void ratingCalculationTest() {
        user.calcRating(5);
        assertEquals("Rating should be 5 after one 5-star rating", 5.0, user.getRating(), 0.001);
        assertEquals("Integer rating should be 5", 5, user.getIntRating());

        user.calcRating(3);
        assertEquals("Rating should be 4 after 5 and 3", 4.0, user.getRating(), 0.001);
        assertEquals("Integer rating should be 4", 4, user.getIntRating());

        user.calcRating(1);
        assertEquals("Rating should be 3 after 5, 3, and 1", 3.0, user.getRating(), 0.001);
        assertEquals("Integer rating should be 3", 3, user.getIntRating());
    }

    @Test
    public void deleteAccountTest() {
        user.deleteAccount();
        assertNull("Username should be null", user.getUsername());
        assertNull("Password should be null", user.getPassword());
        assertEquals("Rating should be 0", 0.0, user.getRating(), 0.001);
        assertNull("Country should be null", user.getCountryOfOrigin());
        assertNull("Address should be null", user.getAddress());
        assertNull("Email should be null", user.getEmail());
        assertNull("Phone number should be null", user.getPhoneNumber());
        assertEquals("Balance should be 0", 0.0, user.getBalance(), 0.001);
    }

    @Test
    public void sendMessageTest() {
        User recipient = new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222");
        Chat chat = new Chat(user, recipient);

        user.sendMessage(chat, "Hello");
        assertEquals("Chat should have 1 message", 1, chat.getMessages().size());
        assertEquals("Message content should match", "Hello", chat.getMessages().getFirst().getText());
    }

    @Test
    public void postReviewTest() {
        User otherUser = new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222");
        user.postReview(otherUser, 5);
        assertEquals("Other user's rating should be updated", 5.0, otherUser.getRating(), 0.001);
    }

    @Test
    public void toStringTest() {
        String expected = "User [Username: user1, Password: pass1, Rating: 0, Number of Ratings: 0, " +
                "Country: USA, Address: 123 St, Email: user1@test.com, Phone Number: 1111111111, Balance: $0.00]";
        assertEquals("toString should match expected format", expected, user.toString());
    }

    @Test
    public void equalsTest() {
        User sameUser = new User("user1", "differentPass", 0.0, "Different", "Different",
                "different@test.com", "9999999999");
        User differentUser = new User("user2", "pass2", 0.0, "UK", "456 St", "user2@test.com", "2222222222");

        assertEquals("Users with same username should be equal", user, sameUser);
        assertNotEquals("Users with different usernames should not be equal", user, differentUser);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestUser.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            result.getFailures().forEach(failure -> System.out.println(failure.toString()));
        }
    }
}