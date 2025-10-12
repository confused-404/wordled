package wordled.gui;

import java.awt.*;

public class PersonalColors {
    public static final Color WINDOW_BACKGROUND_COLOR = new Color(25, 25, 25);

    public static final Color LETTER_BUTTON_STARTING_BACKGROUND_COLOR = new Color(25, 25, 25);
    public static final Color LETTER_BUTTON_STARTING_BORDER_COLOR = Color.decode("#818181");
    public static final Color LETTER_BUTTON_GREEN_BACKGROUND_COLOR = Color.decode("#0A7D00");
    public static final Color LETTER_BUTTON_YELLOW_BACKGROUND_COLOR = Color.decode("#D5BC00");
    public static final Color LETTER_BUTTON_GRAY_BACKGROUND_COLOR = Color.decode("#404040");

    public static final Color NEXT_WORD_BUTTON_COLOR = Color.decode("#4D4D4D");

    public static Color getColorForStatus(int status) {
        switch (status) {
            case -1:
                return LETTER_BUTTON_STARTING_BACKGROUND_COLOR;
            case 2:
                return LETTER_BUTTON_GREEN_BACKGROUND_COLOR;
            case 1:
                return LETTER_BUTTON_YELLOW_BACKGROUND_COLOR;
            case 0:
                return LETTER_BUTTON_GRAY_BACKGROUND_COLOR;
            default:
                return LETTER_BUTTON_STARTING_BACKGROUND_COLOR;
        }
    }

    public static int getStatusForColor(Color color) {
        if (color.equals(LETTER_BUTTON_GREEN_BACKGROUND_COLOR)) {
            return 2;
        } else if (color.equals(LETTER_BUTTON_YELLOW_BACKGROUND_COLOR)) {
            return 1;
        } else if (color.equals(LETTER_BUTTON_GRAY_BACKGROUND_COLOR)) {
            return 0;
        } else {
            return -1;
        }
    }

    public static Color getNextColor(Color color) {
        if (color.equals(LETTER_BUTTON_GREEN_BACKGROUND_COLOR)) {
            return LETTER_BUTTON_GRAY_BACKGROUND_COLOR;
        } else if (color.equals(LETTER_BUTTON_YELLOW_BACKGROUND_COLOR)) {
            return LETTER_BUTTON_GREEN_BACKGROUND_COLOR;
        } else if (color.equals(LETTER_BUTTON_GRAY_BACKGROUND_COLOR)) {
            return LETTER_BUTTON_YELLOW_BACKGROUND_COLOR;
        } else {
            return LETTER_BUTTON_GRAY_BACKGROUND_COLOR;
        }
    }
}