package game.objects;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.input.Controllable;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.MovementIntent;
import game.utils.CollisionSignal;
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
                addMovementIntent(new MovementIntent(getX(), getY(), getX() + (int)actionParameters[0], getY() + (int)actionParameters[1]));
                setOrientation((Orientation) actionParameters[2]);
            }
        };

        inputScheme = new InputScheme();

        inputScheme.setKeyAction(KeyCode.UP, moveAction, 0, -1, Orientation.NORTH);
        inputScheme.setKeyAction(KeyCode.DOWN, moveAction, 0, 1, Orientation.SOUTH);
        inputScheme.setKeyAction(KeyCode.LEFT, moveAction, -1, 0, Orientation.WEST);
        inputScheme.setKeyAction(KeyCode.RIGHT, moveAction, 1, 0, Orientation.EAST);

        setOrientationDependantDisplay(true);

        addOrientationKey(Orientation.NORTH, 12);
        addOrientationKey(Orientation.NONE, 12);
        addOrientationKey(Orientation.SOUTH, 0);
        addOrientationKey(Orientation.WEST, 4);
        addOrientationKey(Orientation.EAST, 8);
    }

    @Override
    void handleCollision(CollisionSignal signal)
    {

    }

    @Override
    public InputScheme getInputScheme()
    {
        return inputScheme;
    }


}
