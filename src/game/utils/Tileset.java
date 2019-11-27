package game.utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class Tileset
{
    private Image asset;
    private int tileWidth, tileHeight;
    private HashMap<Character, Integer> tileMap;

    public Tileset(Image asset, int tileWidth, int tileHeight)
    {
        this.asset = asset;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        tileMap = new HashMap<>();
    }

    public ImageView getTileImageView(Character identifier)
    {
        try
        {
            ImageView tileImage = new ImageView(asset);
            int x = (int) ((tileMap.get(identifier)) % (asset.getWidth() / tileWidth));
            int y = (int) ((tileMap.get(identifier)) / (asset.getWidth() / tileHeight));

            Rectangle2D viewport = new Rectangle2D(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            tileImage.setViewport(viewport);
            return tileImage;
        }
        catch(NullPointerException e)
        {
            System.out.println("ERROR : Tile not set for " + identifier);
            return null;
        }

    }

    public void setTileMapping(char identifier, int tileIndex)
    {
        tileMap.put(identifier, tileIndex);
    }
}
