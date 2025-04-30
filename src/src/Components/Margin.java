package Components;

import javax.swing.*;

/**
 * Margin - A custom JPanel used to create vertical space within a layout.
 * This class extends JPanel and is used to add a vertical margin or spacing
 * between other components in the layout. The margin height is configurable
 * through the constructor, and the panel is set to be non-opaque so it does
 * not render any background.
 *
 * @author Ana Farmus, L35
 * @version May 4, 2025
 */
public class Margin extends JPanel {
    public Margin(int height) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(height));
        setOpaque(false);
    }

}
