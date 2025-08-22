package wordled.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wordled.LetterStatus;

public class Solver {
    private HashMap<String, ArrayList<Character>> processInput(LetterStatus[] statuses, String guess) {
        ArrayList<Character> badLetters = new ArrayList<Character>();
        ArrayList<Character> partialLetters = new ArrayList<Character>();
        ArrayList<Character> correctLetters = new ArrayList<Character>();

        for (int i = 0; i < 5; i++) {
            switch (statuses[i]) {
                case LetterStatus.GRAY:
                    badLetters.add(i, guess.charAt(i));
                    break;
                case LetterStatus.YELLOW:
                    partialLetters.add(i, guess.charAt(i));
                    break;
                case LetterStatus.GREEN:
                    correctLetters.add(i, guess.charAt(i));
                    break;
                case LetterStatus.EMPTY:
                    System.err.println("Should not have been reached, empty letter in calculations");
                    break;
            }
        }

        HashMap<String, ArrayList<Character>> results = new HashMap<>();

        results.put("badLetters", badLetters);
        results.put("partialLetters", partialLetters);
        results.put("correctLetters", correctLetters);

        return results;
    }

    public ArrayList<String> filterPossibleWords(LetterStatus[] statuses, String guess, ArrayList<String> possibleWords) {
        HashMap<String, ArrayList<Character>> letterResults = processInput(statuses, guess);
        ArrayList<Character> badLetters = letterResults.get("badLetters");
        ArrayList<Character> partialLetters = letterResults.get("partialLetters");
        ArrayList<Character> correctLetters = letterResults.get("correctLetters");

        ArrayList<Character> presentLetters = new ArrayList<Character>();
        for (int i = 0; i < partialLetters.size(); i++) {
            presentLetters.add(partialLetters.get(i));
        }
        for (int i = 0; i < correctLetters.size(); i++) {
            presentLetters.add(correctLetters.get(i));
        }

        
        for (int i = 0; i < possibleWords.size(); i++) {
            boolean wordRemoved = false;
            // filter out all words with bad letters in them
            for (int j = 0; j < badLetters.size(); j++) {
                if (possibleWords.get(i).contains(String.valueOf(badLetters.get(j)))) {
                    possibleWords.remove(i);
                    i--; // account for removal shifting indices
                    wordRemoved = true;
                }
            }

            if (wordRemoved) continue;

            // filter out all words without correct letters in correct positions
            for (int j = 0; j < correctLetters.size(); j++) {
                if (correctLetters.get(j) == 0) continue; // empty index
                if (possibleWords.get(i).charAt(j) != correctLetters.get(j)) {
                    possibleWords.remove(i);
                    i--;
                    wordRemoved = true;
                }
            }

            if (wordRemoved) continue;

            // filter out all words that do not contain partial letters
            for (int j = 0; j < partialLetters.size(); j++) {
                int indexOfPartialLetter = possibleWords.get(i).indexOf(String.valueOf(partialLetters.get(j)));
                if (indexOfPartialLetter == -1 || indexOfPartialLetter == j) {
                    possibleWords.remove(i);
                    i--; // account for removal shifting indices
                    wordRemoved = true;
                }
            }

            if (wordRemoved) continue;
        }

        return null;
    }
}
