package engine.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

class EndGameScene extends Scene {

    //Ecran de fin présentant le score du joueur et proposant son enregistrement

    private GameWindow gameWindow;
    private VBox vb;

    private static final Font FONT = Font.font("", FontWeight.BOLD,70);
    private static final Font scoreFont = Font.font("", FontWeight.BOLD,50);
    private static final Font textFont = Font.font("", FontWeight.MEDIUM,20);

    EndGameScene(Parent root, GameWindow gameWindow)
    {
        super(root);

        this.gameWindow = gameWindow;
        addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) endGame();
        });
    }

    //création du contenu
    Node createContent(int width, int height, int score){
        Pane root = new Pane();
        root.setPrefSize(width, height);

        root.getChildren().clear();
        if (this.vb != null) this.vb.getChildren().clear();
        vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setTranslateX(20);
        Text title = new Text(50, 100, "GAME OVER");
        title.setFont(FONT);
        Text scoreText = new Text(100, 170, "Your score is\n" + Integer.toString(score));
        scoreText.setTextAlignment(TextAlignment.CENTER);
        scoreText.setFont(scoreFont);
        Text text = new Text(20, 300,"Enter your name to save your score and press Enter\nElse press Escape\nYour name must not exceed 16 characters\nor contain special characters");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(textFont);
        vb.getChildren().addAll(title,scoreText,text);
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
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

        root.getChildren().addAll(hb, vb);
        return root;
    }

    //Ajout du score si les conditions sont respetées
    private void addScore (String playerName, int score) {
        if(playerName.length() <= 16 && playerName.length()>0){
            //Le pseudo dois faire 16 caractères max et ne doit pas contenir de / car il servent de délimiteur dans le fichier de stockage des scores
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
