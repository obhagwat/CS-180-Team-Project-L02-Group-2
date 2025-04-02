package Objects;
import java.util.ArrayList;
import java.io.Serializable;


/**
 * Solicitor Class: Contains all methods and fields pertaining to Solicitor objects; Solicitors are entities
 * posting contracts, and asking for bids from Contractors.
 *  @author Ana Farmus, Lab sec 02
 *  @author
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
                     String solicitorName, String agencyLevel, String branch, String subBranch) {
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
}
