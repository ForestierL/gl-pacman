package game.objects;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import game.utils.Direction;

public abstract class GameObject extends Sprite
{
    public Direction direction = Direction.NONE;

    public GameObject(SpriteTexture spriteTexture, int x, int y, int width, int height)
    {
        super(spriteTexture, x, y, width, height);
    }

    void setCollisionSignal(CollisionSignal collisionSignal)
    {
        super.setCollisionSignal(collisionSignal.ordinal());
    }

    @Override
    public boolean handleCollision(int signal)
    {
        return handleCollision(CollisionSignal.values()[signal]);
    }

    public abstract boolean handleCollision(CollisionSignal signal);

}
