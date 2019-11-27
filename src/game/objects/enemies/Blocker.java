package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.objects.enemies.Monster;
import game.utils.Direction;
import game.utils.Point;
import game.utils.Terrain;

import static engine.graphics.Orientation.*;
import static engine.graphics.Orientation.EAST;

public class Blocker extends Monster {

    public Blocker(SpriteTexture spriteTexture, int x, int y, int width, int height){

        super(spriteTexture, x, y, width, height);
    }

    @Override
    public Direction chase(char[][] terrain, int x, int y) {
        Point p = Terrain.getPlayer(terrain);

        terrain = Terrain.copy(terrain, p.getX(), p.getY());
        Direction o = Terrain.getShortestDirection(terrain, x, y);
        return o;

    }
}