import java.util.ArrayList;

/**
 * Solicitor Class: Contains all methods and fields pertaining to Solicitor objects; Solicitors are entities
 * posting contracts, and asking for bids from Contractors.
 *  @author Ana Farmus, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Mar 26, 2025
 */
public class Solicitor extends User {
    private String solicitorID;
    private String solicitorName;
    private String agencyLevel;
    private String branch;
    private String subBranch;
    private ArrayList<Contract> contractsSolicted;  //All contracts this agency has posted
    private ArrayList<Contract> openContracts;  // Contracts which are still "up for grabs"

}
