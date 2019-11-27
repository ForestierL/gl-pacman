package game.utils;

import game.PacmanWorld;
import game.objects.GameObject;
import engine.physics.MovementIntent;

public class DisplacementSmoother {
    private GameObject gameObject;
    private int currentDistanceX, currentDistanceY;
    public DisplacementSmoother(GameObject gameObject) {
        this.gameObject = gameObject;
        this.currentDistanceX = 0;
        this.currentDistanceY = 0;
    }

    public MovementIntent getMovementIntent(Direction direction) {

        PacmanWorld scatmanWorld = (PacmanWorld) gameObject.getWorld();
        int distanceX = 0;
        int distanceY = 0;

        /*/ le radar
        System.out.println(scatmanWorld.level.terrain[gameObject.getY()-1][gameObject.getX()-1]+"|"
                          + scatmanWorld.level.terrain[gameObject.getY()-1][gameObject.getX()]+"|"
                          + scatmanWorld.level.terrain[gameObject.getY()-1][gameObject.getX()+1]);
        System.out.println(scatmanWorld.level.terrain[gameObject.getY()][gameObject.getX()-1]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()][gameObject.getX()]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()][gameObject.getX()+1]);
        System.out.println(scatmanWorld.level.terrain[gameObject.getY()+1][gameObject.getX()-1]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()+1][gameObject.getX()]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()+1][gameObject.getX()+1]);
        //*/
        switch(direction)
        {
            case X_NEGATIVE:
                System.out.println("X_NEGTIVE");
                if(scatmanWorld.level.terrain[gameObject.getY()][gameObject.getX()-1] == '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceX = -1;
                }
                break;

            case X_POSITIVE:
                System.out.println("X_POSITIVE");
                if(scatmanWorld.level.terrain[gameObject.getY()][gameObject.getX()+1]== '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceX = 1;
                }
                break;

            case Y_NEGATIVE:
                System.out.println("Y_NEGTIVE");
                if(scatmanWorld.level.terrain[gameObject.getY()-1][gameObject.getX()]== '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceY = -1;
                }
                break;

            case Y_POSITIVE:
                System.out.println("Y_POSITIVE");
                if(scatmanWorld.level.terrain[gameObject.getY()+1][gameObject.getX()]== '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceY = 1;
                }
                break;

            default:
                break;
        }
        currentDistanceX = distanceX;
        currentDistanceY = distanceY;
        return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + distanceX, gameObject.getY() + distanceY);
    }
}
