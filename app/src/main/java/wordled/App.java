package wordled;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import wordled.gui.PersonalColors;

public class App extends JFrame {
    public static void main(String[] args) {
        new App();
    }

    public App() {
        super();
        this.setTitle("Wordled");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        wordled.Controller controller = new wordled.Controller(new File("data\\WordList.txt"));

        wordled.gui.MainPanel mainPanel = new wordled.gui.MainPanel(controller);
        this.getContentPane().add(mainPanel);

        this.pack();
        this.setMinimumSize(new Dimension(800, 300));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
