package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;

public class Crazy extends Monster{

    public Crazy(SpriteTexture spriteTexture, int x, int y){
        super(spriteTexture, x, y);
    }

    @Override
    public void handleCollision(CollisionSignal signal) {

    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y) {
        return null;
    }
}