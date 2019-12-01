package game;

import engine.graphics.SpriteTexture;
import engine.input.InputScheme;
import engine.physics.Entity;
import engine.physics.GameWorld;
import game.objects.GameObject;
import game.objects.collectibles.Gem;
import game.objects.Pacman;
import game.objects.Wall;
import game.objects.collectibles.Powerup;
import game.objects.enemies.Blocker;
import game.objects.enemies.Chaser;
import game.objects.enemies.Crazy;
import game.utils.Level;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PacmanWorld extends GameWorld
{
    public InputScheme usedInputs = new InputScheme();
    public Pacman pacman;
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

        ArrayList<Entity> walls = new ArrayList<>();
        ArrayList<Entity> collectibles = new ArrayList<>();
        ArrayList<Entity> movers = new ArrayList<>();

        for(int y = 0; y < level.getHeight(); y++)
        {
            for(int x = 0; x < level.getWidth(); x++)
            {
                char currentChar = level.terrain[y][x];

                int posX = x * tileWidth;
                int posY = y * tileHeight;

                if(currentChar == '1') // TODO  : Charger une texture différente selon l'emplacement du mur (esthétique, pas prioritaire).
                {
                    Wall newWall = new Wall(posX, posY, tileWidth, tileHeight);

                    walls.add(newWall);
                }
                else if(currentChar == '0')
                {
                    Gem gem = new Gem(posX, posY, tileWidth, tileHeight);

                    collectibles.add(gem);
                }
                else if(currentChar == 'P')
                {
                    Pacman player = new Pacman(posX, posY, tileWidth, tileHeight);
                    usedInputs = player.getInputScheme();
                    this.pacman = player;
                    movers.add(player);
                }
                else if(currentChar == 'G')
                {
                    Chaser monster = new Chaser(new SpriteTexture(new Image("monster_scary.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'C')
                {
                    Crazy monster = new Crazy(new SpriteTexture(new Image("monster_scary.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'B')
                {
                    Blocker monster = new Blocker(new SpriteTexture(new Image("monster_scary.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'u')
                {
                    Powerup powerup = new Powerup(posX, posY, tileWidth, tileHeight);

                    collectibles.add(powerup);
                }
            }
        }

        addAll(walls);
        addAll(collectibles);
        addAll(movers);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
