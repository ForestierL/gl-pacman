package game.objects;

import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class Wall extends GameObject
{
    public Wall(int x, int y, int width, int height)
    {
        super(new SpriteTexture(new Image("mapTileset.png")), x, y, width, height);
        setDefaultSubImage(7);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        return false;
    }
}
