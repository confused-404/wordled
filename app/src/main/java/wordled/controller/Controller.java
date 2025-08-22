package wordled.controller;

import wordled.model.Model;
import wordled.view.LettersPanel;

public class Controller {
    private Model model;
    private LettersPanel lettersPanel;

    public Controller() {
        this.model = new Model();
    }

    public void handleLetterButtonClick(int index) {
        this.model.cycleLetterStatus(index);
        updateView();
    }

    public void handleNextWordButtonClick() {
        if (!model.readyForNextWord()) {
            this.lettersPanel.showNotReadyDialog();
            return;
        }
        this.model.generateNextWord();
        updateView();
    }
    
    public void updateView() {
        lettersPanel.updateButtons(model.getCurrentWord(), this.model.getLetterStatuses());
    }
    
    public void setLettersPanel(LettersPanel lettersPanel) {
        this.lettersPanel = lettersPanel;
        updateView();
    }
}