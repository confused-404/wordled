package wordled.gui;

import javax.swing.*;

import wordled.WordleController;
import wordled.gui.PersonalColors;

import java.awt.*;

public class LettersPanel extends JPanel {
    private LetterButton[] letterButtons;
    private WordleController controller;
    private int[] statuses;

    public LettersPanel(WordleController controller) {
        super(new GridLayout(1, 5, 20, 0));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        this.controller = controller;
        this.letterButtons = new LetterButton[5];
        this.statuses = new int[5];
        for (int i = 0; i < 5; i++) this.statuses[i] = -1;
        this.addLetterButtons();
    }

    public void updateButtons(String word, int[] newStatuses) {
        for (int i = 0; i < 5; i++) {
            this.updateButtonText(i, word.charAt(i));
            this.updateButtonColor(i, newStatuses[i]);
            this.updateButtonBorder(i, newStatuses[i]);
            this.statuses[i] = newStatuses[i];
        }
    }

    private void updateButtonText(int index, char letter) {
        LetterButton button = this.letterButtons[index];
        button.setText(String.valueOf(letter).toUpperCase());
    }

    private void updateButtonColor(int index, int status) {
        LetterButton button = this.letterButtons[index];
        button.setBackground(PersonalColors.getColorForStatus(status));
    }

    public void updateButtonBorder(int index, int status) {
        LetterButton button = this.letterButtons[index];
        if (status == -1) {
            button.setBorder(BorderFactory.createLineBorder(PersonalColors.LETTER_BUTTON_STARTING_BORDER_COLOR, 4));
        } else {
            button.setBorder(BorderFactory.createLineBorder(PersonalColors.getColorForStatus(status), 4));
        }
    }

    private void addLetterButtons() {
        for (int i = 0; i < 5; i++) {
            LetterButton currButton = new LetterButton(this.controller, this, i);
            this.letterButtons[i] = currButton;
            this.add(currButton);
        }
    }

    public void setStatus(int index, int status) {
        this.statuses[index] = status;
    }

    public int[] getStatuses() {
        return this.statuses.clone();
    }

    public void showNotReadyDialog() {
        JOptionPane.showMessageDialog(this,
                "All letter boxes must be set to a status (Green, Yellow, or Gray) before proceeding.",
                "Invalid Action",
                JOptionPane.WARNING_MESSAGE);
    }
}