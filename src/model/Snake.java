package model;

import java.awt.*;

import static ui.SimpleSnakeGame.HEIGHT;
import static ui.SimpleSnakeGame.WIDTH;

public class Snake extends GameObject{

    private int length;
    private static final double SPEED = 1;

    private Color snakeColor = Color.BLACK;

    private int direction;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;


    public Snake(){
        super(WIDTH/2, HEIGHT/2);
        length = 2;
        direction = LEFT;
        width = 10;
        height = 10;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public void grow(){
        length++;
    }

    @Override
    public void tick() {
        switch (direction) {
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(snakeColor);
        g.fillRect(x, y, width, height);
    }


}
