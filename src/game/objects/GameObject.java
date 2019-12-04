package game.objects;

import engine.graphics.Sprite;
import engine.graphics.SpriteTexture;
import game.objects.modifiers.AppliableEffect;
import game.utils.CollisionSignal;
import game.utils.Direction;
import javafx.util.Pair;

import java.awt.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class GameObject extends Sprite
{
    public ConcurrentHashMap<AppliableEffect, Double> appliedEffects = new ConcurrentHashMap<>();

    public Direction direction = Direction.NONE;

    public GameObject(SpriteTexture spriteTexture, int x, int y, int width, int height)
    {
        super(spriteTexture, x, y, width, height);

    }

    public void setCollisionSignal(CollisionSignal collisionSignal)
    {
        super.setCollisionSignal(collisionSignal.ordinal());
    }

    @Override
    public boolean handleCollision(int signal)
    {
        return handleCollision(CollisionSignal.values()[signal]);
    }

    public abstract boolean handleCollision(CollisionSignal signal);

    @Override
    public void update(double elapsedTime)
    {
        for(AppliableEffect effect : appliedEffects.keySet())
        {
            double remainingTime = appliedEffects.get(effect) - elapsedTime;
            if(remainingTime > 0)
            {
                appliedEffects.put(effect, remainingTime);
            }
            else
            {
                effect.removeModifier(this);
                appliedEffects.remove(effect);
            }

        }
    }

    public void addEffect(AppliableEffect appliableEffect, double duration)
    {
        appliableEffect.applyModifier(this, duration);
        appliedEffects.put(appliableEffect, duration);
    }

    public void removeEffect(AppliableEffect appliableEffect)
    {
        appliedEffects.remove(appliableEffect);
    }

}
