package Pages;

import Components.*;
import Database.*;
import Interfaces.PageInterface;
import NetworkIO.*;

import javax.swing.*;

/**
 * Page - basic page, foundation for all other pages
 * page initialization occurs asynchronously using SwingUtilities to ensure
 * UI elements are updated on the Event Dispatch Thread
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2025
 */
public class Page implements PageInterface {
    public Client client;
    public GUIWindow window;
    public Panel panel;
    public Database database = Database.getInstance();

    /**
     * Constructor to initialize the Page with the provided client object.
     *
     * @param client The client object representing the current user.
     */
    public Page(Client client) {
        this.client = client;
        window = GUIWindow.getInstance();
        panel = new Panel();
//        SwingUtilities.invokeLater(() -> initializeContent());
    }

    public Page(Client client, Panel panel) {
        this.client = client;
        this.panel = panel;
        window = GUIWindow.getInstance();
        SwingUtilities.invokeLater(() -> initializeContent());
    }

    /**
     * Initializes the content of the page. This method should be overridden by subclasses
     * to define the page's specific components.
     */
    public void initializeContent() { }

    /**
     * Adds components to the page. This method should be overridden by subclasses to
     * define how components are added to the page.
     */
    public void addComponents() { }

    /**
     * Displays an error message in a dialog box.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Returns the panel associated with this page.
     *
     * @return The panel used to display the content of the page.
     */
    public JPanel getPanel() {
        return panel;
    }
}
