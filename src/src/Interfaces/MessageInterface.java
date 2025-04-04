package Interfaces;

import Objects.Bid;
import Objects.User;

import java.util.Date;

/**
 *  Message Interface: Interface for Message class
 *  @author Sarah Stone, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */

public interface MessageInterface {
    Date getTimeStamp();
    String getText();
    User getSender();
    User getRecipient();
    Bid getBid();

    boolean verifyMessage();
}
