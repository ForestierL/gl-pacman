package engine.graphics;

import game.utils.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.security.Key;

public class SpriteTexture extends ImageView
{
    protected Image image;
    protected int cellWidth = 32, cellHeight = 32;
    protected float coefZoomX = 1;
    protected float coefZoomY = 1;


    private final int numCells = 4;  // number of images in an animation
    protected Rectangle2D[] cellClips = new Rectangle2D[numCells];

    private final Duration FRAME_TIME = Duration.seconds(.5);


    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);

    public SpriteTexture(Image image)
    {
        this.image = image;
        this.setImage(image);

    }


    public int[] getSubImageCoordinates(int subImageIdentifier)
    {

        int[] coordinates = new int[4];
        int srcX = (int) ((subImageIdentifier) % (image.getWidth() / cellWidth));
        int srcY = (int) ((subImageIdentifier) / (image.getWidth() / cellHeight));

        coordinates[0] = srcX  * cellWidth;
        coordinates[1] = srcY * cellHeight;
        coordinates[2] = cellWidth;
        coordinates[3] = cellHeight;

        return coordinates;
    }

    public int getCellWidth() { return cellWidth; }

    public int getCellHeight() { return cellHeight; }

    public void setZoomX(int width) {
        float coef = width/(float) cellWidth;
        setCoefZoomX(coef);
    }

    public void setZoomY(int height) {
        float coef = height/(float) cellHeight;
        setCoefZoomY(coef);
    }

    public void setCoefZoomX(float coefZoomX) {
        this.coefZoomX = coefZoomX;
    }

    public void setCoefZoomY(float coefZoomY) {
        this.coefZoomY = coefZoomY;
    }

    public float getCoefZoomX() {
        return coefZoomX;
    }

    public float getCoefZoomY() {
        return coefZoomY;
    }



}