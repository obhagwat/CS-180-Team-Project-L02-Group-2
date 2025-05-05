package Interfaces;

import Objects.Bid;
import Objects.Contract;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Solicitor Interface: Interface for Solicitor class
 *  @author Sarah Stone, Lab sec 02
 *  @version Apr 6, 2025
 */

public interface SolicitorInterface {
    String getSolicitorName();
    String getAgencyLevel();
    String getBranch();
    String getSubBranch();
    ArrayList<Contract> getContractsSolicited();
    ArrayList<Contract> getOpenContracts();

    void setSolicitorName(String solicitorName);
    void setAgencyLevel(String agencyLevel);
    void setBranch(String branch);
    void setSubBranch(String subBranch);
    void setContractsSolicited(ArrayList<Contract> contractsSolicited);
    void setOpenContracts(ArrayList<Contract> openContracts);

    void postContract(String title, String contractDescription, LocalDateTime deadline, ArrayList<Bid> bids);
    void makePayment(Bid bid);
    void closeContract(Contract contract);
}
