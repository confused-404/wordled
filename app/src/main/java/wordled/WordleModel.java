package wordled;

import java.io.File;

public interface WordleModel {
    public int setInitialWords(File f);
    public String getGuess();
    public void update(int[] result);
}
    