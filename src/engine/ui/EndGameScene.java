package engine.ui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EndGameScene extends Scene {
    private int width;
    private int height;
    private GameWindow gameWindow;
    private HBox hb;
    private Pane root;

    private static final Font FONT = Font.font("", FontWeight.BOLD,70);
    private static final Font scoreFont = Font.font("", FontWeight.BOLD,50);
    private static final Font textFont = Font.font("", FontWeight.MEDIUM,20);

    public EndGameScene(Parent root, GameWindow gameWindow, int width, int height)
    {
        super(root);

        this.width = width;
        this.height = height;
        this.gameWindow = gameWindow;

        addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) endGame();
        });
    }

    Node createContent(int width, int height, int score){
        root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(50, 100, "GAME OVER");
        title.setFont(FONT);
        Text scoreText = new Text(100, 170, "Your score is\n" + Integer.toString(score));
        scoreText.setTextAlignment(TextAlignment.CENTER);
        scoreText.setFont(scoreFont);
        Text text = new Text(20, 300,"Enter your name to save your score and press Enter\nElse press Escape\nYour name must not exceed 8 characters\nor contain special characters");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(textFont);
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        hb.setTranslateX((int)(width/4));
        hb.setTranslateY(400);
        hb.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                addScore(textField.getText(), score);
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                endGame();
            }
        });

        root.getChildren().addAll(hb, title, scoreText, text);
        return root;
    }

    private void addScore (String playerName, int score) {
        if(playerName.length() <= 8){
            boolean test = false;
            if(!playerName.contains("/")){
                Score newScore = new Score(playerName, score);
                try {
                    newScore.addScore();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gameWindow.openScores();
            }
        }
    }

    private void endGame(){
        gameWindow.openScores();
    }
}
