package game.objects.enemies;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.utils.Direction;
import game.utils.Point;
import game.utils.Terrain;

public class Blocker extends Monster {

    public Blocker(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {

        super(spriteTexture, x, y, width, height, difficulty);
    }

    @Override
    protected Direction chase() {

        Point p = Terrain.getPlayer(this.getTerrain());
        PacmanWorld pc = (PacmanWorld) this.getWorld();
        Direction pacmanDir = pc.pacman.direction;

        int[][] terrain2 = Terrain.copy(this.getTerrain());

        if (pacmanDir == Direction.Y_NEGATIVE) {
            terrain2[p.getY()][p.getX() + 1] = 1;
            terrain2[p.getY() + 1][p.getX()] = 1;
            terrain2[p.getY()][p.getX() - 1] = 1;
        }
        if (pacmanDir == Direction.Y_POSITIVE) {
            terrain2[p.getY()][p.getX() + 1] = 1;
            terrain2[p.getY() - 1][p.getX()] = 1;
            terrain2[p.getY()][p.getX() - 1] = 1;
        }
        if (pacmanDir == Direction.X_NEGATIVE) {
            terrain2[p.getY()][p.getX() + 1] = 1;
            terrain2[p.getY() - 1][p.getX()] = 1;
            terrain2[p.getY() + 1][p.getX()] = 1;
        }
        if (pacmanDir == Direction.X_POSITIVE) {
            terrain2[p.getY()][p.getX() - 1] = 1;
            terrain2[p.getY() + 1][p.getX()] = 1;
            terrain2[p.getY() - 1][p.getX()] = 1;
        }

        Direction d;
        d = Terrain.getShortestDirection2(terrain2, this.getX2(), this.getY2());
        if (d == Direction.NONE)
            d = Terrain.getShortestDirection2(this.getTerrain(), this.getX2(), this.getY2());
        if (d != Direction.NONE)
            this.direction = d;
        double r;
        r = Math.random() * (4);
        if (r * this.getDifficulty() < 0.5)
            this.direction = Terrain.randomDir(this.getTerrain(), this.getX2(), this.getY2());

        return this.direction;

    }
}