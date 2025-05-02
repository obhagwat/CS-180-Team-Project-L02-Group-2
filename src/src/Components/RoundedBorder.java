package Components;

import javax.swing.border.Border;
import java.awt.*;

/**package Components;

 import javax.swing.border.Border;
 import java.awt.*;

 /**
 * rounded border: has rounded border stuff.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class RoundedBorder implements Border {
    private final int radius;
    private final Color borderColor;

    public RoundedBorder(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
// * rounded border: has rounded border stuff.
// *
// * @author Ana Farmus, Lab sec 02
// * @version May 4, 2025
// */
//public class RoundedBorder implements Border {
//    private final int radius;
//    private final Color borderColor;
//
//    public RoundedBorder(int radius, Color borderColor) {
//        this.radius = radius;
//        this.borderColor = borderColor;
//    }
//
//    @Override
//    public Insets getBorderInsets(Component c) {
//        return new Insets(radius, radius, radius, radius);
//    }
//
//    @Override
//    public boolean isBorderOpaque() {
//        return false;
//    }
//
//    @Override
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(borderColor);
//        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
//    }