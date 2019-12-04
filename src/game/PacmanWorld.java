package game;

import engine.graphics.MovingSpriteTexture;
import engine.graphics.Orientation;
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
import game.objects.enemies.Monster;
import game.objects.modifiers.*;
import game.utils.Direction;
import game.utils.Level;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PacmanWorld extends GameWorld
{
    public InputScheme usedInputs = new InputScheme();
    public Pacman pacman;
    public ArrayList monsters;
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
                    pacman.resizeEntity(1); //POUR TESTER
                    movers.add(player);
                }
                else if(currentChar == 'G')
                {
                    Chaser monster = new Chaser(new MovingSpriteTexture(new Image("monster_angry.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'C')
                {
                    Crazy monster = new Crazy(new MovingSpriteTexture(new Image("monster_angry.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'B')
                {
                    Blocker monster = new Blocker(new MovingSpriteTexture(new Image("monster_angry.png")), posX, posY, tileWidth, tileHeight, 3);

                    movers.add(monster);
                }
                else if(currentChar == 'u')
                {

                    AppliableEffect powerupEffect = new MiniEffect();

                    Powerup powerup = new Powerup(posX, posY, tileWidth, tileHeight, powerupEffect);

                    collectibles.add(powerup);
                }

                else if(currentChar == 'i')
                {

                    EffectModifier powerupEffect = new InvincibleEffect(new SimpleEffect());

                    Powerup powerup = new Powerup(posX, posY, tileWidth, tileHeight, powerupEffect);

                    collectibles.add(powerup);
                }


            }
        }

        addAll(walls);
        addAll(collectibles);
        addAll(movers);
        this.monsters = movers;
    }

    public int getPlayerScore() {
        return playerScore;
    }



    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
