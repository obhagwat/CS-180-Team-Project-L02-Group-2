package Interfaces;

import Objects.User;

import java.time.LocalDateTime;

/**
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface NotificationInterface {
    String getMessage();
    LocalDateTime getTimestamp();
    User getRecipient();
}
