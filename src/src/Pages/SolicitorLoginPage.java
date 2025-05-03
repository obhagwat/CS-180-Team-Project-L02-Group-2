package Pages;

import Components.*;
import Components.Button;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

/**
 * Solicitor login page for solicitors logging in
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */

public class SolicitorLoginPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    //need username + password fields
    private Button loginButton;
    private TransparentButton goBacktoHomeButton;


    /**
     * constructor
     * @param client representing current user
     */
    public SolicitorLoginPage(Client client) {
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
        loginButton = new Button("Login", e -> (new SolicitorHomePage(client)), Constants.SIZE_500_45);
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
        // add margin between title and username text field
        // add username text field
        // add small margin between username and password text fields
        // add password text field
        // add margin between text fields and login button
        // add login button
        panel.add(new Margin(4));
        panel.add(goBacktoHomeButton);
        panel.revalidate();
        panel.repaint();
    }
}
