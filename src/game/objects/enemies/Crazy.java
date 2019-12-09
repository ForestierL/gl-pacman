package game.objects.enemies;

import engine.graphics.SpriteTexture;
import game.utils.Direction;

public class Crazy extends Monster {
    private Direction lastDir;

    public Crazy(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {
        super(spriteTexture, x, y, width, height, difficulty);
        lastDir = this.direction;
    }


    @Override
    protected Direction chase() {

        double r;

        Direction lastDirCont = Direction.X_NEGATIVE;
        if (this.lastDir == Direction.X_NEGATIVE)
            lastDirCont = Direction.X_POSITIVE;
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
                (direction == Direction.Y_NEGATIVE && getY2() > 0 && getTerrain()[getY2() - 1][getX2()] == 1) ||
                (direction == Direction.Y_POSITIVE && getY2() < getTerrain().length && getTerrain()[getY2() + 1][getX2()] == 1) ||
                (direction == Direction.X_NEGATIVE && getX2() > 0 && getTerrain()[getY2()][getX2() - 1] == 1) ||
                (direction == Direction.X_POSITIVE && getX2() < getTerrain()[0].length && getTerrain()[getY2()][getX2() + 1] == 1)) && cpt < 5);
        this.lastDir = this.direction;

        return this.direction;
    }
}