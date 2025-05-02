package Pages;

import Components.BlueLabel;
import Components.Button;
import Components.PasswordField;
import Components.TextField;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;

public class RegisterPage extends Page implements PageInterface {
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
    public RegisterPage(Client client) {
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
