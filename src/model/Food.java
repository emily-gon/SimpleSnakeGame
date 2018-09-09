package model;

import java.awt.*;

public class Food extends GameObject{

    private static final int FOOD_WIDTH = 10;
    private static final int FOOD_HEIGHT = 10;

    //EFFECTS: constructs a food object with x,y coordinate and width,height constant
    public Food(int x, int y) {
        super(x,y);
        width = FOOD_WIDTH;
        height = FOOD_HEIGHT;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //ImageIcon cake = new ImageIcon("pink-cupcake-hi.png");
        //JLabel label = new JLabel(cake);

        g.setColor(Color.magenta);
        g.fillOval(x,y,FOOD_WIDTH,FOOD_HEIGHT);
    }

    public boolean eaten(Snake snake){
        Rectangle thisObject = new Rectangle(getX(),getY(),FOOD_WIDTH,FOOD_HEIGHT);
        Rectangle snakeObject = new Rectangle(snake.getHead().x,snake.getHead().y,
                snake.getWidth(),snake.getHeight());
        return thisObject.intersects(snakeObject);
    }

}
