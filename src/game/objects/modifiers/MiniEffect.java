package game.objects.modifiers;

import game.objects.GameObject;

public class MiniEffect extends EffectModifier
{
    public MiniEffect(AppliableEffect appliableEffect)
    {
        super(appliableEffect);
    }

    @Override
    public void applyModifier(GameObject gameObject, double duration)
    {
        super.applyModifier(gameObject, duration);
        gameObject.resizeEntity(16, 16);
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        gameObject.resizeEntity(32, 32);
        gameObject.setOnTheGrid();
    }

}
