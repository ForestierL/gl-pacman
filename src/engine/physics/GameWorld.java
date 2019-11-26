package engine.physics;

import java.util.ArrayList;

public class GameWorld
{
    private ArrayList<Entity> entities = new ArrayList<>();

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
        if(currentIntention != null && isValidMovement(currentIntention))
        {
            entity.validateIntention();
        }
    }

    private boolean isValidMovement(MovementIntention movementIntention)
    {
        for(Entity entity : entities)
        {
            if(entity.getX() == movementIntention.dstX && entity.getY() == movementIntention.dstY)
                return false;
        }
        return true;
    }

    public void add(Entity entity)
    {
        entities.add(entity);
    }

    public void remove(Entity entity)
    {
        entities.remove(entity);
    }

    public void clearEntities()
    {
        entities.clear();
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }
}
