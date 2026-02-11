package wordled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Model implements WordleModel {
    private ArrayList<String> validWords;
    private String lastGuess;
    private static final String STARTING_GUESS = "tales";
    private Random random;

    public Model() {
        this.validWords = new ArrayList<>();
        this.lastGuess = "";
        this.random = new Random();
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
            // System.out.println("file not loaded");
            return -1;
        }
    }

    public int characterDiversity(String s) {
        Set<Character> seen = new HashSet<>();
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!seen.contains(s.charAt(i))) {
                score++;
                seen.add(s.charAt(i));
            }
        }
        return score;
    }

    private int[][] wordListFrequencies() {
        int[][] freq = new int[26][5];
        for (String w : this.validWords) {
            for (int i = 0; i < 5; i++) {
                char c = w.charAt(i);
                freq[c - 'a'][i]++;
            }
        }
        return freq;
    }

    private int[] maxFrequencyPerPosition(int[][] freq) {
        int[] maxPos = new int[5];
        for (int i = 0; i < 26; i++) {
            for (int p = 0; p < 5; p++) {
                if (freq[i][p] > maxPos[p])
                    maxPos[p] = freq[i][p];
            }
        }
        return maxPos;
    }

    private double scoreWord(String w, int[][] freqs, int[] maxPos) {
        double score = 1.0;
        for (int i = 0; i < 5; i++) {
            char c = w.charAt(i);
            int freq = (c >= 'a' && c <= 'z') ? freqs[c - 'a'][i] : 0;
            int diff = freq - maxPos[i];
            score *= (1.0 + (double) diff * diff);
        }
        score += this.random.nextDouble();
        return score;
    }

    private String pickBestWord() {
        if (this.validWords.isEmpty())
            return null;
        int[][] freq = wordListFrequencies();
        int[] maxPos = maxFrequencyPerPosition(freq);

        String best = this.validWords.get(0);
        double bestScore = Double.POSITIVE_INFINITY;

        for (String w : this.validWords) {
            if (w.length() != 5)
                continue;
            double s = scoreWord(w, freq, maxPos);
            if (s < bestScore) {
                bestScore = s;
                best = w;
            }
        }
        return best;
    }

    public String getGuess() {
        // System.out.println("pool size: " + String.valueOf(this.validWords.size()));

        if (this.lastGuess.equals("")) {
            System.out.println("starting guess trigger");
            this.lastGuess = Model.STARTING_GUESS;
            return Model.STARTING_GUESS;
        }

        this.lastGuess = this.pickBestWord();
        System.out.println("best guess: " + this.lastGuess);
        this.validWords.remove(this.lastGuess);
        return this.lastGuess;
    }

    private int countOfCharInString(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                count++;
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
