package game.objects;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import game.utils.PacmanCollisionSignal;

public abstract class GameObject extends Sprite
{
    public GameObject(SpriteTexture spriteTexture, int x, int y)
    {
        super(spriteTexture, x, y);
    }

    protected void setCollisionSignal(PacmanCollisionSignal collisionSignal)
    {
        super.setCollisionSignal(collisionSignal.ordinal());
    }

    @Override
    public void handleCollision(int signal)
    {
        handleCollision(PacmanCollisionSignal.values()[signal]);
    }

    abstract void handleCollision(PacmanCollisionSignal signal);

}
