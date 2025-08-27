package wordled.view;

import javax.swing.*;

import wordled.controller.Controller;
import wordled.view.PersonalColors;

import java.awt.*;

/**
 * @deprecated Class deprecated because I jumped the gun
 */
@Deprecated
public class NextWordPanel extends JPanel {
    public NextWordPanel(Controller controller) {
        super(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(PersonalColors.WINDOW_BACKGROUND_COLOR);
        this.add(new NextWordButton(controller));
    }
}
