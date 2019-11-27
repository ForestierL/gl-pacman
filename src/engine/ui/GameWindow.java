package engine.ui;

import engine.graphics.*;
import engine.physics.GameWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;


public class GameWindow extends Application
{
    private GameWorld world;
    private GraphicsDisplay graphicsDisplay;
    private BackgroundDisplay backgroundDisplay;

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
        stage.setResizable(false);
        stage.setTitle(name);
        stage.setScene(scene);

        group.getChildren().addAll(backgroundDisplay.getGridLayers());
        group.getChildren().add(graphicsDisplay);

        stage.show();
        startGame();
    }

    private void startGame()
    {
        initialize();
        run();
    }

    private void initialize()
    {
        System.out.println("GameWindow : initialize.");
    }

    private void run()
    {
        System.out.println("GameWindow : run loop.");
        new AnimationTimer()
        {
            private long lastHandle;

            @Override
            public void start()
            {
                super.start();
                lastHandle = System.nanoTime();
            }

            @Override
            public void handle(long currentNanoTime)
            {
                long elapsed = currentNanoTime - lastHandle;

                world.udpate(elapsed);
                graphicsDisplay.render();

                lastHandle = currentNanoTime;
            }
        }.start();
    }


    protected void setGameWorld(GameWorld world)
    {
        this.world = world;
        graphicsDisplay.setGameWorld(this.world);
    }

    protected void addToBackground(ArrayList<GridLayer> gridLayers)
    {
        backgroundDisplay.addAll(gridLayers);
    }

    public GraphicsDisplay getGraphicsDisplay() {
        return graphicsDisplay;
    }

    public void setGraphicsDisplay(GraphicsDisplay graphicsDisplay) {
        this.graphicsDisplay = graphicsDisplay;
    }

    public BackgroundDisplay getBackgroundDisplay() {
        return backgroundDisplay;
    }

    public void setBackgroundDisplay(BackgroundDisplay backgroundDisplay) {
        this.backgroundDisplay = backgroundDisplay;
    }
}
