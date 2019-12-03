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
        for (int i=0; i< scores.length; i++){
            System.out.println(scores[i]);
        }
        for (int column=0; column<3; column++){
            scoreboard[column].setTranslateX(50+((int)((width-170)/2)*column));
            scoreboard[column].setTranslateY(120);
            for (int line=1; line<=10; line++){
                if(scores[line-1] != null) {
                    if (column == 1) {
                        scoreboard[column].getChildren().add(new Text(scores[line - 1].getName()));
                    } else if (column == 2) {
                        scoreboard[column].getChildren().add(new Text(scores[line - 1].getScore()));
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
        String[] elements = new String[2];
        elements[0]="";
        elements[1]="";
        int count = 0;
        while ((line = text.readLine()) != null && count<10){
            elements = line.split("/");
            scores[count] = new Score(elements[0], elements[1]);
            System.out.println(elements[0]);
            count++;
        }
        text.close();
        return scores;
    }

    private void returnToMenu(){
        gameWindow.returnToMenu();
    }
}
