package engine.physics;

import engine.graphics.PlayerSpriteTexture;
import engine.graphics.SpriteTexture;

public class Sprite
{
    private Orientation orientation = Orientation.NONE;
    private SpriteTexture spriteTexture;
    private int posX, posY, posZ;

    Sprite(SpriteTexture spriteTexture, int posX, int posY, int posZ)
    {
        this.spriteTexture = spriteTexture;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public Sprite(int posX, int posY, int posZ)
    {
        //polymorphisme ? (Loïc)
        /*this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;*/
        this(null, posX, posY, posZ);
    }

    void setOrientation(Orientation orientation)
    {
        spriteTexture.setOrientation(orientation);

    }
    public void changeMode(){

        this.spriteTexture = new PlayerSpriteTexture(Orientation.NORTH, PlayerSpriteTexture.State.powered);
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

    public SpriteTexture getSpriteTexture (){ return spriteTexture; }

}
