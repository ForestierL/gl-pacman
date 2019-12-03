package game.objects.modifiers;

import game.objects.GameObject;

public class MiniEffect implements AppliableEffect
{

    @Override
    public void applyModifier(GameObject gameObject, double duration)
    {
        gameObject.resizeEntity(0.5);
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        gameObject.resizeEntity(2);
        gameObject.setOnTheGrid();
    }

}
