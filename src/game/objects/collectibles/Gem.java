package game.objects.collectibles;

import engine.audios.SoundManager;
import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class Gem extends GameObject


{
    private SoundManager sm;
    public Gem(int x, int y, int width, int height, SoundManager sm)
    {
        super(new SpriteTexture(new Image("points.png")), x, y, width, height);
        Random randomGenerator = new Random();
        int randomSubImage =  randomGenerator.nextInt(3);
        setDefaultSubImage(randomSubImage);
        setCollisionSignal(CollisionSignal.GEM);
        setCollisionState(false);
        resizeHitbox(8, 8);
        this.sm = sm;
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case PACMAN : case PACMAN_INVINCIBLE:
                PacmanWorld pacmanWorld = (PacmanWorld)getWorld();
                pacmanWorld.setPlayerScore(pacmanWorld.getPlayerScore() + 10);
            this.getWorld().notifyObservers();
                pacmanWorld.decreaseGemCount(1);
                System.out.println(pacmanWorld.getPlayerScore());
                pacmanWorld.remove(this);
                //sm.playMusic();
                return true;
            default:
                return true;
        }
    }
}
