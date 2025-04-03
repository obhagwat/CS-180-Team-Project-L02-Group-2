package Objects;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;


/**
 * Solicitor Class: Contains all methods and fields pertaining to Solicitor objects; Solicitors are entities
 * posting contracts, and asking for bids from Contractors.
 *  @author Ana Farmus, Lab sec 02
 *  @author Saahil Kajarekar, Lab sec 2
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Solicitor extends User implements Serializable {
    private String solicitorName;
    private String agencyLevel;
    private String branch;
    private String subBranch;
    private ArrayList<Contract> contractsSolicted;  //All contracts this agency has posted
    private ArrayList<Contract> openContracts;  // Contracts which are still "up for grabs"

    public Solicitor(String username, String password, double rating, String countryOfOrigin, String address, String email, String phoneNumber,
                     String solicitorName, String agencyLevel, String branch, String subBranch, double money) {
        super(username, password, rating, countryOfOrigin, address, email, phoneNumber);
        this.solicitorName = solicitorName;
        this.agencyLevel = agencyLevel;
        this.branch = branch;
        this.subBranch = subBranch;
        contractsSolicted = new ArrayList<>();
        openContracts = new ArrayList<>();
    }
    public String getSolicitorName() {
        return solicitorName;
    }

    public void setSolicitorName(String solicitorName) {
        this.solicitorName = solicitorName;
    }

    public String getAgencyLevel() {
        return agencyLevel;
    }

    public void setAgencyLevel(String agencyLevel) {
        this.agencyLevel = agencyLevel;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubBranch() {
        return subBranch;
    }

    public void setSubBranch(String subBranch) {
        this.subBranch = subBranch;
    }

    public ArrayList<Contract> getContractsSolicted() {
        return contractsSolicted;
    }

    public void setContractsSolicted(ArrayList<Contract> contractsSolicted) {
        this.contractsSolicted = contractsSolicted;
    }

    public ArrayList<Contract> getOpenContracts() {
        return openContracts;
    }

    public void setOpenContracts(ArrayList<Contract> openContracts) {
        this.openContracts = openContracts;
    }

    /**
     * Posts a contract by adding it to the solicitor's list of contracts.
     * @param contractDescription Description of the contract.
     * @param contractStatus Status of the contract (true for open, false for closed).
     * @param deadline The deadline for bid submissions.
     * @param bids The list of initial bids (can be empty).
     */
    public void postContract(String contractDescription, boolean contractStatus, LocalDateTime deadline, ArrayList<Bid> bids) {
        // Create a new contract
        Contract newContract = new Contract(this, contractDescription, contractStatus, deadline, bids);

        // Add the contract to the solicitor's list of contracts
        contractsSolicted.add(newContract);

        // If the contract is open, add it to openContracts as well
        if (contractStatus) {
            openContracts.add(newContract);
        }
    }

    /**
     * makes a payment to contractor according to a bid using the super user methods
     */
    public void makePayment(Bid bid) {
        super.setBalance(super.getBalance() - bid.getRequestedPay());
    }
//    /**
//     * selects the winning bid for the contract, based on on what the user enters hold off on the code for now
//     * @param contract  the contract that wins the bid
//     */
//    public void selectWinningBid(Bid bid){
//
//    }
}
