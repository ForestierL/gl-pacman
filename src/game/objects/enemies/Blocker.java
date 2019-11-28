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

    public Blocker(SpriteTexture spriteTexture, int x, int y, int width, int height) {

        super(spriteTexture, x, y, width, height);
    }

    @Override
    public Direction chase(char[][] terrain, int x, int y) {
        Point p = Terrain.getPlayer(terrain);
        PacmanWorld pc = (PacmanWorld) this.getWorld();
        Direction pacmanDir = pc.pacman.direction;
        System.out.println(pacmanDir);
        char[][] terrain2 = Terrain.fullCopy(terrain);
        Direction o;
        if (!(getX() / 32 == this.oldX && getY() / 32 == this.oldY &&
                ((this.direction == Direction.X_POSITIVE && x <= terrain[0].length && terrain[y][x + 1] != '1') ||
                        (this.direction == Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] != '1') ||
                        (this.direction == Direction.Y_POSITIVE && y <= terrain.length && terrain[y + 1][x] != '1') ||
                        (this.direction == Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] != '1')))) {
            if (pacmanDir == Direction.Y_NEGATIVE) {
                terrain2[p.getY()][p.getX() + 1] = '1';
                terrain2[p.getY() + 1][p.getX()] = '1';
                terrain2[p.getY()][p.getX() - 1] = '1';
            }
            if (pacmanDir == Direction.Y_POSITIVE) {
                terrain2[p.getY()][p.getX() + 1] = '1';
                terrain2[p.getY() - 1][p.getX()] = '1';
                terrain2[p.getY()][p.getX() - 1] = '1';
            }
            if (pacmanDir == Direction.X_NEGATIVE) {
                terrain2[p.getY()][p.getX() + 1] = '1';
                terrain2[p.getY() - 1][p.getX()] = '1';
                terrain2[p.getY() + 1][p.getX()] = '1';
            }
            if (pacmanDir == Direction.X_POSITIVE) {
                terrain2[p.getY()][p.getX() - 1] = '1';
                terrain2[p.getY() + 1][p.getX()] = '1';
                terrain2[p.getY() - 1][p.getX()] = '1';
            }
            this.direction = Terrain.getShortestDirection(terrain2, x, y);
            if (this.direction == Direction.NONE)
                this.direction = Terrain.getShortestDirection(terrain, x, y);
        }

        return this.direction;

}
}