package game.objects.modifiers;

import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class InvincibleEffect extends EffectModifier
{
    public InvincibleEffect(AppliableEffect appliableEffect)
    {
        super(appliableEffect);
    }

    @Override
    public void applyModifier(GameObject gameObject, double duration)
    {
        PacmanWorld pc = (PacmanWorld)gameObject.getWorld();
        pc.pacman.isInvincible = true;
        super.applyModifier(gameObject, duration);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN_INVINCIBLE);
        gameObject.setSpriteTexture(new SpriteTexture(new Image("player_powered.png")));
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        PacmanWorld pc = (PacmanWorld)gameObject.getWorld();
        pc.pacman.isInvincible = false;
        super.removeModifier(gameObject);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN);
        gameObject.setSpriteTexture(new SpriteTexture(new Image("player_normal.png")));
    }
}
