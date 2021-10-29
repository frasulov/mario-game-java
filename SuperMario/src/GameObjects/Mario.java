package GameObjects;

import Game.CONSTANTS;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mario extends GameObject{

    private int lives;
    private int coins;
    private int points;
    private Animation animation;
    private Direction direction;
    private HashMap<String, BufferedImage> marioImages = new HashMap<>();

    public Mario(int x, int y, int width, int height) {
        super(x, y, width, height, false, null);
        direction = Direction.RIGHT;
        loadMarioImages();
        BufferedImage image = getMarioImage(direction, false, false);
        setImage(image);
        lives = 3;

        animation = new Animation(marioImages, direction);
    }

    @Override
    public void draw(Graphics g){
        super.setImage(getMarioImage(direction, getVelX() != 0, getVelY() != 0));
        super.draw(g);
    }

    public BufferedImage getMarioImage(Direction direction, boolean isMovingInXOrbit, boolean isMovingInYOrbit) {
        if (direction == Direction.RIGHT) {
            if (!isMovingInXOrbit && !isMovingInYOrbit) {
                return marioImages.get("static");
            } else if (isMovingInXOrbit) {
                animation.setDirection(direction);
                return animation.animateMario(20);
            }
        }
            return marioImages.get("static");
    }


    public void loadMarioImages(){

        try {
            marioImages.put("static", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "mario_right_static.png")));
            marioImages.put("right_move_1", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_1.png")));
            marioImages.put("right_move_2", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_2.png")));
            marioImages.put("right_move_3", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
