package wordled.model;

import wordled.LetterStatus;

public class Model {
    private String currentWord;
    private LetterStatus[] letterStatuses;

    public Model() {
        this.currentWord = "TALES";
        this.letterStatuses = new LetterStatus[5];
        for (int i = 0; i < 5; i++) {
            letterStatuses[i] = LetterStatus.EMPTY;
        }
    }

    public void cycleLetterStatus(int index) {
        LetterStatus current = letterStatuses[index];
        switch (current) {
            case EMPTY:
                letterStatuses[index] = LetterStatus.GREEN;
                break;
            case GREEN:
                letterStatuses[index] = LetterStatus.YELLOW;
                break;
            case YELLOW:
                letterStatuses[index] = LetterStatus.GRAY;
                break;
            case GRAY:
                letterStatuses[index] = LetterStatus.GREEN;
                break;
        }
    }

    public void generateNextWord() {
        this.currentWord = "SMILE"; // dummy
        for (int i = 0; i < 5; i++) {
            letterStatuses[i] = LetterStatus.EMPTY;
        }
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public LetterStatus getLetterStatus(int index) {
        return letterStatuses[index];
    }

    public LetterStatus[] getLetterStatuses() {
        LetterStatus[] letterStatuses = new LetterStatus[5];
        for (int i = 0; i < 5; i++) {
            letterStatuses[i] = this.letterStatuses[i];
        }
        return letterStatuses;
    }

    public boolean readyForNextWord() {
        for (LetterStatus l : this.letterStatuses) {
            if (l == LetterStatus.EMPTY) return false;
        }
        return true;
    }
}