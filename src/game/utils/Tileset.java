package game.utils;

import javafx.scene.image.Image;

public class Tileset
{
    int width, height;
    Image asset;
    public Tileset(Image asset, int width, int height)
    {
        this.asset = asset;
        this.width = width;
        this.height = height;
    }
}
