package game.objects;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.input.Controllable;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.MovementIntent;
import game.utils.CollisionSignal;
import game.utils.Direction;
import javafx.scene.input.KeyCode;

public class Pacman extends GameObject implements Controllable
{
    private InputScheme inputScheme;

    public Pacman(SpriteTexture spriteTexture, int x, int y)
    {
        super(spriteTexture, x, y);

        setCollisionSignal(CollisionSignal.PACMAN);

        InputAction moveAction = new InputAction()
        {
            @Override
            protected void execute(Object... actionParameters)
            {
                setOrientation((Orientation) actionParameters[0]);
                direction = (Direction) actionParameters[1];
            }
        };

        setSpeed(6);
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
    }

    @Override
    void handleCollision(CollisionSignal signal) {

    }


    @Override
    public void update()
    {
        int distanceX = 0;
        int distanceY = 0;
        switch(direction)
        {
            case NONE:
                return;
            case X_NEGATIVE:
                distanceX = -1;
                break;
            case X_POSITIVE:
                distanceX = 1;
                break;
            case Y_NEGATIVE:
                distanceY = -1;
                break;
            case Y_POSITIVE:
                distanceY = 1;
                break;
            default:
                return;

        }
        addMovementIntent(new MovementIntent(getX(), getY(), getX() + distanceX, getY() + distanceY));
    }

    @Override
    public InputScheme getInputScheme()
    {
        return inputScheme;
    }


}
