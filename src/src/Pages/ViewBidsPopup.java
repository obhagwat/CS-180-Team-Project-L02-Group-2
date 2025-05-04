package Pages;

import NetworkIO.Client;
import Objects.Bid;
import Objects.Contract;

import javax.swing.*;
import java.util.List;

public class ViewBidsPopup {
    public ViewBidsPopup(Client client, Contract contract) {
        List<Bid> bids = contract.getBids();
        StringBuilder sb = new StringBuilder();
        for (Bid bid : bids) {
            sb.append(bid.getContractor().getContractorName()).append(" - $").append(bid.getRequestedPay()).append("\n");
        }
        if(bids.isEmpty()){
            sb.append("No Bids Yet!");
            JOptionPane.showMessageDialog(null, sb.toString(), "No Bids Yet!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, sb.toString(), "Submitted Bids", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
