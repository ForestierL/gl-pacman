package game.objects.collectibles;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class Powerup extends GameObject
{

    public Powerup(int x, int y, int width, int height)
    {
        super(new SpriteTexture(new Image("points.png")), x, y, width, height);
        setDefaultSubImage(4);
        setCollisionState(false);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case PACMAN:
                PacmanWorld pacmanWorld = (PacmanWorld)getWorld();
                pacmanWorld.setPlayerScore(pacmanWorld.getPlayerScore() + 5000);
                System.out.println("POWERUP");
                pacmanWorld.remove(this);
                return true;
            default:
                return true;
        }
    }
}
