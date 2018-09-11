package model;

public class Parts{

    //x and y coordinate of the part
    private int x;
    private int y;

    private int direction;

    public Parts(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    //getters
    public int getDirection(){
        return direction;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //setters
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
