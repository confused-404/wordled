package wordled.view;

import javax.swing.*;
import javax.swing.border.Border;

import wordled.view.PersonalColors;
import wordled.controller.Controller;
import wordled.view.FixedStateButtonModel;
import java.awt.*;

public class LetterButton extends JButton {
    public LetterButton(Controller controller, int index) {
        super();
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Inter", Font.BOLD, 64));
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setRolloverEnabled(false);

        this.setText("?");
        this.setBackground(PersonalColors.LETTER_BUTTON_STARTING_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(PersonalColors.LETTER_BUTTON_STARTING_BORDER_COLOR, 4));
        this.setModel(new FixedStateButtonModel()); // get rid of blue hover effect

        this.addActionListener(e -> controller.handleLetterButtonClick(index));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // clear whenever updating button
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        // dynamically update font size
        int newFontSize = Math.min(getWidth(), getHeight()) / 2;
        this.setFont(new Font("Inter", Font.BOLD, newFontSize));

        super.paintComponent(g);
    }
}