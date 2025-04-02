import java.time.LocalDateTime;
import java.io.Serializable;


/**
 * Contract Class: Contains all methods and fields pertaining to message objects
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Message implements Serializable {
    private LocalDateTime timeStamp;   // Marks the time the message was sent
    private String text;    // message contents
    private User sender;
    private User receiver;
    private Bid bid;    //

    public Message(LocalDateTime timeStamp, String text, User sender, User receiver, Bid bid) {
        this.timeStamp = timeStamp;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }
}
