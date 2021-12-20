package GameObjects;

import Game.CONSTANTS;
import map.MapCamera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Mario extends GameObject{

    private int lives;
    private int coins;
    private int points;
    private MarioState horizontal;
    private MarioState vertical;
    private Animation animation;
    private Direction direction;
    private HashMap<String, BufferedImage> marioImages = new HashMap<>();

    public Mario(int x, int y, int width, int height) {
        super(x, y, width, height, false, null);
        direction = Direction.RIGHT;
        loadMarioImages();
        BufferedImage image = getMarioImage(direction, horizontal, vertical);
        setImage(image);
        lives = 3;
        horizontal = MarioState.IDLE;
        vertical = MarioState.IDLE;
        animation = new Animation(marioImages, direction);
    }

    @Override
    public void draw(Graphics g){
        super.setImage(getMarioImage(direction, horizontal, vertical));
        super.draw(g);
    }

    public BufferedImage getMarioImage(Direction direction, MarioState horizontalState, MarioState verticalState) {


        if (direction == Direction.RIGHT) {
            if(verticalState != MarioState.IDLE){
                return marioImages.get("jump_right");
            }

            if (horizontalState == MarioState.IDLE && verticalState == MarioState.IDLE) {
                return marioImages.get("static_right");
            } else if (horizontalState == MarioState.RUNNING) {
                animation.setDirection(direction);
                return animation.animateMario(20);
            }
        }else{
            if(verticalState != MarioState.IDLE){
                return marioImages.get("jump_left");
            }

            if (horizontalState == MarioState.IDLE && verticalState == MarioState.IDLE) {
                return marioImages.get("static_left");
            } else if (horizontalState == MarioState.RUNNING) {
                animation.setDirection(direction);
                return animation.animateMario(20);
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
            marioImages.put("jump_left", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "jump_left.png")));
            marioImages.put("jump_right", ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/mario", "jump_right.png")));
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
        if (vertical == MarioState.IDLE){
            vertical = MarioState.JUMPING;
            setVelY(10);
        }

    }

    public void move(Direction direction, MapCamera mapCamera) {
        horizontal = MarioState.RUNNING;
        this.direction = direction;
        if (direction == Direction.RIGHT) {
            setVelX(4);
            if (getX() >= CONSTANTS.WIDTH/2) {
                setVelX(0);
                mapCamera.setVelY(0);
                mapCamera.setVelX(-4);
            }
        }else {
            if (getX() > 0) {
                setVelX(-4);
            }else{
                setVelX(0);
                setX(1);
            }
        }

    }

    public MarioState getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(MarioState horizontal) {
        this.horizontal = horizontal;
    }

    public MarioState getVertical() {
        return vertical;
    }

    public void setVertical(MarioState vertical) {
        this.vertical = vertical;
    }
}
