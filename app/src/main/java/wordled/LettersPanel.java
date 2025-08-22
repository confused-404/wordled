package wordled;

import javax.swing.*;
import java.awt.*;

public class LettersPanel extends JPanel {
    private JButton[] letterButtons;

    public LettersPanel() {
        super(new GridLayout(1, 5, 20, 0));

        this.letterButtons = new JButton[5];
        this.addLetterButtons();
    }

    private void addLetterButtons() {
        String startingWord = "TALES";

        for (int i = 0; i < 5; i++) {
            LetterButton currButton = new LetterButton();

            currButton.setText(String.valueOf(startingWord.charAt(i)));

            letterButtons[i] = currButton;
            this.add(currButton);
        }
    }
}
