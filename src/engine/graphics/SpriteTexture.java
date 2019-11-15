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

public abstract class SpriteTexture extends ImageView
{
    private Image image;
    private Orientation orientation;
    private final double width =32, height = 32; // size of a single image or cell

    private final int numCells = 4;  // number of images in an animation
    private Rectangle2D[] cellClips = new Rectangle2D[numCells];

    private final Duration FRAME_TIME = Duration.seconds(.5);
    private final Timeline timeline;

    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);


    public SpriteTexture(Image image, Orientation orientation) {

        this.image = image;
        this.orientation = orientation;

        for (int i = 0; i < numCells; i++) {
            cellClips[i] = new Rectangle2D(i * width, 0, width, height);
        }

        this.setImage(image);
        setViewport(cellClips[0]);

        this.timeline = new Timeline(
                new KeyFrame(FRAME_TIME, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCells);
                    setViewport(cellClips[frameCounter.get()]);
                })
        );
    }

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
    } // stop the animation


    public double getWidth() { return width; }

    public double getHeight() { return height; }

}