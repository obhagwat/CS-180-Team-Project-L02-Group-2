package Pages;

import Components.*;
import Components.Button;
import Components.TextField;
import Database.Database;
import NetworkIO.Client;
import Objects.Contract;
import Objects.Solicitor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchContractsPage extends Page {
    private JPanel listPanel;
    private List<Contract> allContracts;

    public SearchContractsPage(Client client) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        BlueLabel heading = new BlueLabel("Available Contracts", 24, 0);
        panel.add(new Margin(20));
        panel.add(heading);

        // 🔍 Search field
        JPanel searchBar = new JPanel();
        searchBar.setLayout(new BoxLayout(searchBar, BoxLayout.X_AXIS));
        TextField searchInput = new TextField("Search by title or description", new Dimension(400, 50));
        Button searchButton = new Button("Search", e -> renderFilteredContracts(searchInput.getText()), new Dimension(100, 30));
        searchBar.add(searchInput);
        searchBar.add(Box.createHorizontalStrut(10));
        searchBar.add(searchButton);
        panel.add(searchBar);
        panel.add(new Margin(20));

        // 🔄 Load all contracts with valid deadlines
        allContracts = new ArrayList<>();
        for (Solicitor s : Database.getInstance().getSolicitors()) {
            for (Contract c : s.getContractsSolicited()) {
                if (c.getDeadline().isAfter(LocalDateTime.now())) {
                    allContracts.add(c);
                }
            }
        }

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        // ⬅️ Go back
        panel.add(new Margin(20));
        Button goBack = new Button("Go Back", e -> GUIWindow.getInstance().switchPage(new ContractorHomePage(client)), new Dimension(200, 50));
        panel.add(goBack);
        panel.add(new Margin(20));

        // Initial full list render
        renderFilteredContracts("");
    }

    private void renderFilteredContracts(String query) {
        listPanel.removeAll();
        String lowercaseQuery = query.toLowerCase();

        List<Contract> filtered = allContracts.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(lowercaseQuery) ||
                        c.getContractDescription().toLowerCase().contains(lowercaseQuery))
                .collect(Collectors.toList());

        for (Contract contract : filtered) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(contract.getContractDescription()), BorderLayout.CENTER);
            Button viewDetails = new Button("View and Bid", e -> new ContractDetailsPopup(client, contract), new Dimension(300, 50));
            card.add(viewDetails, BorderLayout.EAST);
            listPanel.add(card);
            listPanel.add(new Margin(10));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }
}
