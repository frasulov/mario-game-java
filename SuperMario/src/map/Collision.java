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

//        List<GameObject> objects = map.getBricks().stream().filter(x -> !(x instanceof FloorBrick) && x.getCollisionTypes().contains(CollisionType.TOP)).collect(Collectors.toList());
//        for(GameObject object: objects) {
//            if (mario.isFalling() && mario.getX() + mario.getWidth() >= object.getX() &&
//                    mario.getX() <= object.getX() + object.getWidth() &&
//                    mario.getY() + mario.getHeight() >= object.getY()) {
//                mario.setVelY(0);
//                mario.setFalling(false);
//                mario.setY(object.getY() - mario.getHeight());
//            }
//        }

    }

    public void updateJumpState(){
        Mario mario = map.getMario();

        if (mario.getHorizontal() == MarioState.IDLE)
            return;

        List<GameObject> objects = map.getBricks().stream().filter(x -> x.getCollisionTypes().contains(CollisionType.TOP)).collect(Collectors.toList());
        int count = 0;

//        if (mario.getVertical() != MarioState.JUMPING){
//            mario.setVertical(MarioState.FALLING);
//        }

//        for(GameObject object: objects){
////
//            if (mario.getX() + mario.getWidth() > object.getX() && mario.getX() < object.getX() + object.getWidth()){
//                count ++;
//            }
//
//
//            if (marioLocation >= object.getY() && marioLocation <= object.getY() + object.getHeight()){
//                count ++;
//            }
//        }
//        if (count != 0){
//            mario.setVelY(0);
//            mario.setFalling(true);
//        }
    }

    public void checkTopCollision(){
        Mario mario = map.getMario();
        List<GameObject> objects = map.getBricks().stream().filter(x -> x.getCollisionTypes().contains(CollisionType.BOTTOM)).collect(Collectors.toList());
//        for(GameObject object: objects) {
//            if (mario.isJumping() && mario.getRectangle().intersects(object.getRectangle())) {
//                mario.setVelY(0);
//                mario.setJumping(false);
//                mario.setFalling(true);
//                mario.setY(object.getY() + object.getHeight());
//            }
//
//
//        }
    }

    public void checkLeftCollision(){

        Mario mario = map.getMario();
        List<GameObject> objects = map.getBricks().stream().filter(x -> x.getCollisionTypes().contains(CollisionType.LEFT)).collect(Collectors.toList());
        for (GameObject object : objects) {
            if (mario.getDirection() == Direction.RIGHT &&
                mario.getRightBounds().intersects(object.getLeftBounds())) {
                mario.setVelX(0);
                mario.setX(object.getX() - mario.getDimension().width);
            }
        }


//        for(GameObject object: objects){
//            if (mario.getDirection() == Direction.RIGHT && mario.getRectangle().intersects(object.getRectangle())){
//                mario.setVelX(0);
////                mario.setX(object.getX() - mario.getWidth());
//                mario.setX(mario.getX() - 1);
//            }


//            if(mario.getDirection() == Direction.RIGHT &&
//               mario.getX() + mario.getWidth() > object.getX() &&
//               mario.getX() < object.getX() + object.getWidth() &&
//               object.getY() <= mario.getY() + mario.getHeight() &&
//               object.getY() + object.getHeight() >= mario.getY()){
//                    mario.setVelX(0);
//                    mario.setX(object.getX() - mario.getWidth());
//            }
//        }
    }

    public void checkRightCollision(){


//        Mario mario = map.getMario();
//        List<GameObject> objects = map.getBricks().stream().filter(x -> x.getCollisionTypes().contains(CollisionType.RIGHT)).collect(Collectors.toList());
//        for(GameObject object: objects) {
//            if (mario.getDirection() == Direction.LEFT && !mario.isJumping() && mario.getRectangle().intersects(object.getRectangle())) {
//                mario.setVelX(0);
//                mario.setX(object.getX() + object.getWidth());
//            }
//        }


        //        for(GameObject object: objects){
//            if(mario.getDirection() == Direction.LEFT &&
//                    mario.getX() < object.getX() + object.getWidth() &&
//                    mario.getX() > object.getX() &&
//                    object.getY() <= mario.getY() + mario.getHeight() &&
//                    object.getY() + object.getHeight() >= mario.getY()){
//                mario.setVelX(0);
//                mario.setX(object.getX() + object.getWidth());
//            }
//        }

    }

}
