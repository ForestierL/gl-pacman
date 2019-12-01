package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import game.utils.Direction;
import game.utils.Terrain;

public class Crazy extends Monster {
    private Direction lastDir;

    public Crazy(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {
        super(spriteTexture, x, y, width, height, difficulty);
        lastDir = this.direction;
    }


    @Override
    public Direction chase(int[][] terrain, int x, int y) {

        double r;

        Direction lastDirCont = Direction.X_NEGATIVE;
        if (this.lastDir == Direction.X_NEGATIVE)
            lastDirCont = Direction.X_POSITIVE;
        if (this.lastDir == Direction.X_POSITIVE)
            lastDirCont = Direction.X_NEGATIVE;
        if (this.lastDir == Direction.Y_NEGATIVE)
            lastDirCont = Direction.Y_POSITIVE;
        if (this.lastDir == Direction.Y_POSITIVE)
            lastDirCont = Direction.Y_NEGATIVE;
        int cpt = 0;
        do {
            r = 1. + Math.random() * (4);
            Direction o = Direction.values()[(int) r];
            this.direction = o;
            cpt++;
        } while (((int) r == lastDirCont.ordinal() ||
                (direction == Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] == 1) ||
                (direction == Direction.Y_POSITIVE && y < terrain.length && terrain[y + 1][x] == 1) ||
                (direction == Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] == 1) ||
                (direction == Direction.X_POSITIVE && x < terrain[0].length && terrain[y][x + 1] == 1)) && cpt < 5);
        this.lastDir = this.direction;

        return this.direction;
    }
}