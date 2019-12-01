package engine.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteTexture extends ImageView
{
    private Image image;
    private int cellWidth = 32, cellHeight = 32;
    private float coefZoomX = 1;
    private float coefZoomY = 1;

    /*
    private final int numCells = 4;  // number of images in an animation
    private Rectangle2D[] cellClips = new Rectangle2D[numCells];

    private final Duration FRAME_TIME = Duration.seconds(.5);
    private final Timeline timeline;

    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0); */

    public SpriteTexture(Image image)
    {
        this.image = image;
        this.setImage(image);
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
        );
        */
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