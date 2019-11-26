package engine.physics;

public class MovementIntent
{
    int srcX, srcY, dstX, dstY;

    public MovementIntent(int srcX, int srcY, int dstX, int dstY)
    {
        this.srcX = srcX;
        this.srcY = srcY;
        this.dstX = dstX;
        this.dstY = dstY;
    }
}
