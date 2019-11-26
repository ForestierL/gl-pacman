package game;

import engine.graphics.GridLayer;
import engine.graphics.SpriteTexture;
import engine.physics.Orientation;
import engine.ui.GameWindow;
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
        super("Pacman", 600, 500, 32, 32);

        /*

        Level level = new Level();
        level.loadFromJson("resources/level1.json");
        ArrayList<GridLayer> layers = level.getGridLayers();
        gridLayers.addAll(layers);

         */

        Level customLevel = initLevel("resources/levels/customlevel.plv");

        SpriteTexture pacmanTexture = new SpriteTexture(new Image("player_normal.png"), Orientation.NONE);

        Pacman player = new Pacman(pacmanTexture, 1, 1);
        scene.setOnKeyPressed(player.getInputScheme());

        PacmanWorld world = new PacmanWorld();
        world.setLevel(customLevel);
        world.add(player);

        setGameWorld(world);
    }

    private Level initLevel(String levelPath)
    {
        File levelFile = new File(levelPath);

        Level level = new Level();
        level.loadFromPLV(levelFile);

        // level.printTerrain();

        Tileset standardTileset = new Tileset(new Image("mapTileset.png"), 32, 32);
        standardTileset.setTileMapping('0', 48);
        standardTileset.setTileMapping('1', 7);
        standardTileset.setTileMapping('G', 58);
        standardTileset.setTileMapping('P', 56);
        standardTileset.setTileMapping('e', 45);

        level.setTileset(standardTileset);


        ArrayList<GridLayer> layers = level.getGridLayers();

        backgroundDisplay.addAll(layers);

        return level;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
