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
        return new Hitbox(movementIntent.dstX, movementIntent.dstY, getWidth(), getHeight());
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

    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }
    public void setPosX(int x) {
        setX(x);
    }
    public void setPosY(int y) {
        setY(y);
    }
    public void setWidth(int width) {
        setWidth(width);
    }
    public void setHeight(int height) {
        setHeight(height);
    }

    public void resize(float percent) {
        resize(getWidth()*percent, getHeight()*percent);
    }
    public void resize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void resizeCenter(int width, int height)
    {
        double tempX = (getWidth()-width)/2;
        double tempY = (getHeight()-height)/2;
        resize(width, height);
        setPosX((int) (getX()+tempX));
        setPosY((int) (getY()+tempY));
    }

}
