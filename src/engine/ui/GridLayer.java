package engine.ui;

import engine.graphics.SpriteTexture;
import engine.physics.Orientation;
import engine.physics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GridLayer extends GridPane
{
    ArrayList<Sprite> sprites;

    public GridLayer()
    {
        super();
    }

    public void add(Sprite sprite, int i, int j)
    {
        ImageView imageView = sprite.getSpriteTexture();
        add(imageView, i, j);
    }

    public void fillLayer(Image image, int width, int height)
    {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                add(new SpriteTexture(image, Orientation.NONE), i, j);
            }
        }
    }
}
