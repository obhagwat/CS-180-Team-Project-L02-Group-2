package Pages;

import Components.Button;
import Components.Constants;
import Components.BlueLabel;
import NetworkIO.Client;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Contractor home page - shows applied contracts and search option
 *
 * @author Ovi Bhagwat, lab sec 02
 * @version May 1, 2025
 */
public class ContractorHomePage extends Page {
    private BlueLabel heading;
    private BlueLabel subheading;
    private Button searchContractsButton;
    private JPanel contractList;

    public ContractorHomePage(Client client) {
        super(client);
    }

    /**
     * Initializes the components for the contractor dashboard, including
     * the heading, search button, and list of applied contracts.
     */
    @Override
    public void initializeContent() {
        heading = new BlueLabel("Contractor Dashboard", 32, 1);
        subheading = new BlueLabel("Your Applied Contracts:", 18, 2);

        searchContractsButton = new Button("Search Contracts", e -> window.switchPage(new SearchContractsPage(client)), Constants.SIZE_400_40);

        contractList = new JPanel();
        contractList.setLayout(new BoxLayout(contractList, BoxLayout.Y_AXIS));
        contractList.setOpaque(false);

        Contractor contractor = client.getContractor();
        if (contractor != null) {
            List<Bid> bids = contractor.bidsUnderReview();
            for (Bid b : bids) {
                Contract c = b.getContract();
                contractList.add(new BlueLabel("- " + c.getContractDescription() + " | Status: " + c.getDeadline(), 16, 2));
                contractList.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        } else {
            showError("No contractor data found.");
        }

        getPanel().add(heading);
        getPanel().add(Box.createRigidArea(new Dimension(0, 20)));
        getPanel().add(searchContractsButton);
        getPanel().add(Box.createRigidArea(new Dimension(0, 20)));
        getPanel().add(subheading);
        getPanel().add(contractList);
    }

    public static void main(String[] args) {
        // Fake client with dummy data
        Client mockClient = new Client();

        mockClient.setContractor(new Contractor(
                "greenfieldconstruction", "securePass123", 0.0, "USA", "789 Elm St",
                "contact@greenfield.com", "5551234567",
                "Greenfield Construction Inc.", "LLC", "75", 2010,
                Industry.CONSTRUCTION, "USA"));

        JFrame frame = new JFrame("Contractor Home Page Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new ContractorHomePage(mockClient).getPanel());
        frame.setVisible(true);
    }
}
