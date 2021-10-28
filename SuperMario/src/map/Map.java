package map;

import GameObjects.*;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<QuestionBrick> questionBricks = new ArrayList<>();
    private ArrayList<CustomObject> customObjects = new ArrayList<>();
    private int width, height;

    public Map(){
    }


    public void setDimensions(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void addBrick(Brick brick){
        bricks.add(brick);
    }

    public void addQuestionBrick(Brick brick){
        questionBricks.add((QuestionBrick) brick);
    }

    public void addCustomObject(CustomObject customObject) {
        customObjects.add(customObject);
    }

    public void draw(Graphics2D g) {

        for(Brick brick: bricks){
            brick.draw(g);
        }

        for(QuestionBrick brick: questionBricks){
            brick.draw(g);
        }

        for(CustomObject customObject: customObjects){
            customObject.draw(g);
        }
    }

    public ArrayList<GameObject> getAllObjectsInMap(){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for(Brick brick: bricks){
            gameObjects.add(brick);
        }
        for(QuestionBrick brick: questionBricks){
            gameObjects.add(brick);
        }

        for(CustomObject customObject: customObjects){
            gameObjects.add(customObject);
        }
        return gameObjects;
    }



    public void updateGameObjects(){
        for(QuestionBrick brick: questionBricks){
            brick.animate();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
