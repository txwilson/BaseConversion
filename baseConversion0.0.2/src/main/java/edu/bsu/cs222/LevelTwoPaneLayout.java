package edu.bsu.cs222;


import javafx.scene.control.Label;

class LevelTwoPaneLayout extends QuizPaneLayout implements Observer {

    @Override
    public Label levelAndQuestionDisplay(){
        levelAndQuestion = super.levelAndQuestionDisplay();
        levelAndQuestion.setText("Medium, Question " + getQuestionNumber());
        return levelAndQuestion;
    }

}
