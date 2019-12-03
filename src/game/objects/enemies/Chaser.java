package game.objects.enemies;

import engine.graphics.SpriteTexture;
import game.utils.Direction;
import game.utils.Terrain;

public class Chaser extends Monster {


    public Chaser(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {
        super(spriteTexture, x, y, width, height, difficulty);

    }

    @Override
    public Direction chase(int[][] terrain, int x, int y) {

            Direction d = Terrain.getShortestDirection2(terrain, x, y);
            double r;

            if(d != Direction.NONE)
                this.direction = d;
            r = Math.random() * (4);
            if(r*this.difficulty < 2.5)
                this.direction = Terrain.randomDir(terrain, x, y);

        return this.direction;
    }
}

