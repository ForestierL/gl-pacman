package game;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import engine.input.Controllable;
import engine.input.InputAction;
import engine.input.InputScheme;
import engine.physics.MovementIntention;
import engine.graphics.Orientation;
import javafx.scene.input.KeyCode;

public class Pacman extends Sprite implements Controllable
{
    InputScheme inputScheme;

    public Pacman(SpriteTexture spriteTexture, int x, int y)
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
    public InputScheme getInputScheme()
    {
        return inputScheme;
    }
}
