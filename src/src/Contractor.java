import java.util.ArrayList;

/**
 * Contractor Class: Contains all methods and fields pertaining to Contractor objects, which are entities
 * offering bids to complete contracts
 *
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */
public class Contractor extends User {
    private String contractorName;
    private String companyType;
    private String numEmployees;
    private int yearFounded;
    private String industry;
    private ArrayList<Contract> contractsWon;   //Contracts for which they were selected
    private ArrayList<Bid> allBids;  //These are only visible to the contractor

    public Contractor(String username, String password, double rating, String countryOfOrigin, String address, String email, String phoneNumber,
                      String contractorName, String companyType, String numEmployees, int yearFounded, String industry, String country) {
        super(username, password, rating, countryOfOrigin, address, email, phoneNumber);
        this.contractorName = contractorName;
        this.companyType = companyType;
        this.numEmployees = numEmployees;
        this.yearFounded = yearFounded;
        this.industry = industry;
        this.contractsWon = new ArrayList<>();
        this.allBids = new ArrayList<>();
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(String numEmployees) {
        this.numEmployees = numEmployees;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public ArrayList<Contract> getContractsWon() {
        return contractsWon;
    }

    public void setContractsWon(ArrayList<Contract> contractsWon) {
        this.contractsWon = contractsWon;
    }

    public ArrayList<Bid> getAllBids() {
        return allBids;
    }

    public void setAllBids(ArrayList<Bid> allBids) {
        this.allBids = allBids;
    }
}
