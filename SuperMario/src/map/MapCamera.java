package map;

import Game.CONSTANTS;
import Game.GUI;
import GameObjects.GameObject;
import GameObjects.Mario;

public class MapCamera {

    private double x, y;
    private double velX, velY;
    private GUI gui;

    public MapCamera(GUI gui){
        this.gui = gui;
        this.x = 0;
        this.y = 0;
    }


    public void updateCameraLocation(){
        Map map = gui.getMap();
        MapMaker mapMaker = gui.getMapMaker();
        x += velX;
        y += velY;

//        if (-1*x >= CONSTANTS.SquareWidth){
//            x = 0;
//            mapMaker.loadMap(1);
//        }

//        if (map.getLastMapXCordinate() - CONSTANTS.WIDTH + 3*CONSTANTS.SquareWidth < -1*x){
//            mapMaker.loadMap(3);
//        }

//        mapMaker.loadMap(1);
        for(GameObject object: map.getAllObjectsInMap()){
//            if (x <= map.getWidth() - CONSTANTS.WIDTH) {
                if (object.isMoveWithBackground()) {
                    object.setLocation(object.getX()+velX, object.getY()+velY);
                }
//            }
        }
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        if (velX != 0) {
            for (GameObject enemy : gui.getMap().getEnemies()) {
                enemy.setVelX(-2 + velX);
            }
        }else{
            for (GameObject enemy : gui.getMap().getEnemies()) {
                enemy.setVelX(-2);
            }
        }
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
