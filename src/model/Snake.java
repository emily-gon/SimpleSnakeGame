package model;

import java.awt.*;
import java.util.ArrayList;

import static ui.SimpleSnakeGame.HEIGHT;
import static ui.SimpleSnakeGame.WIDTH;

public class Snake extends GameObject{

    private int length;
    private ArrayList<Point> snakeParts = new ArrayList<>();
    private Point head;
    private static final double SPEED = 1;

    private Color snakeColor = Color.BLACK;

    private int direction;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;


    public Snake(Point head){
        super(WIDTH/2, HEIGHT/2);
        length = 5;
        direction = LEFT;
        width = 10;
        height = 10;

        this.head = head;
        snakeParts = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            snakeParts.add(new Point(WIDTH/2+i, HEIGHT/2));
        }
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public void grow(){
        length++;
    }

    @Override
    public void tick() {
        for (int i = 0; i < length; i++) {
            Point part = snakeParts.get(i);
            switch (direction) {
                case UP:
                    part.y -= SPEED;
                    break;
                case RIGHT:
                    part.x += SPEED;
                    break;
                case DOWN:
                    part.y += SPEED;
                    break;
                case LEFT:
                    part.x -= SPEED;
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(snakeColor);
        for (int i = 0; i < length; i++) {
            Point part = snakeParts.get(i);
            g.fillRect(part.x, part.y, width, height);
        }
    }
}
