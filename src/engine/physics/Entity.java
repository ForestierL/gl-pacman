package engine.physics;

import engine.graphics.GraphicsDisplay;

import java.util.ArrayList;

public abstract class Entity
{
    private int x, y, z;
    private MovementIntention movementIntention;

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

    public void addMovementIntention(MovementIntention movementIntention)
    {
        this.movementIntention = movementIntention;
    }

    public abstract void render(GraphicsDisplay graphicsDisplay);

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
}
