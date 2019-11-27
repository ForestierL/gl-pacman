package engine.physics;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Hitbox extends Rectangle
{
    public Hitbox(int x, int y, double width, double height)
    {
        super();
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setFill(Color.RED);
    }

    public Hitbox transformHitbox(MovementIntent movementIntent)
    {
        return new Hitbox(movementIntent.dstX * 32, movementIntent.dstY * 32, getWidth(), getHeight());
    }

    public boolean intersects(Hitbox other)
    {
        if (this.getHeight() + getY() <= other.getY()
                || this.getY() >= other.getHeight() + other.getY()) {
            return false;
        }
        if (this.getWidth() + getX() <= other.getX()
                || this.getX() >= other.getWidth() + other.getX()) {
            return false;
        }
        return true;
    }
}
