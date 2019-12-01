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

    public Sprite(SpriteTexture spriteTexture, int x, int y, int width, int height)
    {
        super(x, y, width ,height);
        this.spriteTexture = spriteTexture;
        setX(x);
        setY(y);
        orientationDependantDisplay = false;
        defaultSubImage = 0;
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
                            getX(), getY(),
                            coordinates[2] * graphicsDisplay.getResolutionX() * spriteTexture.getCoefZoomX(),
                            coordinates[3] * graphicsDisplay.getResolutionY() * spriteTexture.getCoefZoomY()
                    );
        }
    }

    @Override
    public boolean handleCollision(int signal)
    {
        return true;
    }

    @Override
    public void update(double elapsedTime) {

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

    public void resizeEntity(double percent) {
        resizeEntity((int) (getWidth()*percent), (int) (getHeight()*percent));
    }
    public void resizeEntity(int width, int height) {

        double tempX = (getWidth() - width) >> 1; //décalage d'un bit à droite pour divier par 2
        double tempY = (getHeight() - height) >> 1; //décalage d'un bit à droite pour divier par 2

        setX((int) (getX()+tempX));
        setY((int) (getY()+tempY));

        super.setWidth(width);
        super.setHeight(height);

        spriteTexture.setZoomX(width);
        spriteTexture.setZoomY(height);


    }
}
