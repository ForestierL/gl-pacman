package game.objects.collectibles;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.objects.modifiers.AppliableEffect;
import game.objects.modifiers.InvincibleEffect;
import game.objects.modifiers.MiniEffect;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class Powerup extends GameObject
{
    private AppliableEffect powerupEffect;

    public Powerup(int x, int y, int width, int height, AppliableEffect powerUpEffect)
    {
        super(new SpriteTexture(new Image("points.png")), x, y, width, height);
        if(powerUpEffect.getClass() == MiniEffect.class)
            setDefaultSubImage(6);
        if(powerUpEffect.getClass() == InvincibleEffect.class)
            setDefaultSubImage(4);
        setCollisionSignal(CollisionSignal.POWERUP);
        setCollisionState(false);
        resizeHitbox(12, 12);
        powerupEffect = powerUpEffect;
    }

    @Override
    public boolean handleCollision(CollisionSignal signal)
    {
        switch(signal)
        {
            case PACMAN: case PACMAN_INVINCIBLE:
                PacmanWorld pacmanWorld = (PacmanWorld)getWorld();
                pacmanWorld.setPlayerScore(pacmanWorld.getPlayerScore() + 700);
                this.getWorld().notifyObservers();
                pacmanWorld.pacman.addEffect(powerupEffect, 7);

                pacmanWorld.remove(this);

                return true;

            default:
                return true;
        }
    }
}
