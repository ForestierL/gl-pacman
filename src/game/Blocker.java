package game;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.tiledutils.Point;
import game.utils.tiledutils.Terrain;

import static engine.graphics.Orientation.*;
import static engine.graphics.Orientation.EAST;

public class Blocker extends Monster{

    public Blocker(SpriteTexture spriteTexture, int x, int y){

        super(spriteTexture, x, y);
    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y) {
        Point p = Terrain.getPlayer(terrain);

        terrain = Terrain.copy(terrain, p.getX(), p.getY());
        Orientation o = Terrain.getShortestOrientation(terrain, x, y);
        return o;

    }
}