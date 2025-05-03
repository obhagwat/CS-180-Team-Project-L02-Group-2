package Pages;

import Components.*;
import Components.Button;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

/**
 * Contractor login page for solicitors logging in
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */

public class ContractorLoginPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private TextField usernameField;
    private PasswordField passwordField;    private Button loginButton;
    private TransparentButton goBacktoHomeButton;


    /**
     * constructor
     * @param client representing current user
     */
    public ContractorLoginPage(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * initializes page content
     */
    public void initializeContent() {
        panel.removeAll();

        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Login", 40, 0);

        usernameField = new TextField("Enter your Username", Constants.SIZE_500_45);
        passwordField = new PasswordField("Enter your Password", Constants.SIZE_500_45);

        loginButton = new Button("Login", e -> window.switchPage(new SolicitorHomePage(client)), Constants.SIZE_500_45);
        goBacktoHomeButton = new TransparentButton("Go back to Home",
                e -> window.switchPage(new LandingPage(client)), Constants.SIZE_500_45);
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
        panel.add(usernameField);
        panel.add(new Margin(8));
        panel.add(passwordField);
        panel.add(new Margin(50));
        panel.add(loginButton);
        panel.add(new Margin(4));
        panel.add(goBacktoHomeButton);
        panel.revalidate();
        panel.repaint();
    }
}
