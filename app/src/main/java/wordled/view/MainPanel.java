package wordled.view;

import javax.swing.*;

import wordled.controller.Controller;

import java.awt.*;

/**
 * @deprecated Class deprecated because I jumped the gun
 */
@Deprecated
public class MainPanel extends JPanel {
    private LettersPanel lettersPanel;
    private NextWordPanel nextWordPanel;

    public MainPanel(Controller controller) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        this.lettersPanel = new LettersPanel(controller);
        this.nextWordPanel = new NextWordPanel(controller);

        // horizontal padding for the letters panel
        JPanel horizontalLettersContainer = new JPanel();
        horizontalLettersContainer.setLayout(new BoxLayout(horizontalLettersContainer, BoxLayout.X_AXIS));
        horizontalLettersContainer.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        horizontalLettersContainer.add(Box.createHorizontalGlue());
        horizontalLettersContainer.add(this.lettersPanel);
        horizontalLettersContainer.add(Box.createHorizontalGlue());

        this.add(Box.createVerticalGlue());
        this.add(horizontalLettersContainer);
        this.add(Box.createVerticalGlue());
        this.add(this.nextWordPanel);
        this.add(Box.createVerticalGlue()); 
    }

    public LettersPanel getLettersPanel() { return this.lettersPanel; }
}
