package game.objects;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;

public abstract class GameObject extends Sprite
{
    public GameObject(SpriteTexture spriteTexture, int x, int y)
    {
        super(spriteTexture, x, y);
    }

    protected void setCollisionSignal(CollisionSignal collisionSignal)
    {
        super.setCollisionSignal(collisionSignal.ordinal());
    }

    @Override
    public void handleCollision(int signal)
    {
        handleCollision(CollisionSignal.values()[signal]);
    }

    abstract void handleCollision(CollisionSignal signal);

}
