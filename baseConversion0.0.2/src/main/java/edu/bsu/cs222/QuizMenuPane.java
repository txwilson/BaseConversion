package edu.bsu.cs222;

import javafx.scene.control.Label;

class QuizMenuPane extends MainMenuPane {

    public Label menuQuestion(){
        mainQuestion = super.menuQuestion();
        mainQuestion.setText("Select Quiz Difficulty");
        return mainQuestion;
    }
}