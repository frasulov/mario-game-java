package Game;

import GameObjects.Direction;
import GameObjects.Mario;
import map.MapCamera;

import javax.swing.*;

public class Game implements Runnable {

    private GameStatus gameStatus;
    private GUI gui;
    private GamePad gamePad;
    private MapCamera mapCamera;
    public boolean running = false;
    public int tickCount = 0;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Game() {
        gui = new GUI(this, CONSTANTS.WIDTH, CONSTANTS.HEIGHT);
        gamePad = new GamePad(this);
        mapCamera = new MapCamera(gui);

        JFrame frame = new JFrame("Super Mario");
        frame.add(gui);
        frame.addKeyListener(gamePad);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        start();
    }

    public void doAction(ActionType actionType){
        Mario mario = gui.getMap().getMario();
        if (actionType == ActionType.LEFT){
            mario.move(Direction.LEFT, mapCamera);
        }else if(actionType == ActionType.RIGHT) {
            mario.move(Direction.RIGHT, mapCamera);
        }else if (actionType == ActionType.RELEASED){
            mario.setVelX(0);
        }
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
        gameStatus = GameStatus.PLAY;
    }

    public synchronized void stop() {
        running = false;
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 64;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (shouldRender) {
                gui.getMap().updateGameObjects();
                gui.repaint();
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }

        }
    }

    public void tick() {
        tickCount++;
    }

    public void render() {
//        System.out.println("here running");
    }
}