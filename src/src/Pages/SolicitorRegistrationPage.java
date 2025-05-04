package Pages;

import Components.*;
import Components.Button;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

public class SolicitorRegistrationPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private TextField agencyLevelField;
    private TextField branchField;
    private TextField subBranchField;
    private TextField physicalAddressField;
    private TextField emailAddressField;
    private TextField phoneNumberField;
    private TextField usernameField;
    private TextField passwordField;



    private Button continueButton;  // Leads to payment info page
    private TransparentButton goBackButton;

    public SolicitorRegistrationPage(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * Initializes page content
     */
    public void initializeContent() {
        panel.removeAll();
        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Register", 40, 0);


        agencyLevelField = new TextField("Enter agency level", Constants.SIZE_100_20);
        branchField = new TextField("Enter branch", Constants.SIZE_100_20);
        subBranchField = new TextField("Enter sub-branch", Constants.SIZE_100_20);
        physicalAddressField = new TextField("Enter physical address", Constants.SIZE_100_20);
        emailAddressField = new TextField("Enter email address", Constants.SIZE_100_20);
        phoneNumberField = new TextField("Enter phone number", Constants.SIZE_100_20);
        usernameField = new TextField("Enter username", Constants.SIZE_100_20);
        passwordField = new TextField("Enter password", Constants.SIZE_100_20);

        //continueButton = new Button("Continue", e -> window.switchPage(new SolicitorPaymentInfoPage(client)), Constants.SIZE_500_45);
        continueButton = new Button("Continue", e -> register(), Constants.SIZE_500_45);
        goBackButton = new TransparentButton("Go Back",
                e -> window.switchPage(new RegisterPage(client)), Constants.SIZE_500_45);

        addComponents();
    }

    /**
     * Adds all the components to the page layout, including the logo, heading,
     * fields in 2 columns, and navigation buttons.
     */
    public void addComponents() {
        panel.add(new Margin(100));
        panel.add(logoLabel);
        panel.add(new Margin(6));
        panel.add(titleLabel);
        panel.add(new Margin(50));

        // Fields in a 3-row, 2-column grid with spacing and margin
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(agencyLevelField);
        fieldsPanel.add(branchField);
        fieldsPanel.add(subBranchField);
        fieldsPanel.add(physicalAddressField);
        fieldsPanel.add(emailAddressField);
        fieldsPanel.add(phoneNumberField);

        panel.add(fieldsPanel);
        panel.add(new Margin(50));

        panel.add(continueButton);
        panel.add(new Margin(10));
        panel.add(goBackButton);
        panel.add(new Margin(100));

        panel.revalidate();
        panel.repaint();
    }

    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String agencyLevel = agencyLevelField.getText();
        String branch = branchField.getText();
        String subBranch = subBranchField.getText();
        String physicalAddress = physicalAddressField.getText();
        String emailAddress = emailAddressField.getText();
        String phoneNumber = phoneNumberField.getText();

        client.sendToServer("CREATE_SOLICITOR: " + username + ", " + password + ", " + agencyLevel + ", " + branch + ", " + subBranch + ", " + physicalAddress + ", " + emailAddress + ", " + phoneNumber);
        String response = client.readFromServer();
        if (response.equals("SUCCESS")) {
            window.switchPage(new SolicitorPaymentInfoPage(client));
        }
    }
}
