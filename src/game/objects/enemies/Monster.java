package game.objects.enemies;

import engine.audios.SoundManager;
import engine.graphics.*;
import game.PacmanWorld;
import game.objects.GameObject;
import game.utils.*;
import javafx.scene.image.Image;

import java.io.File;

public abstract class Monster extends GameObject {

    private SoundManager soundManager;
    private SoundManager soundManager2;

    private int oldX;
    private int oldY;
    private int origX;
    private int origY;

    private int difficulty;

    private String state;
    private boolean blocked;

    private int x;
    private int y;
    private int[][] terrain;

    private DisplacementSmoother displacementSmoother;

    protected Monster(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {

        super(spriteTexture, x, y, width, height);

        this.direction = Direction.Y_NEGATIVE;

        this.difficulty = difficulty;

        this.state = "chasing";
        this.blocked = false;

        this.setSpeed(0.02 - 0.002 * difficulty);
        this.setCollisionSignal(CollisionSignal.MONSTER);

        this.x = x/32;
        this.y = y/32;

        this.oldX = x/32;
        this.oldY = y/32;

        this.origX = x/32;
        this.origY = y/32;

        setOrientationDependantDisplay(true);
        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 8);
        addOrientationKey(Orientation.EAST, 4);

        displacementSmoother = new DisplacementSmoother(this);
        soundManager = new SoundManager(new File("resources/audio/sounds/monster_hit_you.mp3").toURI().toString(), 1.0);
        soundManager2 = new SoundManager(new File("resources/audio/sounds/monster_take_downt.mp3").toURI().toString(), 1.0);
    }

    protected int[][] getTerrain() {
        PacmanWorld tmp = (PacmanWorld) this.getWorld();
        return Terrain.transformMatrix(tmp.level.terrain);
    }

    private void update(){
        this.x = this.getX2();
        this.y = this.getY2();
        this.terrain = this.getTerrain();
    }

    protected Direction move() {
        this.update();
        if (this.atIntersection()) {
            this.forceMove();
        }
        return this.direction;
    }

    protected Direction forceMove() {
        this.update();
        switch (state) {
            case "scared":
                this.direction = this.flight();
                break;
            case "dead":
                if (this.oldX == x && this.oldY == y)
                    this.setDead(false);
                this.direction = this.goHome();
                break;
            case "goingHome":
                this.direction = this.goHome();
                if (this.oldX == x && this.oldY == y)
                    block(true);
                break;
            default:
                this.direction = this.chase();
                break;
        }
        return this.direction;
    }

    protected boolean hasSidePath() {

        int x = this.getX2();
        int y = this.getY2();
        if ((this.direction == Direction.Y_NEGATIVE || this.direction == Direction.Y_POSITIVE) && ((x > 0 && terrain[y][x - 1] != '1') || (x <= terrain[0].length && terrain[y][x + 1] != 1))) {
            return true;
        }
        return (this.direction == Direction.X_NEGATIVE || this.direction == Direction.X_POSITIVE) && ((y > 0 && terrain[y - 1][x] != '1') || (y <= terrain.length && terrain[y + 1][x] != 1));
    }

    protected boolean atIntersection() {
        if ((getX2() == this.oldX && getY2() == this.oldY &&
                ((this.direction == Direction.X_POSITIVE && x <= terrain[0].length && terrain[y][x + 1] != 1) ||
                        (this.direction == Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] != 1) ||
                        (this.direction == Direction.Y_POSITIVE && y <= terrain.length && terrain[y + 1][x] != 1) ||
                        (this.direction == Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] != 1))))
            return false;

        if (hasSidePath()) {
            return true;
        }
        if ((this.direction == Direction.X_NEGATIVE) && x > 0 && terrain[y][x - 1] != 1) {
            return false;
        }
        if ((this.direction == Direction.X_POSITIVE) && x <= terrain[0].length && terrain[y][x + 1] != 1) {
            return false;
        }
        if ((this.direction == Direction.Y_NEGATIVE) && y > 0 && terrain[y - 1][x] != 1) {
            return false;
        }
        if ((this.direction == Direction.Y_POSITIVE) && y <= terrain.length && terrain[y + 1][x] != 1) {
            return false;
        }
        return true;
    }

    protected abstract Direction chase();

    protected Direction flight() {


        Direction d = Terrain.getShortestDirection2(getTerrain(), x, y);

        if (d != Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] != 1) {
            return Direction.X_NEGATIVE;
        }
        if (d != Direction.X_POSITIVE && x <= terrain[0].length && terrain[y][x + 1] != 1) {
            return Direction.X_POSITIVE;
        }
        if (d != Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] != 1) {
            return Direction.Y_NEGATIVE;
        }
        if (d != Direction.Y_POSITIVE && y <= terrain.length && terrain[y + 1][x] != 1) {
            return Direction.Y_POSITIVE;
        }

        return d;
    }


    protected Direction goHome() {
        return Terrain.getShortestDirection(getTerrain(), getX2(), getY2(), this.origX, this.origY);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal) {

        switch (signal) {
            case MONSTER:
                break;

            case PACMAN:
                if (this.state.equals("scared")) {
                    this.setDead(true);
                } else {
                    PacmanWorld pacmanWorld = (PacmanWorld) getWorld();
                    if (!this.state.equals("dead")) {
                        soundManager2.playMusic();
                        pacmanWorld.pacman.die();
                    }
                }
                return true;

            case PACMAN_INVINCIBLE:

                this.setDead(true);
                return true;
        }

        return true;
    }

    public void setDead(boolean b) {
        PacmanWorld pc = (PacmanWorld) this.getWorld();
        if (b) {
            if (!this.state.equals("dead")) {
                pc.setPlayerScore(200 + pc.getPlayerScore());

                this.getWorld().notifyObservers();
                this.state = "dead";
                soundManager.playMusic();
                this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("monster_dig_down.png"), new Image("monster_underground.png")));
                this.forceMove();
                this.direction = this.forceMove();
                this.setOrientation(Orientation.values()[this.direction.ordinal()]);
                oldX = getX2();
                oldY = getY2();
                this.addMovementIntent(displacementSmoother.getMovementIntent(direction));
            }
        } else {if(!pc.pacman.isInvincible){
            this.state = "chasing";
            this.setSpeed(0.0002);
            this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("monster_dig_up.png"), new Image("monster_angry.png")));
        }}


    }

    public void setScared(boolean b) {

        if (b && !this.state.equals("dead")) {
            this.state = "scared";
            this.setSpeed(0.020);
            this.setSpriteTexture(new MovingSpriteTexture(new Image("monster_scary.png")));
            this.direction = this.forceMove();
            this.setOrientation(Orientation.values()[this.direction.ordinal()]);
            oldX = getX2();
            oldY = getY2();
            this.addMovementIntent(displacementSmoother.getMovementIntent(direction));
        } else if (!this.state.equals("dead")) {
            this.state = "chasing";
            this.setSpriteTexture(new MovingSpriteTexture(new Image("monster_angry.png")));
            this.setSpeed(0.0015);
        }


    }

    @Override
    public void update(double elapsedTime) {
        if (this.state != "blocked") {
            super.update(elapsedTime);
            this.direction = this.move();
            this.setOrientation(Orientation.values()[this.direction.ordinal()]);
            oldX = getX2();
            oldY = getY2();
            this.addMovementIntent(displacementSmoother.getMovementIntent(direction));
        }
    }

    public void block(boolean b) {
        if (b)
            this.blocked = true;
        else{
            this.blocked = false;
            this.state = "chasing";}
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDifficulty() {
        return this.difficulty;
    }
    public int getX2(){return super.getX()/32;}
    public int getY2(){return super.getY()/32;}


}
