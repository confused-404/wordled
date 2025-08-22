package wordled.view;

import javax.swing.*;

import wordled.Controller;

import java.awt.*;

public class MainPanel extends JPanel {
    private LettersPanel lettersPanel;
    private NextWordPanel nextWordPanel;

    public MainPanel(Controller controller) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.lettersPanel = new LettersPanel(controller);
        this.nextWordPanel = new NextWordPanel(controller);

        this.add(this.lettersPanel);
        this.add(Box.createVerticalStrut(30));
        this.add(this.nextWordPanel);
    }

    public LettersPanel getLettersPanel() { return this.lettersPanel; }
}
