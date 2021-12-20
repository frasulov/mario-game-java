package GameObjects;

import Game.CONSTANTS;
import map.CollisionType;
import map.MapCamera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;

public class GameObject {

    private double x, y;
    private double width, height;
    private boolean moveWithBackground;
    private double velX, velY;
    private Dimension dimension;
    private BufferedImage image;
    private HashSet<CollisionType> collisionTypes;

    public GameObject(double x, double y, double width, double height, boolean moveWithBackground, BufferedImage image, CollisionType ...args) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.velX = 0;
        this.velY = 0;
        this.width = width;
        this.height = height;
        this.moveWithBackground = moveWithBackground;
        this.collisionTypes = new HashSet<>(Arrays.asList(args));
        dimension = new Dimension((int)width, (int)height);
    }

    public GameObject(double x, double y, boolean moveWithBackground, BufferedImage image, CollisionType ...args) {
        this(x, y, CONSTANTS.SquareWidth, CONSTANTS.SquareHeight, moveWithBackground, image, args);
    }


    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, (int) x, (int) y,(int)width,(int)height, null);
        }
    }


    public void updateLocation(MapCamera mapCamera) {

        if(this instanceof Mario){
            if (((Mario) this).getVertical() == MarioState.JUMPING) {
                if (((Mario) this).getHorizontal() == MarioState.RUNNING) {
                    if (((Mario) this).getDirection() == Direction.RIGHT && x >= CONSTANTS.WIDTH/2) {
                        this.setVelX(0);
                        mapCamera.setVelX(-4);
                    }
                }
                if (getVelY() <= 0){
                    ((Mario) this).setVertical(MarioState.FALLING);
                }
                velY -= CONSTANTS.Gravity;
                y -= velY;
            }
            if (((Mario) this).getVertical() == MarioState.FALLING){
                    y += velY;
                    velY += CONSTANTS.Gravity;
            }

        }

        if (this instanceof Goomba){
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

    public HashSet<CollisionType> getCollisionTypes() {
        return collisionTypes;
    }

    public void setCollisionTypes(HashSet<CollisionType> collisionTypes) {
        this.collisionTypes = collisionTypes;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getTopBounds(){
        return new Rectangle((int)x+dimension.width/6, (int)y, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+dimension.width/6, (int)y + dimension.height/2, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getLeftBounds(){
        return new Rectangle((int)x, (int)y + dimension.height, dimension.width, dimension.height);
    }

    public Rectangle getRightBounds(){
        return new Rectangle((int)x + dimension.width, (int)y + dimension.height, dimension.width, dimension.height);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, dimension.width, dimension.height);
    }
}
