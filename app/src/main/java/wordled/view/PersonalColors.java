package wordled.view;

import java.awt.*;
import wordled.LetterStatus;

public class PersonalColors {
    public static final Color LETTER_BUTTON_STARTING_BACKGROUND_COLOR = new Color(0, 0, 0, 230);
    public static final Color LETTER_BUTTON_STARTING_BORDER_COLOR = Color.decode("#818181");
    public static final Color LETTER_BUTTON_GREEN_BACKGROUND_COLOR = Color.decode("#0A7D00");
    public static final Color LETTER_BUTTON_YELLOW_BACKGROUND_COLOR = Color.decode("#D5BC00");
    public static final Color LETTER_BUTTON_GRAY_BACKGROUND_COLOR = Color.decode("#404040");
    
    public static final Color NEXT_WORD_BUTTON_COLOR = Color.decode("#4D4D4D");

    public static Color getColorForStatus(LetterStatus status) {
        switch (status) {
            case EMPTY:
                return LETTER_BUTTON_STARTING_BACKGROUND_COLOR;
            case GREEN:
                return LETTER_BUTTON_GREEN_BACKGROUND_COLOR;
            case YELLOW:
                return LETTER_BUTTON_YELLOW_BACKGROUND_COLOR;
            case GRAY:
                return LETTER_BUTTON_GRAY_BACKGROUND_COLOR;
            default:
                return LETTER_BUTTON_STARTING_BACKGROUND_COLOR;
        }
    }
}