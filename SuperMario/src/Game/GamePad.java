package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePad implements KeyListener {

    private Game game;

    public GamePad(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            game.doAction(ActionType.LEFT);
        }else if (keyCode == KeyEvent.VK_RIGHT) {
            game.doAction(ActionType.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT) {
            game.doAction(ActionType.RELEASED);
        }
    }
}
