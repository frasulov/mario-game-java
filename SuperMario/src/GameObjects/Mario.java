package GameObjects;

import Game.CONSTANTS;
import map.MapCamera;

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
    private boolean jumping;
    private boolean falling;
    private boolean running;
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
        super.setImage(getMarioImage(direction, running, getVelY() != 0));
        super.draw(g);
    }

    public BufferedImage getMarioImage(Direction direction, boolean isMovingInXOrbit, boolean isMovingInYOrbit) {
        if (direction == Direction.RIGHT) {
            if (!isMovingInXOrbit && !isMovingInYOrbit) {
                return marioImages.get("static_right");
            } else if (isMovingInXOrbit) {
                animation.setDirection(direction);
                return animation.animateMario(20);
            }else if (isMovingInYOrbit){
                return marioImages.get("static_right");
            }
        }else{
            if (!isMovingInXOrbit && !isMovingInYOrbit) {
                return marioImages.get("static_left");
            } else if (isMovingInXOrbit) {
                animation.setDirection(direction);
                return animation.animateMario(20);
            }else if(isMovingInYOrbit){
                return marioImages.get("static_left");
            }
        }
            return marioImages.get("static_right");
    }


    public void loadMarioImages(){
        try {
            marioImages.put("static_right", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "mario_right_static.png")));
            marioImages.put("static_left", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "static_left.png")));
            marioImages.put("right_move_1", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_1.png")));
            marioImages.put("right_move_2", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_2.png")));
            marioImages.put("right_move_3", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "right_move_3.png")));
            marioImages.put("left_move_1", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "left_move_1.png")));
            marioImages.put("left_move_2", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "left_move_2.png")));
            marioImages.put("left_move_3", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "left_move_3.png")));
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

    public void jump() {
        if (!jumping && !falling) {
            jumping = true;
            setVelY(10);
        }
    }

    public void move(Direction direction, MapCamera mapCamera) {
        running = true;
        this.direction = direction;
        if (direction == Direction.RIGHT) {
            System.out.println("go to right --------------");
            setVelX(4);
            if (getX() >= CONSTANTS.WIDTH/2) {
                setVelX(0);
                mapCamera.moveCamera(5, 0);
            }
        }else {
            if (getX() > 0) {
                setVelX(-4);
            }
        }

    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
