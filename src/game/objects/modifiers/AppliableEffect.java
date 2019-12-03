package game.objects.modifiers;

import game.objects.GameObject;

public interface AppliableEffect

{
    void applyModifier(GameObject gameObject, double duration);

    void removeModifier(GameObject gameObject);

}
