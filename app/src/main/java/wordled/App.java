package wordled;

import java.awt.*;
import javax.swing.*;

import wordled.view.MainPanel;

public class App extends JFrame {
    public static void main(String[] args) {
        new App();
    }

    public App() {
        super();
        this.setTitle("Wordled");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller controller = new Controller();
        MainPanel mainPanel = new MainPanel(controller);
        controller.setLettersPanel(mainPanel.getLettersPanel());
        this.add(mainPanel);

        this.setVisible(true);
    }
}
