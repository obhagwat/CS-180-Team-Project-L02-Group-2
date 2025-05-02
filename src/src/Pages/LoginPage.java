package Pages;

import Components.Button;
import Components.BlueLabel;
import Components.PasswordField;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;

/**
 * Login page for logging in
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class LoginPage extends Page implements PageInterface {
    private JLabel logoLabel; // For displaying the logo
    private BlueLabel titleLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button backButton;

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
//        logoLabel = new Logo(image path, width, height);
        titleLabel = new BlueLabel("Login", 40, 1);
    }

}
