package game;

import engine.graphics.Orientation;
import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.Direction;
import game.objects.GameObject;
import game.utils.CollisionSignal;
import game.utils.tiledutils.Point;
import game.utils.tiledutils.Terrain;
import javafx.scene.input.KeyCode;

import static engine.graphics.Orientation.*;

public abstract class Monster extends GameObject {

    private Orientation orientation;

    private boolean scared;

    private boolean dead;

    public Monster(SpriteTexture spriteTexture, int x, int y)
    {
        super(spriteTexture, x, y);

        this.orientation = NORTH;
        this.scared = false;
        this.dead = false;

        setOrientationDependantDisplay(true);

        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(EAST, 8);
    }

    public Orientation getDir(char[][] terrain, int x, int y){
        if(this.atIntersection(terrain, x, y)){
            if(this.scared)
                return this.chase(terrain, x, y);
            else
                return this.flight(terrain, x , y);
        }
        return this.orientation;
    }

    public Orientation recalculateDir(char[][] terrain, int x, int y){

        if(this.scared)
            return this.chase(terrain, x, y);
        else
           return this.flight(terrain, x , y);

    }

    private boolean hasSideRoads(char[][]terrain, int x, int y){
        if((this.orientation == Orientation.NORTH || this.orientation == Orientation.SOUTH) && (y>0 && terrain[x][y-1] != 1) || (y<=terrain[x].length && terrain[x][y+1] != 1))
            return true;
        if((this.orientation == Orientation.WEST || this.orientation == Orientation.EAST) && (x>0 && terrain[x-1][y] != 1) || (x<=terrain.length && terrain[x+1][y] != 1))
            return true;
        return false;
    }

    public boolean atIntersection(char[][] terrain, int x, int y){
        Point p = Terrain.getPlayer(terrain);
        if(hasSideRoads(terrain, x, y))
                return true;
        if(this.orientation == Orientation.NORTH && x>0 && terrain[x-1][y] != 1){
            return true;
        }
        if(this.orientation == Orientation.SOUTH && x<=terrain.length && terrain[x-1][y] != 1){
            return true;
        }
        if(this.orientation == Orientation.WEST && y>0 && terrain[x-1][y] != 1){
            return true;
        }
        if(this.orientation == EAST && y<terrain[0].length && terrain[x-1][y] != 1){
            return true;
        }
        return false;
    }

    public abstract Orientation chase(char[][] terrain, int x, int y);

    public Orientation flight(char[][] terrain, int x, int y){

        Orientation o = Terrain.getShortestOrientation(terrain, x, y);

        if(o != NORTH && x>0 && terrain[x-1][y] != 1){
            this.orientation = Orientation.NORTH;
            return NORTH;
        }
        if(o != SOUTH && x<terrain.length && terrain[x+1][y] != 1){
            this.orientation = Orientation.SOUTH;
            return SOUTH;
        }
        if(o != WEST && y>0 && terrain[x][y-1] != 1){
            this.orientation = Orientation.WEST;
            return WEST;
        }
        if(o != EAST && y<terrain[0].length && terrain[x][y+1] != 1){
            this.orientation = Orientation.EAST;
            return EAST;
        }
        this.orientation = o;
        return o;
    }

    public Orientation dig(int x, int y, int origX, int origY){
        if(x < origX) {
            this.orientation = Orientation.SOUTH;
            return Orientation.SOUTH;
        }
        if(y > origY) {
            this.orientation = Orientation.WEST;
            return Orientation.WEST;
        }
        if(y < origY) {
            this.orientation = Orientation.EAST;
            return EAST;
        }
        this.orientation = Orientation.NORTH;
        return Orientation.NORTH;
    }


    public void blocked(){}

}
