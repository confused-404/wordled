import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Model implements WordleModel {
    private ArrayList<String> validWords;
    private Random random = new Random();

    public Model() {
        this.validWords = new ArrayList<>();
    }

    public int setInitialWords(File f) {
        this.validWords.clear();

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    this.validWords.add(word);
                }
            }
            return this.validWords.size();
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public String getGuess() {
        int min = 0;
        int max = this.validWords.size() - 1;
        int randomIndex = random.nextInt(max - min + 1) + min;
        return this.validWords.get(randomIndex);
    }

    public void update(int[] result) {

    }
}