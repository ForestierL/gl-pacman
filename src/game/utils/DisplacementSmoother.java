package game.utils;

import game.PacmanWorld;
import game.objects.GameObject;
import engine.physics.MovementIntent;

public class DisplacementSmoother {
    private GameObject gameObject;
    private Direction currentDirection;
    private int currentDistanceX, currentDistanceY;
    public DisplacementSmoother(GameObject gameObject) {
        this.gameObject = gameObject;
        this.currentDistanceX = 0;
        this.currentDistanceY = 0;
        this.currentDirection = Direction.NONE;
    }

    public MovementIntent getMovementIntent(Direction direction) {

        PacmanWorld scatmanWorld = (PacmanWorld) gameObject.getWorld();
        int distanceX = 0;
        int distanceY = 0;


        /*/ le radar
        System.out.println(scatmanWorld.level.terrain[(gameObject.getY()-32)/32][(gameObject.getX()-32)/32]+"|"
                          + scatmanWorld.level.terrain[(gameObject.getY()-32)/32][gameObject.getX()/32]+"|"
                          + scatmanWorld.level.terrain[(gameObject.getY()-32)/32][(gameObject.getX()+32)/32]);
        System.out.println(scatmanWorld.level.terrain[gameObject.getY()/32][(gameObject.getX()-32)/32]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()/32][gameObject.getX()/32]+"|"
                + scatmanWorld.level.terrain[gameObject.getY()/32][(gameObject.getX()+32)/32]);
        System.out.println(scatmanWorld.level.terrain[(gameObject.getY()+32)/32][(gameObject.getX()-32)/32]+"|"
                + scatmanWorld.level.terrain[(gameObject.getY()+32)/32][gameObject.getX()/32]+"|"
                + scatmanWorld.level.terrain[(gameObject.getY()+32)/32][(gameObject.getX()+32)/32]);
        //*/

        //[gameObject.getY()/32][(gameObject.getX())/32] (en haut a gauche)
        //[(gameObject.getY()+gameObject.getHeight())/32][(gameObject.getX())/32] (en bas a gauche)
        //[gameObject.getY()/32][(gameObject.getX()+gameObject.getWidth())/32] (en haut a droite)
        //[gameObject.getY()+gameObject.getHeight()/32][(gameObject.getX()+gameObject.getWidth())/32] (en bas a droite)
        switch(direction)
        {
            case X_NEGATIVE:/*
                System.out.println("X_Neg");
                System.out.println("coin haut a gauche:" + scatmanWorld.level.terrain[gameObject.getY()/32]                          [(gameObject.getX()-32)/32]);
                System.out.println("coin bas a gauche:" + scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [(gameObject.getX()-32)/32]);
                System.out.println("coin haut a droite:" + scatmanWorld.level.terrain[gameObject.getY()/32]                          [((gameObject.getX()+gameObject.getWidth())-1-32)/32]);
                System.out.println("coin bas a droite:" + scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [((gameObject.getX()+gameObject.getWidth())-1-32)/32]);
                System.out.println("coin haut a gauche Y:" + gameObject.getY() + " |X;" + gameObject.getX());
                System.out.println("coin bas a gauche Y:" + (gameObject.getY()+gameObject.getHeight()-1) + " |X;" + gameObject.getX() );
                System.out.println("coin haut a droite Y:" + gameObject.getY()+ " |X;" + (gameObject.getX() + gameObject.getWidth()-1));
                System.out.println("coin bas a droite Y:" + (gameObject.getY()+gameObject.getHeight()-1) + " |X;" + (gameObject.getX() + gameObject.getWidth()-1));*/

                if(     scatmanWorld.level.terrain[gameObject.getY()/32]                          [(gameObject.getX()-32)/32] == '1'
                     || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [(gameObject.getX()-32)/32] == '1'
                     || scatmanWorld.level.terrain[gameObject.getY()/32]                          [((gameObject.getX()+gameObject.getWidth()-1)-32)/32] == '1'
                     || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [((gameObject.getX()+gameObject.getWidth()-1)-32)/32] == '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceX = -1;
                    currentDirection = Direction.X_NEGATIVE;
                }
                break;

            case X_POSITIVE:
                if(        scatmanWorld.level.terrain[gameObject.getY()/32]                          [(gameObject.getX()+32)/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [(gameObject.getX()+32)/32] == '1'
                        || scatmanWorld.level.terrain[gameObject.getY()/32]                          [((gameObject.getX()+gameObject.getWidth()-1)+32)/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1)/32] [((gameObject.getX()+gameObject.getWidth()-1)+32)/32] == '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceX = 1;
                    currentDirection = Direction.X_POSITIVE;
                }
                break;

            case Y_NEGATIVE:
                if(        scatmanWorld.level.terrain[(gameObject.getY()-32)/32]                        [gameObject.getX()/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1-32)/32] [gameObject.getX()/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()-32)/32]                        [(gameObject.getX()+gameObject.getWidth()-1)/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1-32)/32] [(gameObject.getX()+gameObject.getWidth()-1)/32] == '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceY = -1;
                    currentDirection = Direction.Y_NEGATIVE;
                }
                break;

            case Y_POSITIVE:
                if(        scatmanWorld.level.terrain[(gameObject.getY()+32)/32]                        [gameObject.getX()/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1+32)/32] [gameObject.getX()/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+32)/32]                        [(gameObject.getX()+gameObject.getWidth()-1)/32] == '1'
                        || scatmanWorld.level.terrain[(gameObject.getY()+gameObject.getHeight()-1+32)/32] [(gameObject.getX()+gameObject.getWidth()-1)/32] == '1'){
                    return new MovementIntent(gameObject.getX(), gameObject.getY(), gameObject.getX() + currentDistanceX, gameObject.getY() + currentDistanceY);
                } else {
                    distanceY = 1;
                    currentDirection = Direction.Y_POSITIVE;
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
