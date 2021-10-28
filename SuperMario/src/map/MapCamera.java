package map;

import Game.CONSTANTS;
import Game.GUI;
import GameObjects.GameObject;

public class MapCamera {

    private double x, y;
    private GUI gui;

    public MapCamera(GUI gui){
        this.gui = gui;
        this.x = 0;
        this.y = 0;
    }


    public void moveCamera(double xAmount, double yAmount){
        Map map = gui.getMap();
        x += xAmount;
        y += yAmount;
        for(GameObject object: map.getAllObjectsInMap()){
            System.out.println("x: "+x);
            if (x <= map.getWidth() - CONSTANTS.WIDTH) {
                if (object.isMoveWithBackground()) {
                    object.setLocation(object.getX() - xAmount, object.getY() + yAmount);
                    gui.repaint();
                }
            }
        }
    }

}