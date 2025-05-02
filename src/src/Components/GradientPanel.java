package Components;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Arrays;

/**
 * GradientPanel - custom panel with a vertical gradient.
 */
public class GradientPanel extends Panel {
    private final List<Color> colors;

    public GradientPanel(Color... colors) {
        this.colors = Arrays.asList(colors);
        setOpaque(false);  //to allow transparent components
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // important for correct layout and painting

        Graphics2D g2d = (Graphics2D) g.create();

        int panelHeight = getHeight();
        int sectionHeight = panelHeight / (colors.size() - 1);

        for (int i = 0; i < colors.size() - 1; i++) {
            int y = i * sectionHeight;
            GradientPaint gradient = new GradientPaint(
                    0, y, colors.get(i),
                    0, y + sectionHeight, colors.get(i + 1)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, y, getWidth(), sectionHeight);
        }

        g2d.dispose();
    }

}
