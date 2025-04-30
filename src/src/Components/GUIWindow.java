package Components;

import Pages.*;
import javax.swing.*;
import java.awt.*;

/**
 * GUI Window - contains representation of main GUI window for the application, which
 * handles layout and management of different pages.
 *
 * @author Ana Farmus, lab sec 02
 * @version May 4, 2024
 */
public class GUIWindow extends JFrame {
    private static volatile GUIWindow instance = null;
    private Panel contentPanel;
    private CardLayout cardLayout;

    public GUIWindow(String title) {
        super(title);
        initializeGUIWindow();
    }

    public void initializeGUIWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        cardLayout = new CardLayout();
        contentPanel = new Panel(cardLayout);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public synchronized static GUIWindow getInstance() {
        if (instance == null) {
            instance = new GUIWindow("GOVBid");
        }
        return instance;
    }

    public void switchPage(Page newPage) {
        contentPanel.removeAll();
        contentPanel.add(newPage.getPanel(), "content");
        cardLayout.show(contentPanel, "content");
        revalidate();
        repaint();
    }

}
