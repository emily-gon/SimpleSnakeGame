package model;

import java.awt.*;
import java.util.ArrayList;

import static ui.SimpleSnakeGame.HEIGHT;
import static ui.SimpleSnakeGame.WIDTH;

public class Snake extends GameObject{

    //always in multiple of 10, START_LENGTH/10 is how many parts the snake has
    public static final int START_LENGTH = 40;

    private ArrayList<Parts> snakeParts;
    private Parts head;

    //x and y coordinates of head's previous position
    private int headX;
    private int headY;

    private static final int MOVE_LENGTH = 10;
    //timer determines the speed of snake, smaller the timer, faster the snake
    private int timer = 10;

    private Color snakeColor = Color.BLACK;

    //int for directions
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;


    public Snake(){
        super(WIDTH/2, HEIGHT/2);
        width = 10;
        height = 10;

        head =  new Parts(WIDTH/2, HEIGHT/2,3);
        headX = head.getX();
        headY = head.getY();

        snakeParts = new ArrayList<>();
        snakeParts.add(head);
        for (int i = width; i < START_LENGTH; i += width) {
            snakeParts.add(new Parts(WIDTH/2+i, HEIGHT/2,3));
        }
    }

    public Parts getHead(){
        return head;
    }

    public int getLength(){
        return snakeParts.size();
    }

    public void setDirection(int direction){
        head.setDirection(direction);
        headX = head.getX();
        headY = head.getY();
    }

    public void grow(){
        Parts last = snakeParts.get(snakeParts.size()-1);
            switch(last.getDirection()) {
                case UP:
                    snakeParts.add(new Parts(last.getX(),last.getY()+width,last.getDirection()));
                    break;
                case RIGHT:
                    snakeParts.add(new Parts(last.getX()-width,last.getY(),last.getDirection()));
                    break;
                case DOWN:
                    snakeParts.add(new Parts(last.getX(),last.getY()-width,last.getDirection()));
                    break;
                case LEFT:
                    snakeParts.add(new Parts(last.getX()+width,last.getY(),last.getDirection()));
                    break;
            }
            System.out.println(snakeParts.size());
    }

    @Override
    public void tick() {
        if (timer == 0) {
            move();
            timer = 10;
        }
        timer--;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(snakeColor);
        for (Parts part: snakeParts) {
            g.fillRect(part.getX(), part.getY(), width, height);
        }
    }

    private void move(){
        for (Parts part : snakeParts) {
            int x = part.getX();
            int y = part.getY();
            if (x == headX && y == headY) {
                part.setDirection(head.getDirection());
            }
            switch (part.getDirection()) {
                case UP:
                    part.setY(y - MOVE_LENGTH);
                    break;
                case RIGHT:
                    part.setX(x + MOVE_LENGTH);
                    break;
                case DOWN:
                    part.setY(y + MOVE_LENGTH);
                    break;
                case LEFT:
                    part.setX(x - MOVE_LENGTH);
            }

        }
    }
}
