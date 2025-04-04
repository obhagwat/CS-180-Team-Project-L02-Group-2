package Objects;
import Interfaces.NotificationInterface;

import java.time.LocalDateTime;

/**
 * Notification Class: Contains all methods and fields pertaining to notification objects
 * these are for instant updates for bid status changes
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Notification implements NotificationInterface {
    private String message;
    private LocalDateTime timestamp;
    private User recipient;

    public Notification(String message, User recipient) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getRecipient() {
        return recipient;
    }
}

