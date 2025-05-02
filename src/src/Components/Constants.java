package Components;

import java.awt.*;

/**
 * Constants: Contains all constant values used throughout the application, including
 * predefined colors, window dimensions, font settings, padding, and other UI related properties.
 *
 * @author Ana Farmus, Lab sec 02
 * @version May 4, 2025
 */
public class Constants {
    //colors
    public static final Color WHITE = Color.decode("#F3F3F3");
    public static final Color GRAY = Color.decode("#E7E7E9");
    public static final Color VIOLET = Color.decode("#C4C4DF");
    public static final Color DUST_BLUE = Color.decode("#9CADD8");
    public static final Color BREEZY_BLUE = Color.decode("#7793C2");
    public static final Color PANES_GREY = Color.decode("#5A6484");
    public static final Color GREY = Color.decode("#a2a1a0");
    public static final Color LIGHT_GREY = Color.decode("#E2E1DF");
    public static final Color PRIMARY_WHITE = Color.decode("#F0F0F0");

    //Window dimensions
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 700;

    public static final String FONT_NAME = "Verdana";
    public static final int LEFT_PADDING = 15;
    public static final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 15);

    //Edge radius for rounded corners + button sizes
    public static final int EDGE_RADIUS = 10;
    public static final Dimension SIZE_400_40 = new Dimension(350, 40);
    public static final Dimension SIZE_500_45 = new Dimension(500, 45);
    public static final Dimension SIZE_500_250 = new Dimension(500, 250);

}