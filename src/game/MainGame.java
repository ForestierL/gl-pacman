package game;

import engine.ui.GameWindow;
import engine.ui.MenuScene;

import java.util.Observer;

public class MainGame extends GameWindow
{
    public MainGame()
    {
        super("Pacman", 500, 500);

        PacmanGameScene gameScene = new PacmanGameScene(gameGroup, this);

        String[] menuObjects = {"Play", "Settings", "Score"};

        MenuScene menuScene = new MenuScene(menuGroup, this);

        setGameScene(gameScene);
        setMenuScene(menuScene, menuObjects);

    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
