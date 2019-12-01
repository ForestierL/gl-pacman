package game.objects.modifiers;

import game.objects.GameObject;

public abstract class EffectModifier implements AppliableEffect
{
    private AppliableEffect appliableEffect;

    EffectModifier(AppliableEffect appliableEffect)
    {
        this.appliableEffect = appliableEffect;
    }

    @Override
    public void applyModifier(GameObject gameObject, double duration)
    {
        appliableEffect.applyModifier(gameObject, duration);
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        appliableEffect.removeModifier(gameObject);
    }
}
