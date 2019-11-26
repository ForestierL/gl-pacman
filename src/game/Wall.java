package game;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import engine.physics.Orientation;
import javafx.scene.image.Image;

public class Wall extends Sprite
{
    public Wall(int x, int y)
    {
        super(new SpriteTexture(new Image("mapTileset.png"), Orientation.NONE), x, y);
        spriteTexture.setCurrentSubImage(7);
    }

}
