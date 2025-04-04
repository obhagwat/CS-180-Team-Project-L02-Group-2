package Interfaces;

import Objects.Bid;
import Objects.Solicitor;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *  Contract Interface: Interface for Contract class
 *  @author Sarah Stone, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */

public interface ContractInterface {
    Solicitor getSolicitor();
    String getContractDescription();
    boolean isContractStatus();
    LocalDateTime getDeadline();
    ArrayList<Bid> getBids();
    Bid getWinningBid();

    void setSolicitor(Solicitor solicitor);
    void setContractDescription(String contractDescription);
    void setContractStatus(boolean contractStatus);
    void setDeadline(LocalDateTime deadline);
    void setBids(ArrayList<Bid> bids);
    void setWinningBid(Bid winningBid);

    void addBid(Bid bid);
}
