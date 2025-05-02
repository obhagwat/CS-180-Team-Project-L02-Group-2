package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Grey Label: basic text label class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class GreyLabel extends JLabel {
    public GreyLabel(String text, int fontSize, int boldOrNot) {
        super(text);
        setForeground(Constants.GREY);
        setVisible(true);
        setFont(new Font(Constants.FONT_NAME, boldOrNot, fontSize));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
