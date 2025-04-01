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
 *  @version Mar 26, 2025
 */
public class Contractor extends User {
    private String contractorName;
    private String companyType;
    private String numEmployees;
    private int yearFounded;
    private String industry;
    private ArrayList<Contract> contractsWon;   //Contracts for which they were selected
    private ArrayList<Bid> publicBids;  // These are visible to anyone
    private ArrayList<Bid> allBids;  //These are only visible to the contractor

}
