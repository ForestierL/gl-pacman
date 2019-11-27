package game;

import engine.physics.GameWorld;
import game.objects.GemPoint;
import game.objects.Wall;
import game.utils.Level;

public class PacmanWorld extends GameWorld
{

    public int playerScore = 0;
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

                if(currentChar == '1') // TODO  : Charger une texture différente selon l'emplacement du mur (esthétique, pas prioritaire).
                {
                    Wall newWall = new Wall(x, y);

                    add(newWall);
                }
                else if(currentChar == '0')
                {
                    GemPoint gemPoint = new GemPoint(x, y);

                    add(gemPoint);
                }
            }
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
