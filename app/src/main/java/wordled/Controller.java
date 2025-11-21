package wordled;

import java.io.File;

public class Controller implements WordleController {
    private int[] letterColors;
    private WordleModel model;

    public Controller(File f) {
        this.letterColors = new int[] {-1, -1, -1, -1, -1};
        this.model = new Model();
        this.model.setInitialWords(f);
    }

    public int[] getLetterColors() {
        return this.letterColors;
    }

    @Override
    public void setColor(int letterIndex, int color) { // 0 = gray, 1 = yellow, 2 = green
        this.letterColors[letterIndex] = color;
    }

    @Override
    public void submit() {
        this.model.update(this.letterColors);
    }

    @Override
    public char[] reportGuess() {
        char[] guessArr = new char[5];
        String guessString = this.model.getGuess();
        for (int i = 0; i < 5; i++) {
            guessArr[i] = guessString.charAt(i);
        }
        return guessArr;
    }

}
