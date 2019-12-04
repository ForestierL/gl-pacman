package game.objects.collectibles;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class LargeGem extends GameObject
{

    public LargeGem(int x, int y, int width, int height)
    {
        super(new SpriteTexture(new Image("points.png")), x, y, width, height);
        setDefaultSubImage(5);
        setCollisionSignal(CollisionSignal.LARGE_GEM);
        setCollisionState(false);
        setVisible(false);
        resizeHitbox(12, 12);
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case PACMAN : case PACMAN_INVINCIBLE:
                PacmanWorld pacmanWorld = (PacmanWorld)getWorld();
                if(pacmanWorld.largeGemSpawned)
                {
                    pacmanWorld.setPlayerScore(pacmanWorld.getPlayerScore() + 200);
                    System.out.println(pacmanWorld.getPlayerScore());
                    pacmanWorld.remove(this);
                }

                return true;
            default:
                return true;
        }
    }
}
