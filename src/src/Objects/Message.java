package Objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;


/**
 * Contract Class: Contains all methods and fields pertaining to message objects
 *  @author Ana Farmus, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class Message implements Serializable {
    private Date timeStamp;   // Marks the time the message was sent
    private String text;    // message contents
    private User sender;
    private User recipient;
    private Bid bid;    //
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");


    public Message(Date timeStamp, String text, User sender, User recipient) {
        this.timeStamp = timeStamp;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Message(Date timeStamp, String text, User sender, User recipient, Bid bid) {
        this.timeStamp = timeStamp;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.bid = bid;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }


    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public Bid getBid() {
        return bid;
    }

    public boolean verifyMessage() {
        if (sender == null || recipient == null) {
            return false;
        }
        if (this.text == null || this.text.trim().isEmpty()) {
            return false;
        }
        if (this.text.length() > 500) {
            return false; // check message length
        }
        return true;
    }

    @Override
    public String toString() {
        if (recipient != null) {
            return String.format("[%s] From: %s To: %s - %s",
                    dateFormat.format(timeStamp), sender.getUsername(), recipient.getUsername(), text);
        }
        return String.format("[%s] From: %s - %s",
                dateFormat.format(timeStamp), sender.getUsername(), text);
    }

}
