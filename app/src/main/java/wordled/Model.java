package wordled;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Model implements WordleModel {
    private ArrayList<String> validWords;
    private String lastGuess;
    private static final String STARTING_GUESS = "tales";

    public Model() {
        this.validWords = new ArrayList<>();
        this.lastGuess = "";
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
        System.out.println("pool size: " + String.valueOf(this.validWords.size()));

        if (this.lastGuess.equals("")) {
            this.lastGuess = Model.STARTING_GUESS;
            return Model.STARTING_GUESS;
        }

        this.lastGuess = this.validWords.get(0);
        this.validWords.remove(0);
        return this.lastGuess;
    }

    private int countOfCharInString(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) count++;
        }
        return count;
    }

    public void update(int[] result) {
        // process input
        ArrayList<Character> badLetters = new ArrayList<>();
        ArrayList<LetterPair> partialLetters = new ArrayList<>();
        ArrayList<LetterPair> correctLetters = new ArrayList<>();

        ArrayList<Character> presentLetters = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            char c = this.lastGuess.charAt(i);
            switch (result[i]) {
                case 0:
                    badLetters.add(c);
                    break;
                case 1:
                    partialLetters.add(new LetterPair(c, i));
                    presentLetters.add(c);
                    break;
                case 2:
                    correctLetters.add(new LetterPair(c, i));
                    presentLetters.add(c);
                    break;
                default:
                    System.err.println("Error: invalid letter status");
            }
        }

        for (int i = 0; i < this.validWords.size(); i++) { // filter down valid words
            boolean badWord = false;
            String w = this.validWords.get(i);

            // filter out words with bad letters (but accounting for multiple letters)
            for (char c : badLetters) {
                if (w.contains(String.valueOf(c))) {
                    if (!presentLetters.contains(c)) {
                        badWord = true;
                        break;
                    }
                }
            }

            if (badWord) {
                this.validWords.remove(i);
                i--;
                continue;
            }

            // filter out words without correct letters in correct positions
            for (LetterPair p : correctLetters) {
                if (w.charAt(p.index) != p.letter) {
                    badWord = true;
                    break;
                }
            }

            if (badWord) {
                this.validWords.remove(i);
                i--;
                continue;
            }

            // filter out words with partial letters in wrong positions
            for (LetterPair p : partialLetters) {
                if (w.charAt(p.index) == p.letter) {
                    badWord = true;
                    break;
                }
            }

            // filter out words without all present letters
            for (char c : presentLetters) {
                if (!w.contains(String.valueOf(c))) {
                    badWord = true;
                    break;
                }
            }

            if (badWord) {
                this.validWords.remove(i);
                i--;
                continue;
            }

            // filter out words with wrong amount of letters
            for (char c : badLetters) {
                if (presentLetters.contains(c)) {
                    // if words frequency of a letter does not equal results frequency of a letter
                    if (Collections.frequency(presentLetters, c) != countOfCharInString(w, c)) {
                        badWord = true;
                        break;
                    }
                }
            }

            if (badWord) {
                this.validWords.remove(i);
                i--;
                continue;
            }
        }
    }
}