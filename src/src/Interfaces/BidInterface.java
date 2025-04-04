package Interfaces;

import Objects.Contract;
import Objects.Contractor;

/**
 *  Bid Interface: Interface for Bid class
 *  @author Sarah Stone, Lab sec 02
 *  @author
 *  @author
 *  @author
 *
 *  @version Apr --, 2025
 */

public interface BidInterface {
    Contractor getContractor();
    Contract getContract();
    double getRequestedPay();
    String getStatus();
    boolean isAccepted();

    void setContractor(Contractor contractor);
    void setContract(Contract contract);
    void setRequestedPay(double requestedPay);
    void setStatus(String status);
}
