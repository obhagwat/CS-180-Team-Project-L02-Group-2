package Pages;

import Components.*;
import NetworkIO.*;
import javax.swing.*;

/**
 * Landing page - prompts user to login or register
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class LandingPage extends Page {
    private JLabel logoLabel;
    private Label heading;
    private Label tagline;
    private Button loginButton;
    private Button createAccountButton;

    /**
     * Constructor to initialize the LandingPage with a client object.
     *
     * @param client The client object representing the current user.
     */
    public LandingPage(Client client) {
        super(client);
    }

    /**
     * Initializes the components for the LandingPage, including the logo, labels, and buttons
     * for logging in or creating a new account.
     */
    public void initializeContent() {
        logoLabel = new Logo("images/whiteLogo.png", 50, 50);
        heading = new Label("GOVBid", 44, 1);
        tagline = new Label("The better way to contract", 14, 2);

        loginButton = new Button("login", () -> window.switchPage(new LoginPage(client)), Constants.SIZE_400_40);
//        createAccountButton = new Button("Create Account", () -> window.switchPage(new RegisterPage(client)), Constants.SIZE_400_40, true);

        addComponents();
    }

    /**
     * Adds all the components to the page layout, including the logo, heading, tagline,
     * login button, and register button.
     */
    @Override
    public void addComponents() {
        panel.add(new Margin(150));
        panel.add(logoLabel);
        panel.add(new Margin(20));
        panel.add(heading);
        panel.add(new Margin(10));
        panel.add(tagline);
        panel.add(new Margin(50));
        panel.add(loginButton);
        panel.add(new Margin(10));
//        panel.add(createAccountButton);
        panel.revalidate();
    }
}
