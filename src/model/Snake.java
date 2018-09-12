package model;

import java.awt.*;
import java.util.ArrayList;

import static ui.SimpleSnakeGame.HEIGHT;
import static ui.SimpleSnakeGame.WIDTH;

public class Snake extends GameObject{

    //always in multiple of 10, START_LENGTH/10 is how many parts the snake has
    public static final int START_LENGTH = 80;

    private ArrayList<Parts> snakeParts;
    private Parts head;

    private static final int MOVE_LENGTH = 10;
    //timer determines the speed of snake, smaller the timer, faster the snake
    private int timer = 20;

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

    public int getDirection(){
        return head.getDirection();
    }

    public void setDirection(int direction){
        head.setDirection(direction);
    }

    public void grow(){
        Parts last = snakeParts.get(snakeParts.size()-1);
            switch(last.getDirection()) {
                case UP:
                    snakeParts.add(new Parts(last.getX(),last.getY()+width,last.getDirection()));
                    snakeParts.add(new Parts(last.getX(),last.getY()+width*2,last.getDirection()));
                    break;
                case RIGHT:
                    snakeParts.add(new Parts(last.getX()-width,last.getY(),last.getDirection()));
                    snakeParts.add(new Parts(last.getX()-width*2,last.getY(),last.getDirection()));
                    break;
                case DOWN:
                    snakeParts.add(new Parts(last.getX(),last.getY()-width,last.getDirection()));
                    snakeParts.add(new Parts(last.getX(),last.getY()-width*2,last.getDirection()));
                    break;
                case LEFT:
                    snakeParts.add(new Parts(last.getX()+width,last.getY(),last.getDirection()));
                    snakeParts.add(new Parts(last.getX()+width*2,last.getY(),last.getDirection()));
                    break;
            }
            System.out.println(snakeParts.size());
    }

    @Override
    public void tick() {
        if (timer == 0) {
            move();
            timer = 20;
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
        snakeParts.remove((getLength())-1);

        switch (head.getDirection()) {
            case UP:
                head = new Parts(head.getX(),head.getY()-MOVE_LENGTH,head.getDirection());
                snakeParts.add(0,head);
                break;
            case RIGHT:
                head = new Parts(head.getX()+MOVE_LENGTH,head.getY(),head.getDirection());
                snakeParts.add(0,head);
                break;
            case DOWN:
                head = new Parts(head.getX(),head.getY()+MOVE_LENGTH,head.getDirection());
                snakeParts.add(0,head);                break;
            case LEFT:
                head = new Parts(head.getX()-MOVE_LENGTH,head.getY(),head.getDirection());
                snakeParts.add(0,head);
        }


    }

    public boolean touch() {
        for (Parts part : snakeParts) {
            Rectangle body = new Rectangle(part.getX(),part.getY(),width-1,height-1);
            Rectangle head = new Rectangle(this.head.getX(),this.head.getY(),width-1,height-1);
            if (body.contains(head))
                return true;
        }
        return false;
    }
}
