package game;

import engine.graphics.GridLayer;
import engine.graphics.SpriteTexture;
import engine.ui.GameWindow;
import game.objects.Pacman;
import game.utils.Level;
import game.utils.Tileset;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainGame extends GameWindow
{
    public MainGame() throws IOException
    {
        super("Pacman", 500, 500, 32, 32);

        /*

        Level level = new Level();
        level.loadFromJson("resources/level1.json");
        ArrayList<GridLayer> layers = level.getGridLayers();
        gridLayers.addAll(layers);

         */



        Level customLevel = initLevel("resources/levels/customlevel.plv");

        PacmanWorld world = initWorld(customLevel);

        initGraphics(world, 100);

        setGameWorld(world);
    }

    private PacmanWorld initWorld(Level level)
    {
        SpriteTexture pacmanTexture = new SpriteTexture(new Image("player_normal.png"));

        Pacman player = new Pacman(pacmanTexture, 1, 1);
        scene.setOnKeyPressed(player.getInputScheme());

        PacmanWorld world = new PacmanWorld();
        world.setLevel(level);
        world.add(player);

        return world;
    }

    private void initGraphics(PacmanWorld world, int uiMargin)
    {
        getGraphicsDisplay().setWidth(world.level.getWidth() * 32);
        getGraphicsDisplay().setHeight(world.level.getHeight() * 32 + uiMargin);
    }

    private Level initLevel(String levelPath)
    {
        File levelFile = new File(levelPath);

        Level level = new Level();
        level.loadFromPLV(levelFile);

        // level.printTerrain();

        Tileset standardTileset = new Tileset(new Image("mapTileset.png"), 32, 32);
        standardTileset.setTileMapping('0', 48);

        level.setTileset(standardTileset);


        ArrayList<GridLayer> layers = level.getGridLayers();

        addToBackground(layers);

        return level;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
