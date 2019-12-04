package game.objects;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class Wall extends GameObject
{
    public Wall(int x, int y, int width, int height, int textureId)
    {
        super(new SpriteTexture(new Image("mapTileset.png")), x, y, width, height);
        setDefaultSubImage(textureId);
        this.setCollisionSignal(CollisionSignal.WALL);
    }

    public  Wall(int x, int y, int width, int height) {
        this(x,y,width,height,7);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        return false;
    }
}
