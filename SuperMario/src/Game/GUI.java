package Game;

import map.Map;
import map.MapMaker;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    private Map map;
    private Game game;

    public GUI(Game game, int width, int height) {
        this.game = game;
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        MapMaker mapMaker = new MapMaker();
        map = mapMaker.generateMap();
        setBackground(Color.decode("#6185f8"));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        GameStatus gameStatus = game.getGameStatus();

        if (gameStatus == GameStatus.PLAY) {
                map.draw(g2);
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
