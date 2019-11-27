package game;

import engine.graphics.SpriteTexture;
import engine.input.InputScheme;
import engine.physics.GameWorld;
import game.objects.collectibles.Gem;
import game.objects.Pacman;
import game.objects.Wall;
import game.objects.collectibles.Powerup;
import game.objects.enemies.Crazy;
import game.utils.Level;
import javafx.scene.image.Image;

public class PacmanWorld extends GameWorld
{
    InputScheme usedInputs = new InputScheme();

    public int playerScore = 0;
    public Level level;

    public void setLevel(Level level)
    {
        this.level = level;
        levelToEntities();
    }

    private void levelToEntities()
    {
        int tileWidth = level.tileWidth;
        int tileHeight = level.tileHeight;
        for(int y = 0; y < level.getHeight(); y++)
        {
            for(int x = 0; x < level.getWidth(); x++)
            {
                char currentChar = level.terrain[y][x];

                if(currentChar == '1') // TODO  : Charger une texture différente selon l'emplacement du mur (esthétique, pas prioritaire).
                {
                    Wall newWall = new Wall(x, y, tileWidth, tileHeight);

                    add(newWall);
                }
                else if(currentChar == '0')
                {
                    Gem gem = new Gem(x, y, tileWidth, tileHeight);

                    add(gem);
                }
                else if(currentChar == 'P')
                {
                    Pacman player = new Pacman(x, y, tileWidth, tileHeight);
                    usedInputs = player.getInputScheme();

                    add(player);
                }
                else if(currentChar == 'G')
                {
                    Crazy monster = new Crazy(new SpriteTexture(new Image("monster_scary.png")), x, y, tileWidth, tileHeight);

                    add(monster);
                }
                else if(currentChar == 'u')
                {
                    Powerup powerup = new Powerup(x, y, tileWidth, tileHeight);

                    add(powerup);
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
