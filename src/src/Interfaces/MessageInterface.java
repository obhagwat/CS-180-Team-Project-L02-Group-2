package Interfaces;

import Objects.Bid;
import Objects.User;

import java.util.Date;

/**
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface MessageInterface {
    Date getTimeStamp();
    String getText();
    User getSender();
    User getRecipient();
    Bid getBid();

    boolean verifyMessage();
}
