package Components;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Custom logo class to display logo image
 *
 * @author Ana Farmus
 * @version May 4, 2025
 */
public class Logo extends JLabel {

    public Logo(String imagePath, int width, int height) {
        super();
        createLogo(imagePath, width, height);
    }

    public void createLogo(String imagePath, int width, int height) {
        try {
            java.net.URL resource = getClass().getClassLoader().getResource(imagePath);
            if (resource == null) {
                throw new FileNotFoundException("Image not found: " + imagePath);
            }
            ImageIcon logoIcon = new ImageIcon(resource);
            Image scaledImage = logoIcon.getImage().getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
            this.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        } catch (Exception e) {
            e.printStackTrace();
            this.setText("Logo not found");
            this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        }
    }
}
