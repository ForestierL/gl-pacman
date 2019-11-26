package engine.physics;

import engine.graphics.GraphicsDisplay;

public abstract class Entity
{
    private GameWorld world;
    private int collisionSignal = 0;
    private boolean hasCollision = true;
    private int x, y, z;
    private MovementIntention movementIntention;

    public abstract void render(GraphicsDisplay graphicsDisplay);

    public void addMovementIntention(MovementIntention movementIntention)
    {
        this.movementIntention = movementIntention;
    }

    MovementIntention getMovementIntention()
    {
        return movementIntention;
    }

    void validateIntention()
    {
        try
        {
            setX(movementIntention.dstX);
            setY(movementIntention.dstY);
            movementIntention = null;
        }
        catch(NullPointerException e)
        {
            System.out.println("ERROR : Given intention is null.");
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public boolean hasCollision()
    {
        return hasCollision;
    }

    public void setCollisionState(boolean hasCollision)
    {
        this.hasCollision = hasCollision;
    }

    public abstract void handleCollision(int signal);

    public int getCollisionSignal()
    {
        return collisionSignal;
    }

    public void setCollisionSignal(int collisionSignal)
    {
        this.collisionSignal = collisionSignal;
    }

    public GameWorld getWorld() {
        return world;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }
}
