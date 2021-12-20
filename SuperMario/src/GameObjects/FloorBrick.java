package GameObjects;

import map.CollisionType;
import java.awt.image.BufferedImage;

public class FloorBrick extends Brick{

    public FloorBrick(double x, double y, BufferedImage image, CollisionType...types) {
        super(x, y, image, types);
        setIs_breakable(false);
        setIs_empty(true);
    }

}