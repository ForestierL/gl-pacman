package engine.physics;

import java.util.ArrayList;

public class GameWorld
{
    private ArrayList<Entity> entities = new ArrayList<>();

    public void udpate()
    {
        for(Entity entity : entities) { entity.setX(entity.getX()+1); }
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
