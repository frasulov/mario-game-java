package GameObjects;

import Game.CONSTANTS;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    private double x, y;
    private double width, height;
    private boolean moveWithBackground;
    private double velX, velY;
    private Dimension dimension;
    private BufferedImage image;

    public GameObject(double x, double y, double width, double height, boolean moveWithBackground, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.velX = 0;
        this.velY = 0;
        this.width = width;
        this.height = height;
        this.moveWithBackground = moveWithBackground;
    }

    public GameObject(double x, double y, boolean moveWithBackground, BufferedImage image) {
        this(x,y,CONSTANTS.SquareWidth, CONSTANTS.SquareHeight,moveWithBackground, image);
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, (int) x, (int) y,(int)width,(int)height, null);
        }
    }


    public void updateLocation() {

        if(this instanceof Mario){

            if (((Mario) this).isJumping()) {
                if (getVelY() <= 0){
                    ((Mario) this).setJumping(false);
                    ((Mario) this).setFalling(true);
                }
                velY -= CONSTANTS.Gravity;
                y -= velY;
            }
            if (((Mario) this).isFalling()){
                if (getY() >= 506){
                    ((Mario) this).setFalling(false);
                    setVelY(0);
                }else{
                    y += velY;
                    velY += CONSTANTS.Gravity;
                }
            }


        }


        x += velX;
    }

    public boolean isMoveWithBackground() {
        return moveWithBackground;
    }

    public void setMoveWithBackground(boolean moveWithBackground) {
        this.moveWithBackground = moveWithBackground;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
