package wordled.gui;

import javax.swing.*;

import wordled.WordleController;

import java.awt.*;

public class LetterButton extends JButton {
    public LetterButton(WordleController controller, LettersPanel parentPanel, int index) {
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

        this.addActionListener(e -> {
            this.setBackground(PersonalColors.getNextColor(this.getBackground()));
            int status = PersonalColors.getStatusForColor(this.getBackground());
            controller.setColor(index, status);
            parentPanel.setStatus(index, status);
            parentPanel.updateButtonBorder(index, status);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // clear whenever updating button
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // dynamically update font size
        int newFontSize = Math.min(this.getWidth(), this.getHeight()) / 2;
        this.setFont(new Font("Inter", Font.BOLD, newFontSize));

        super.paintComponent(g);
    }
}