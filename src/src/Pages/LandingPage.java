package Pages;

import Components.*;
import NetworkIO.*;
import javax.swing.*;
import Components.GradientPanel;
import Components.Constants;



import static Components.Constants.*;

/**
 * Landing page - prompts user to login or register
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class LandingPage extends Page {
    private JLabel logoLabel;
    private WhiteLabel heading;
    private WhiteLabel tagline;
    private TransparentButton loginButton;
    private TransparentButton createAccountButton;

    /**
     * Constructor to initialize the LandingPage with a client object.
     *
     * @param client The client object representing the current user.
     */
    public LandingPage(Client client) {
            super(client);

            // REPLACE default panel with gradient BEFORE calling initializeContent
            this.panel = new GradientPanel(
                    Constants.PANES_GREY,
                    Constants.BREEZY_BLUE,
                    Constants.DUST_BLUE,
                    Constants.VIOLET,
                    Constants.GRAY,
                    Constants.WHITE
            );
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Update the window to use the new panel
            window = GUIWindow.getInstance();
//            window.setContentPane(panel);

            // Now add components
            initializeContent();
        }




        /**
         * Initializes the components for the LandingPage, including the logo, labels, and buttons
         * for logging in or creating a new account.
         */
    public void initializeContent() {
        logoLabel = new Logo("Components/whiteLogo.png", 65, 65);
        heading = new WhiteLabel("GOVBid", 46, 0);
        tagline = new WhiteLabel("The better way to contract", 14, 2);

        loginButton = new TransparentButton("Login", e -> window.switchPage(new LoginPage(client)), Constants.SIZE_500_45);
        createAccountButton = new TransparentButton("Create Account", e -> window.switchPage(new RegisterPage(client)), Constants.SIZE_500_45);

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
        panel.add(createAccountButton);
        panel.revalidate();
    }
}
