package GameObjects;

import Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Brick extends GameObject {

    private boolean is_breakable;
    private boolean is_empty;


    public Brick(double x, double y, BufferedImage image) {
        super(x, y, true, image);
        setDimension(new Dimension(20,20));
    }

    public boolean isIs_breakable() {
        return is_breakable;
    }

    public void setIs_breakable(boolean is_breakable) {
        this.is_breakable = is_breakable;
    }

    public boolean isIs_empty() {
        return is_empty;
    }

    public void setIs_empty(boolean is_empty) {
        this.is_empty = is_empty;
    }
}
