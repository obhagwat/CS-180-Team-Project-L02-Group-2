package Interfaces;

import Objects.User;

import java.time.LocalDateTime;

/**
 *  Notification Interface: Interface for Notification class
 *  @author Sarah Stone, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */

public interface NotificationInterface {
    String getMessage();
    LocalDateTime getTimestamp();
    User getRecipient();
}
