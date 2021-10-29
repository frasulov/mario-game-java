package GameObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Animation {

    private ArrayList<BufferedImage> images;
    private BufferedImage current;
    private int count, index;
    private Direction direction;
    private ArrayList<BufferedImage> marioLeft;
    private ArrayList<BufferedImage> marioRight;

    public Animation(ArrayList<BufferedImage> images){
        this.images = images;
        current = images.get(index);
    }

    public Animation(HashMap<String, BufferedImage> images, Direction direction){
        marioLeft = new ArrayList<>();
        marioRight = new ArrayList<>();
        for (Map.Entry<String, BufferedImage> entry : images.entrySet()){
            if (entry.getKey().startsWith("right")){
                marioRight.add(entry.getValue());
            }else if (entry.getKey().startsWith("left")){
                marioLeft.add(entry.getValue());
            }
        }
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void nextFrame() {

        if (index >= images.size()-1){
            index = 0;
        }else{
            index++;
        }
        current = images.get(index);
    }

    public BufferedImage animateMario(int speed) {
        count++;
        if(count > speed){
            if (direction == Direction.RIGHT) {
                if (index >= marioRight.size() - 1)
                    index = 0;
                else
                    index ++;
                current = marioRight.get(index);
            }else {
                if (index >= marioLeft.size() - 1)
                    index = 0;
                else
                    index ++;
                current = marioLeft.get(index);
            }
            count = 0;
        }
        return current;
    }

    public BufferedImage animate(int speed){
        count++;

        if(count > speed){
            nextFrame();
            count = 0;
        }

        return current;
    }


}
