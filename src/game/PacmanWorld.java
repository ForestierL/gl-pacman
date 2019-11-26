package game;

import engine.physics.GameWorld;
import game.utils.Level;

public class PacmanWorld extends GameWorld
{
    Level level;


    public void setLevel(Level level)
    {
        this.level = level;
        levelToEntities();
    }

    private void levelToEntities()
    {
        for(int y = 0; y < level.getHeight(); y++)
        {
            for(int x = 0; x < level.getWidth(); x++)
            {
                char currentChar = level.terrain[y][x];

                if(currentChar == '1')
                {
                    Wall newWall = new Wall(x, y);
                    add(newWall);
                }
            }
        }
    }

}
