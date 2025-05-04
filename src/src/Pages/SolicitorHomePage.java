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

        BlueLabel heading = new BlueLabel("Your Posted Contracts", 24, 0);
        panel.add(new Margin(20));
        panel.add(heading);

        JPanel contractsPanel = new JPanel();
        contractsPanel.setLayout(new BoxLayout(contractsPanel, BoxLayout.Y_AXIS));

        List<Contract> postedContracts = client.getSolicitor().getContractsSolicited();
        for (Contract contract : postedContracts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.add(new JLabel(contract.getContractDescription()), BorderLayout.CENTER);
            Button viewBids = new Button("View Bids", e -> new ViewBidsPopup(client, contract), new Dimension(30,10));
            card.add(viewBids, BorderLayout.EAST);
            contractsPanel.add(card);
            contractsPanel.add(new Margin(10));
        }

        JScrollPane scrollPane = new JScrollPane(contractsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);

        Button createButton = new Button("Create New Contract", e -> new CreateContractPopup(client), new Dimension(30,10));
        panel.add(new Margin(20));
        panel.add(createButton);
    }

    public static void main(String[] args) {
        Client mockClient = Client.startMockClientSolicitorOnly();
        GUIWindow window = GUIWindow.getInstance();
        window.switchPage(new SolicitorHomePage(mockClient));
    }

}