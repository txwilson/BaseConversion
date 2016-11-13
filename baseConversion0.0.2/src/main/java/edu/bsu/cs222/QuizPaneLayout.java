package edu.bsu.cs222;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class QuizPaneLayout implements Observer {

    private int submittedAnswer;
    private int questionNumber = 1;
    private int retryTurn = 0;
    private int score=0;
    final int NUMBER_OF_QUESTIONS=5;
    final int TEXT_SIZE=20;
    private final int QUESTION_SIZE=17;
    private boolean enterKeyed=false;
    private FixedBaseNumber updatedQuestion= new FixedBaseNumber(0);
    private FixedBaseNumber currentInput;
    private BorderPane mainLayout = new BorderPane();
    private BorderPane topPaneBorder = new BorderPane();
    private BorderPane rightPaneBorder = new BorderPane();
    private BorderPane topPaneOfTopPane = new BorderPane();
    private Label convertFromLabel = new Label();
    private Label rightOrWrong = new Label();
    private Label rightOrWrongText = new Label();
    private Label question= new Label();
    protected Label levelAndQuestion= new Label();
    protected VBox leftSide= new VBox(QUESTION_SIZE);
    private Button retry = new Button();
    protected Button calculateButton = new Button("Calculate");
    private TextField userInput;

    public BorderPane mainWindowForQuiz(){
        mainLayout.setTop(topPane());
        mainLayout.setCenter(userInput());
        mainLayout.setRight(rightPane());
        mainLayout.setLeft(leftSide());
        mainLayout.setStyle("-fx-background-color: #00CC99;");
        mainLayout.getParent();

        return mainLayout;
    }

    private BorderPane topPane(){
        topPaneBorder.setTop(questionTopPaneOfTopPane());
        topPaneBorder.setCenter(questionLabel());
        topPaneBorder.setRight(numberLabel());
        topPaneBorder.setStyle("-fx-background-color: #999999;");
        return topPaneBorder;
    }

    private TextField userInput(){
        userInput = new TextField();
        final int TEXT_WIDTH = 320;
        userInput.setMaxWidth(TEXT_WIDTH);
        userInput.setPromptText("Type answer here!");
        userInput.setCursor(Cursor.TEXT);
        userInput.setFont(Font.font(TEXT_SIZE));
        enterPressed();
        enterKeyed=false;
        return userInput;
    }

    public BorderPane rightPane(){
        rightPaneBorder.setCenter(calculateButton());
        return rightPaneBorder;
    }

    private VBox leftSide(){
        leftSide.getChildren().removeAll(rightOrWrongText, rightOrWrong, retry);
        leftSide.getChildren().addAll(rightOrWrongText, rightOrWrong,retry);
        final int LEFT_WIDTH = 70;
        leftSide.setMaxWidth(LEFT_WIDTH);
        rightOrWrong.setVisible(false);
        rightOrWrongText.setVisible(false);
        retry.setVisible(false);
        retryButton().setVisible(false);
        return leftSide;
    }

    private BorderPane questionTopPaneOfTopPane(){
        topPaneOfTopPane.setCenter(levelAndQuestionDisplay());
        return topPaneOfTopPane;
    }

    private Label questionLabel(){
        question.setVisible(true);
        question.setText("Covert this number from "+updatedQuestion.getStartBase()+" to "+(updatedQuestion.getEndBase())+": ");
        question.setFont(new Font("Arial", QUESTION_SIZE));
        question.setTextFill(Color.WHITE);
        return question;
    }

    private Label numberLabel(){
        convertFromLabel.setVisible(true);
        String question=convertToCorrectBase();
        convertFromLabel.setText(question);
        convertFromLabel.setFont(new Font("Arial", QUESTION_SIZE));
        convertFromLabel.setTextFill(Color.WHITE);
        return convertFromLabel;
    }

    private String convertToCorrectBase(){
        final int BASE_BINARY=2;
        final int BASE_DECIMAL=10;
        final int BASE_HEXADECIMAL=16;
        String base = updatedQuestion.getStartBase();
        switch (base){
            case "Binary":
                return updatedQuestion.toNumberString(BASE_BINARY);
            case "Hexadecimal":
                return updatedQuestion.toNumberString(BASE_HEXADECIMAL);
        }
        return updatedQuestion.toNumberString(BASE_DECIMAL);
    }

    private void enterPressed(){
        userInput.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER && !enterKeyed){
                calculateButton.fire();
                enterKeyed=true;
            }
        });
    }

    private Button calculateButton(){
        final int BUTTON_SIZE = 14;
        calculateButton.setFont(new Font("Arial", BUTTON_SIZE));
        calculateButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,3 );");
        calculateButton.setCursor(Cursor.HAND);
        calculateButton.setOnAction(event -> {
            submittedAnswer=formatInput();
            userInput.setEditable(false);
            checkIfCorrect();
        });
        return calculateButton;
    }

    private void checkIfCorrect(){
        if(submittedAnswer == updatedQuestion.getDecimalValue()){

            displayCorrect();
        }
        else{
            displayWrong();
        }
    }

    public Label levelAndQuestionDisplay(){
        levelAndQuestion.setText("Easy, Question " + getQuestionNumber());
        levelAndQuestion.setFont(Font.font("Arial", FontWeight.BOLD , QUESTION_SIZE));
        levelAndQuestion.setTextFill(Color.WHITE);
        return levelAndQuestion;
    }

    private int formatInput(){
        if(userInput.getText().isEmpty()){
            userInput.setText("0");
        }
        String base = updatedQuestion.getEndBase();
        return determineDecimalValue(base);
    }

    private int determineDecimalValue(String base){
        String answer = userInput.getText();
        answer=answer.toLowerCase();
        switch (base){
            case "Binary":
                currentInput= FixedBaseNumber.parseBinary(answer);
                return currentInput.getDecimalValue();
            case "Hexadecimal":
                currentInput=FixedBaseNumber.parseHex(answer);
                return currentInput.getDecimalValue();
            case "Decimal":
                return decimalCase(answer);
        }
        return 0;
    }

    private int decimalCase(String answer) {
        currentInput = FixedBaseNumber.parseDecimal(answer);
        return currentInput.getDecimalValue();
    }

    protected void displayCorrect(){
        Image image = new Image(getClass().getResourceAsStream("/small-green-check-mark.png"));
        rightOrWrong.setGraphic(new ImageView(image));
        rightOrWrongText.setText("CORRECT");
        rightOrWrongText.setTextFill(Color.WHITE);
        rightOrWrongText.setVisible(true);
        rightOrWrong.setTextFill(Color.WHITE);
        rightOrWrong.setVisible(true);
        retryButton().setVisible(false);
        if(retryTurn==0){
            score++;
        }
    }

    protected void displayWrong(){
        Image image = new Image(getClass().getResourceAsStream("/red-x-mark.png"));
        rightOrWrong.setGraphic(new ImageView(image));
        rightOrWrongText.setText("WRONG  ");
        rightOrWrongText.setTextFill(Color.WHITE);
        rightOrWrongText.setVisible(true);
        rightOrWrong.setVisible(true);
        final int RETRY_AMOUNT = 2;
        if(retryTurn < RETRY_AMOUNT){
            retryButton().setVisible(true);
        }
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public Button retryButton(){
        retry.setText("Retry");
        retry.setCursor(Cursor.HAND);
        retry.setOnAction(event ->  {
            retryTurn++;
            mainWindowForQuiz();
            userInput.setEditable(true);
            rightOrWrong.setVisible(false);
            rightOrWrongText.setVisible(false);
            retry.setVisible(false);
        });
        return retry;
    }

    public void showScore(){
        final int SCORE_SIZE = 35;
        question.setVisible(false);
        convertFromLabel.setVisible(false);
        levelAndQuestion.setText("Score: "+score+"/"+NUMBER_OF_QUESTIONS);
        levelAndQuestion.setFont(new Font("Arial", SCORE_SIZE));
        score=0;
        questionNumber=1;
    }

    public void setRetryTurn(int retryTurn) {
        this.retryTurn = retryTurn;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void resetPane(){
        mainLayout = makeSceneInstance();
    }

    public BorderPane makeSceneInstance() {
        return new BorderPane();
    }

    @Override
    public void update(FixedBaseNumber currentBaseQuestion) {
        this.updatedQuestion=currentBaseQuestion;
    }
}