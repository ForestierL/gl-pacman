package game.objects.modifiers;

import engine.audios.SoundManager;
import game.objects.GameObject;

import java.io.File;

public abstract class EffectModifier implements AppliableEffect
{
    private AppliableEffect appliableEffect;
    SoundManager soundManager = new SoundManager(new File("resources/audio/sounds/power_start.mp3").toURI().toString(),1.0);
    SoundManager soundManager2 = new SoundManager(new File("resources/audio/sounds/power_stop.mp3").toURI().toString(),1.0);

    EffectModifier(AppliableEffect appliableEffect)
    {
        this.appliableEffect = appliableEffect;
    }

    @Override
    public void applyModifier(GameObject gameObject, double duration)
    {
        appliableEffect.applyModifier(gameObject, duration);
        soundManager.playMusic();
    }

    @Override
    public void removeModifier(GameObject gameObject)
    {
        appliableEffect.removeModifier(gameObject);
        soundManager2.playMusic();
    }
}
