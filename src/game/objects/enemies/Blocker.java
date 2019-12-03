package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.enemies.Monster;
import game.utils.Direction;
import game.utils.Point;
import game.utils.Terrain;

import static engine.graphics.Orientation.*;
import static engine.graphics.Orientation.EAST;

public class Blocker extends Monster {

    public Blocker(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {

        super(spriteTexture, x, y, width, height, difficulty);
    }

    @Override
    public Direction chase(int[][] terrain, int x, int y) {

        Point p = Terrain.getPlayer(terrain);
        PacmanWorld pc = (PacmanWorld) this.getWorld();
        Direction pacmanDir = pc.pacman.direction;

        int[][] terrain2 = Terrain.copy(terrain);

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
        d = Terrain.getShortestDirection2(terrain2, x, y);
        if (d == Direction.NONE)
            d = Terrain.getShortestDirection2(terrain, x, y);
        if (d != Direction.NONE)
            this.direction = d;
        double r;
        r = Math.random() * (4);
        if (r * this.difficulty < 2)
            this.direction = Terrain.randomDir(terrain, x, y);

        return this.direction;

    }
}