package Pages;

import Components.*;
import Components.Button;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;

public class SolicitorPaymentInfoPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;

    private TextField bankNameField;
    private TextField routingNumberField;
    private TextField accountNumberField;

    private Button createAccountButton;  //leads to payment info page
    private TransparentButton goBackButton;


    public SolicitorPaymentInfoPage(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * initializes page content
     */
    public void initializeContent() {
        panel.removeAll();

        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Payment Information", 40, 0);

        bankNameField = new TextField("Enter the name of your financial institution", Constants.SIZE_500_45);
        routingNumberField = new TextField("Enter the account routing number", Constants.SIZE_500_45);
        accountNumberField = new TextField("Enter the account number", Constants.SIZE_500_45);


        createAccountButton = new Button("Create Account",
                e -> window.switchPage(new SolicitorAccountCreated(client)), Constants.SIZE_500_45);
        goBackButton = new TransparentButton("Go Back",
                e -> window.switchPage(new SolicitorRegistrationPage(client)), Constants.SIZE_500_45);
        addComponents();
    }

    /**
     * Adds all the components to the page layout, including the logo, heading, tagline,
     * login button, and register button.
     */
    public void addComponents() {
        panel.add(new Margin(100));
        panel.add(logoLabel);
        panel.add(new Margin(6));
        panel.add(titleLabel);
        panel.add(new Margin(50));
        panel.add(bankNameField);
        panel.add(new Margin(10));
        panel.add(routingNumberField);
        panel.add(new Margin(10));
        panel.add(accountNumberField);
        panel.add(new Margin(50));
        panel.add(createAccountButton);
        panel.add(new Margin(4));
        panel.add(goBackButton);

        panel.revalidate();
        panel.repaint();
    }
}