package GameObjects;

import map.CollisionType;

import java.awt.image.BufferedImage;

public class CustomObject extends GameObject{


    public CustomObject(double x, double y, double width, double height, boolean moveWithBackground, BufferedImage image, CollisionType ...types) {
        super(x, y, width, height, moveWithBackground, image, types);
    }

}
