package edu.bsu.cs222;

import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

class BaseCalculatorWindow implements Observer {

    private FixedBaseNumber currentInput;
    private BorderPane mainLayout;
    private BorderPane bottomPane = new BorderPane();
    private ToggleGroup radioButtonGroup = new ToggleGroup();
    private TextField inputField;
    private boolean validLength = true;
    private final int BINARY_BASE = 2;
    private final int DECIMAL_BASE = 10;
    private final int HEX_BASE = 16;

    public BorderPane CalculatorLayout() {
        mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #00CC99;");
        bottomPaneOfMainLayout();
        inputField();
        conversionAnswers();
        placeRadioButtonOnTop();
        labels();
        return mainLayout;
    }

    private BorderPane bottomPaneOfMainLayout() {
        mainLayout.setBottom(bottomPane);
        return mainLayout;
    }

    private Label binaryLabel() {
        Label binaryLabel = new Label();
        binaryLabel.setText("Binary");
        return binaryLabel;
    }

    private Label decimalLabel() {
        Label decimalLabel = new Label();
        decimalLabel.setText("Decimal");
        return decimalLabel;
    }

    private Label hexadecimalLabel() {
        Label hexadecimalLabel = new Label();
        hexadecimalLabel.setText("Hexadecimal");
        return hexadecimalLabel;
    }

    private BorderPane labels() {
        BorderPane labels = new BorderPane();
        labels.setLeft(binaryLabel());
        labels.setCenter(decimalLabel());
        labels.setRight(hexadecimalLabel());
        bottomPane.setTop(labels);
        mainLayout.setBottom(bottomPane);
        return mainLayout;
    }

    private BorderPane invalidLengthLayout() {
        bottomPane.setLeft(invalidLengthMessage());
        bottomPane.setCenter(invalidLengthMessage());
        bottomPane.setRight(invalidLengthMessage());
        return bottomPane;
    }

    private TextField invalidLengthMessage() {
        TextField invalidLengthMessage = new TextField();
        invalidLengthMessage.setEditable(false);
        invalidLengthMessage.setCursor(Cursor.TEXT);
        invalidLengthMessage.setText("Too many digits!");
        return invalidLengthMessage;
    }

    private BorderPane newAnswerPlacement() {
        if (validLength) {
            bottomPane.setLeft(newBaseAnswer(BINARY_BASE));
            bottomPane.setCenter(newBaseAnswer(DECIMAL_BASE));
            bottomPane.setRight(newBaseAnswer(HEX_BASE));
            return bottomPane;
        } else {
            setValidLength(true);
            bottomPane = invalidLengthLayout();
            return bottomPane;
        }
    }

    private TextField newBaseAnswer(int base) {
        TextField newBaseAnswer = new TextField();
        newBaseAnswer.setText(currentInput.toNumberString(base));
        newBaseAnswer.setCursor(Cursor.TEXT);
        newBaseAnswer.setEditable(false);
        return newBaseAnswer;
    }

    private TextField inputField() {
        final int TEXT_SIZE = 20;
        inputField = new TextField();
        inputField.setPromptText("Enter a number");
        inputField.setCursor(Cursor.TEXT);
        inputField.setFont(Font.font(TEXT_SIZE));
        mainLayout.setCenter(inputField);
        return inputField;
    }

    private TextField binaryAnswer() {
        TextField binaryAnswer = new TextField();
        binaryAnswer.setPromptText("Binary");
        binaryAnswer.setCursor(Cursor.TEXT);
        binaryAnswer.setEditable(false);
        return binaryAnswer;
    }

    private TextField decimalAnswer() {
        TextField decimalAnswer = new TextField();
        decimalAnswer.setPromptText("Decimal");
        decimalAnswer.setCursor(Cursor.TEXT);
        decimalAnswer.setEditable(false);
        return decimalAnswer;
    }

    private TextField hexadecimalAnswer() {
        TextField hexadecimalAnswer = new TextField();
        hexadecimalAnswer.setPromptText("Hexadecimal");
        hexadecimalAnswer.setCursor(Cursor.TEXT);
        hexadecimalAnswer.setEditable(false);
        return hexadecimalAnswer;
    }

    private BorderPane conversionAnswers() {
        bottomPane.setLeft(binaryAnswer());
        bottomPane.setCenter(decimalAnswer());
        bottomPane.setRight(hexadecimalAnswer());
        return bottomPane;
    }

    private HBox radioButtonLayout() {
        HBox hBoxRadioButtons = new HBox();
        hBoxRadioButtons.getChildren().add(baseTwoAnswer());
        hBoxRadioButtons.getChildren().add(baseTenAnswer());
        hBoxRadioButtons.getChildren().add(baseSixteenAnswer());
        hBoxRadioButtons.setSpacing(DECIMAL_BASE);
        hBoxRadioButtons.setStyle("-fx-background-color: #999999;");
        return hBoxRadioButtons;
    }

    private RadioButton baseTwoAnswer() {
        RadioButton baseTwoAnswer = new RadioButton("binary");
        baseTwoAnswer.setToggleGroup(radioButtonGroup);
        baseTwoAnswer.setUserData(BINARY_BASE);
        baseTwoAnswer.setSelected(true);
        return baseTwoAnswer;
    }

    private RadioButton baseTenAnswer() {
        RadioButton baseTenAnswer = new RadioButton("decimal");
        baseTenAnswer.setToggleGroup(radioButtonGroup);
        baseTenAnswer.setUserData(DECIMAL_BASE);
        return baseTenAnswer;
    }

    private RadioButton baseSixteenAnswer() {
        RadioButton baseSixteenAnswer = new RadioButton("hexadecimal");
        baseSixteenAnswer.setToggleGroup(radioButtonGroup);
        baseSixteenAnswer.setUserData(HEX_BASE);
        return baseSixteenAnswer;
    }

    private BorderPane placeRadioButtonOnTop() {
        mainLayout.setTop(radioButtonLayout());
        return mainLayout;
    }

    public void setValidLength(boolean validLength) {
        this.validLength = validLength;
    }

    public TextField getInputField() {
        return inputField;
    }

    public ToggleGroup getRadioButtonGroup() {
        return radioButtonGroup;
    }

    private void setCurrentInput(FixedBaseNumber currentInput) {
        this.currentInput = currentInput;
    }

    @Override
    public void update(FixedBaseNumber currentQuestion) {
        setCurrentInput(currentQuestion);
        conversionAnswers();
        newAnswerPlacement();
    }
}
