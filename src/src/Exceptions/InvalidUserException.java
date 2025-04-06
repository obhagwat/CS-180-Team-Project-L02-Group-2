package Exceptions;

/**
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}