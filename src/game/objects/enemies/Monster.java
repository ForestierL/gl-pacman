package game.objects.enemies;

import engine.audios.SoundManager;
import engine.graphics.*;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.MovementIntent;
import game.PacmanWorld;
import game.objects.GameObject;
import game.objects.Pacman;
import game.utils.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.File;

import static engine.graphics.Orientation.*;

public abstract class Monster extends GameObject {

    SoundManager soundManager = new SoundManager(new File("resources/audio/sounds/monster_hit_you.mp3").toURI().toString(),1.0);
    SoundManager soundManager2 = new SoundManager(new File("resources/audio/sounds/monster_take_downt.mp3").toURI().toString(),1.0);
    private Orientation orientation;

    private boolean scared;

    private boolean dead;
    public int oldX;
    public int oldY;
    private int origX;
    private int origY;
    public int difficulty;
    private int ite;
    private int cpt;
    private boolean goingHome;
    public boolean isBlocked;

    private DisplacementSmoother displacementSmoother;

    public Monster(SpriteTexture spriteTexture, int x, int y, int width, int height, int difficulty) {
        super(spriteTexture, x, y, width, height);

        this.direction = Direction.Y_NEGATIVE;
        this.scared = false;
        this.dead = false;
        this.difficulty = difficulty;
        setSpeed(0.02 - 0.002 * difficulty);
        setCollisionSignal(CollisionSignal.MONSTER);

        oldX = getX() / 32;
        oldY = getY() / 32;

        this.origX = getX() / 32;
        this.origY = getY() / 32;

        setOrientationDependantDisplay(true);
        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 8);
        addOrientationKey(Orientation.EAST, 4);

        displacementSmoother = new DisplacementSmoother(this);
    }

    private char[][] getTerrain() {
        PacmanWorld tmp = (PacmanWorld) this.getWorld();
        return tmp.level.terrain;
    }

    public Direction move() {

        int x = this.getX() / 32;
        int y = this.getY() / 32;
        char[][] terrain = getTerrain();
        int[][] ter = Terrain.transformMatrix(terrain);


        PacmanWorld pc = (PacmanWorld) this.getWorld();

        if (this.atIntersection(terrain, x, y)) {

            if (!this.goingHome) {
                if (!this.scared) {
                    if (!dead)
                        return this.chase(ter, x, y);
                    else {
                        if (this.oldX == x && this.oldY == y) {
                            this.setDead(false);
                            this.move();
                        } else {
                            return this.dig(ter, x, y);
                        }
                    }

                } else {
                    return this.flight(ter, x, y);
                }
            } else {
                if (this.oldX == x && this.oldY == y) {
                    this.setGoingHome(false);
                    this.block(true);
                }
                return this.goHome(ter, x, y);
            }
        }
        return this.direction;
    }

    public void block(boolean b) {
        this.isBlocked = b;
    }

    public void setGoingHome(boolean b) {
        this.goingHome = b;
    }

    public Direction forceMove() {

        int x = this.getX() / 32;
        int y = this.getY() / 32;
        char[][] terrain = getTerrain();
        int[][] ter = Terrain.transformMatrix(terrain);
        if (!this.scared) {
            if (!dead)
                return this.chase(ter, x, y);
            else {
                return this.dig(ter, x, y);
            }
        } else {
            return this.flight(ter, x, y);
        }

    }


    private boolean hasSidePath() {

        int x = this.getX() / 32;
        int y = this.getY() / 32;
        char[][] terrain = getTerrain();
        if ((this.direction == Direction.Y_NEGATIVE || this.direction == Direction.Y_POSITIVE) && ((x > 0 && terrain[y][x - 1] != '1') || (x <= terrain[0].length && terrain[y][x + 1] != '1')))
            return true;
        if ((this.direction == Direction.X_NEGATIVE || this.direction == Direction.X_POSITIVE) && ((y > 0 && terrain[y - 1][x] != '1') || (y <= terrain.length && terrain[y + 1][x] != '1')))

            return true;

        return false;


    }

    private boolean atIntersection(char[][] terrain, int x, int y) {
        if ((getX() / 32 == this.oldX && getY() / 32 == this.oldY &&
                ((this.direction == Direction.X_POSITIVE && x <= terrain[0].length && terrain[y][x + 1] != '1') ||
                        (this.direction == Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] != '1') ||
                        (this.direction == Direction.Y_POSITIVE && y <= terrain.length && terrain[y + 1][x] != '1') ||
                        (this.direction == Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] != '1'))))
            return false;

        if (hasSidePath()) {
            return true;
        }
        if ((this.direction == Direction.X_NEGATIVE) && x > 0 && terrain[y][x - 1] != '1') {
            return false;
        }
        if ((this.direction == Direction.X_POSITIVE) && x <= terrain[0].length && terrain[y][x + 1] != '1') {
            return false;
        }
        if ((this.direction == Direction.Y_NEGATIVE) && y > 0 && terrain[y - 1][x] != '1') {
            return false;
        }
        if ((this.direction == Direction.Y_POSITIVE) && y <= terrain.length && terrain[y + 1][x] != '1') {
            return false;
        }
        return true;
    }

    public abstract Direction chase(int[][] terrain, int x, int y);

    private Direction flight(int[][] terrain, int x, int y) {


        Direction d = Terrain.getShortestDirection2(terrain, x, y);

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

    private Direction dig(int[][] terrain, int origX, int origY) {
        return Terrain.getShortestDirection(terrain, origX, origY, this.origX, this.origY);
    }

    private Direction goHome(int[][] terrain, int origX, int origY) {
        return Terrain.getShortestDirection(terrain, origX, origY, this.origX, this.origY);
    }


    @Override
    public boolean handleCollision(CollisionSignal signal) {

        switch (signal) {
            case MONSTER:
                break;

            case PACMAN:
                if (this.scared) {

                    this.setDead(true);

                } else {
                    PacmanWorld pacmanWorld = (PacmanWorld) getWorld();
                    if(!this.dead){
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

    public void holdInPlace(int ite) {
        this.cpt = 0;
        this.ite = ite;
    }

    public void setDead(boolean b) {

        int x = this.getX() / 32;
        int y = this.getY() / 32;

        this.dead = b;
        this.scared = false;


        if (b) {
            soundManager.playMusic();
            this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("monster_dig_down.png"), new Image("monster_underground.png")));
            this.forceMove();
            this.direction = this.forceMove();
            setOrientation(Orientation.values()[this.direction.ordinal()]);
            oldX = (getX()) / 32;
            oldY = (getY()) / 32;
            addMovementIntent(displacementSmoother.getMovementIntent(direction));
        } else {
            this.setSpeed(0.0002);
            this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("monster_dig_up.png"), new Image("monster_angry.png")));
        }


    }

    public void setScared(boolean b) {

        this.scared = b;
        if (b && !this.dead) {
            this.setSpeed(0.020);
            this.setSpriteTexture(new MovingSpriteTexture(new Image("monster_scary.png")));
            this.direction = this.forceMove();
            setOrientation(Orientation.values()[this.direction.ordinal()]);
            oldX = (getX()) / 32;
            oldY = (getY()) / 32;
            addMovementIntent(displacementSmoother.getMovementIntent(direction));
        } else if (!this.dead) {
            this.setSpriteTexture(new MovingSpriteTexture(new Image("monster_angry.png")));
            this.setSpeed(0.0015);
        }


    }

    @Override
    public void update(double elapsedTime) {
        if (!this.isBlocked) {
            super.update(elapsedTime);
            this.direction = this.move();
            setOrientation(Orientation.values()[this.direction.ordinal()]);
            oldX = (getX()) / 32;
            oldY = (getY()) / 32;
            addMovementIntent(displacementSmoother.getMovementIntent(direction));
        }
    }

}
