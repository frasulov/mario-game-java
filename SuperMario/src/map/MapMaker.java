package map;

import Game.CONSTANTS;
import GameObjects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapMaker {

    BufferedImage floor_image;
    HashMap<Character, BufferedImage> images;
    ArrayList<String> mapLines;
    Map map;
    int xCordinate;
    int yCordinate;


    public MapMaker(){
        images = new HashMap<>();
        fillHashMapWithImage();
        map = new Map();
        mapLines = new ArrayList<>();
        readMapFromFile();
        yCordinate = CONSTANTS.SquareHeight * mapLines.size();
        loadMap(210);
    }

    public void loadMap(int mapLength){

//        if (xCordinate >= mapLines.get(0).length()){
//            return false;
//        }
        int yAxis = 0;
        for(String line: mapLines){
            for (int i=0; i<mapLength; i++){
                if (xCordinate + i >= line.length())
                    break;
                char ch = line.charAt(xCordinate + i);
                int xAxis = (xCordinate + i)*CONSTANTS.SquareWidth;
                if (ch == '='){
                    Brick brick = new FloorBrick(xAxis, yAxis, images.get('='), CollisionType.TOP);
                    map.addBrick(brick);
                }else if(ch == '%'){
                    Brick brick = new FloorBrick(xAxis, yAxis, images.get('='));
                    map.addBrick(brick);
                } else if (ch == '+'){
                    Brick brick = new NormalBrick(xAxis, yAxis, images.get('+'), CollisionType.LEFT, CollisionType.TOP, CollisionType.RIGHT, CollisionType.BOTTOM);
                    map.addBrick(brick);
                }else if (ch == '?'){
                    Brick brick = new QuestionBrick(xAxis, yAxis, images.get('?'));
                    map.addQuestionBrick(brick);
                }else if (ch == '('){
                    map.addBrick(new NormalBrick(xAxis, yAxis, images.get(ch), CollisionType.LEFT));
                }else if (ch == ')'){
                    map.addBrick(new NormalBrick(xAxis, yAxis, images.get(ch), CollisionType.RIGHT));
                }else if (ch == '['){
                    map.addBrick(new NormalBrick(xAxis, yAxis, images.get(ch), CollisionType.LEFT, CollisionType.TOP));
                }else if (ch == ']'){
                    map.addBrick(new NormalBrick(xAxis, yAxis, images.get(ch), CollisionType.RIGHT, CollisionType.TOP));
                }else if (ch == '*') {
                    Brick brick = new NormalBrick(xAxis, yAxis, images.get(ch));
                    map.addBrick(brick);
                }else if (ch == '0' || ch == 'E' || ch == '|' || ch == 'U' || ch == 'L' || ch == 'G' || ch == 'D' || ch == 'H' || ch == 'Q'){
                    CustomObject customObject = new CustomObject(xAxis, yAxis, CONSTANTS.SquareWidth,CONSTANTS.SquareHeight, true, images.get(ch));
                    map.addCustomObject(customObject);
                }else if (ch == 'M'){
                    map.setMario(new Mario(xAxis, yAxis, CONSTANTS.MarioWidth, CONSTANTS.MarioHeight));
                }else if (ch == '$'){
                    map.getEnemies().add(new Goomba(xAxis, yAxis, CONSTANTS.SquareWidth, CONSTANTS.SquareHeight, images.get(ch)));
                }
            }
            yAxis += CONSTANTS.SquareHeight;

        }
        xCordinate += mapLength;
        map.setDimensions(xCordinate, yCordinate);
        map.setLastMapXCordinate(xCordinate*CONSTANTS.SquareWidth);
    }

    public void readMapFromFile(){
        File file = new File(CONSTANTS.FilePath+"SuperMario/src/Background/level.txt");
        Scanner myReader = null;

        try {
            myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                mapLines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            myReader.close();
        }
    }


    public Map generateMap(){
        Scanner myReader = null;
        try {
            File file = new File(CONSTANTS.FilePath+"SuperMario/src/Background/level.txt");
            myReader = new Scanner(file);
            int yAxis = 0;
            int xAxis = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                xAxis = 0;
                for(int i = 0; i<data.length(); i++) {

                    if (data.charAt(i) == '='){
                        Brick brick = new FloorBrick(xAxis, yAxis, images.get('='), CollisionType.TOP);
                        map.addBrick(brick);
                    }else if(data.charAt(i) == '%'){
                        Brick brick = new FloorBrick(xAxis, yAxis, images.get('='));
                        map.addBrick(brick);
                    } else if (data.charAt(i) == '+'){
                        Brick brick = new NormalBrick(xAxis, yAxis, images.get('+'), CollisionType.LEFT, CollisionType.TOP, CollisionType.RIGHT, CollisionType.BOTTOM);
                        map.addBrick(brick);
                    }else if (data.charAt(i) == '?'){
                        Brick brick = new QuestionBrick(xAxis, yAxis, images.get('?'));
                        map.addQuestionBrick(brick);
                    }else if (data.charAt(i) == '('){
                        map.addBrick(new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)), CollisionType.LEFT));
                    }else if (data.charAt(i) == ')'){
                        map.addBrick(new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)), CollisionType.RIGHT));
                    }else if (data.charAt(i) == '['){
                        map.addBrick(new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)), CollisionType.LEFT, CollisionType.TOP));
                    }else if (data.charAt(i) == ']'){
                        map.addBrick(new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)), CollisionType.RIGHT, CollisionType.TOP));
                    }else if (data.charAt(i) == '*') {
                        Brick brick = new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)));
                        map.addBrick(brick);
                    }else if (data.charAt(i) == '0' || data.charAt(i) == 'E' || data.charAt(i) == '|' || data.charAt(i) == 'U' || data.charAt(i) == 'L' || data.charAt(i) == 'G' || data.charAt(i) == 'D' || data.charAt(i) == 'H' || data.charAt(i) == 'Q'){
                        CustomObject customObject = new CustomObject(xAxis, yAxis, CONSTANTS.SquareWidth,CONSTANTS.SquareHeight, true, images.get(data.charAt(i)));
                        map.addCustomObject(customObject);
                    }else if (data.charAt(i) == 'M'){
                        map.setMario(new Mario(xAxis, yAxis, CONSTANTS.MarioWidth, CONSTANTS.MarioHeight));
                    }

                    xAxis += CONSTANTS.SquareWidth;
                }

                yAxis += CONSTANTS.SquareHeight;
            }
            map.setDimensions(xAxis, yAxis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            myReader.close();
        }

        return map;
    }

    public void fillHashMapWithImage(){


        try {
            images.put('+', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "hasar.png")));
            images.put('H', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "hasar_sade.png")));
            images.put('?', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "question.png")));
            images.put('=', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "block.png")));
            images.put('[', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "left_top_tube.png")));
            images.put(']', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "right_top_tube.png")));
            images.put('(', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "left_tube.png")));
            images.put(')', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "right_tube.png")));
            images.put('*', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "kerpic.png")));
            images.put('U', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "qala_ust_bosh.png")));
            images.put('D', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "qala_ust_dolu.png")));
            images.put('L', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "qala_qapi_sol.png")));
            images.put('G', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "qala_qapi_sag.png")));
            images.put('Q', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "qala_qapi_ust.png")));
            images.put('B', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "bosh_qaranliq.png")));
            images.put('0', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "bayraq_ustu.png")));
            images.put('|', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "direk.png")));
            images.put('E', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "bayraq.png")));
            images.put('T', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "tepe.png")));
            images.put('$', ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "goomba_right.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
