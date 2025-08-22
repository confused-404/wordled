package wordled.view;

import javax.swing.*;

import wordled.controller.Controller;

import java.awt.*;

public class NextWordPanel extends JPanel {
    public NextWordPanel(Controller controller) {
        super(new FlowLayout(FlowLayout.CENTER));
        this.add(new NextWordButton(controller));
    }
}
