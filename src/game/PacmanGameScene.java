package game;

import engine.graphics.GridLayer;
import engine.ui.GameScene;
import engine.ui.GameWindow;
import game.utils.Level;
import game.utils.Tileset;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class PacmanGameScene extends GameScene
{
    public PacmanGameScene(Parent root, GameWindow gameWindow)
    {
        super(root, gameWindow, 500, 500, 32, 32, 100);
    }

    private PacmanWorld initWorld(Level level)
    {
        PacmanWorld world = new PacmanWorld();
        world.setLevel(level);
        setOnKeyPressed(world.usedInputs);
        return world;
    }

    private Level initLevel(String levelPath)
    {
        File levelFile = new File(levelPath);

        Level level = new Level();
        level.loadFromPLV(levelFile);

        Tileset standardTileset = new Tileset(new Image("mapTileset.png"), 32, 32);
        standardTileset.setTileMapping('0', 48);

        level.setTileset(standardTileset);


        ArrayList<GridLayer> layers = level.getGridLayers();

        addToBackground(layers);

        return level;
    }

    private void initGraphics(PacmanWorld world)
    {
        getGraphicsDisplay().setWidth(world.level.getWidth() * 32);
        getGraphicsDisplay().setHeight(world.level.getHeight() * 32 + this.getUiMargin());
    }

    @Override
    public void createGame()
    {
        Level customLevel = initLevel("resources/levels/customlevel.plv");
        customLevel.tileHeight = 32;
        customLevel.tileWidth = 32;

        PacmanWorld world = initWorld(customLevel);

        initGraphics(world);

        setGameWorld(world);
    }
}
