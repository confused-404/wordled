package wordled.gui;

import javax.swing.*;

import java.awt.*;

import wordled.WordleController;

public class MainPanel extends JPanel {
    private LettersPanel lettersPanel;
    private JButton nextWordButton;

    public MainPanel(WordleController controller) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        this.lettersPanel = new LettersPanel(controller);
        char[] guess = controller.reportGuess();
        int[] statuses = new int[5];
        for (int i = 0; i < 5; i++)
            statuses[i] = -1;
        this.lettersPanel.updateButtons(new String(guess), statuses);

        // horizontal padding for the letters panel
        JPanel horizontalLettersContainer = new JPanel();
        horizontalLettersContainer.setLayout(new BoxLayout(horizontalLettersContainer, BoxLayout.X_AXIS));
        horizontalLettersContainer.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);
        horizontalLettersContainer.add(Box.createHorizontalGlue());
        horizontalLettersContainer.add(this.lettersPanel);
        horizontalLettersContainer.add(Box.createHorizontalGlue());

        this.nextWordButton = new JButton("Get next word");
        this.nextWordButton.setBackground(PersonalColors.NEXT_WORD_BUTTON_COLOR);
        this.nextWordButton.setForeground(Color.WHITE);
        this.nextWordButton.setFocusPainted(false);
        this.nextWordButton.setFont(new Font("Inter", Font.PLAIN, 16));
        this.nextWordButton.addActionListener(e -> {
            boolean allSet = true;
            int[] currentStatuses = this.lettersPanel.getStatuses();
            for (int i = 0; i < 5; i++) {
                if (currentStatuses[i] == -1) {
                    allSet = false;
                    break;
                }
            }
            if (!allSet) {
                this.lettersPanel.showNotReadyDialog();
                return;
            }
            controller.submit();
            char[] nextGuess = controller.reportGuess();
            int[] newStatuses = new int[5];
            for (int i = 0; i < 5; i++)
                newStatuses[i] = -1;
            this.lettersPanel.updateButtons(new String(nextGuess), newStatuses);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);
        buttonPanel.add(this.nextWordButton);

        this.add(Box.createVerticalGlue());
        this.add(horizontalLettersContainer);
        this.add(Box.createVerticalGlue());
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }
}