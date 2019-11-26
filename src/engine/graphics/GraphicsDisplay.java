package engine.graphics;

import engine.physics.Entity;
import engine.physics.GameWorld;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GraphicsDisplay extends Canvas
{
    private int tileWidth, tileHeight;
    private double resolutionX, resolutionY; // Tile resolutions on screen.
    private GameWorld gameWorld;
    GraphicsContext graphicsContext = getGraphicsContext2D();

    public GraphicsDisplay(GameWorld gameWorld, int width, int height, int tileWidth, int tileHeight)
    {
        super(width, height);
        this.gameWorld = gameWorld;
        setTileResolution(1.0, 1.0);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void setGameWorld(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void render()
    {
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        for(Entity entity : gameWorld.getEntities())
        {
            entity.render(this);
        }

    }

    public void setTileResolution(double resolutionX, double resolutionY)
    {
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
    }

    public double getResolutionX()
    {
        return resolutionX;
    }

    public double getResolutionY()
    {
        return resolutionY;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
