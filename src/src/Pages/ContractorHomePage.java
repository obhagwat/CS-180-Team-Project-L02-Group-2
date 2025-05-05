package Pages;

import Components.*;
import Components.Button;
import NetworkIO.Client;
import Objects.Bid;
import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContractorHomePage extends Page {
    public ContractorHomePage(Client client) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Top menu bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(0x7793C2));
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        Logo logoLabel = new Logo("Components/whiteLogo.png", 40, 40);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Contractor Home Page", SwingConstants.LEFT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topBar.add(logoLabel, BorderLayout.WEST);
        topBar.add(titleLabel, BorderLayout.CENTER);

        Button deleteAccountButton = new Button("Delete Account", e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete your account? This action cannot be undone.",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (client.getContractor() != null) {
                    Database.getInstance().deleteContractor(client.getContractor().getUsername());
                }
                Database.getInstance().serializeDatabase();
                GUIWindow.getInstance().switchPage(new LandingPage(client));
            }
        }, new Dimension(350, 40));
        deleteAccountButton.setContentAreaFilled(true);
        deleteAccountButton.setOpaque(true);
        deleteAccountButton.setBackground(Constants.WHITE);
        deleteAccountButton.setForeground(Constants.BREEZY_BLUE);
        topBar.add(deleteAccountButton, BorderLayout.EAST);

        panel.add(topBar);

        BlueLabel heading = new BlueLabel("Your Applied Contracts", 24, 0);
        panel.add(new Margin(20));
        panel.add(heading);

        JPanel contractsPanel = new JPanel();
        contractsPanel.setLayout(new BoxLayout(contractsPanel, BoxLayout.Y_AXIS));

        List<Bid> appliedContracts = client.getContractor().getAllBids();
        for (Bid bid : appliedContracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(bid.getContract().getContractDescription()), BorderLayout.WEST);
            card.add(new JLabel("Status: " + bid.getStatus()), BorderLayout.EAST);
            Button deleteBid = new Button("Withdraw Bid", e -> {
                client.getContractor().withdrawBid(bid);
                Database.getInstance().serializeDatabase();
                GUIWindow.getInstance().switchPage(new ContractorHomePage(client));
            }, new Dimension(150, 50));
            card.add(deleteBid, BorderLayout.SOUTH);

            contractsPanel.add(card);
            contractsPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(contractsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        Button searchButton = new Button("Search Contracts", e -> window.switchPage(new SearchContractsPage(client)), new Dimension(300,50));
        panel.add(new Margin(20));
        panel.add(searchButton);
    }
    public static void main(String[] args) {
        Client mockClient = Client.startMockClientContractorOnly();
        GUIWindow window = GUIWindow.getInstance();
        window.switchPage(new ContractorHomePage(mockClient));
    }

}