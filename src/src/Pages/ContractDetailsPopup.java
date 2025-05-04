package Pages;

import NetworkIO.Client;
import Objects.Contract;

import javax.swing.*;

public class ContractDetailsPopup {
    public ContractDetailsPopup(Client client, Contract contract) {
        JTextField bidAmount = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Contract Title: " + contract.getContractDescription()));
        panel.add(new JLabel("Deadline: " + contract.getDeadline()));
        panel.add(new JLabel("Enter Bid Amount:"));
        panel.add(bidAmount);

        int result = JOptionPane.showConfirmDialog(null, panel, "Contract Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int amount = Integer.parseInt(bidAmount.getText());
                client.getContractor().replyWithBid(contract, amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid bid amount.");
            }
        }
    }
}

