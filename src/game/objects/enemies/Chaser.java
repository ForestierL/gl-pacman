package game.objects.enemies;

import engine.graphics.SpriteTexture;
import game.utils.Direction;
import game.utils.Terrain;

public class Chaser extends Monster {

    public Chaser(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {

        super(spriteTexture, x, y, width, height, difficulty);

    }

    @Override
    protected Direction chase() {

        Direction d = Terrain.getShortestDirection2(getTerrain(), getX2(), getY2());

        double r;
        if (d != Direction.NONE)
            this.direction = d;
        r = Math.random() * (4);
        if (r * this.getDifficulty() < 0.5)
            this.direction = Terrain.randomDir(getTerrain(), getX2(), getY2());

        return this.direction;
    }
}

