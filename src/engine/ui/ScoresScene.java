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

class ScoresScene extends Scene {

    //Création d'une fenêtre contenant un tableau des 10 meilleurs scores

    private GameWindow gameWindow;
    private VBox[] scoreboard;

    private static final Font FONT = Font.font("", FontWeight.BOLD,40);

    ScoresScene(Parent root, GameWindow gameWindow)
    {
        super(root);

        this.gameWindow = gameWindow;

        addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) returnToMenu();
        });
    }

    //Génération du tableau
    Node createContent(int width, int height){
        Pane root = new Pane();
        root.setPrefSize(width, height);

        Text title = new Text(180, 70, "Scores");
        title.setFont(FONT);
        if (this.scoreboard != null){
            this.scoreboard[0].getChildren().clear();
            this.scoreboard[1].getChildren().clear();
            this.scoreboard[2].getChildren().clear();
        }
        scoreboard = new VBox[]{new VBox(10), new VBox(10), new VBox(10)};

        scoreboard[0].getChildren().add(new Label("Place"));
        scoreboard[1].getChildren().add(new Label("Name"));
        scoreboard[2].getChildren().add(new Label("Score"));
        for (int place=1; place<11; place++){
            scoreboard[0].getChildren().add(new Text(Integer.toString(place)));
        }

        //Création d'un tableau d'objets Score
        Score scores[] = new Score[10];
        //Chargement du fichier txt contenant les scores
        try {
            scores = loadFile(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ajout des scores dans le tableau
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
        //Ajout de tout les éléments à la fenêtre
        root.getChildren().add(title);
        root.getChildren().addAll(scoreboard);
        return root;
    }

    //Récupération des score du fichier
    private Score[] loadFile(Score[] scores) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("src/game/scores/scores.txt"));
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

    //Tri des scores
    private Score[] sortScores(String scores[][]){
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
