package Pages;

/**
 * PageInterface - Defines the essential methods for any page in the application.
 *
 * This interface outlines the structure that any page class must follow, including methods
 * for initializing the content and adding components to the page. Implementing classes will define
 * how content is set up (e.g., labels, buttons, input fields) and how components are added to the page.
 *
 * @author Ana Farmus, L-35
 * @version December 6, 2024
 */
public interface PageInterface {

    /**
     * Initializes the content of the page, such as setting up labels, buttons, and fields.
     */
    void initializeContent();

    /**
     * Adds components (buttons, labels, etc.) to the page's layout.
     */
    void addComponents();
}

