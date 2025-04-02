import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.text.SimpleDateFormat;


/**
 * Contract Class: Contains all methods and fields pertaining to message objects
 *  @author Ana Farmus, Lab sec 02
 *
 *  @version Apr --, 2025
 */
public class Message implements Serializable {
    private LocalDateTime timeStamp;   // Marks the time the message was sent
    private String text;    // message contents
    private User sender;
    private User recipient;
    private Bid bid;    //
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");

    public Message(LocalDateTime timeStamp, String text, User sender, User recipient, Bid bid) {
        this.timeStamp = timeStamp;
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.bid = bid;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
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
}
