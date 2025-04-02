package Objects;
import java.io.Serializable;

/**
 * Bid Class: Contains all methods and fields pertaining to bid objects, which are contract proposals
 * offered by contractors.
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Bid implements Serializable {
    private Contractor contractor;  // The contractor bidding the bid
    private Contract contract;  // The contract this bid is on
    private double requestedPay; //Contractor's asking price
    private String status;  // Rejected, Accepted, Under Consideration

    public Bid(Contractor contractor, Contract contract, double requestedPay, String status) {
        this.contractor = contractor;
        this.contract = contract;
        this.requestedPay = requestedPay;
        this.status = status;
    }
    //sarahs test comment

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public double getRequestedPay() {
        return requestedPay;
    }

    public void setRequestedPay(double requestedPay) {
        this.requestedPay = requestedPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Bid [Contractor: %s, Contract: %s, Requested Pay: $%.2f, Status: %s]",
                contractor, contract, requestedPay, status);
    }

}
