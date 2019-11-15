package engine.ui;

import engine.graphics.PlayerSpriteTexture;
import engine.physics.Orientation;
import engine.physics.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        HBox box = new HBox();

        Sprite player = new Sprite(new PlayerSpriteTexture(Orientation.NORTH), 50, 50, 50);
        box.getChildren().add(player.getSpriteTexture().getImageView());
        root.getChildren().add(box);
        stage.show();
    }
}
