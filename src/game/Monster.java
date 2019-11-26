package game;

import engine.graphics.Orientation;
import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.Direction;
import engine.physics.MovementIntention;
import game.utils.tiledutils.Point;
import game.utils.tiledutils.Terrain;
import javafx.scene.input.KeyCode;

import static engine.graphics.Orientation.*;

public abstract class Monster extends Sprite {

    private Orientation orientation;

    private boolean scared;

    private boolean dead;

    public Monster(SpriteTexture spriteTexture, int x, int y)
    {
        super(spriteTexture, x, y);

        InputAction moveAction = new InputAction()
        {
            @Override
            protected void execute(Object... actionParameters)
            {
                addMovementIntention(new MovementIntention(getX(), getY(), getX() + (int)actionParameters[0], getY() + (int)actionParameters[1]));
                setOrientation((Orientation) actionParameters[2]);
            }
        };


        setOrientationDependantDisplay(true);

        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(EAST, 8);
    }

    public Orientation move(char[][] terrain, int x, int y){
        if(this.checkDir(terrain, x, y)){
            if(this.scared)
                return this.chase(terrain, x, y);
            else
                return this.flight(terrain, x , y);
        }
        return this.orientation;
    }

    public boolean checkDir(char[][] terrain, int x, int y){
        Point p = Terrain.getPlayer(terrain);
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
            return NORTH;
        }
        if(o != SOUTH && x<terrain.length && terrain[x+1][y] != 1){
            return SOUTH;
        }
        if(o != WEST && y>0 && terrain[x][y-1] != 1){
            return WEST;
        }
        if(o != EAST && y<terrain[0].length && terrain[x][y+1] != 1){
            return EAST;
        }
        return o;
    }

    public Orientation dig(int x, int y, int origX, int origY){
        if(x < origX)
            return Orientation.SOUTH;
        if(y > origY)
            return Orientation.WEST;
        if(y < origY)
            return EAST;
        return Orientation.NORTH;
    }

    public void blocked(){}

}
