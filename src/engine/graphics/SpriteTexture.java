package engine.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public  abstract class  SpriteTexture
{
    Image image;
    ImageView imageView;

    Pane layer;

    double x, y, r;
    String orientation;

    boolean removable = false;

    double width, height;

    boolean canMove = true;

    public SpriteTexture(Pane layer, Image image, double x, double y, double r, String orientation) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.r = r;
        this.orientation = orientation;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);

        this.width = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.height = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void move() {

        //TODO: animation
    }

    public ImageView getView() {
        return imageView;
    }

    public void updateUI() {

        imageView.relocate(x, y);
        imageView.setRotate(r);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getCenterX() {
        return x + width * 0.5;
    }

    public double getCenterY() {
        return y + height * 0.5;
    }

    // TODO: per-pixel-collision
    public boolean collidesWith( SpriteTexture otherSprite) {

        return ( otherSprite.x + otherSprite.width >= x && otherSprite.y + otherSprite.height >= y && otherSprite.x <= x + width && otherSprite.y <= y + height);

    }

    /**
     * Set flag that the sprite can be removed from the UI.
     */
    public void remove() {
        setRemovable(true);
    }

    /**
     * Set flag that the sprite can't move anymore.
     */
    public void stopMovement() {
        this.canMove = false;
    }

    public abstract void checkRemovability();

}
