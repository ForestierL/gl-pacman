package game;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import javafx.scene.image.Image;

public class Wall extends Sprite
{
    public Wall(int x, int y)
    {
        super(new SpriteTexture(new Image("mapTileset.png")), x, y);
        setDefaultSubImage(7);
    }

}
