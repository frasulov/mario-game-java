package map;

import Game.Game;
import GameObjects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Collision {

    private Map map;

    public Collision(Map map){
        this.map = map;
    }


    public void checkBottomCollision(){

        Mario mario = map.getMario();
        List<Brick> floorBricks = map.getBricks().stream().filter(x -> x.getCollisionTypes().contains(CollisionType.TOP)).collect(Collectors.toList());
        for(Brick brick: floorBricks){
            if (mario.getX() + mario.getWidth() >= brick.getX() && mario.getX() < brick.getX() + brick.getWidth() &&
                    mario.getY() + mario.getHeight() > brick.getY() && mario.getY() + mario.getHeight() < brick.getY() + brick.getHeight()){
                mario.setY(brick.getY() - mario.getHeight());
                mario.setVertical(MarioState.IDLE);
                mario.setVelY(0);
            }
        }
    }

    public void checkYCordinateCollision(){
        Mario mario = map.getMario();
        ArrayList<GameObject> objects = map.getAllObjectsInMap();
        for (GameObject object: objects) {
            if (object instanceof Brick && !(object instanceof FloorBrick)) {
                if (mario.getVertical() == MarioState.JUMPING) {
                    if(
                            (mario.getX() > object.getX() && mario.getX() < object.getX()+object.getWidth()) &&
                                    (mario.getY() < object.getY() + object.getHeight() && mario.getY() > object.getY())
                    ){
                        mario.setVelY(0);
                        mario.setHorizontal(MarioState.FALLING);
                        mario.setY(object.getY() + object.getHeight() + 2);
                        System.out.println("y mario"+mario.getY());
                        System.out.println("y object:" + object.getY() + object.getClass());
                    }
                }
            }
        }
    }

    public void checkXCordinateCollsion(){
        Mario mario = map.getMario();
        ArrayList<GameObject> objects = map.getAllObjectsInMap();
        for (GameObject object: objects) {
            if (object instanceof Brick && !(object instanceof FloorBrick)) {
                if (mario.getDirection() == Direction.RIGHT) {
                    if( (mario.getX() + mario.getWidth() > object.getX() && mario.getX()+ mario.getWidth() < object.getX() + object.getWidth())
                            && ((mario.getY() < object.getY() + object.getHeight() && mario.getY() > object.getY()) ||
                            (mario.getY() + mario.getHeight() < object.getY() && mario.getY() + mario.getHeight() > object.getY()))){

                    mario.setVelX(0);
                    mario.setX(object.getX() - mario.getWidth());
                    }
                }else {
                    if ((mario.getX() < object.getX() + object.getWidth() && mario.getX() > object.getX())
                            && ((mario.getY() < object.getY() + object.getHeight() && mario.getY() > object.getY()) ||
                            (mario.getY() + mario.getHeight() < object.getY() && mario.getY() + mario.getHeight() > object.getY()))) {

                        mario.setVelX(0);
                        mario.setX(object.getX() + mario.getWidth());
                    }
                }
            }
        }
    }

}
