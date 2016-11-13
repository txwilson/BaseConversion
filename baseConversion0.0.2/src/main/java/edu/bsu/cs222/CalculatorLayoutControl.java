package edu.bsu.cs222;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

class CalculatorLayoutControl {

    private FixedBaseNumber newBase;
    private String userInput;
    private BaseCalculatorWindow calculator1 = new BaseCalculatorWindow();
    private BorderPane newPane;

    public Scene calculatorLayout() {
        setNewBase(new FixedBaseNumber(0));
        newBase.registerObserver(calculator1);
        newPane = calculator1.CalculatorLayout();
        BorderPane rightSide = new BorderPane();
        rightSide.setCenter(calculateButton(calculator1.getRadioButtonGroup(), calculator1.getInputField()));
        newPane.setRight(rightSide);
        Scene calcScene = new Scene(newPane, 450, 100);
        return calcScene;
    }

    private void setNewBase(FixedBaseNumber newBase) {
        this.newBase = newBase;
    }

    private Button calculateButton(ToggleGroup radioButtons, TextField inputField) {
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(event -> {
            String currentNumber = inputField.getText();
            setUserInput(currentNumber);
            determineBase(radioButtons);
        });
        return calculateButton;
    }

    private void determineBase(ToggleGroup radioButtons) {
        FixedBaseNumber entryNumber;
        if (radioButtons.getSelectedToggle().getUserData() == Integer.valueOf(2)) {
            entryNumber = FixedBaseNumber.parseBinary(userInput);
        } else if (radioButtons.getSelectedToggle().getUserData() == Integer.valueOf(10)) {
            entryNumber = FixedBaseNumber.parseDecimal(userInput);
        } else {
            entryNumber = FixedBaseNumber.parseHex(userInput);
        }
        int parsedInt = entryNumber.getDecimalValue();
        newBase.setDecimalValue(parsedInt);
        if (parsedInt == -1) {
            calculator1.setValidLength(false);
        }
        newBase.notifyObservers();
    }

    private void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
