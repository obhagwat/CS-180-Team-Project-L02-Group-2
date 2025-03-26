/**
 * User Class: Contains all methods and fields pertaining to User objects, which are divided into
 * Solicitors and Contractors
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Mar 26, 2025
 */
public class User {
    private String username;
    private String password;
    private double rating;      // How reputable they are.
    // only a winning bidder can rate a solicitor and solicitors can only rate their chosen bidder.
    private String countryOfOrigin;
    private String address;
    private String email;
    private boolean verified;   // Whether it is confirmed they are who they say they are
}
