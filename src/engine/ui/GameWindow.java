package engine.ui;

import engine.graphics.PlayerSpriteTexture;
import engine.graphics.SpriteTexture;
import engine.physics.Input;
import engine.physics.Orientation;
import engine.physics.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GameWindow extends Application
{
    public ArrayList<GridLayer> gridLayers;
    Scene scene;
    private String name;
    private int width;
    private int height;

    public GameWindow(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        gridLayers = new ArrayList<>();
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle(name);

        Group group = new Group();
        scene = new Scene(group, width, height);
        stage.setScene(scene);
        scene.setFill(Color.GAINSBORO);
        Player p = new Player(new PlayerSpriteTexture(Orientation.NORTH),1,1,1);

        Input i = new Input(scene, p);
        gameInit(group);

        gameEnd();

        stage.show();
    }

    private void gameInit(Group group)
    {
        group.getChildren().addAll(gridLayers);
    }

    private void gameEnd()
    {

    }

    private void update()
    {

    }

    private void render()
    {

    }
}
