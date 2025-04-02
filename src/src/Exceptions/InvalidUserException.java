package Exceptions;

/**
 * Invaliduser Exception: Contains invalid User objects
 *  @author Sarah Stone, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}