package game.objects.enemies;

import engine.graphics.SpriteTexture;
import game.utils.Direction;
import game.utils.Terrain;

public class Chaser extends Monster {


    public Chaser(SpriteTexture spriteTexture, int x, int y, int width, int height) {
        super(spriteTexture, x, y, width, height);

    }

    @Override
    public Direction chase(char[][] terrain, int x, int y) {
        if(!(getX()/32 == this.oldX && getY()/32 == this.oldY &&
                ((this.direction == Direction.X_POSITIVE && x<=terrain[0].length && terrain[y][x+1] != '1') ||
                        (this.direction == Direction.X_NEGATIVE && x>0 && terrain[y][x-1] != '1') ||
                        (this.direction == Direction.Y_POSITIVE && y<=terrain.length && terrain[y+1][x] != '1') ||
                        (this.direction == Direction.Y_NEGATIVE && y>0 && terrain[y-1][x] != '1') ))){
            Direction o = Terrain.getShortestDirection(terrain, x, y);
            this.direction = o;}

        return this.direction;
    }
}

