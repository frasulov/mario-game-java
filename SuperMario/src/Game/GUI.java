package Game;

import GameObjects.Brick;
import GameObjects.CustomObject;
import GameObjects.GameObject;
import GameObjects.QuestionBrick;
import map.Collision;
import map.Map;
import map.MapMaker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI extends JPanel {

    private Map map;
    private MapMaker mapMaker;
    private Game game;
    private Collision collision;

    public GUI(Game game, int width, int height) {
        this.game = game;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        mapMaker = new MapMaker();
        map = mapMaker.getMap();
        setBackground(Color.decode("#6185f8"));
        collision = new Collision(map);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        GameStatus gameStatus = game.getGameStatus();

        if (gameStatus == GameStatus.PLAY) {
                map.draw(g2);
        }

        drawGameInformation(g2);

    }

    public void drawGameInformation(Graphics2D g) {
        // get game font
        Font font = new Font("Verdana", Font.PLAIN, 12);
        // draw time
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(25f));
        g.drawString("TIME", CONSTANTS.WIDTH-300, 50);
        g.drawString(String.format("%5d", 400), CONSTANTS.WIDTH-300, 75);

        g.drawString("WORLD", CONSTANTS.WIDTH-500, 50);
        g.drawString("   1-1 ", CONSTANTS.WIDTH-500, 75);

        g.drawString("MARIO", 100, 50);
        g.drawString(String.format("%06d", 1700), 100, 75);


        g.drawString(String.format("x %2d", 3), 450, 75);

    }

    public void objectRemover(){
        CopyOnWriteArrayList<Brick> bricks = map.getBricks();
        for(Brick brick: bricks){
            if (brick.getX() < -1*brick.getWidth()){
                map.getBricks().remove(brick);
            }
        }
        CopyOnWriteArrayList<QuestionBrick> questionBricks = map.getQuestionBricks();
        for(Brick brick: questionBricks){
            if (brick.getX() < -1*brick.getWidth()){
                map.getQuestionBricks().remove(brick);
            }
        }

        CopyOnWriteArrayList<CustomObject> customObjects = map.getCustomObjects();
        for(GameObject object: customObjects){
            if (object.getX() < -1*object.getWidth()){
                map.getCustomObjects().remove(object);
            }
        }
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public MapMaker getMapMaker() {
        return mapMaker;
    }

    public void checkCollisions(){
        collision.checkBottomCollision();
        collision.updateJumpState();
//        collision.checkTopCollision();
//        collision.checkLeftCollision();
//        collision.checkRightCollision();
    }
}
