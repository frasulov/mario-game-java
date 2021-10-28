package GameObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private ArrayList<BufferedImage> images;
    private BufferedImage current;
    private int count, index;

    public Animation(ArrayList<BufferedImage> images){
        this.images = images;
        current = images.get(index);
    }

    public BufferedImage animate(int speed){
        count++;

        if(count > speed){
            nextFrame();
            count = 0;
        }

        return current;
    }

    private void nextFrame() {

        if (index >= images.size()-1){
            index = 0;
        }else{
            index++;
        }
        current = images.get(index);
    }

}
