package wordled.view;

import javax.swing.*;

import wordled.controller.Controller;

import java.awt.*;

/**
 * @deprecated Class deprecated because I jumped the gun
 */
@Deprecated
public class NextWordButton extends JButton {
    public NextWordButton(Controller controller) {
        super("Get next word");

        this.setForeground(Color.WHITE);
        this.setBackground(PersonalColors.NEXT_WORD_BUTTON_COLOR);
        this.setFont(new Font("Inter", Font.BOLD, 14));
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setRolloverEnabled(false);
        this.setModel(new FixedStateButtonModel());
        this.setBorder(null);

        this.addActionListener(e -> controller.handleNextWordButtonClick());
    }

    @Override
    protected void paintComponent(Graphics g) {
        // clear whenever updating button
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        // dynamically update font size
        int newFontSize = Math.min(getWidth(), getHeight()) / 3;
        this.setFont(new Font("Inter", Font.BOLD, newFontSize));

        super.paintComponent(g);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(170, 30);
    }

    @Override
    public Dimension getPreferredSize() {
        Container frame = this.getParent().getParent();
        if (frame != null) {
            int frameWidth = frame.getWidth();
            int frameHeight = frame.getHeight();

            int preferredWidth = (int) (frameWidth * 0.22);
            int preferredHeight = (int) (frameHeight * 0.1);

            return new Dimension(Math.max(preferredWidth, 170), Math.max(preferredHeight, 30));
        } else {
            // default size
            return new Dimension(170, 30);
        }
    }
}