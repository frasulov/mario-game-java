package map;

import GameObjects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {

    private CopyOnWriteArrayList<Brick> bricks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<QuestionBrick> questionBricks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<CustomObject> customObjects = new CopyOnWriteArrayList<>();
    private Mario mario;
    private double lastMapXCordinate;
    private int width, height;
    private CopyOnWriteArrayList<GameObject> enemies = new CopyOnWriteArrayList<>();

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

        for(GameObject enemy: enemies){
            enemy.draw(g);
        }

        mario.draw(g);
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

        for(GameObject enemy: enemies){
            gameObjects.add(enemy);
        }

        gameObjects.add(mario);

        return gameObjects;
    }



    public void updateGameObjects(MapCamera mapCamera){
        for(QuestionBrick brick: questionBricks){
            brick.animate();
        }

        for(GameObject enemy: enemies){
            if(enemy instanceof Goomba){
                enemy.updateLocation(mapCamera);
            }
        }

//        for(GameObject gameObject: getAllObjectsInMap()){
//            gameObject.updateLocation();
//        }

        mario.updateLocation(mapCamera);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public CopyOnWriteArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(CopyOnWriteArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public CopyOnWriteArrayList<QuestionBrick> getQuestionBricks() {
        return questionBricks;
    }

    public void setQuestionBricks(CopyOnWriteArrayList<QuestionBrick> questionBricks) {
        this.questionBricks = questionBricks;
    }

    public CopyOnWriteArrayList<CustomObject> getCustomObjects() {
        return customObjects;
    }

    public void setCustomObjects(CopyOnWriteArrayList<CustomObject> customObjects) {
        this.customObjects = customObjects;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLastMapXCordinate() {
        return lastMapXCordinate;
    }

    public void setLastMapXCordinate(double lastMapXCordinate) {
        this.lastMapXCordinate = lastMapXCordinate;
    }

    public CopyOnWriteArrayList<GameObject> getEnemies() {
        return enemies;
    }

    public void setEnemies(CopyOnWriteArrayList<GameObject> enemies) {
        this.enemies = enemies;
    }
}
