package edu.bsu.cs222;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

class LevelThreePaneLayout extends QuizPaneLayout {

    private final int TIME_LIMIT=30;
    private IntegerProperty seconds = new SimpleIntegerProperty(TIME_LIMIT);
    private Label countDown = new Label();
    private Timeline timeline = new Timeline();

    @Override
    public Label levelAndQuestionDisplay(){
        levelAndQuestion = super.levelAndQuestionDisplay();
        levelAndQuestion.setText("Hard, Question " + getQuestionNumber());
        return levelAndQuestion;
    }

    @Override
    public BorderPane rightPane(){
        BorderPane rightPaneThree= super.rightPane();
        leftSide.getChildren().remove(timer());
        leftSide.getChildren().add(timer());
        return rightPaneThree;
    }

    private Label timer(){
        countDown.setFont(new Font("Cambria",TEXT_SIZE));
        countDown.textProperty().bind(seconds.asString());
        seconds.set(TIME_LIMIT);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(TIME_LIMIT+1), new KeyValue(seconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(ActionEvent-> calculateButton.fire());
        return countDown;
    }

    @Override
    public void showScore() {
        super.showScore();
        resetTimer();
    }

    @Override
    protected void displayCorrect(){
        super.displayCorrect();
        resetTimer();
    }

    protected void displayWrong(){
        super.displayWrong();
        retryButton().setVisible(false);
        resetTimer();
    }

    private void resetTimer(){
        timeline.setOnFinished(ActionEvent-> timeline.stop());
        leftSide.getChildren().remove(timer());
    }
}
