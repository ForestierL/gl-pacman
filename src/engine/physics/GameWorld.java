package engine.physics;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld
{
    private CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();

    public void udpate(long elapsedTime)
    {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        for(Entity entity : entities)
        {
            entity.timeSinceLastUpdate += elapsedSeconds;

            if(entity.timeSinceLastUpdate > 1.0 / entity.getSpeed())
            {
                entity.timeSinceLastUpdate = 0;
                entity.update();
                manageMovementIntents(entity);
            }

        }
    }

    private void manageMovementIntents(Entity entity)
    {
        MovementIntent currentIntent = entity.getMovementIntent();

        if(currentIntent != null)
        {
            for (Entity otherEntity : entities)
            {
                if (otherEntity.getX() == currentIntent.dstX && otherEntity.getY() == currentIntent.dstY)
                {
                    if(!otherEntity.hasCollision())
                    {
                        entity.validateIntent();
                        entity.handleCollision(otherEntity.getCollisionSignal());
                        otherEntity.handleCollision(entity.getCollisionSignal());
                    }
                    return;
                }
            }
            entity.validateIntent();

        }
    }


    public void add(Entity entity)
    {
        entities.add(entity);
        entity.setWorld(this);
    }

    public void remove(Entity entity)
    {
        entities.remove(entity);
    }

    public void clearEntities()
    {
        entities.clear();
    }

    public CopyOnWriteArrayList<Entity> getEntities()
    {
        return entities;
    }
}
