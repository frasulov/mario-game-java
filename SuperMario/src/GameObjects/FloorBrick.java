package GameObjects;

import java.awt.image.BufferedImage;

public class FloorBrick extends Brick{

    public FloorBrick(double x, double y, BufferedImage image) {
        super(x, y, image);
        setIs_breakable(false);
        setIs_empty(true);
    }

}
