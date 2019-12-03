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

public class EndGameScene extends Scene {
    private int width;
    private int height;
    private GameWindow gameWindow;
    private HBox hb;

    private static final Font FONT = Font.font("", FontWeight.BOLD,60);

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
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(30, 100, "GAME OVER");
        title.setFont(FONT);
        Text text = new Text(20, 200,"Enter your name to save your score and press Enter");
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        hb.setTranslateX((int)(width/4));
        hb.setTranslateY(300);
        hb.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                addScore(textField.getText(), score);
            }
        });

        root.getChildren().addAll(hb, title, text);
        return root;
    }

    private void addScore (String playerName, int score){
        /*
        Ajouter le score
         */
    }

    private void endGame(){
        gameWindow.endGame();
    }
}
