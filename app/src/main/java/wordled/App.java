package wordled;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import wordled.gui.PersonalColors;
import wordled.gui.MainPanel;

public class App extends JFrame {
    public static void main(String[] args) {
        new App();
    }

    public App() {
        super();
        this.setTitle("Wordled");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

        File wordFile = new File("data/WordList.txt");

        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // System.out.println("Looking for file at = " + wordFile.getAbsolutePath());
        // System.out.println("Exists? = " + wordFile.exists());

        WordleController controller = new Controller(wordFile);

        MainPanel mainPanel = new MainPanel(controller);
        this.getContentPane().add(mainPanel);

        this.pack();
        this.setMinimumSize(new Dimension(800, 300));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
