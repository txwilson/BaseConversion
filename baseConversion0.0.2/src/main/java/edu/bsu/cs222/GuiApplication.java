package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiApplication extends Application {

    private Stage window;
    private Stage calcStage = new Stage();
    private TutorialPaneLayout tutorialPaneLayout = new TutorialPaneLayout();
    private QuizPaneLayout layout = new QuizPaneLayout();
    private MainMenuPane mainMenuPane = new MainMenuPane();
    private QuizMenuPane quizMenu = new QuizMenuPane();
    private TutorialsMenuPane tutorialMenu = new TutorialsMenuPane();
    private LevelTwoPaneLayout layoutTwo = new LevelTwoPaneLayout();
    private LevelThreePaneLayout layoutThree = new LevelThreePaneLayout();
    private BorderPane quizPane = new BorderPane();
    private BorderPane center = new BorderPane();
    private Button next = new Button("Next");
    private RandomNumberGenerator randomDecimal = new RandomNumberGenerator();
    private RandomBasesGenerator randomBases = new RandomBasesGenerator();
    private FixedBaseNumber fixedBase = new FixedBaseNumber(0);
    private CalculatorLayoutControl control = new CalculatorLayoutControl();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Base Calculator App");
        window.setScene(firstSceneLayout());
        window.show();
    }

    private Scene firstSceneLayout() {
        return new Scene(mainMenu(),500,250);
    }

    private BorderPane mainMenu(){
        BorderPane mainMenu = mainMenuPane.makeMainMenu();
        mainMenu.setCenter(centerPane());
        return mainMenu;
    }

    private VBox centerPane(){
        VBox selectMenu = mainMenuPane.makeSelectMenu();
        selectMenu.getChildren().addAll(goToCalculatorButton(), goToQuizButton(), goToTutorialsButton());
        selectMenu.setAlignment(Pos.CENTER);
        return selectMenu;
    }

    private Button goToCalculatorButton(){
        Button goToCalculator = new Button("Calculator");
        goToCalculator.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        goToCalculator.setCursor(Cursor.HAND);
        goToCalculator.setOnAction(event -> {
            calcStage.setResizable(false);
            calcStage.setTitle("Base Calculator");
            calcStage.setScene(control.calculatorLayout());
            calcStage.show();
        });
        return goToCalculator;
    }

    private Button goToTutorialsButton(){
        Button goToTutorials = new Button("Tutorials");
        goToTutorials.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        goToTutorials.setCursor(Cursor.HAND);
        goToTutorials.setOnAction(event -> window.setScene(changeToTutorial()));
        return goToTutorials;
    }

    private Button goToQuizButton(){
        Button goToQuiz = new Button("Quiz");
        goToQuiz.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        goToQuiz.setCursor(Cursor.HAND);
        goToQuiz.setOnAction(event -> window.setScene(changeToQuiz()));
        return goToQuiz;
    }

    private Scene changeToTutorial(){
        return new Scene(mainTutorialMenu(), 500, 250);
    }

    private Scene changeToQuiz() {
        return new Scene(mainQuizMenu(), 500, 250);
    }

    private Scene returnToMainMenu(){
        return new Scene(mainMenu(), 500, 250);

    }

    private BorderPane mainTutorialMenu(){
        BorderPane mainTutorial = tutorialMenu.makeMainMenu();
        mainTutorial.setCenter(centerTutorialPane());
        return mainTutorial;
    }

    private BorderPane mainQuizMenu(){
        BorderPane mainQuiz = quizMenu.makeMainMenu();
        mainQuiz.setCenter(centerQuizPane());
        return mainQuiz;
    }

    private VBox centerTutorialPane(){
        VBox selectLevels = tutorialMenu.makeSelectMenu();
        selectLevels.getChildren().addAll(tutorialPaneLayout.binToDecButton(),tutorialPaneLayout.binToHexButton(),tutorialPaneLayout.decToHexButton() ,returnToMenuButton());
        selectLevels.setAlignment(Pos.CENTER);
        return selectLevels;
    }

    private VBox centerQuizPane(){
        VBox selectLevels = quizMenu.makeSelectMenu();
        selectLevels.getChildren().addAll(levelOneButton(), levelTwoButton(), levelThreeButton(), returnToMenuButton());
        selectLevels.setAlignment(Pos.CENTER);
        return selectLevels;
    }

    private Button returnToMenuButton(){
        Button backButton = new Button("Main Menu");
        backButton.setCursor(Cursor.HAND);
        backButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        backButton.setOnAction(event -> {
            window.setScene(returnToMainMenu());
        });
        return backButton;
    }

    private Scene tutorialScene() {
        return new Scene(tutorialPaneLayout.tutorials(), 500, 300);
    }


    private Button levelOneButton() {
        Button levelOne = new Button("Easy");
        levelOne.setCursor(Cursor.HAND);
        levelOne.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        levelOne.setOnAction(event -> {
            fixedBase.setDecimalValue(randomDecimal.generateLevelOneDecimal());
            fixedBase.registerObserver(layout);
            randomBases.setRandomBasesOne(fixedBase);
            window.setScene(changeToQuizModeLevelOne());
        });
        return levelOne;
    }

    private Button levelTwoButton() {
        Button levelTwo = new Button("Medium");
        levelTwo.setCursor(Cursor.HAND);
        levelTwo.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        levelTwo.setOnAction(event -> {
            fixedBase.setDecimalValue(randomDecimal.generateLevelTwoDecimal());
            fixedBase.registerObserver(layoutTwo);
            randomBases.setRandomBasesTwo(fixedBase);
            window.setScene(changeToQuizModeLevelTwo());
        });
        return levelTwo;
    }

    private Button levelThreeButton() {
        Button levelThree = new Button("Hard");
        levelThree.setCursor(Cursor.HAND);
        levelThree.setStyle("-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,3,3 );");
        levelThree.setOnAction(event -> {
            fixedBase.setDecimalValue(randomDecimal.generateLevelThreeDecimal());
            fixedBase.registerObserver(layoutThree);
            randomBases.setRandomBasesThree(fixedBase);
            window.setScene(changeToQuizModeLevelThree());
        });
        return levelThree;
    }

    private Scene changeToQuizModeLevelTwo() {
        return new Scene(quizModeLevelTwo(), 500, 250);
    }

    private BorderPane quizModeLevelTwo() {
        quizPane = layoutTwo.mainWindowForQuiz();
        center = layoutTwo.makeSceneInstance();
        quizPane.setBottom(center);
        center.setRight(nextButtonTwo());
        return quizPane;
    }

    private Scene changeToQuizModeLevelThree() {
        return new Scene(quizModeLevelThree(), 500, 250);
    }

    private BorderPane quizModeLevelThree() {
        quizPane = layoutThree.mainWindowForQuiz();
        center = layoutThree.makeSceneInstance();
        quizPane.setBottom(center);
        center.setRight(nextButtonThree());
        return quizPane;
    }

    private Scene changeToQuizModeLevelOne() {
        return new Scene(quizModeLevelOne(), 500, 250);
    }

    private BorderPane quizModeLevelOne() {
        quizPane = layout.mainWindowForQuiz();
        center = layout.makeSceneInstance();
        quizPane.setBottom(center);
        center.setRight(nextButton());
        return quizPane;
    }

    private Button homeButton() {
        Button home = new Button("Main Menu");
        home.setCursor(Cursor.HAND);
        home.setOnAction(event -> {
            resetLayouts();
            window.setScene(firstSceneLayout());
            quizPane.getRight().setVisible(true);
            quizPane.getBottom().setVisible(true);
        });
        return home;
    }

    private void resetLayouts(){
        layout.resetPane();
        layoutTwo.resetPane();
        layoutThree.resetPane();
    }

    private Button nextButton(){
        return makeNext(layout);
    }

    private Button nextButtonTwo(){
        return makeNext(layoutTwo);
    }

    private Button nextButtonThree(){
        return makeNext(layoutThree);
    }

    private Button makeNext(QuizPaneLayout levelLayout){
        next.setCursor(Cursor.HAND);
        next.setOnAction(event -> {
            generateCorrect(levelLayout);
            levelLayout.setQuestionNumber(levelLayout.getQuestionNumber()+1);
            levelLayout.setRetryTurn(0);
            quizPane = levelLayout.mainWindowForQuiz();
            center = levelLayout.makeSceneInstance();
            quizPane.setBottom(center);
            center.setRight(next);
            if(levelLayout.getQuestionNumber()>layout.NUMBER_OF_QUESTIONS){
                quizPane.setCenter(homeButton());
                quizPane.getRight().setVisible(false);
                quizPane.getBottom().setVisible(false);
                levelLayout.showScore();
            }
        });
        return  next;
    }

    private void generateCorrect(QuizPaneLayout levelLayout){
        if(levelLayout == layout){
            fixedBase.setDecimalValue(randomDecimal.generateLevelOneDecimal());
            randomBases.setRandomBasesOne(fixedBase);
        }
        if(levelLayout == layoutTwo){
            fixedBase.setDecimalValue(randomDecimal.generateLevelTwoDecimal());
            randomBases.setRandomBasesTwo(fixedBase);
        }
        if(levelLayout == layoutThree){
            fixedBase.setDecimalValue(randomDecimal.generateLevelThreeDecimal());
            randomBases.setRandomBasesThree(fixedBase);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}