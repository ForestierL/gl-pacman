package engine.ui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.WeakHashMap;

public class ScoresScene extends Scene {
    private int width;
    private int height;
    private GameWindow gameWindow;

    private static final Font FONT = Font.font("", FontWeight.BOLD,40);
    private static final Font label = Font.font("", FontWeight.BOLD,20);
    private static final Font scoreBoard = Font.font("", FontWeight.MEDIUM,15);

    public ScoresScene(Parent root, GameWindow gameWindow, int width, int height)
    {
        super(root);

        this.width = width;
        this.height = height;
        this.gameWindow = gameWindow;

        addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) returnToMenu();
        });
    }

    Node createContent(int width, int height){
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(180, 70, "Scores");
        title.setFont(FONT);

        VBox scoreboard[] = {new VBox(10),new VBox(10), new VBox(10)};

        scoreboard[0].getChildren().add(new Label("Place"));
        scoreboard[1].getChildren().add(new Label("Name"));
        scoreboard[2].getChildren().add(new Label("Score"));
        for (int place=1; place<11; place++){
            scoreboard[0].getChildren().add(new Text(Integer.toString(place)));
        }

        Score scores[] = new Score[10];
        try {
            scores = loadFile(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int column=0; column<3; column++){
            scoreboard[column].setTranslateX(50+((int)((width-170)/2)*column));
            scoreboard[column].setTranslateY(120);
            for (int line=1; line<=10; line++){
                if(scores[line-1] != null) {
                    if (column == 1) {
                        scoreboard[column].getChildren().add(new Text(scores[line - 1].getName()));
                    } else if (column == 2) {
                        scoreboard[column].getChildren().add(new Text(Integer.toString(scores[line - 1].getScore())));
                    }
                }
            }
        }

        root.getChildren().add(title);
        root.getChildren().addAll(scoreboard);
        return root;
    }

    public Score[] loadFile(Score[] scores) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("src/game/scores/Scores.txt"));
        String line;
        String[][] elements = new String[10][];
        String name;
        int score;
        int count = 0;
        while ((line = text.readLine()) != null && count<10){
            elements[count] = line.split("/");
            name = elements[count][0];
            score = Integer.parseInt(elements[count][1]);
            scores[count] = new Score(name, score);
            count++;
        }
        text.close();
        scores = sortScores(elements);
        return scores;
    }

    public Score[] sortScores(String scores[][]){
        Score sortedScores[] = new Score[10];
        String maxScore[] = scores[0];
        int id = 0;
        for (int count=0; count<10; count++) {
            for (int n=0; n<scores.length; n++) {
                if(Integer.parseInt(scores[n][1]) > Integer.parseInt(maxScore[1])){
                    id = n;
                    maxScore = scores[n];
                }
            }
            sortedScores[count] = new Score(maxScore[0], Integer.parseInt(maxScore[1]));
            scores[id][1] = "-1";
        }

        return sortedScores;
    }

    private void returnToMenu(){
        gameWindow.returnToMenu();
    }
}
