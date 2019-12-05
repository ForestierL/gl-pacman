package game.objects;

import engine.graphics.ChangingMovingSpriteTexture;
import engine.graphics.MovingSpriteTexture;
import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.input.Controllable;
import engine.input.InputAction;
import engine.input.InputScheme;
import game.PacmanWorld;
import game.objects.collectibles.Gem;
import game.objects.collectibles.Powerup;
import game.objects.enemies.Blocker;
import game.objects.enemies.Chaser;
import game.objects.enemies.Crazy;
import game.utils.CollisionSignal;
import game.utils.Direction;
import game.utils.DisplacementSmoother;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import static game.utils.CollisionSignal.POWERUP;

public class Pacman extends GameObject implements Controllable {
    private InputScheme inputScheme;
    private DisplacementSmoother displacementSmoother;
    private int oldX;
    private int oldY;
    public boolean isInvincible;
    public int lives;
    public boolean isDead;
    public boolean isBlocked;
    public int origX;
    public int origY;
    public int cpt;


    public Pacman(int x, int y, int width, int height, int lives) {
        super(new MovingSpriteTexture(new Image("player_normal.png")), x, y, width, height);

        setCollisionSignal(CollisionSignal.PACMAN);
        this.oldX = getX() / 32;
        this.oldY = getY() / 32;
        this.isInvincible = false;
        InputAction moveAction = new InputAction() {
            @Override
            protected void execute(Object... actionParameters) {
                setOrientation((Orientation) actionParameters[0]);
                direction = (Direction) actionParameters[1];
            }
        };

        setSpeed(0.007);
        setPriority(5);
        inputScheme = new InputScheme();

        inputScheme.setKeyAction(KeyCode.UP, moveAction, Orientation.NORTH, Direction.Y_NEGATIVE);
        inputScheme.setKeyAction(KeyCode.DOWN, moveAction, Orientation.SOUTH, Direction.Y_POSITIVE);
        inputScheme.setKeyAction(KeyCode.LEFT, moveAction, Orientation.WEST, Direction.X_NEGATIVE);
        inputScheme.setKeyAction(KeyCode.RIGHT, moveAction, Orientation.EAST, Direction.X_POSITIVE);

        setOrientationDependantDisplay(true);
        cpt = 0;
        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(Orientation.EAST, 8);

        this.lives = lives;
        isBlocked = false;
        isDead = false;
        this.origX = x;
        this.origY = y;

        displacementSmoother = new DisplacementSmoother(this);
    }

    public void die() {
        if (!this.isDead) {
            if (this.lives <= 0) {
                this.getWorld().notifyObservers();

            } else {

                this.lives -= 1;
                this.setDead(true);
                this.block(true);
                this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("player_dead.png"), new Image("entity_null.png")));
            }
            PacmanWorld pc = (PacmanWorld) this.getWorld();
            for (int i = 0; i < pc.getEntities().size(); i++) {

                if (pc.getEntities().get(i).getClass() == Chaser.class) {
                    Chaser tmp = (Chaser) pc.getEntities().get(i);
                    tmp.setGoingHome(true);
                }
                if (pc.getEntities().get(i).getClass() == Blocker.class) {
                    Blocker tmp = (Blocker) pc.getEntities().get(i);
                    tmp.setGoingHome(true);
                }
                if (pc.getEntities().get(i).getClass() == Crazy.class) {
                    Crazy tmp = (Crazy) pc.getEntities().get(i);
                    tmp.setGoingHome(true);
                }
            }
        }

    }

    public void block(boolean b) {
        this.isBlocked = b;
    }

    public void setDead(boolean b) {

        this.isDead = b;

    }


    @Override
    public boolean handleCollision(CollisionSignal signal) {

        switch (signal) {
            case GEM:
                this.getWorld().notifyObservers();
                break;
            case POWERUP:
                this.getWorld().notifyObservers();
                break;
            case MONSTER:

                this.getWorld().notifyObservers();
                break;



        }
        return true;
    }


    @Override
    public void update(double elapsedTime) {
        if (!this.isBlocked) {
            super.update(elapsedTime);
            PacmanWorld pc = (PacmanWorld) this.getWorld();
            pc.level.terrain[oldY][oldX] = '0';
            oldX = getX() / 32;
            oldY = getY() / 32;


            pc.level.terrain[getY() / 32][getX() / 32] = 'P';

            addMovementIntent(displacementSmoother.getMovementIntent(direction));
        } else {
            PacmanWorld pc = (PacmanWorld) this.getWorld();
            if (this.origX != this.getX() || this.origY != this.getY()) {

                int cptBlocked = 0;
                int cptTotal = 0;
                for (int i = 0; i < pc.getEntities().size(); i++) {


                    if (pc.getEntities().get(i).getClass() == Chaser.class) {
                        Chaser tmp = (Chaser) pc.getEntities().get(i);
                        cptTotal++;
                        if (tmp.isBlocked)
                            cptBlocked++;
                    }
                    if (pc.getEntities().get(i).getClass() == Blocker.class) {
                        Blocker tmp = (Blocker) pc.getEntities().get(i);
                        cptTotal++;
                        if (tmp.isBlocked)
                            cptBlocked++;
                    }
                    if (pc.getEntities().get(i).getClass() == Crazy.class) {
                        Crazy tmp = (Crazy) pc.getEntities().get(i);
                        cptTotal++;
                        if (tmp.isBlocked)
                            cptBlocked++;
                    }

                }
                if (cptTotal == cptBlocked) {
                    this.setX(this.origX);
                    this.setY(this.origY);
                    this.setSpriteTexture(new ChangingMovingSpriteTexture(new Image("player_appear.png"), new Image("player_normal.png")));
                    cpt++;
                }
            }
            if (cpt != 0)
                cpt++;


            if (cpt == 50) {
                for (int i = 0; i < pc.getEntities().size(); i++) {


                    if (pc.getEntities().get(i).getClass() == Chaser.class) {
                        Chaser tmp = (Chaser) pc.getEntities().get(i);
                        tmp.block(false);

                    }
                    if (pc.getEntities().get(i).getClass() == Blocker.class) {
                        Blocker tmp = (Blocker) pc.getEntities().get(i);
                        tmp.block(false);

                    }
                    if (pc.getEntities().get(i).getClass() == Crazy.class) {
                        Crazy tmp = (Crazy) pc.getEntities().get(i);
                        tmp.block(false);

                    }
                }
                this.block(false);
                this.setDead(false);
                this.cpt = 0;


            }

        }


    }

    @Override
    public InputScheme getInputScheme() {
        return inputScheme;
    }

    public int getLives() {
        return lives;
    }
}
