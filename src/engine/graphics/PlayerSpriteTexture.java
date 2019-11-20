package engine.graphics;

import engine.physics.Orientation;
import javafx.scene.image.Image;

public class PlayerSpriteTexture extends SpriteTexture {

    public enum State{
        normal, //normal => servira au retour à la normale après absorption du pouvoir
        powered
    }

    public PlayerSpriteTexture(Orientation orientation, State state) {
        super(new Image("engine/graphics/resources/player_"+ state +".png"), orientation);

    } // constructor with state (e.g: powered player)

    public PlayerSpriteTexture(Orientation orientation) {
        super( new Image("engine/graphics/resources/player_normal.png"),orientation);
    } // simple constructor
}