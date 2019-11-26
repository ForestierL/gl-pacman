package engine.graphics;

import engine.physics.Entity;
import engine.physics.Orientation;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Sprite extends Entity
{
    private Orientation orientation = Orientation.NONE;
    private SpriteTexture spriteTexture;

    Sprite(SpriteTexture spriteTexture, int x, int y, int z)
    {
        this.spriteTexture = spriteTexture;
        setX(x);
        setY(y);
        setZ(z);
    }

    public Sprite(SpriteTexture spriteTexture, int x, int y)
    {
        this(spriteTexture, x, y, 0);
    }


    void setOrientation(Orientation orientation)
    {
        spriteTexture.setOrientation(orientation);
        this.orientation = orientation;
    }

    public void setSpriteTexture(SpriteTexture spriteTexture)
    {
        this.spriteTexture = spriteTexture;
    }

    public Orientation getOrientation() {
        return orientation;
    }


    SpriteTexture getSpriteTexture(){ return spriteTexture; }

    @Override
    public void render(GraphicsDisplay graphicsDisplay)
    {
        graphicsDisplay.graphicsContext.drawImage(spriteTexture.getImage(), 0, 0, 32, 32, getX(), getY(), 32, 32);
    }
}
