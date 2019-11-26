package game;

import engine.graphics.PlayerSpriteTexture;
import engine.graphics.SpriteTexture;
import engine.physics.Input;
import engine.physics.Orientation;
import engine.physics.Player;
import engine.ui.GameWindow;
import engine.graphics.GridLayer;
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
        super("Pacman", 600, 500);

        /*

        Level level = new Level();
        level.loadFromJson("resources/level1.json");
        ArrayList<GridLayer> layers = level.getGridLayers();
        gridLayers.addAll(layers);

         */

        File levelFile = new File("resources/levels/customlevel.plv");

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
        level.addTerrainTilesIdentifiers('0', '1');
        level.addEntityTilesIdentifiers('G', 'P');
        level.setEmptyTileIdentifier('e');

        ArrayList<GridLayer> layers = level.getGridLayers();

        for(GridLayer layer : layers) layer.resizeImageGrid(32, 32);

        backgroundDisplay.addAll(layers);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
