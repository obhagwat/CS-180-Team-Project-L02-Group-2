package Interfaces;

import Objects.Chat;
import Objects.User;

/**
 * User Interface: Interface for User class
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface UserInterface {
    String getUsername();
    String getPassword();
    double getRating();
    int getIntRating();
    int getNumOfRatings();
    String getCountryOfOrigin();
    String getAddress();
    String getEmail();
    String getPhoneNumber();
    double getBalance();

    void setUsername(String username);
    void setPassword(String password);
    void setRating(double rating);
    void setNumOfRatings(int numOfRatings);
    void setCountryOfOrigin(String countryOfOrigin);
    void setAddress(String address);
    void setEmail(String email);
    void setPhoneNumber(String phoneNumber);
    void setBalance(double balance);

    void deleteAccount();
    void sendMessage(Chat chat, String message);
    void postReview(User user, int rating);
    void calcRating(int newRating);
    void calcIntRating();
}
