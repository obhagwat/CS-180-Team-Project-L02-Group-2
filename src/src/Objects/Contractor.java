package Objects;

import Database.Database;
import Interfaces.ContractorInterface;

import java.util.ArrayList;
import java.io.Serializable;


/**
 * Contractor Class: Contains all methods and fields pertaining to Contractor objects, which are entities
 * offering bids to complete contracts
 *
 * @author Ana Farmus, Lab sec 02
 * @author Saahil Kajarekar, Lab sec 2
 * @author Sarah Stone
 * @version Apr 6, 2025
 */
public class Contractor extends User implements Serializable, ContractorInterface {
    private String contractorName;
    private String companyType;
    private String numEmployees;
    private String yearFounded;
    private Industry industry;
    private ArrayList<Contract> contractsWon;   //Contracts for which they were selected
    private ArrayList<Bid> allBids; //These are only visible to the contractor

    public Contractor(String username, String password,
                      double rating, String countryOfOrigin, String address, String email,
                      String phoneNumber, String contractorName, String companyType,
                      String numEmployees, String yearFounded, Industry industry) {
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

    public String getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(String yearFounded) {
        this.yearFounded = yearFounded;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
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

    /**
     * recieves a payment from Solictor according to a bid using the super user methhods
     */
    public void recievePayment(Bid bid) {
        super.setBalance(super.getBalance() + bid.getRequestedPay());

    }

    /**
     * adds a new big to all bids for a contract
     *
     * @param contract  to be bid on
     * @param bidAmount the requested pay for the job
     */
    public void replyWithBid(Contract contract, double bidAmount) {
        allBids.add(new Bid(this, contract, bidAmount, "Under Consideration"));
        contract.addBid(new Bid(this, contract, bidAmount, "Under Consideration"));
        Database.getInstance().serializeDatabase();
    }

    /**
     * adds a contract to the contracts won arrayList
     *
     * @param contract the contract to be added
     */
    public void addToContractsWon(Contract contract) {
        contractsWon.add(contract);
    }

    /**
     * returns a list of all bids under consideration
     *
     * @return an arraylist of all bids under consideration
     */
    public ArrayList<Bid> bidsUnderReview() {
        ArrayList<Bid> bidsUnderReview = new ArrayList<>();
        for (Bid bid : allBids) {
            String status = bid.getStatus();
            if (status.equals("Under Consideration")) {
                bidsUnderReview.add(bid);
            }
        }
        return bidsUnderReview;
    }

    @Override
    public String toString() {
        return String.format("Contractor [Name: %s, Company Type: %s, Employees: %s, Industry: %s, Contracts Won: %d]",
                contractorName, companyType, numEmployees, industry, contractsWon.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contractor contractor = (Contractor) o;
        return username.equals(contractor.username);
    }
}
