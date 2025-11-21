package wordled;

public interface WordleController {
    public void setColor(int letterIndex, int color);
    public void submit();
    public char[] reportGuess();
}
