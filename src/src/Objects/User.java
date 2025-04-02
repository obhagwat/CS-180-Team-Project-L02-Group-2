package Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * User Class: Contains all methods and fields pertaining to User objects, which are divided into
 * Solicitors and Contractors
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class User implements Serializable {
    protected String username;
    protected String password;
    protected double rating;
    protected int intRating;
    protected int numOfRatings;
    // How reputable they are.
    // only a winning bidder can rate a solicitor and solicitors can only rate their chosen bidder.
    protected String countryOfOrigin;
    protected String address;
    protected String email;
    protected String phoneNumber;
    protected double balance;    // When a payment is made, all we have to do is subtract or add
//example

    public User(String username, String password, String countryOfOrigin, String address, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.rating = 0;
        this.intRating = 0;
        this.numOfRatings = 0;
        this.countryOfOrigin = countryOfOrigin;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public int getIntRating() { return intRating; }

    public void setRating(double rating) {
        this.rating = rating;
        calcIntRating();
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deleteAccount() {
        username = null;
        password = null;
        rating = 0;
        intRating = 0;
        numOfRatings = 0;
        countryOfOrigin = null;
        address = null;
        email = null;
        phoneNumber = null;
        balance = 0;
    }

    public void sendMessage(String text, User recipient) {
        new Message(new Date(), text, this, recipient);
    }

    public void sendMessage(String text, User recipient, Bid bid) {
        new Message(new Date(), text, this, recipient, bid);
    }

    public void postReview(User user, double rating) {
        user.calcRating(rating);
    }

    public void calcRating(double newRating) {
        numOfRatings++;
        rating = (rating * (numOfRatings - 1) + newRating) / numOfRatings;
        calcIntRating();
    }

    public void calcIntRating() {
        intRating = (int) Math.round(rating);
    }

    @Override
    public String toString() {
        return String.format("User [Username: %s, Password: %s, Rating: %d, Number of Ratings: %d, Country: %s, Address: %s, Email: %s, Phone Number: %s, Balance: $%.2f]",
                username, password, intRating, numOfRatings, countryOfOrigin, address, email, phoneNumber, balance);
    }

}
