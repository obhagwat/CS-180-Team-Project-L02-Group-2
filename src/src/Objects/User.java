package Objects;

import Interfaces.UserInterface;

import java.io.Serializable;
import java.util.Date;

/**
 * User Class: Contains all methods and fields pertaining to User objects, which are divided into
 * Solicitors and Contractors
 *  @author Ana Farmus, Lab sec 02
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */
public class User implements Serializable, UserInterface {
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

    public User(String username, String password, double rating, String countryOfOrigin, String address, String email, String phoneNumber) {
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

    public int getNumOfRatings() { return numOfRatings; }

    public void setNumOfRatings(int numOfRatings) { this.numOfRatings = numOfRatings; }

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

    /**
     * Deletes user account by setting all fields to null or 0
     */
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

    /**
     * Allows a user to send a message to another user in a chat
     *
     * @param chat      The chat which the message is being sent in
     * @param text      The message being sent
     */
    public void sendMessage(Chat chat, String text) {
        chat.addMessage(new Message(new Date(), text, this, chat.getUser1().equals(this) ? chat.getUser2() : chat.getUser1()));
    }

    /**
     * Adds a review for another user and calculates their new average rating
     *
     * @param user      The other user which is being rated
     * @param rating    The rating being given to the other user
     */
    public void postReview(User user, int rating) {
        user.calcRating(rating);
    }

    /**
     * When a user receives a new rating, re-calculates their average rating
     *
     * @param newRating     The new rating to be calculated in to the average
     */
    public void calcRating(int newRating) {
        numOfRatings++;
        rating = (rating * (numOfRatings - 1) + newRating) / numOfRatings;
        calcIntRating();
    }

    /**
     * Every time the user's rating is modified, this method should be called to round to the nearest integer
     */
    public void calcIntRating() {
        intRating = (int) Math.round(rating);
    }

    @Override
    public String toString() {
        return String.format("User [Username: %s, Password: %s, Rating: %d, Number of Ratings: %d, Country: %s, Address: %s, Email: %s, Phone Number: %s, Balance: $%.2f]",
                username, password, intRating, numOfRatings, countryOfOrigin, address, email, phoneNumber, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

}
