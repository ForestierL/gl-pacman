
package engine.physics;

import engine.graphics.SpriteTexture;
import engine.physics.Sprite;

public class Monster extends Sprite {

    private boolean isFleeing;


    public Monster(SpriteTexture spriteTexture, int posX, int posY, int posZ){

        super(spriteTexture, posX, posY, posZ);

        this.isFleeing = false;
    }


}
