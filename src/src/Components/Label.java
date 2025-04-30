package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Label: basic tetx label class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class Label extends JLabel {
    public Label(String text, int fontSize, int boldOrNot) {
        super(text);
        setForeground(Constants.BREEZY_BLUE);
        setVisible(true);
        setFont(new Font(Constants.FONT_NAME, boldOrNot, fontSize));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
