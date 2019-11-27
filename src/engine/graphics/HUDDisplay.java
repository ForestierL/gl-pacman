package engine.graphics;

import engine.ui.GameWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HUDDisplay extends Canvas
{
    GraphicsContext graphicsContext = getGraphicsContext2D();

    public HUDDisplay(int width, int height)
    {
        super(width, height);
        graphicsContext.setFill(Color.GREEN);
    }

    public void render()
    {
        //graphicsContext.clearRect(, , getWidth(), getHeight());

    }
}
