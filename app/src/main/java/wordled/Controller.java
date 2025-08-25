package wordled;

public class Controller implements WordleController {
    int[] letterColors;

    public Controller() {
        this.letterColors = new int[5];
    }

    @Override
    public void setColor(int letterIndex, int color) { // 0 = gray, 1 = yellow, 2 = green
        this.letterColors[letterIndex] = color;
    }

    @Override
    public void submit() {
        // send current letterColors as result to Model
    }

    @Override
    public char[] reportGuess() {
        // convert String guess from Model into a char array to forward to GUI
    }

}
