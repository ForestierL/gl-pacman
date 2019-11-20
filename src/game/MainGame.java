package game;

import engine.ui.GameWindow;

public class MainGame extends GameWindow
{
    public MainGame()
    {
        super("Pacman", 1280, 720);
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
