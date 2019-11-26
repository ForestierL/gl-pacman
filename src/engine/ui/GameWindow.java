package engine.ui;

import engine.graphics.*;
import engine.physics.GameWorld;
import engine.physics.Orientation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;


public class GameWindow extends Application implements ComponentListener
{
    private GameWorld world;
    private GraphicsDisplay graphicsDisplay;
    protected BackgroundDisplay backgroundDisplay;

    private String name;
    private int width, height, tileWidth, tileHeight;

    private Group group = new Group();
    protected Scene scene = new Scene(group);

    public GameWindow(String name, int width, int height, int tileWidth, int tileHeight)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        world = new GameWorld();
        backgroundDisplay = new BackgroundDisplay(tileWidth, tileHeight);
        graphicsDisplay = new GraphicsDisplay(world, width, height, tileWidth, tileHeight);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle(name);
        stage.setScene(scene);
        scene.setFill(Color.PERU);

        for(GridLayer gridLayer : backgroundDisplay.getGridLayers()) group.getChildren().add(gridLayer);
        group.getChildren().add(graphicsDisplay);

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

    protected void setGameWorld(GameWorld world)
    {
        this.world = world;
        graphicsDisplay.setGameWorld(this.world);
    }


    @Override
    public void componentResized(ComponentEvent e)
    {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
