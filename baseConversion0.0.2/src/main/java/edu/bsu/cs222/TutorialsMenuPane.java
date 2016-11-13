package edu.bsu.cs222;

import javafx.scene.control.Label;

public class TutorialsMenuPane extends MainMenuPane {

    public Label menuQuestion() {
        mainQuestion = super.menuQuestion();
        mainQuestion.setText("Select Tutorial");
        return mainQuestion;
    }
}
