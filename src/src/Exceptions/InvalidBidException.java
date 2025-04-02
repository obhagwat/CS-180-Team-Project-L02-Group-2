package Exceptions;

/**
 * InvalidBid Exception: Contains invalid Bid objects
 *  @author Sarah Stone, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class InvalidBidException extends Exception {
    public InvalidBidException(String message) {
        super(message);
    }
}