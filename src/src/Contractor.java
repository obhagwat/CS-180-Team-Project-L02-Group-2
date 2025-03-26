import java.util.ArrayList;

/**
 * Contractor Class: Contains all methods and fields pertaining to Contractor objects, which are entities
 * offering bids to complete contracts
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Mar 26, 2025
 */
public class Contractor extends User {
    private String contractorID;
    private String contractorName;
    private String phone;
    private String companyType;
    private String numEmployees;
    private int yearFounded;
    private String industry;
    // array of govts with which the contractor is registered
    // array of govt ID Nums for those govts
    private ArrayList<Contract> contractsWon;   //Contracts for which they were selected
    private ArrayList<Bid> publicBids;  // These are visible to anyone
    private ArrayList<Bid> allBids;  //These are only visible to the contractor
    private String link2Certs;      // This will be the link to the contractor's portfolio/website that appears on their profile




//    private ArrayList<Contract> prev;

}
