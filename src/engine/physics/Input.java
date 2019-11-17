//TODO : A DEPLACER, INTEGRATION RAPIDE CAR PROBLEMES DE PUSH
// => travail de THOMAS
package engine.physics;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static engine.physics.Direction.*;

public class Input {

    private Scene scene;

    private Player player;

    public Input(Scene scene, Player player){

        this.scene = scene;
        this.player = player;

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.DOWN) {
                player.move(Y_POSITIVE,1);
            }
        });


        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.UP) {
                player.move(Y_NEGATIVE,1);
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.RIGHT) {
                player.move(X_POSITIVE,1);
            }
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode()== KeyCode.RIGHT) {
                player.move(X_POSITIVE,1);
            }
        });}
}




