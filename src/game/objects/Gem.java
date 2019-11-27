package game.objects;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

import java.util.Random;

public class Gem extends GameObject
{

    public Gem(int x, int y)
    {
        super(new SpriteTexture(new Image("points.png")), x, y);
        Random randomGenerator = new Random();
        int randomSubImage =  randomGenerator.nextInt(3);
        setDefaultSubImage(randomSubImage);
        setCollisionState(false);
    }

    @Override
    void handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case PACMAN:
                PacmanWorld pacmanWorld = (PacmanWorld)getWorld();
                pacmanWorld.setPlayerScore(pacmanWorld.getPlayerScore() + 100);
                System.out.println(pacmanWorld.getPlayerScore());
                pacmanWorld.remove(this);
        }
    }
}
