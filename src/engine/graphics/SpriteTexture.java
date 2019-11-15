package engine.graphics;

import engine.physics.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class SpriteTexture
{
    private Image image;
    private Orientation orientation;
    private ImageView imageView;
    private double width, height;

    public SpriteTexture(Image image, Orientation orientation) {

        this.image = image;
        this.orientation = orientation;
        this.imageView = new ImageView();
        this.imageView.setImage(image);

        this.width = image.getWidth();
        this.height = image.getHeight();

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public ImageView getImageView() { return imageView; }

}
