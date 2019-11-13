package engine.physics;

import engine.graphics.SpriteTexture;

public class Sprite
{
    private SpriteTexture spriteTexture;
    private int posX;
    private int posY;
    private int posZ;

    public Sprite()
    {

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
