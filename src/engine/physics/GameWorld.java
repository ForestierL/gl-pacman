package engine.physics;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld
{
    private CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();

    public void udpate()
    {

        for(Entity entity : entities)
        {
            manageMovementIntentions(entity);
        }
    }

    private void manageMovementIntentions(Entity entity)
    {
        MovementIntent currentIntention = entity.getMovementIntent();

        if(currentIntention != null)
        {
            for (Entity otherEntity : entities)
            {
                if (otherEntity.getX() == currentIntention.dstX && otherEntity.getY() == currentIntention.dstY)
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
