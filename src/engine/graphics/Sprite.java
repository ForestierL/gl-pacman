package engine.graphics;

import engine.physics.Entity;

import java.util.Hashtable;

public class Sprite extends Entity
{
    private boolean orientationDependantDisplay;
    private Orientation orientation = Orientation.NONE;
    private SpriteTexture spriteTexture;
    private int defaultSubImage;

    private Hashtable<Orientation, Integer> orientationMap = new Hashtable<>();

    private Sprite(SpriteTexture spriteTexture, int x, int y, int z)
    {
        this.spriteTexture = spriteTexture;
        setX(x);
        setY(y);
        setZ(z);
        orientationDependantDisplay = false;
        defaultSubImage = 0;
    }

    public Sprite(SpriteTexture spriteTexture, int x, int y)
    {
        this(spriteTexture, x, y, 0);
    }


    private int getSubImage()
    {
        if(orientationDependantDisplay)
            return orientationMap.getOrDefault(orientation, 0);
        else
            return defaultSubImage;
    }

    protected void addOrientationKey(Orientation orientation, Integer subImageIdentifier)
    {
        orientationMap.put(orientation, subImageIdentifier);
    }

    SpriteTexture getSpriteTexture(){ return spriteTexture; }

    @Override
    public void render(GraphicsDisplay graphicsDisplay)
    {
        if(isVisible())
        {
            int[] coordinates = spriteTexture.getSubImageCoordinates(getSubImage());

            graphicsDisplay.graphicsContext.drawImage
                    (
                            spriteTexture.getImage(), coordinates[0], coordinates[1],
                            coordinates[2], coordinates[3],
                            getX() * graphicsDisplay.getTileWidth(), getY() * graphicsDisplay.getTileHeight(),
                            coordinates[2] * graphicsDisplay.getResolutionX(), coordinates[3] * graphicsDisplay.getResolutionY()
                    );
        }
    }

    @Override
    public void handleCollision(int signal)
    {

    }

    @Override
    public void update() {

    }

    // GETTERS & SETTERS

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }

    public boolean isOrientationDependantDisplay()
    {
        return orientationDependantDisplay;
    }

    public void setOrientationDependantDisplay(boolean orientationDependantDisplay)
    {
        this.orientationDependantDisplay = orientationDependantDisplay;
    }

    public void setSpriteTexture(SpriteTexture spriteTexture)
    {
        this.spriteTexture = spriteTexture;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getDefaultSubImage()
    {
        return defaultSubImage;
    }

    public void setDefaultSubImage(int defaultSubImage)
    {
        this.defaultSubImage = defaultSubImage;
    }
}
