package engine.graphics;

import engine.physics.Orientation;
import javafx.scene.image.Image;

import java.io.File;

public class PlayerSpriteTexture extends SpriteTexture {

    public enum State{
        normal, //normal => servira au retour à la normale après absorption du pouvoir
        powered
    }

    public PlayerSpriteTexture(Orientation orientation, State state) {
        super(new Image(new File("engine/graphics/resources/player_"+ state +".png").toURI().toString()), orientation);

    } // constructor with state (e.g: powered player)

    public PlayerSpriteTexture(Orientation orientation) {
        super( new Image(new File("engine/graphics/resources/player_normal.png").toURI().toString()),orientation);
    } // simple constructor
}