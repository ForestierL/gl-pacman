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
            System.out.println(key.getCode());
            switch (key.getCode()) {

                case DOWN: //KeyCode.DOWN
                    player.setOrientation(Orientation.SOUTH);
                    break;
                case UP:
                    player.setOrientation(Orientation.NORTH);
                    break;
                case LEFT:
                    player.setOrientation(Orientation.WEST);
                    break;
                case RIGHT:
                    player.setOrientation(Orientation.EAST);
                    break;
            }
        });
    }
}




