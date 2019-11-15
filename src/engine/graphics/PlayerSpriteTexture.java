package engine.graphics;

import engine.physics.Orientation;
import javafx.scene.image.Image;

public class PlayerSpriteTexture extends SpriteTexture {

    public PlayerSpriteTexture(Orientation orientation) {
        super( new Image("engine/graphics/resources/player.png"),orientation);
    }


}
