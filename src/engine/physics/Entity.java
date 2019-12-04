package engine.physics;

import engine.graphics.GraphicsDisplay;

public abstract class Entity
{
    private Hitbox hitbox;
    private GameWorld world;
    private int collisionSignal;
    private boolean hasCollision = true, visible = true;
    private int x, y, width, height, priority = 0;
    private double speed = 1.0;
    double timeSinceLastUpdate = 0;
    private MovementIntent movementIntent;

    public Entity(int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        hitbox = new Hitbox(x, y, width, height);
    }

    public abstract void render(GraphicsDisplay graphicsDisplay);

    protected void addMovementIntent(MovementIntent movementIntent)
    {
        this.movementIntent = movementIntent;
    }

    MovementIntent getMovementIntent()
    {
        return movementIntent;
    }

    void validateIntent()
    {
        try
        {
            setX(movementIntent.dstX);
            setY(movementIntent.dstY);
            movementIntent = null;
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
        hitbox.setX(x);
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        hitbox.setY(y);
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        hitbox.setWidth(width);
        this.width = width;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        hitbox.setHeight(height);
        this.height = height;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public boolean hasCollision()
    {
        return hasCollision;
    }

    public void setCollisionState(boolean hasCollision)
    {
        this.hasCollision = hasCollision;
    }

    public abstract boolean handleCollision(int signal);

    public abstract void update(double elapsedTime);

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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed)
    {
        if(speed < 0)
            this.speed = 0;
        else
            this.speed = speed;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void resizeHitbox(int x, int y){
        hitbox.resizeCenter(x,y);
    }
}
