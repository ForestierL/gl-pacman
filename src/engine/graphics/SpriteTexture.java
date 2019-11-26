package engine.graphics;

import engine.physics.Orientation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteTexture extends ImageView
{
    private Image image;
    private Orientation orientation;
    private final int cellWidth = 32, cellHeight = 32;
    private int currentSubImage; // Image used to display the entity ingame
    private int subImageX, subImageY;

    /*
    private final int numCells = 4;  // number of images in an animation
    private Rectangle2D[] cellClips = new Rectangle2D[numCells];

    private final Duration FRAME_TIME = Duration.seconds(.5);
    private final Timeline timeline;

    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0); */


    public SpriteTexture(Image image, Orientation orientation)
    {

        this.image = image;
        this.orientation = orientation;
        this.setImage(image);

        setCurrentSubImage(0);
/*
        for (int i = 0; i < numCells; i++) {
            if(orientation != Orientation.NONE)
                cellClips[i] = new Rectangle2D(i * width, orientation.ordinal()*height, width, height);
            else
                cellClips[i] = new Rectangle2D(i * width, 0, width, height);
        }


        setViewport(cellClips[0]);

        this.timeline = new Timeline(
                new Key
    private final Timeline timeline;Frame(FRAME_TIME, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCells);
                    setViewport(cellClips[frameCounter.get()]);
                })
        ); */
    }


    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
        /*
        for (int i = 0; i < numCells; i++) {
            if(orientation != Orientation.NONE)
                cellClips[i] = new Rectangle2D(i * width, orientation.ordinal()*height, width, height);
            else
                cellClips[i] = new Rectangle2D(i * width, 0, width, height);
        }
        setViewport(cellClips[0]); */
    }

    public void setCurrentSubImage(int currentSubImage)
    {
        this.currentSubImage = currentSubImage;

        subImageX = (int) ((currentSubImage) % (image.getWidth() / cellWidth));
        subImageY = (int) ((currentSubImage) / (image.getWidth() / cellHeight));
    }

    public int getCurrentSubImage()
    {
        return currentSubImage;
    }

    public int getCellWidth() { return cellWidth; }

    public int getCellHeight() { return cellHeight; }

    public int getSubImageX()
    {
        return subImageX;
    }

    public int getSubImageY()
    {
        return subImageY;
    }

    /*
    public void playContinuously() {
        frameCounter.set(0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();
        timeline.playFromStart();
    } // play the animation

    public void stop() {
        frameCounter.set(0);
        setViewport(cellClips[frameCounter.get()]);
        timeline.stop();
    } // stop the animation */

}