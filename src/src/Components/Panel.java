package Components;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;

/**
 * Panel: basic panel class.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class Panel extends JPanel {
    public Panel() {
        initializeStyle();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public Panel(LayoutManager layout) {
        super(layout);
        initializeStyle();
    }

    public void initializeStyle() {
        setVisible(true);
        setOpaque(true);
        setBackground(Constants.WHITE);

    }
}
