package game;

import engine.graphics.PlayerSpriteTexture;
import engine.graphics.SpriteTexture;
import engine.physics.Input;
import engine.physics.Orientation;
import engine.physics.Player;
import engine.ui.GameWindow;
import engine.ui.GridLayer;
import game.utils.Level;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;

public class MainGame extends GameWindow
{
    public MainGame()
    {
        super("Pacman", 720, 834);
        Level level = new Level();
        level.loadFromJson("resources/level1.json");
        ArrayList<GridLayer> layers = level.getGridLayers();
        gridLayers.addAll(layers);
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
