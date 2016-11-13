package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class MainMenuPane {

    Label mainQuestion= new Label();

    public BorderPane makeMainMenu() {
        BorderPane centralPane = new BorderPane();
        BorderPane top = new BorderPane();
        top.setTop(menuQuestion());
        centralPane.setTop(top);
        top.setStyle("-fx-background-color: #999999;");
        centralPane.setStyle("-fx-background-color: #00CC99;");
        BorderPane.setAlignment(mainQuestion, Pos.CENTER);
        return centralPane;
    }

    public Label menuQuestion(){
        mainQuestion.setText("Main Menu");
        int MENU_SIZE = 18;
        mainQuestion.setFont(Font.font("Arial", FontWeight.BOLD, MENU_SIZE));
        mainQuestion.setTextFill(Color.WHITE);
        return mainQuestion;
    }

    public VBox makeSelectMenu() {
        return new VBox(30);
    }
}