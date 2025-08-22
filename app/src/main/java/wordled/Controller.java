package wordled;

import wordled.view.LettersPanel;

public class Controller {
    private Model model;
    private LettersPanel lettersPanel;

    public Controller() {
        this.model = new Model();
    }

    public void handleLetterButtonClick(int index) {
        model.cycleLetterStatus(index);
        updateView();
    }

    public void handleNextWordButtonClick() {
        model.generateNextWord();
        updateView();
    }
    
    public void updateView() {
        lettersPanel.updateButtons(model.getCurrentWord(), model.getLetterStatuses());
    }
    
    public void setLettersPanel(LettersPanel lettersPanel) {
        this.lettersPanel = lettersPanel;
        updateView();
    }
}