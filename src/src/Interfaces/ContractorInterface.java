package Interfaces;

import Objects.Bid;
import Objects.Contract;
import Objects.Industry;

import java.util.ArrayList;

/**
 *  Contractor Interface: Interface for Contractor class
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface ContractorInterface {
    String getContractorName();
    String getCompanyType();
    String getNumEmployees();
    String getYearFounded();
    Industry getIndustry();
    ArrayList<Contract> getContractsWon();
    ArrayList<Bid> getAllBids();

    void setContractorName(String contractorName);
    void setCompanyType(String companyType);
    void setNumEmployees(String numEmployees);
    void setYearFounded(String yearFounded);
    void setIndustry(Industry industry);
    void setContractsWon(ArrayList<Contract> contractsWon);
    void setAllBids(ArrayList<Bid> allBids);

    void recievePayment(Bid bid);
    void replyWithBid(Contract contract, double bidAmount);
    void addToContractsWon(Contract contract);
    ArrayList<Bid> bidsUnderReview();
}
