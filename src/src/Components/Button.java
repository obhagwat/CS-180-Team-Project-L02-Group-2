package Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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
     * @param action the action to be executed when the button is clicked
     * @param buttonSize the size of the button
     */
    public Button(String label, Runnable action, Dimension buttonSize) {
        super(label);

    }


}
