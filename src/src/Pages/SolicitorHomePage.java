package Pages;

import Components.Button;
import Components.Constants;
import Components.Label;
import NetworkIO.Client;
import Objects.Contract;
import Objects.Solicitor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Solicitor home page - shows open contracts and create button
 *
 * @author Ovi Bhagwat, lab sec 02
 * @version May 1, 2025
 */
public class SolicitorHomePage extends Page {
    private Label heading;
    private Label subheading;
    private Button createContractButton;
    private JPanel openContractList;

    public SolicitorHomePage(Client client) {
        super(client);
    }

    /**
     * Initializes the components for the solicitor dashboard, including
     * the heading, create contract button, and list of open contracts.
     */
    @Override
    public void initializeContent() {
        heading = new Label("Solicitor Dashboard", 32, 1);
        subheading = new Label("Your Open Contracts:", 18, 2);

        createContractButton = new Button("Create New Contract", () -> window.switchPage(new CreateContractPage(client)), Constants.SIZE_400_40);

        openContractList = new JPanel();
        openContractList.setLayout(new BoxLayout(openContractList, BoxLayout.Y_AXIS));
        openContractList.setOpaque(false);

        Solicitor solicitor = client.getSolicitor();
        if (solicitor != null) {
            List<Contract> contracts = solicitor.getOpenContracts();
            for (Contract c : contracts) {
                openContractList.add(new Label("- " + c.getContractDescription() + " | Bids: " + c.getBids(), 16, 2));
                openContractList.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        } else {
            showError("No solicitor data found.");
        }

        openContractList.add(heading);
        openContractList.add(Box.createRigidArea(new Dimension(0, 20)));
        openContractList.add(createContractButton);
        openContractList.add(Box.createRigidArea(new Dimension(0, 20)));
        openContractList.add(subheading);
        openContractList.add(openContractList);

        addComponents();
    }
}
