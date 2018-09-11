package model;

import java.awt.*;

public abstract class GameObject {

    // x, y position of object
    protected int x,y;

    //width and height of snake parts
    protected int width,height;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
