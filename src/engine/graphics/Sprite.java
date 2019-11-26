package engine.graphics;

import engine.physics.Entity;
import engine.physics.Orientation;

public class Sprite extends Entity
{
    private Orientation orientation = Orientation.NONE;
    protected SpriteTexture spriteTexture;

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


    public void setOrientation(Orientation orientation)
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
        int subImageX = spriteTexture.getSubImageX() * spriteTexture.getCellWidth();
        int subImageY = spriteTexture.getSubImageY() * spriteTexture.getCellHeight();

        graphicsDisplay.graphicsContext.drawImage
                (
                spriteTexture.getImage(), subImageX, subImageY,
                spriteTexture.getCellWidth(), spriteTexture.getCellHeight(),
                getX() * graphicsDisplay.getTileWidth(), getY() * graphicsDisplay.getTileHeight(),
                spriteTexture.getCellWidth() * graphicsDisplay.getResolutionX(), spriteTexture.getCellHeight() * graphicsDisplay.getResolutionY()
        );
    }
}
