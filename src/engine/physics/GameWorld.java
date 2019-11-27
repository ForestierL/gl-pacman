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
            Hitbox transformedHitbox = entity.getHitbox().transformHitbox(currentIntent);
            for (Entity otherEntity : entities)
            {
                Hitbox otherHitbox = otherEntity.getHitbox();
                if (transformedHitbox.intersects(otherHitbox) && (transformedHitbox != otherHitbox))
                {
                    System.out.println("other entity : " + otherEntity.toString());

                    boolean valid = entity.handleCollision(otherEntity.getCollisionSignal()) && otherEntity.handleCollision(entity.getCollisionSignal());

                    if(valid)
                        entity.validateIntent();
                    else
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
