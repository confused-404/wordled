package wordled.view;

import javax.swing.*;

import wordled.controller.Controller;

import java.awt.*;

public class NextWordButton extends JButton {
    public NextWordButton(Controller controller) {
        super("Get next word");
        this.addActionListener(e -> controller.handleNextWordButtonClick());
    }
}