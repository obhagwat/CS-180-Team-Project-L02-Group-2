// SearchContractsPage.java
package Pages;

import Components.*;
import Components.Button;
import NetworkIO.Client;
import Objects.Contract;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchContractsPage extends Page {
    public SearchContractsPage(Client client) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        BlueLabel heading = new BlueLabel("Available Contracts", 24, 0);
        panel.add(new Margin(20));
        panel.add(heading);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        List<Contract> contracts = client.getSolicitor().getOpenContracts();
        for (Contract contract : contracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(contract.getContractDescription()), BorderLayout.CENTER);
            Button viewDetails = new Button("View", e -> new Pages.ContractDetailsPopup(client, contract), new Dimension(30,10));
            card.add(viewDetails, BorderLayout.EAST);
            listPanel.add(card);
            listPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);
    }
}

