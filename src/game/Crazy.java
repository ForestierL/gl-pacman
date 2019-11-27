package game;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;

public class Crazy extends Monster{

    public Crazy(SpriteTexture spriteTexture, int x, int y){
        super(spriteTexture, x, y);
    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y) {
        return null;
    }
}