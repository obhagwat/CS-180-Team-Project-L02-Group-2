package Pages;

import Components.Button;
import Components.GUIWindow;
import Database.Database;
import NetworkIO.Client;
import Objects.Bid;
import Objects.Contract;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewBidsPopup extends JDialog {
    public ViewBidsPopup(Client client, Contract contract) {
        super((Frame) null, "Submitted Bids", true);
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);

        List<Bid> bids = contract.getBids();

        JPanel bidsPanel = new JPanel();
        bidsPanel.setLayout(new BoxLayout(bidsPanel, BoxLayout.Y_AXIS));

        if (bids.isEmpty()) {
            bidsPanel.add(new JLabel("No Bids Yet!"));
        } else {
            for (Bid bid : bids) {
                JPanel bidCard = new JPanel(new BorderLayout());
                bidCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                String info = bid.getContractor().getContractorName() + " - $" + bid.getRequestedPay();
                bidCard.add(new JLabel(info), BorderLayout.CENTER);

                Button chooseWinner = new Button("Choose Winner", e -> {
                    contract.setWinningBid(bid);
                    bid.setStatus("Accepted");
                    Database.getInstance().serializeDatabase();
                    JOptionPane.showMessageDialog(this, "Winner selected: " + bid.getContractor().getContractorName());
                    dispose();
                    GUIWindow.getInstance().switchPage(new SolicitorHomePage(client));
                }, new Dimension(150, 30));
                bidCard.add(chooseWinner, BorderLayout.EAST);

                bidsPanel.add(bidCard);
                bidsPanel.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scrollPane = new JScrollPane(bidsPanel);
        add(scrollPane, BorderLayout.CENTER);

        Button closeButton = new Button("Close", e -> dispose(), new Dimension(100, 30));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
