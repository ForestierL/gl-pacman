package engine.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;

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

        for (int column=0; column<3; column++){
            scoreboard[column].setTranslateX(50+((int)((width-170)/2)*column));
            scoreboard[column].setTranslateY(120);

            for (int line=1; line<=10; line++){
                /*
                Récupérer les scores et pseudos
                 */
            }
        }

        root.getChildren().add(title);
        root.getChildren().addAll(scoreboard);
        return root;
    }

    private void returnToMenu(){
        gameWindow.returnToMenu();
    }
}
