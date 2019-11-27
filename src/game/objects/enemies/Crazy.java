package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;

public class Crazy extends Monster{

    public Crazy(SpriteTexture spriteTexture, int x, int y, int width, int height){
        super(spriteTexture, x, y, width, height);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal) {
        return true;
    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y)
    {
        return null;
    }
}