package wordled.view;

import javax.swing.*;

import wordled.LetterStatus;
import wordled.controller.Controller;
import wordled.view.PersonalColors;

import java.awt.*;

/**
 * @deprecated Class deprecated because I jumped the gun
 */
@Deprecated
public class LettersPanel extends JPanel {
    private LetterButton[] letterButtons;
    private Controller controller;

    public LettersPanel(Controller controller) {
        super(new GridLayout(1, 5, 20, 0));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        this.controller = controller;
        this.letterButtons = new LetterButton[5];
        this.addLetterButtons();
    }

    public void updateButtons(String word, LetterStatus[] statuses) {
        for (int i = 0; i < 5; i++) {
            updateButtonText(i, word.charAt(i));
            updateButtonColor(i, statuses[i]);
            updateButtonBorder(i, statuses[i]);
        }
    }

    private void updateButtonText(int index, char letter) {
        LetterButton button = letterButtons[index];
        button.setText(String.valueOf(letter));
    }

    private void updateButtonColor(int index, LetterStatus status) {
        LetterButton button = letterButtons[index];
        button.setBackground(PersonalColors.getColorForStatus(status));
    }

    private void updateButtonBorder(int index, LetterStatus status) {
        LetterButton button = letterButtons[index];
        if (status == LetterStatus.EMPTY) {
            button.setBorder(BorderFactory.createLineBorder(PersonalColors.LETTER_BUTTON_STARTING_BORDER_COLOR, 4));
        } else {
            button.setBorder(BorderFactory.createLineBorder(PersonalColors.getColorForStatus(status), 4));
        }
    }

    private void addLetterButtons() {
        for (int i = 0; i < 5; i++) {
            LetterButton currButton = new LetterButton(controller, i);
            letterButtons[i] = currButton;
            this.add(currButton);
        }
    }

    public void showNotReadyDialog() {
        JOptionPane.showMessageDialog(this,
                "All letter boxes must be set to a status (Green, Yellow, or Gray) before proceeding.",
                "Invalid Action",
                JOptionPane.WARNING_MESSAGE);
    }
}