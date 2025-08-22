package wordled;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new LettersPanel());
        this.add(Box.createVerticalStrut(30));
        this.add(new NextWordPanel());
    }
}
