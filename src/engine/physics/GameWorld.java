package engine.physics;

import java.util.ArrayList;
import java.util.Iterator;
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
        MovementIntention currentIntention = entity.getMovementIntention();
        if(currentIntention != null && isValidMovement(entity, currentIntention))
        {
            entity.validateIntention();
        }
    }

    private boolean isValidMovement(Entity movedEntity, MovementIntention movementIntention)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            Entity entity = entities.get(i);
            if(entity.getX() == movementIntention.dstX && entity.getY() == movementIntention.dstY)
            {
                if(entity.hasCollision())
                    return false;
                else
                {
                    entity.handleCollision(movedEntity.getCollisionSignal());
                    movedEntity.handleCollision(entity.getCollisionSignal());
                }
            }
        }
        return true;
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
