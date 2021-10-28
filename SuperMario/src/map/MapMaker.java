package map;

import Game.CONSTANTS;
import GameObjects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MapMaker {

    BufferedImage floor_image;
    HashMap<Character, BufferedImage> images = new HashMap<>();


    public MapMaker(){

    }


    public Map generateMap(){
        fillHashMapWithImage();
        Map map = new Map();
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
                        Brick brick = new FloorBrick(xAxis, yAxis, images.get('='));
                        map.addBrick(brick);
                    }else if (data.charAt(i) == '+'){
                        Brick brick = new NormalBrick(xAxis, yAxis, images.get('+'));
                        map.addBrick(brick);
                    }else if (data.charAt(i) == '?'){
                        Brick brick = new QuestionBrick(xAxis, yAxis, images.get('?'));
                        map.addQuestionBrick(brick);
                    }else if (data.charAt(i) == '[' || data.charAt(i) == ']' || data.charAt(i) == '(' || data.charAt(i) == ')' || data.charAt(i) == '*') {
                        Brick brick = new NormalBrick(xAxis, yAxis, images.get(data.charAt(i)));
                        map.addBrick(brick);
                    }else if (data.charAt(i) == '0' || data.charAt(i) == 'E' || data.charAt(i) == '|' || data.charAt(i) == 'U' || data.charAt(i) == 'L' || data.charAt(i) == 'G' || data.charAt(i) == 'D' || data.charAt(i) == 'H' || data.charAt(i) == 'Q'){
                        CustomObject customObject = new CustomObject(xAxis, yAxis, CONSTANTS.SquareWidth,CONSTANTS.SquareHeight, true, images.get(data.charAt(i)));
                        map.addCustomObject(customObject);
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

        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
