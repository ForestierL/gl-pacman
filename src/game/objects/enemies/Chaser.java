package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.physics.MovementIntent;
import game.objects.enemies.Monster;
import game.utils.Direction;
import game.utils.Terrain;

import static engine.graphics.Orientation.*;
import static engine.graphics.Orientation.EAST;

public class Chaser extends Monster {


    public Chaser(SpriteTexture spriteTexture, int x, int y, int width, int height) {
        super(spriteTexture, x, y, width, height);

    }

    @Override
    public Direction chase(char[][] terrain, int x, int y) {

        Direction o = Terrain.getShortestDirection(terrain, x, y);

        return o;
    }
}

