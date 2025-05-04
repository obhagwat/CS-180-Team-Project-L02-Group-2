package Pages;

import Components.*;
import Components.Button;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;
import java.awt.*;

/**
 * Informational page for logging in
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class SolicitorAccountCreated extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private GreyLabel infoLabel;
    private Button goToLogin;

    /**
     * constructor
     * @param client representing current user
     */
    public SolicitorAccountCreated(Client client) {
        super(client);
        initializeContent();
    }

    /**
     * initializes login page components
     */
    public void initializeContent() {
        panel.removeAll();

        logoLabel = new Logo("Components/blueLogo.png", 55, 55);
        titleLabel = new BlueLabel("Account Successfully Created", 40, 0);


        goToLogin = new Button("Go to Login",
                e -> window.switchPage(new SolicitorLoginPage(client)), Constants.SIZE_500_45);
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
        panel.add(goToLogin);
        panel.revalidate();
        panel.repaint();

    }
}
