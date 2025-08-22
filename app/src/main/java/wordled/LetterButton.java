package wordled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum LetterStatus {
    GREEN,
    YELLOW,
    GRAY,
    EMPTY,
}

public class LetterButton extends JButton {
    private LetterStatus currentStatus;

    public LetterButton() {
        super();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LetterButton.this.cycleButtonColor();
            }
        });

        this.currentStatus = LetterStatus.EMPTY;
        this.setBackground(PersonalColors.LETTER_BUTTON_STARTING_BACKGROUND_COLOR);
        this.setForeground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(PersonalColors.LETTER_BUTTON_STARTING_BORDER_COLOR, 4));
        this.setFont(new Font("Inter", Font.BOLD, 64));
        this.setFocusPainted(false);
    }

    private void cycleButtonColor() {
        switch (this.currentStatus) {
            case EMPTY:
                this.currentStatus = LetterStatus.GREEN;
                this.setBackground(PersonalColors.LETTER_BUTTON_GREEN_BACKGROUND_COLOR);
                break;
            case GREEN:
                this.currentStatus = LetterStatus.YELLOW;
                this.setBackground(PersonalColors.LETTER_BUTTON_YELLOW_BACKGROUND_COLOR);
                break;
            case YELLOW:
                this.currentStatus = LetterStatus.GRAY;
                this.setBackground(PersonalColors.LETTER_BUTTON_GRAY_BACKGROUND_COLOR);
                break;
            case GRAY:
                this.currentStatus = LetterStatus.GREEN;
                this.setBackground(PersonalColors.LETTER_BUTTON_GREEN_BACKGROUND_COLOR);
                break;
        }
        this.setBorder(null);
    }

    public LetterStatus getCurrentLetterStatus() {
        return this.currentStatus;
    }
}
