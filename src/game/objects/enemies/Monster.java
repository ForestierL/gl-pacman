package game.objects.enemies;

import engine.graphics.Orientation;
import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.MovementIntent;
import game.PacmanWorld;
import game.objects.GameObject;
import game.objects.Pacman;
import game.utils.*;
import javafx.scene.input.KeyCode;

import static engine.graphics.Orientation.*;

public abstract class Monster extends GameObject {

    private Orientation orientation;

    private boolean scared;

    private boolean dead;
    public int oldX;
    public int oldY;

    private DisplacementSmoother displacementSmoother;

    public Monster(SpriteTexture spriteTexture, int x, int y, int width, int height)
    {
        super(spriteTexture, x, y, width, height);

        setOrientationDependantDisplay(true);
        this.direction = Direction.Y_NEGATIVE;
        this.scared=false;
        this.dead=false;
        setSpeed(300);
        oldX = getX()/32;
        oldY = getY()/32;
        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(EAST, 8);

        displacementSmoother = new DisplacementSmoother(this);
    }
    private char[][] getTerrain(){
        PacmanWorld tmp = (PacmanWorld) this.getWorld();
        return tmp.level.terrain;}

    public Direction move(){


        int x = this.getX()/32;
        int y = this.getY()/32;
        char[][] terrain = getTerrain();
        if(this.atIntersection(terrain, x, y)){
            if(!this.scared) {
                return this.chase(terrain, x, y);

            }
            else {
                return this.flight(terrain, x, y);
            }
        }
        return this.direction;
    }

    public Direction forceComputeDir(){

        int x = this.getX()/32;
        int y = this.getY()/32;
        char[][] terrain = getTerrain();

        if(!this.scared)
            return this.chase(terrain, x, y);
        else
            return this.flight(terrain, x , y);

    }

    private boolean hasSidePath(){

        int x = this.getX()/32;
        int y = this.getY()/32;
        char[][] terrain = getTerrain();
        if((this.direction == Direction.Y_NEGATIVE || this.direction == Direction.Y_POSITIVE) && ((x>0 && terrain[y][x-1] != '1')||(x<=terrain[0].length && terrain[y][x+1] != '1')))
            return true;
        if((this.direction == Direction.X_NEGATIVE || this.direction == Direction.X_POSITIVE) && ((y>0 && terrain[y-1][x] != '1')||(y<=terrain.length && terrain[y+1][x] != '1')))

            return true;

        return false;


    }

    private boolean atIntersection(char[][] terrain, int x, int y){

        Point p = Terrain.getPlayer(terrain);
        if(hasSidePath()){
            return true;
        }
        if((this.direction == Direction.X_NEGATIVE) && x>0 && terrain[y][x-1] != '1'){
            return false;
        }
        if((this.direction == Direction.X_POSITIVE) && x<=terrain[0].length && terrain[y][x+1] != '1'){
            return false;
        }
        if((this.direction == Direction.Y_NEGATIVE) && y>0 && terrain[y-1][x] != '1'){
            return false;
        }
        if((this.direction == Direction.Y_POSITIVE) && y<=terrain.length && terrain[y+1][x] != '1'){
            return false;
        }
        return true;
    }

    public abstract Direction chase(char[][] terrain, int x, int y);

    private Direction flight(char[][] terrain, int x, int y){

        Direction d = Terrain.getShortestDirection(terrain, x, y);

        if(d != Direction.X_NEGATIVE && x>0 && terrain[y][x-1] != '1'){
            return Direction.X_NEGATIVE;
        }
        if(d != Direction.X_POSITIVE && x<=terrain[0].length && terrain[y][x+1] != '1'){
            return Direction.X_POSITIVE;
        }
        if(d != Direction.Y_NEGATIVE && y>0 && terrain[y-1][x] != '1'){
            return Direction.Y_NEGATIVE;
        }
        if(d != Direction.Y_POSITIVE && y<=terrain.length && terrain[y+1][x] != '1'){
            return Direction.Y_POSITIVE;
        }
        return d;
    }

    private Direction dig(int x, int y, int origX, int origY){
        if(x < origX)
            return Direction.X_POSITIVE;
        if(y > origY)
            return Direction.Y_NEGATIVE;
        if(y < origY)
            return Direction.Y_POSITIVE;
        return Direction.X_NEGATIVE;
    }

    @Override
    public boolean handleCollision(CollisionSignal signal) {

        return true;
    }

    public void setDead(boolean b){

        int x = this.getX()/32;
        int y = this.getY()/32;
        char[][] terrain = getTerrain();

        if(b)
            this.move();
        else
            this.move();
        this.dead = b;
    }

    public void setScared(boolean b){

        int x = this.getX()/32;
        int y = this.getY()/32;
        char[][] terrain = getTerrain();

        this.move();

        this.scared = b;
    }

    @Override
    public void update(){

        this.direction = this.move();
        oldX = getX()/32;
        oldY = getY()/32;
        addMovementIntent(displacementSmoother.getMovementIntent(direction));
    }

}
