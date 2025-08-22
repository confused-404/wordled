package wordled.view;

import javax.swing.*;
import wordled.Controller;
import wordled.view.PersonalColors;
import java.awt.*;

public class LetterButton extends JButton {
    public LetterButton(Controller controller, int index) {
        super();
        this.addActionListener(e -> controller.handleLetterButtonClick(index));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Inter", Font.BOLD, 64));
        this.setFocusPainted(false);

        this.setText("?");
        this.setBackground(PersonalColors.LETTER_BUTTON_STARTING_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createLineBorder(PersonalColors.LETTER_BUTTON_STARTING_BORDER_COLOR, 4));
    }
}