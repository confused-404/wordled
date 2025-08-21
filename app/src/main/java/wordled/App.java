package wordled;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
    public static void main(String[] args) {
        new App();
    }

    public App() {
        super();
        this.setTitle("Wordled");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
