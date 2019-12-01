package game;

import engine.ui.MenuScene;
import engine.ui.TestWindow;

public class MainGame extends TestWindow
{
    public MainGame()
    {
        super("Pacman", 500, 500);

        PacmanGameScene gameScene = new PacmanGameScene(gameGroup, this);

        String[] menuObjects = {"Play", "Settings"};

        MenuScene menuScene = new MenuScene(menuGroup, this);

        setGameScene(gameScene);
        setMenuScene(menuScene, menuObjects);

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
