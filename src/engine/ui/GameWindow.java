package engine.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameWindow extends Application
{
    private String name;
    private int width;
    private int height;

    public GameWindow(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle(name);

        StackPane root = new StackPane();
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
}
