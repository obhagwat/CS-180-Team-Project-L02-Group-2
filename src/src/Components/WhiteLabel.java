package Components;

import javax.swing.*;
import java.awt.*;

/**
 * White Label: basic text label class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class WhiteLabel extends JLabel {
    public WhiteLabel(String text, int fontSize, int boldOrNot) {
        super(text);
        setForeground(Constants.WHITE);
        setVisible(true);
        setFont(new Font(Constants.FONT_NAME, boldOrNot, fontSize));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
