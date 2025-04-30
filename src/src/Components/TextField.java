package Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextField extends JTextField {
    private String placeholder;

    public TextField(String placeholder, Dimension size) {
        super(placeholder);
        this.placeholder = placeholder;
        setMaximumSize(size);
        setOpaque(false);
        setText(placeholder);
        setEditable(true);
        setVisible(true);

        setFont(Constants.TEXT_FONT);
        setForeground(Constants.GREY);
        setBackground(Constants.LIGHT_GREY);
        setCaretColor(Constants.PANES_GREY);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        Border padding = BorderFactory.createEmptyBorder(3, Constants.LEFT_PADDING, 0, 0);
        setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(Constants.EDGE_RADIUS, Constants.BREEZY_BLUE), padding));

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Constants.GREY);
                    setText(placeholder);
                }
            }
        });
    }

    public String getText() {
        return super.getText().equals(placeholder) ? "" : super.getText();
    }

    public void setPlaceholder(String text) {
        placeholder = text;
        setText(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), Constants.EDGE_RADIUS, Constants.EDGE_RADIUS);
        super.paintComponent(graphics);
    }
}
