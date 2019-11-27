package game.objects;

import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class Wall extends GameObject
{
    public Wall(int x, int y)
    {
        super(new SpriteTexture(new Image("mapTileset.png")), x, y);
        setDefaultSubImage(7);
    }

    @Override
    public void handleCollision(CollisionSignal signal) {

    }
}
