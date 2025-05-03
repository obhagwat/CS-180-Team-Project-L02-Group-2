//// ViewBidsPopup.java
//package Pages;
//
//import NetworkIO.Client;
//import Objects.Bid;
//import Objects.Contract;
//
//import javax.swing.*;
//import java.util.List;
//
//public class ViewBidsPopup {
//    public ViewBidsPopup(Client client, Contract contract) {
//        List<Bid> bids = client.getSolicitor().getContractsSolicited();
//        StringBuilder sb = new StringBuilder();
//        for (Bid bid : bids) {
//            sb.append(bid.getContractor().getContractorName()).append(" - $").append(bid.getRequestedPay()).append("\n");
//        }
//        JOptionPane.showMessageDialog(null, sb.toString(), "Submitted Bids", JOptionPane.INFORMATION_MESSAGE);
//    }
//}
