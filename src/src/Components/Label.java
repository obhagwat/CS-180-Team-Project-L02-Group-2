package Components;

import javax.swing.*;
import java.awt.*;

/**
 * ghefjqdwkqsliqdheuf2owj12oeh3d2ej
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
