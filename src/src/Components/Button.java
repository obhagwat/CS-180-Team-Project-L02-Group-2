package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Button: basic button class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class Button extends JButton {
//    private Color defaultColor = Constants.BREEZY_BLUE;

    /**
     *  Constructor for Button with a standard style
     * @param text the text label of the button
     * @param actionListener the action to be executed when the button is clicked
     * @param size the size of the button
     */
    public Button(String text, ActionListener actionListener, Dimension size) {
        super(text);

        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);

        setFont(new Font(Constants.FONT_NAME, Font.PLAIN, 16));
        setFocusPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setBackground(Constants.BREEZY_BLUE);
        setForeground(Constants.BREEZY_BLUE);
        setBorder(new RoundedBorder(20, Constants.BREEZY_BLUE));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addActionListener(actionListener);
    }
//    public Button(String label, ActionListener actionListener, Dimension buttonSize) {
//        super(label);
//
//        setPreferredSize(buttonSize);
//        setMaximumSize(buttonSize);
//        setMinimumSize(buttonSize);
//
//        setFont(new Font(Constants.FONT_NAME, Font.PLAIN, 16));
//        setFocusPainted(false);
//        setContentAreaFilled(true);
//        setOpaque(true);
//        setBackground(defaultColor);
//        setForeground(Constants.WHITE);
//        setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        setBorder(new RoundedBorder(20, defaultColor));
//
//        addActionListener(actionListener);
//
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // Ensure the border is painted correctly
//        if (getBorder() != null) {
//            getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
//        }
//    }
}
