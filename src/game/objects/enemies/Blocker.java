package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import game.utils.Point;
import game.utils.Terrain;

public class Blocker extends Monster{

    public Blocker(SpriteTexture spriteTexture, int x, int y, int width, int height){

        super(spriteTexture, x, y, width, height);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal) {
        return true;
    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y) {
        Point p = Terrain.getPlayer(terrain);

        terrain = Terrain.copy(terrain, p.getX(), p.getY());
        Orientation o = Terrain.getShortestOrientation(terrain, x, y);
        return o;

    }
}