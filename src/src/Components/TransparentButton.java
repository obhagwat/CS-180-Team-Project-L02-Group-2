package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Transparent Button: transparent button class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class TransparentButton extends JButton {
    public TransparentButton(String text, ActionListener actionListener, Dimension size) {
        super(text);

        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);

        setFont(new Font(Constants.FONT_NAME, Font.PLAIN, 16));
        setFocusPainted(false);
        setOpaque(false);
        setForeground(Constants.BREEZY_BLUE);
        setBorder(new RoundedBorder(20, Constants.BREEZY_BLUE));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addActionListener(actionListener);
    }
}
