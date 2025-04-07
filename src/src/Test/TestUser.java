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
        user = new User("annapaul", "annabanana", 0.0, "USA",
                "1111 3rd St", "annap@gmail.com", "2064448019");
    }

    @Test
    public void constructorTest() {
        assertNotNull("User object should be created", user);
        assertEquals("Username should match", "annapaul", user.getUsername());
        assertEquals("Password should match", "annabanana", user.getPassword());
        assertEquals("Rating should be 0 initially", 0.0, user.getRating(), 0.0001);
        assertEquals("Country should match", "USA", user.getCountryOfOrigin());
        assertEquals("Address should match", "1111 3rd St", user.getAddress());
        assertEquals("Email should match", "annap@gmail.com", user.getEmail());
        assertEquals("Phone number should match", "2064448019", user.getPhoneNumber());
        assertEquals("Balance should be 0 initially", 0.0, user.getBalance(), 0.0001);
    }

    @Test
    public void gettersAndSettersTest() {
        user.setUsername("johnsnow");
        user.setPassword("gameofthrones");
        user.setCountryOfOrigin("USA");
        user.setAddress("400 Apple Ave");
        user.setEmail("johnsnow@yahoo.com");
        user.setPhoneNumber("9048927493");
        user.setBalance(4500.00);

        assertEquals("Username should be updated", "johnsnow", user.getUsername());
        assertEquals("Password should be updated", "gameofthrones", user.getPassword());
        assertEquals("Country should be updated", "USA", user.getCountryOfOrigin());
        assertEquals("Address should be updated", "400 Apple Ave", user.getAddress());
        assertEquals("Email should be updated", "johnsnow@yahoo.com", user.getEmail());
        assertEquals("Phone number should be updated", "9048927493", user.getPhoneNumber());
        assertEquals("Balance should be updated", 4500.00, user.getBalance(), 0.0001);
    }

    @Test
    public void ratingCalculationTest() {
        user.calcRating(5);
        assertEquals("Rating should be 5 after one 5-star rating", 5.0, user.getRating(), 0.0001);
        assertEquals("Integer rating should be 5", 5, user.getIntRating());

        user.calcRating(3);
        assertEquals("Rating should be 4 after 5 and 3", 4.0, user.getRating(), 0.0001);
        assertEquals("Integer rating should be 4", 4, user.getIntRating());

        user.calcRating(1);
        assertEquals("Rating should be 3 after 5, 3, and 1", 3.0, user.getRating(), 0.0001);
        assertEquals("Integer rating should be 3", 3, user.getIntRating());
    }

    @Test
    public void deleteAccountTest() {
        user.deleteAccount();
        assertNull("Username should be null", user.getUsername());
        assertNull("Password should be null", user.getPassword());
        assertEquals("Rating should be 0", 0.0, user.getRating(), 0.0001);
        assertNull("Country should be null", user.getCountryOfOrigin());
        assertNull("Address should be null", user.getAddress());
        assertNull("Email should be null", user.getEmail());
        assertNull("Phone number should be null", user.getPhoneNumber());
        assertEquals("Balance should be 0", 0.0, user.getBalance(), 0.0001);
    }

    @Test
    public void sendMessageTest() {
        User recipient = new User("lizzysmith", "ilovelizards", 0.0, "USA",
                "3012 Reptile Ct", "lizsmith4@outlook.com", "2904738493");
        Chat chat = new Chat(user, recipient);

        user.sendMessage(chat, "Hello");
        assertEquals("Chat should have 1 message", 1, chat.getMessages().size());
        assertEquals("Message content should match", "Hello", chat.getMessages().getFirst().getText());
    }

    @Test
    public void postReviewTest() {
        User otherUser = new User("bradenwren", "soccer4lyfe", 0.0, "USA",
                "1302 211th St", "brady13@gmail.com", "3385927492");
        user.postReview(otherUser, 5);
        assertEquals("Other user's rating should be updated", 5.0, otherUser.getRating(), 0.0001);
    }

    @Test
    public void toStringTest() {
        String expected = "User [Username: annapaul, Password: annabanana, Rating: 0, " +
                "Number of Ratings: 0, Country: USA, Address: 1111 3rd St, " +
                "Email: annap@gmail.com, Phone Number: 2064448019, Balance: $0.00]";
        assertEquals("toString should match expected format", expected, user.toString());
    }

    @Test
    public void equalsTest() {
        User sameUser = new User("annapaul", "differentPass", 0.0, "Different",
                "Different", "different@test.com", "9999999999");
        User differentUser = new User("user2", "pass2", 0.0, "UK",
                "456 St", "user2@test.com", "2222222222");

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