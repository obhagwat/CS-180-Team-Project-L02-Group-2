// ContractorHomePage.java
package Pages;

import Components.*;
import Components.Button;
import NetworkIO.Client;
import Objects.Bid;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContractorHomePage extends Page {
    public ContractorHomePage(Client client) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        BlueLabel heading = new BlueLabel("Your Applied Contracts", 24, 0);
        panel.add(new Margin(20));
        panel.add(heading);

        JPanel contractsPanel = new JPanel();
        contractsPanel.setLayout(new BoxLayout(contractsPanel, BoxLayout.Y_AXIS));

        List<Bid> appliedContracts = client.getContractor().getAllBids();
        for (Bid bid : appliedContracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(bid.getContract().getContractDescription() + " - Status: " + bid.getStatus()), BorderLayout.CENTER);
            contractsPanel.add(card);
            contractsPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(contractsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        Button searchButton = new Button("Search Contracts", e -> window.switchPage(new SearchContractsPage(client)), new Dimension(30,10));
        panel.add(new Margin(20));
        panel.add(searchButton);
    }
}