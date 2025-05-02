package Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Button: basic button class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class Button extends JButton {
    private Color defaultColor = Constants.BREEZY_BLUE;

    /**
     *  Constructor for Button with a standard style
     * @param label the text label of the button
     * @param actionListener the action to be executed when the button is clicked
     * @param buttonSize the size of the button
     */
    public Button(String label, ActionListener actionListener, Dimension buttonSize) {
        super(label);

        setPreferredSize(buttonSize);
        setMaximumSize(buttonSize);
        setMinimumSize(buttonSize);

        setFont(new Font(Constants.FONT_NAME, Font.PLAIN, 16));
        setFocusPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);
        setBackground(defaultColor);
        setForeground(Constants.WHITE);
        setBorder(new RoundedBorder(20, defaultColor));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        addActionListener(actionListener);

    }
}
