//TODO : A DEPLACER, INTEGRATION RAPIDE CAR PROBLEMES DE PUSH
// => travail de THOMAS

package engine.physics;

import engine.graphics.SpriteTexture;
import engine.graphics.Sprite;

public class Player{

    private int lives;
    private boolean power;

    public Player(int posX, int posY, int posZ){

        //polymorphisme ? (Loïc)
        this.lives = 3;
        this.power = false;

    }

    public Player(SpriteTexture spriteTexture, int posX, int posY, int posZ){


        this.lives = 3;
        this.power = false;
    }




}
