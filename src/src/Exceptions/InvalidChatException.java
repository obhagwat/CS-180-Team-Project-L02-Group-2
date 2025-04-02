package Exceptions;

/**
 * InvalidChat Exception: Contains invalid Chat objects
 *
 * @author Sarah Stone, Lab sec 02
 * @version Apr --, 2025
 */
public class InvalidChatException extends Exception {
    public InvalidChatException(String message) {
        super(message);
    }
}