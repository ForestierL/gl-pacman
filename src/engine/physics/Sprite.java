package engine.physics;

import engine.graphics.SpriteTexture;

public class Sprite
{
    private Orientation orientation = Orientation.NONE;
    private SpriteTexture spriteTexture;
    private int posX = 0;
    private int posY = 0;
    private int posZ = 0;

    public Sprite(SpriteTexture spriteTexture, int posX, int posY, int posZ)
    {
        this.spriteTexture = spriteTexture;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public Sprite(int posX, int posY, int posZ)
    {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public void move(Direction direction, int distance)
    {
        switch(direction)
        {
            case X_POSITIVE: posX += distance;
            case X_NEGATIVE: posX -= distance;
            case Y_POSITIVE: posY += distance;
            case Y_NEGATIVE: posY -= distance;
        }
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setSprite(SpriteTexture spriteTexture)
    {
        this.spriteTexture = spriteTexture;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    public void setPosZ(int posZ)
    {
        this.posZ = posZ;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public int getPosZ()
    {
        return posZ;
    }




}
