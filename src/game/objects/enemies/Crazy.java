package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import game.utils.Direction;

public class Crazy extends Monster{

    public Crazy(SpriteTexture spriteTexture, int x, int y, int width, int height){
        super(spriteTexture, x, y, width, height);
    }



    @Override
    public Direction chase(char[][] terrain, int x, int y) {
        double r = 1.+ Math.random() * (4);
        this.direction = Direction.values()[(int)r];
        return this.direction;
    }
}