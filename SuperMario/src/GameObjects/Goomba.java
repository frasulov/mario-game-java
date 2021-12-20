package GameObjects;

import Game.CONSTANTS;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Goomba extends GameObject implements Enemy{

    private Animation animation;
    private Direction direction = Direction.LEFT;
    private HashMap<String, BufferedImage> enemyImages = new HashMap<>();


    public Goomba(double x, double y, double width, double height, BufferedImage image, Direction direction) {
        this(x,y,width,height,image);
        this.direction = direction;
        if (direction == Direction.RIGHT)
            setVelX(2);
    }

    public Goomba(double x, double y, double width, double height, BufferedImage image) {
        super(x, y, width, height, false, image);
        setVelX(-2);
        animation = new Animation(enemyImages, direction);
    }

    public void loadMarioImages(){
        try {
            enemyImages.put("jump_right", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "jump_right.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        setVelX(-2);
    }
}
