package edu.bsu.cs222;


import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class TutorialPaneLayout {
    private Stage stage = new Stage();
    private Image tutorialImage;
    private BorderPane tutorial;
    private boolean binaryToDecimalTutorial = false;
    private boolean binaryToHexadecimalTutorial = false;
    private boolean decimalToHexadecimalTutorial = false;
    private int currentTutorialImage = 0;
    private TutorialResourceList tutorialResourceList = new TutorialResourceList();

    public Scene tutorialScene(){
        return new Scene(tutorials(), 500, 300);
    }

    public BorderPane tutorials(){
        tutorial = new BorderPane();
        tutorialResourceList.addImagesToBinaryToDecimalArray();
        tutorialResourceList.addImagesToBinaryToHexadecimalArray();
        tutorialResourceList.addImagesToDecimalToHexadecimalArray();
        tutorial.setStyle("-fx-background-color: #00CC99;");
        tutorial.setBottom(createHBox());
        tutorial.setCenter(new ImageView(tutorialImage));
        return tutorial;
    }

    private HBox createHBox(){
        HBox navigationButtons = tutorialSelectMenu();
        navigationButtons.getChildren().addAll(previousTutorialPage(),exitTutorialPage(), nextTutorialPage());
        navigationButtons.setAlignment(Pos.CENTER);
        return navigationButtons;
    }

    public HBox tutorialSelectMenu() {
        return new HBox(30);
    }

    private Button previousTutorialPage(){
        Button previousButton = new Button("Previous");
        previousButton.setCursor(Cursor.HAND);
        previousButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        previousButton.setOnAction(event ->
                checkTutorialForPrevious());
        return previousButton;
    }

    private Button exitTutorialPage(){
        Button exitButton = new Button("Exit");
        exitButton.setCursor(Cursor.HAND);
        exitButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        exitButton.setOnAction(event -> {
            stage.close();
        });
        return exitButton;
    }

    private Button nextTutorialPage(){
        Button nextButton = new Button("Next");
        nextButton.setCursor(Cursor.HAND);
        nextButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        nextButton.setOnAction(event ->
             checkTutorialForNext());
            return nextButton;
    }

    private void checkTutorialForPrevious(){
        if(binaryToDecimalTutorial && !binaryToHexadecimalTutorial && !decimalToHexadecimalTutorial) {
            displayPreviousImageBinaryToDecimal();
        }
        if(binaryToHexadecimalTutorial && !binaryToDecimalTutorial && !decimalToHexadecimalTutorial) {
            displayPreviousImageBinaryToHexadecimal();
        }
        if(!binaryToHexadecimalTutorial && !binaryToDecimalTutorial && decimalToHexadecimalTutorial) {
            displayPreviousImageDecimalToHexadecimal();
        }
    }

    private void displayPreviousImageBinaryToDecimal(){
        if(currentTutorialImage <= 0) {
            return;
        }else{
            currentTutorialImage -= 1;
        }
        tutorialImage = tutorialResourceList.binaryToDecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    private void displayPreviousImageBinaryToHexadecimal(){
        if(currentTutorialImage <= 0) {
            return;
        }else{
            currentTutorialImage -= 1;
        }
        tutorialImage = tutorialResourceList.binaryToHexadecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    private void displayPreviousImageDecimalToHexadecimal(){
        if(currentTutorialImage <= 0) {
            return;
        }else{
            currentTutorialImage -= 1;
        }
        tutorialImage = tutorialResourceList.decimalToHexadecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    private void checkTutorialForNext(){
        if(binaryToDecimalTutorial && !binaryToHexadecimalTutorial && !decimalToHexadecimalTutorial) {
            displayNextImageBinaryToDecimal();
        }
        if(binaryToHexadecimalTutorial && !binaryToDecimalTutorial && !decimalToHexadecimalTutorial) {
            displayNextImageBinaryToHexadecimal();
        }
        if(!binaryToHexadecimalTutorial && !binaryToDecimalTutorial && decimalToHexadecimalTutorial) {
            displayNextImageDecimalToHexadecimal();
        }
    }

    private void displayNextImageBinaryToDecimal(){
        if(currentTutorialImage >= 1) {
            return;
        }else{
            currentTutorialImage += 1;
        }
        tutorialImage = tutorialResourceList.binaryToDecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    private void displayNextImageBinaryToHexadecimal(){
        if(currentTutorialImage >= 2) {
            return;
        }else{
            currentTutorialImage += 1;
        }
        tutorialImage = tutorialResourceList.binaryToHexadecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    private void displayNextImageDecimalToHexadecimal(){
        if(currentTutorialImage >= 3) {
            return;
        }else{
            currentTutorialImage += 1;
        }
        tutorialImage = tutorialResourceList.decimalToHexadecimalImages.get(currentTutorialImage);
        tutorial.setCenter(new ImageView(tutorialImage));
    }

    public Button binToDecButton(){
        Button binToDec = new Button("Bin --> Dec");
        binToDec.setCursor(Cursor.HAND);
        binToDec.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        binToDec.setOnAction(event -> {
            currentTutorialImage = 0;
            binaryToDecimalTutorial = true;
            binaryToHexadecimalTutorial = false;
            decimalToHexadecimalTutorial = false;
            tutorialImage = new Image(getClass().getResourceAsStream("/binaryToDecimalPage1.png"));
            stage.setResizable(false);
            stage.setTitle("Base Calculator");
            stage.setScene(tutorialScene());
            stage.show();
        });
        return binToDec;
    }

    public Button binToHexButton(){
        Button binToHex = new Button("Bin --> Hex");
        binToHex.setCursor(Cursor.HAND);
        binToHex.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        binToHex.setOnAction(event -> {
            currentTutorialImage = 0;
            binaryToDecimalTutorial = false;
            binaryToHexadecimalTutorial = true;
            decimalToHexadecimalTutorial = false;
            tutorialImage = new Image(getClass().getResourceAsStream("/binaryToHexadecimalPage1.png"));
            stage.setResizable(false);
            stage.setTitle("Base Calculator");
            stage.setScene(tutorialScene());
            stage.show();
        });
        return binToHex;
    }

    public Button decToHexButton(){
        Button decToHex = new Button("Dec --> Hex");
        decToHex.setCursor(Cursor.HAND);
        decToHex.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        decToHex.setOnAction(event -> {
            currentTutorialImage = 0;
            binaryToDecimalTutorial = false;
            binaryToHexadecimalTutorial = false;
            decimalToHexadecimalTutorial = true;
            tutorialImage = new Image(getClass().getResourceAsStream("/decimalToHexadecimalPage1.png"));
            stage.setResizable(false);
            stage.setTitle("Base Calculator");
            stage.setScene(tutorialScene());
            stage.show();
        });
        return decToHex;
    }
}


