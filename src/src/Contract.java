import java.util.ArrayList;

/**
 * Contract Class: Contains all methods and fields pertaining to contract objects, which are contract solicitations
 * posted by Solicitors.
 *
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Mar 26, 2025
 */
public class Contract {
    private Solicitor solicitor; // The solicitor who posted the contract
    private String contractDescription;
    private boolean contractStatus; //Whether the contract is still "up for grabs", true = you can bid on it
    private String deadline; // The "due date" for bids
    private ArrayList<Bid> bids;    // The list of bids on this contract
}
