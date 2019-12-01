package engine.ui;

import engine.graphics.BackgroundDisplay;
import engine.graphics.GraphicsDisplay;
import engine.graphics.GridLayer;
import engine.physics.GameWorld;
import game.PacmanWorld;
import game.utils.Level;
import game.utils.Tileset;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

public abstract class GameScene extends Scene
{
    private GameWorld world;
    private GraphicsDisplay graphicsDisplay;
    private BackgroundDisplay backgroundDisplay;


    private int width;
    private int height;

    public GameScene(Parent root, String name, int width, int height, int tileWidth, int tileHeight)
    {
        super(root);

        this.width = width;
        this.height = height;

        world = new GameWorld();
        backgroundDisplay = new BackgroundDisplay(tileWidth, tileHeight);
        graphicsDisplay = new GraphicsDisplay(world, width, height, tileWidth, tileHeight);
    }

    public abstract void createGame();

    protected void setGameWorld(GameWorld world)
    {
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
}
