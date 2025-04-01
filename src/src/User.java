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
public class User {
    protected String username;
    protected String password;
    protected double rating;      // How reputable they are.
    // only a winning bidder can rate a solicitor and solicitors can only rate their chosen bidder.
    protected String countryOfOrigin;
    protected String address;
    protected String email;
    protected String phoneNumber;
    protected double balance;    // When a payment is made, all we have to do is subtract or add


    public User(String username, String password, double rating, String countryOfOrigin, String address, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.rating = rating;
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

    public void setRating(double rating) {
        this.rating = rating;
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
}
