package model;

import java.awt.*;
import java.util.ArrayList;

import static ui.SimpleSnakeGame.HEIGHT;
import static ui.SimpleSnakeGame.WIDTH;

public class Snake extends GameObject{

    private int length;
    private ArrayList<Point> snakeParts;
    private Point head;
    private static final double SPEED = 1;

    private Color snakeColor = Color.BLACK;

    private int currentDirection;
    private int previousDirection;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private int positionNum;


    public Snake(){
        super(WIDTH/2, HEIGHT/2);
        length = 50;
        currentDirection = LEFT;
        previousDirection = LEFT;
        width = 10;
        height = 10;

        head =  new Point(WIDTH/2, HEIGHT/2);
        snakeParts = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            snakeParts.add(new Point(WIDTH/2+i, HEIGHT/2));
        }
    }

    public Point getHead(){
        return head;
    }

    public int getLength(){
        return length;
    }

    public void setDirection(int direction,int positionNum){
        previousDirection = currentDirection;
        this.currentDirection = direction;
        this.positionNum = positionNum;
    }

    public void grow(){
        length++;
    }

    @Override
    public void tick() {
        for (int i = 0; i <= positionNum; i++) {
            Point part = snakeParts.get(i);
            switch (currentDirection) {
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
        for (int i = positionNum +1; i < length; i++) {
            Point part = snakeParts.get(i);
            switch (previousDirection) {
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
        positionNum++;

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
