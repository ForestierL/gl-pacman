package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.utils.CollisionSignal;
import game.utils.Point;
import game.utils.Terrain;

public class Blocker extends Monster{

    public Blocker(SpriteTexture spriteTexture, int x, int y){

        super(spriteTexture, x, y);
    }

    @Override
    public void handleCollision(CollisionSignal signal) {

    }

    @Override
    public Orientation chase(char[][] terrain, int x, int y) {
        Point p = Terrain.getPlayer(terrain);

        terrain = Terrain.copy(terrain, p.getX(), p.getY());
        Orientation o = Terrain.getShortestOrientation(terrain, x, y);
        return o;

    }
}