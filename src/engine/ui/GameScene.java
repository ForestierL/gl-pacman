package engine.ui;

import engine.graphics.BackgroundDisplay;
import engine.graphics.GraphicsDisplay;
import engine.graphics.GridLayer;
import engine.physics.GameWorld;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public abstract class GameScene extends Scene
{
    private GameWorld world;
    private GraphicsDisplay graphicsDisplay;
    private BackgroundDisplay backgroundDisplay;
    private GameWindow gameWindow;

    private int width, height;
    private int uiMargin;

    public GameScene(Parent root, GameWindow gameWindow, int width, int height, int tileWidth, int tileHeight, int uiMargin)
    {
        super(root);

        this.width = width;
        this.height = height;
        this.uiMargin = uiMargin;

        addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) pushEscape();
        });

        this.gameWindow = gameWindow;
        world = new GameWorld();
        backgroundDisplay = new BackgroundDisplay(tileWidth, tileHeight);
        graphicsDisplay = new GraphicsDisplay(world, width, height - uiMargin, tileWidth, tileHeight);

    }


    public abstract void createGame();

    protected void setGameWorld(GameWorld world)
    {
        world.addObserver(this.gameWindow);
        this.world = world;
        graphicsDisplay.setGameWorld(this.world);
    }


    protected void addToBackground(ArrayList<GridLayer> gridLayers)
    {
        backgroundDisplay.addAll(gridLayers);
    }

    public GameWorld getWorld() {
        return world;
    }

    public GraphicsDisplay getGraphicsDisplay() {
        return graphicsDisplay;
    }

    public BackgroundDisplay getBackgroundDisplay() {
        return backgroundDisplay;
    }

    public void setGraphicsDisplay(GraphicsDisplay graphicsDisplay) {
        this.graphicsDisplay = graphicsDisplay;
    }

    public void setBackgroundDisplay(BackgroundDisplay backgroundDisplay) {
        this.backgroundDisplay = backgroundDisplay;
    }

    public int getUiMargin() { return uiMargin; }

    void pushEscape(){
        gameWindow.pauseGame();
    }
}
