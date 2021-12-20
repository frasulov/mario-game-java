package GameObjects;

import Game.CONSTANTS;
import map.CollisionType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionBrick extends Brick{

    private Animation animation;
    private boolean broke;

    public QuestionBrick(double x, double y, BufferedImage image, CollisionType ...types) {
        super(x, y, image, types);
        setIs_breakable(false);
        setIs_empty(false);

        ArrayList<BufferedImage> images = new ArrayList<>();
        try {
            images.add(super.getImage());
            images.add(super.getImage());
            images.add(ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "question_brick_animate_2.png")));
            images.add(ImageIO.read(new File(CONSTANTS.FilePath+"SuperMario/src/backgroundImages/", "question_brick_animate_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        animation = new Animation(images);
    }

    public void animate(){
        if (!broke){
            setImage(animation.animate(30));
        }
    }
}
