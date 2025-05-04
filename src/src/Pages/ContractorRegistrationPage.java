package Pages;

import Components.*;
import Components.Button;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

/**
 * ContractorRegistrationPage: Page for contractor registration input.
 * Collects user information and account credentials before proceeding to payment info.
 *
 * Displays fields for contractor profile and organizational details.
 * Username and password fields are displayed side-by-side.
 *
 * @author Ana Farmus
 * @version May 4, 2025
 */
public class ContractorRegistrationPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private TextField profileNameField;
    private TextField organizationNameField;
    private TextField countryField;
    private TextField physicalAddressField;
    private TextField emailAddressField;
    private TextField phoneNumberField;
    private TextField organizationTypeField;
    private TextField numEmployeesField;
    private TextField foundingYrField;
    private TextField usernameField;
    private TextField passwordField;

    private Button continueButton;  // Leads to payment info page
    private TransparentButton goBackButton;

    /**
     * Constructor
     * @param client the active client instance
     */
    public ContractorRegistrationPage(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * Initializes all components on the page, including form fields and buttons.
     */
    public void initializeContent() {
        panel.removeAll();
        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Register", 40, 0);

        profileNameField = new TextField("Enter username", Constants.SIZE_100_20);
        organizationNameField = new TextField("Enter organization name", Constants.SIZE_100_20);
        countryField = new TextField("Enter country", Constants.SIZE_100_20);
        physicalAddressField = new TextField("Enter phone number", Constants.SIZE_100_20);
        emailAddressField = new TextField("Enter email address", Constants.SIZE_100_20);
        phoneNumberField = new TextField("Enter phone number", Constants.SIZE_100_20);
        organizationTypeField = new TextField("Enter organization type", Constants.SIZE_100_20);
        numEmployeesField = new TextField("Enter number of employees", Constants.SIZE_100_20);
        foundingYrField = new TextField("Enter founding year", Constants.SIZE_100_20);
        usernameField = new TextField("Enter username", Constants.SIZE_300_40);
        passwordField = new TextField("Enter password", Constants.SIZE_300_40);

        continueButton = new Button("Continue",
                e -> window.switchPage(new ContractorPaymentInfoPage(client)), Constants.SIZE_500_45);
        goBackButton = new TransparentButton("Go Back",
                e -> window.switchPage(new RegisterPage(client)), Constants.SIZE_500_45);

        addComponents();
    }

    /**
     * Adds all the components to the page layout, including logo, title,
     * text fields (grouped appropriately), and action buttons.
     */
    public void addComponents() {
        panel.add(new Margin(80));
        panel.add(logoLabel);
        panel.add(new Margin(6));
        panel.add(titleLabel);
        panel.add(new Margin(20));

        // Username + Password fields side by side
        JPanel userPassPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        userPassPanel.setOpaque(false);
        userPassPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPassPanel.add(usernameField);
        userPassPanel.add(passwordField);

        panel.add(userPassPanel);
        panel.add(new Margin(20));

        // Grid layout for profile/org details
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 40));

        fieldsPanel.add(profileNameField);
        fieldsPanel.add(organizationNameField);
        fieldsPanel.add(countryField);
        fieldsPanel.add(physicalAddressField);
        fieldsPanel.add(emailAddressField);
        fieldsPanel.add(phoneNumberField);
        fieldsPanel.add(organizationTypeField);
        fieldsPanel.add(numEmployeesField);
        fieldsPanel.add(foundingYrField);

        panel.add(fieldsPanel);
        panel.add(new Margin(20));

        panel.add(continueButton);
        panel.add(new Margin(10));
        panel.add(goBackButton);
        panel.add(new Margin(100));

        panel.revalidate();
        panel.repaint();
    }
}
