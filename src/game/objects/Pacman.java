package game.objects;

import engine.graphics.MovingSpriteTexture;
import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.input.Controllable;
import engine.input.InputAction;
import engine.input.InputScheme;
import game.PacmanWorld;
import game.utils.CollisionSignal;
import game.utils.Direction;
import game.utils.DisplacementSmoother;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Pacman extends GameObject implements Controllable
{
    private InputScheme inputScheme;
    private DisplacementSmoother displacementSmoother;
    private int oldX;
    private int oldY;
    public boolean isInvincible;
    public int lives;


    public Pacman(int x, int y, int width, int height, int lives)
    {
        super(new MovingSpriteTexture(new Image("player_normal.png")), x, y, width, height);

        setCollisionSignal(CollisionSignal.PACMAN);
        this.oldX = getX()/32;
        this.oldY = getY()/32;
        this.isInvincible = false;
        InputAction moveAction = new InputAction()
        {
            @Override
            protected void execute(Object... actionParameters)
            {
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

        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(Orientation.EAST, 8);

        this.lives = lives;

        displacementSmoother = new DisplacementSmoother(this);
    }

    public void die(){
        this.lives -= 1;
        if(this.lives == 0){
            this.getWorld().notifyObservers();

        }


    }



    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case MONSTER:




        }
        return true;
    }


    @Override
    public void update(double elapsedTime)
    {
        super.update(elapsedTime);
        PacmanWorld pc = (PacmanWorld) this.getWorld();
        pc.level.terrain[oldY][oldX] = '0';
        oldX = getX()/32;
        oldY = getY()/32;


        pc.level.terrain[getY()/32][getX()/32] = 'P';

        addMovementIntent(displacementSmoother.getMovementIntent(direction));

    }

    @Override
    public InputScheme getInputScheme()
    {
        return inputScheme;
    }

}
