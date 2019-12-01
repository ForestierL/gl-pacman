package game.objects.modifiers;

import engine.graphics.SpriteTexture;
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
        super.applyModifier(gameObject, duration);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN_INVINCIBLE);
        gameObject.setSpriteTexture(new SpriteTexture(new Image("player_powered.png")));
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        super.removeModifier(gameObject);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN);
        gameObject.setSpriteTexture(new SpriteTexture(new Image("player_normal.png")));
    }
}
