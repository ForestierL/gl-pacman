package engine.graphics;

import engine.physics.Entity;
import engine.physics.GameWorld;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GraphicsDisplay extends Canvas
{
    private GameWorld gameWorld;
    GraphicsContext graphicsContext = getGraphicsContext2D();

    public GraphicsDisplay(GameWorld gameWorld, int width, int height)
    {
        super(width, height);
        this.gameWorld = gameWorld;
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
}
