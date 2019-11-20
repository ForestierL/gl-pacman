package engine.graphics;

import engine.physics.Orientation;
import javafx.scene.image.Image;

public class MonsterSpriteTexture extends SpriteTexture {

    public enum State{
        angry,
        scary
    };

    public MonsterSpriteTexture(Orientation orientation, State state) {
        super( new Image("resources/monster_" + state +".png"),orientation);
    }

}
