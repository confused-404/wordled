package wordled.gui;

import javax.swing.*;

import java.awt.*;

import wordled.Controller;

public class MainPanel extends JPanel {
    private LettersPanel lettersPanel;
    private JButton nextWordButton;
    private Controller controller;

    public MainPanel(Controller controller) {
        super();
        this.controller = controller;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        this.lettersPanel = new LettersPanel(controller);
        
        char[] guess = controller.reportGuess();
        int[] statuses = new int[5];
        for (int i = 0; i < 5; i++) statuses[i] = -1;
        this.lettersPanel.updateButtons(new String(guess), statuses);

        // horizontal padding for the letters panel
        JPanel horizontalLettersContainer = new JPanel();
        horizontalLettersContainer.setLayout(new BoxLayout(horizontalLettersContainer, BoxLayout.X_AXIS));
        horizontalLettersContainer.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);
        horizontalLettersContainer.add(Box.createHorizontalGlue());
        horizontalLettersContainer.add(this.lettersPanel);
        horizontalLettersContainer.add(Box.createHorizontalGlue());

        nextWordButton = new JButton("Get next word");
        nextWordButton.setBackground(PersonalColors.NEXT_WORD_BUTTON_COLOR);
        nextWordButton.setForeground(Color.WHITE);
        nextWordButton.setFocusPainted(false);
        nextWordButton.setFont(new Font("Inter", Font.PLAIN, 16));
        nextWordButton.addActionListener(e -> {
            // Check all statuses are set
            boolean allSet = true;
            int[] currentStatuses = controller.getLetterColors();
            for (int i = 0; i < 5; i++) {
                System.out.print(currentStatuses[i]);
                if (currentStatuses[i] == -1) {
                    allSet = false;
                    break;
                }
            }
            if (!allSet) {
                lettersPanel.showNotReadyDialog();
                return;
            }
            controller.submit();
            char[] nextGuess = controller.reportGuess();
            int[] newStatuses = new int[5];
            for (int i = 0; i < 5; i++) newStatuses[i] = -1;
            lettersPanel.updateButtons(new String(nextGuess), newStatuses);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);
        buttonPanel.add(nextWordButton);

        this.add(Box.createVerticalGlue());
        this.add(horizontalLettersContainer);
        this.add(Box.createVerticalGlue());
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }
}