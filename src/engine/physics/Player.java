//TODO : A DEPLACER, INTEGRATION RAPIDE CAR PROBLEMES DE PUSH
// => travail de THOMAS

package engine.physics;

import engine.graphics.SpriteTexture;
import engine.physics.Sprite;

public class Player extends Sprite {

    private int lives;
    private boolean power;

    public Player(int posX, int posY, int posZ){

        //polymorphisme ? (Loïc)
        /*super(posX, posY, posZ);
        this.lives = 3;
        this.power = false;*/

        this(null, posX, posY, posZ);
    }
    public Player(SpriteTexture spriteTexture, int posX, int posY, int posZ){

        super(spriteTexture, posX, posY, posZ);

        this.lives = 3;
        this.power = false;
    }


}
