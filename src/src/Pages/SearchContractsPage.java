package Pages;

import Components.*;
import Components.Button;
import Database.Database;
import NetworkIO.Client;
import Objects.Contract;
import Objects.Solicitor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

        // Grab contracts from all solicitors with valid (future) deadlines
        List<Contract> contracts = new ArrayList<>();
        for (Solicitor s : Database.getInstance().getSolicitors()) {
            for (Contract c : s.getContractsSolicited()) {
                if (c.getDeadline().isAfter(LocalDateTime.now())) {
                    contracts.add(c);
                }
            }
        }

        // Render each valid contract as a card
        for (Contract contract : contracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(contract.getContractDescription()), BorderLayout.CENTER);
            Button viewDetails = new Button("View", e -> new Pages.ContractDetailsPopup(client, contract), new Dimension(300, 50));
            card.add(viewDetails, BorderLayout.EAST);
            listPanel.add(card);
            listPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        // Add Go Back button
        panel.add(new Margin(20));
        Button goBack = new Button("Go Back", e -> GUIWindow.getInstance().switchPage(new ContractorHomePage(client)), new Dimension(200, 50));
        panel.add(goBack);
        panel.add(new Margin(20));
    }
}
