package game;

import engine.audios.SoundManager;
import engine.graphics.MovingSpriteTexture;
import engine.input.InputScheme;
import engine.physics.Entity;
import engine.physics.GameWorld;
import game.objects.collectibles.Gem;
import game.objects.Pacman;
import game.objects.Wall;
import game.objects.collectibles.LargeGem;
import game.objects.collectibles.Powerup;
import game.objects.enemies.Blocker;
import game.objects.enemies.Chaser;
import game.objects.enemies.Crazy;
import game.objects.modifiers.*;
import game.utils.Level;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PacmanWorld extends GameWorld
{
    InputScheme usedInputs = new InputScheme();
    public Pacman pacman;
    public LargeGem largeGem;
    private ArrayList monsters;
    private int playerScore = 0;
    private int gemCount = 0;
    public boolean largeGemSpawned = false;
    public Level level;
    private Observer observer;


    public void setLevel(Level level)
    {
        this.level = level;
        levelToEntities();
    }

    private void levelToEntities()
    {
        SoundManager soundManager = new SoundManager(new File("resources/audio/sounds/get_gem1.mp3").toURI().toString(),1.0);

        int tileWidth = level.tileWidth;
        int tileHeight = level.tileHeight;

        AppliableEffect miniEffect = new MiniEffect(new TimedEffect());
        AppliableEffect invincibleEffect = new InvincibleEffect(new TimedEffect());

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
                    Gem gem = new Gem(posX, posY, tileWidth, tileHeight, soundManager);
                    gemCount++;
                    collectibles.add(gem);
                }
                else if(currentChar == 'P')
                {
                    Pacman player = new Pacman(posX, posY, tileWidth, tileHeight, 3);
                    usedInputs = player.getInputScheme();
                    this.pacman = player;
                    pacman.resizeEntity(1); //POUR TESTER
                    movers.add(player);

                    largeGem = new LargeGem(posX, posY, 32, 32);

                    collectibles.add(largeGem);
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
                    Powerup powerup = new Powerup(posX, posY, tileWidth, tileHeight, miniEffect);

                    collectibles.add(powerup);
                }

                else if(currentChar == 'i')
                {
                    Powerup powerup = new Powerup(posX, posY, tileWidth, tileHeight, invincibleEffect);

                    collectibles.add(powerup);
                }
            }
        }

        addAll(walls);
        addAll(collectibles);
        addAll(movers);
        this.monsters = movers;
    }

    public void decreaseGemCount(int amount)
    {
        gemCount -= amount;
        if(gemCount == 0)
            System.out.println("Go to next level");
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore)
    {
        this.playerScore = playerScore;
        if(playerScore > 1000 && !largeGemSpawned)
        {
            largeGemSpawned = true;
            largeGem.setVisible(true);
        }
        notifyObservers(this);
    }

    public void addObserver(Observer observer) {
        this.observer = observer;
    }
    public void notifyObservers(){
        this.observer.update(this, this.getPlayerScore());
    }


}
