package Pages;

import Components.*;
import Components.Button;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

/**
 * Login page for logging in
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class LoginPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private GreyLabel infoLabel;
    private Button solicitorbutton;
    private Button contractorButton;
    private TransparentButton goBacktoHomeButton;

    /**
     * constructor
     * @param client representing current user
     */
    public LoginPage(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * initializes login page components
     */
    public void initializeContent() {
        panel.removeAll();

        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Login", 40, 0);
        infoLabel = new GreyLabel("Please select your account type", 15, 0);
        solicitorbutton = new Button("Solicitor",
                e -> window.switchPage(new LandingPage(client)), Constants.SIZE_300_140);
        contractorButton = new Button("Contractor",
                e -> window.switchPage(new LandingPage(client)), Constants.SIZE_300_140);
        goBacktoHomeButton = new TransparentButton("Go back to Home",
                e -> window.switchPage(new LandingPage(client)), Constants.SIZE_500_45);

        addComponents();

    }

    public void addComponents() {
        panel.add(new Margin(100));
        panel.add(logoLabel);
        panel.add(new Margin(6));
        panel.add(titleLabel);
        panel.add(new Margin(60));
        panel.add(infoLabel);
        panel.add(new Margin(20));

        JPanel buttonRow = new JPanel();
        buttonRow.setOpaque(false);
        buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        buttonRow.add(solicitorbutton);
        buttonRow.add(contractorButton);

        panel.add(buttonRow);


        panel.add(new Margin(5));

        panel.add(goBacktoHomeButton);
        panel.add(new Margin(150));
        panel.revalidate();
        panel.repaint();
    }




}
