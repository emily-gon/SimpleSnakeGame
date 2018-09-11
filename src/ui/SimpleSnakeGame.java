package ui;

import model.Food;
import model.SGame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class SimpleSnakeGame extends Canvas implements Runnable {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private Thread thread;
    private boolean running = false;

    private SGame SGame;

    public SimpleSnakeGame(){
        SGame = new SGame();

        setFocusable(true);
        addKeyListener(new KeyHandler());


        new Window(WIDTH,HEIGHT,"Simple Snake Game",this,SGame);

        Random x = new Random();
        Random y = new Random();
        SGame.addObject(new Food(x.nextInt(WIDTH-11),y.nextInt(HEIGHT-11)));


    }

    public static void main(String[] args) {
        new SimpleSnakeGame();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        SGame.tick();
    }

    private void render(){
        BufferStrategy bus = this.getBufferStrategy();
        if(bus == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bus.getDrawGraphics();

        g.setColor(Color.pink);
        g.fillRect(0,0,WIDTH, HEIGHT);

        SGame.render(g);

        g.dispose();
        bus.show();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
        } catch (Exception e){
            running = false;
            System.out.println("Game has stopped.");
            e.printStackTrace();
        }
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            SGame.keyPressed(e.getKeyCode());
        }
    }
}
