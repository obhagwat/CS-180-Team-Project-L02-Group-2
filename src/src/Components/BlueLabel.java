package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Blue Label: basic text label class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class BlueLabel extends JLabel {
    public BlueLabel(String text, int fontSize, int boldOrNot) {
        super(text);
        setForeground(Constants.BREEZY_BLUE);
        setVisible(true);
        setFont(new Font(Constants.FONT_NAME, boldOrNot, fontSize));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
