package Pages;

import Components.*;
import Components.Button;
import NetworkIO.Client;
import Objects.Contract;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SolicitorHomePage extends Page {
    public SolicitorHomePage(Client client) {
        super(client);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Top menu bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(0x7793C2));
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        Logo logoLabel = new Logo("Components/whiteLogo.png", 40, 40);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("Solicitor Home Page", SwingConstants.LEFT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topBar.add(logoLabel, BorderLayout.WEST);
        topBar.add(titleLabel, BorderLayout.CENTER);
        panel.add(topBar);

        //Section heading
        panel.add(new Margin(20));
        BlueLabel heading = new BlueLabel("Your Posted Contracts", 24, 0);
        panel.add(heading);

        //Contract listing
        JPanel contractsPanel = new JPanel();
        contractsPanel.setLayout(new BoxLayout(contractsPanel, BoxLayout.Y_AXIS));

        List<Contract> postedContracts = client.getSolicitor().getContractsSolicited();
        for (Contract contract : postedContracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(contract.getContractDescription()), BorderLayout.CENTER);
            Button viewBids = new Button("View Bids", e -> new ViewBidsPopup(client, contract), new Dimension(200,50));
            card.add(viewBids, BorderLayout.EAST);
            contractsPanel.add(card);
            contractsPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(contractsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        // Create contract button
        Button createButton = new Button("Create New Contract", e -> new CreateContractPopup(client), new Dimension(300,50));

        panel.add(Box.createVerticalGlue()); // pushes the button up from the bottom
        panel.add(new Margin(20));
        panel.add(createButton);
        panel.add(new Margin(20)); // optional: more breathing room below
    }

    public static void main(String[] args) {
        Client mockClient = Client.startMockClientSolicitorOnly();
        GUIWindow window = GUIWindow.getInstance();
        window.switchPage(new SolicitorHomePage(mockClient));
    }
}
