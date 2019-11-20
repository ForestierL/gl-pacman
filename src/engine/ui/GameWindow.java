package engine.ui;

import engine.graphics.MonsterSpriteTexture;
import engine.graphics.PlayerSpriteTexture;
import engine.physics.Orientation;
import engine.physics.Input;
import engine.physics.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
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
        HBox box = new HBox( 10);

        //Sprite player = new Sprite(new PlayerSpriteTexture(Orientation.NORTH), 100, 100, 100);
        Player player = new Player(new MonsterSpriteTexture(Orientation.NORTH, MonsterSpriteTexture.State.scary), 100, 100, 100); //TODO : TRAVAIL DE THOMAS
        Input input = new Input(scene, player); //TODO : TRAVAIL DE THOMAS

        // Affichage de test pour les textures

        box.getChildren().add(player.getSpriteTexture());
        player.getSpriteTexture().playContinuously();
        box.setAlignment(Pos.CENTER);
        root.getChildren().add(box);


        stage.show();
    }
}
