package engine.ui;

import engine.graphics.*;
import engine.physics.GameWorld;
import engine.physics.Orientation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GameWindow extends Application
{
    private GameWorld world = new GameWorld();
    private GraphicsDisplay graphicsDisplay;
    protected BackgroundDisplay backgroundDisplay = new BackgroundDisplay();

    private String name;
    private int width;
    private int height;

    private Group group = new Group();

    public GameWindow(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle(name);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        scene.setFill(Color.PERU);

        graphicsDisplay = new GraphicsDisplay(world, width, height);
        for(GridLayer gridLayer : backgroundDisplay.getGridLayers()) group.getChildren().add(gridLayer);
        group.getChildren().add(graphicsDisplay);

        world.add(new Sprite(new SpriteTexture(new Image("player_normal.png"), Orientation.NONE), 50, 50));

        stage.show();
        startGame();
    }

    private void startGame()
    {
        initialize();
        run();
        end();
    }

    private void initialize()
    {

    }

    private void run()
    {
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                world.udpate();
                graphicsDisplay.render();
            }
        }.start();
    }

    private void end()
    {

    }


}
