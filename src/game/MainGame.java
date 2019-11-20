package game;

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
        level.loadFromJson("level1.json"); // TODO : Chemin relatif ne marche pas !

        ArrayList<GridLayer> layers = level.getGridLayers();

        gridLayers.addAll(layers);
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
