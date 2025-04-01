import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Contract Class: Contains all methods and fields pertaining to contract objects, which are contract solicitations
 * posted by Solicitors.
 *
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Contract {
    private Solicitor solicitor; // The solicitor who posted the contract
    private String contractDescription; // includes description of work needing to be done, and expected completion date
    private boolean contractStatus; //Whether the contract is still "up for grabs", true = you can bid on it
    private LocalDateTime deadline; // The "due date" for bids
    private ArrayList<Bid> bids;    // The list of bids on this contract

    public Contract(Solicitor solicitor, String contractDescription) {
        this.solicitor = solicitor;
        this.contractDescription = contractDescription;
        this.contractStatus = false;
        this.deadline = LocalDateTime.now();
        this.bids = new ArrayList<>();
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
}
