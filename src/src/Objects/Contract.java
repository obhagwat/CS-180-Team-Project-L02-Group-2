package Objects;

import Interfaces.ContractInterface;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;


/**
 * Contract Class: Contains all methods and fields pertaining to contract objects, which are contract solicitations
 * posted by Solicitors.
 *
 *  @author Ana Farmus, Lab sec 02
 *  @author Saahil Kajarekar, Lab sec 2
 *  @author Sarah Stone*
 *  @version Apr 6, 2025
 */
public class Contract implements Serializable, ContractInterface {
    private Solicitor solicitor; // The solicitor who posted the contract
    private String contractDescription; // includes description of work needing to be done, and expected completion date
    private boolean contractStatus; //Whether the contract is still "up for grabs", true = you can bid on it, or open
    private LocalDateTime deadline; // The "due date" for bids
    private ArrayList<Bid> bids;    // The list of bids on this contract
    private Bid winningBid;

    public Contract(Solicitor solicitor, String contractDescription,
                    boolean contractStatus, LocalDateTime deadline, ArrayList<Bid> bids) {
        this.solicitor = solicitor;
        this.contractDescription = contractDescription;
        this.contractStatus = true; // constructor should initialize contract status to be open
        this.deadline = deadline;
        this.bids = new ArrayList<>();
        this.winningBid = null;
    }

    public Solicitor getSolicitor() {
        return solicitor;
    }

    public void setSolicitor(Solicitor solicitor) {
        this.solicitor = solicitor;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    public boolean isContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(boolean contractStatus) {
        this.contractStatus = contractStatus;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public Bid getWinningBid() { return winningBid; }

    public void setWinningBid(Bid winningBid) { this.winningBid = winningBid; }

    /**
     * Add a bid to the contract if the contract is open, otherwise throw an error
     * @param bid   The bid to add to the contract
     */
    public void addBid(Bid bid) {
        if (contractStatus) {  // Only allow adding bids if the contract is open
            bids.add(bid);
        } else {
            throw new IllegalStateException("Cannot add a bid to a closed contract.");
        }
    }

    @Override
    public String toString() {
        String bidList = bids.isEmpty() ? "No bids" : bids.toString(); // Converts the list of bids to a string
        return String.format("Contract [Solicitor: %s, Description: %s, Status: %s, Deadline: %s, Bids: %s]",
                solicitor,
                contractDescription,
                contractStatus ? "Open" : "Closed",
                deadline,
                bidList);
    }
}
