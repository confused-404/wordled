// package wordled;

// import java.awt.*;
// import javax.swing.*;

// import wordled.view.MainPanel;
// import wordled.view.PersonalColors;

// /**
//  * @deprecated Class deprecated because I jumped the gun
//  */
// @Deprecated
// public class App extends JFrame {
//     public static void main(String[] args) {
//         new App();
//     }

//     public App() {
//         super();
//         this.setTitle("Wordled");
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.getContentPane().setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);

//         Controller controller = new Controller();
//         MainPanel mainPanel = new MainPanel(controller);
//         controller.setLettersPanel(mainPanel.getLettersPanel());
//         this.add(mainPanel);

//         this.pack();
//         this.setMinimumSize(new Dimension(800, 300));
//         this.setLocationRelativeTo(null);
//         this.setVisible(true);
//     }
// }
