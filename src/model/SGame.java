package model;

import ui.SimpleSnakeGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static model.Snake.*;

public class SGame {

    private List<GameObject> objects;
    private Snake snake ;

    private boolean isGameOver;

    public SGame(){
        snake = new Snake();
        objects = new LinkedList<>();
        objects.add(snake);
        isGameOver = false;
    }


    public void tick(){
        for (GameObject go : objects){
            go.tick();
            if (go instanceof Food)
                checkFoodEaten(go);
        }
    }

    private void checkFoodEaten(GameObject go) {
        Food food = (Food) go;
        if (food.eaten(snake)) {
            removeObject(food);

            snake.grow();

            Random x = new Random();
            Random y = new Random();
            addObject(new Food(x.nextInt(SimpleSnakeGame.WIDTH-10),y.nextInt(SimpleSnakeGame.HEIGHT-10)));
        }
    }

    public void render(Graphics g){
        checkGameOver(g);
        for (GameObject go: objects){
            go.render(g);
        }
    }

    public void addObject(GameObject go){
        objects.add(go);
    }

    public void removeObject(GameObject go){
        objects.remove(go);
    }

    public void keyPressed(int keyCode) {
        if ((keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT)&& snake.getDirection() != RIGHT)
            snake.setDirection(LEFT);
        else if ((keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT)&& snake.getDirection() != LEFT)
            snake.setDirection(RIGHT);
        else if ((keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN)&& snake.getDirection() != UP)
            snake.setDirection(DOWN);
        else if ((keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP)&& snake.getDirection() != DOWN)
            snake.setDirection(UP);
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    private void checkGameOver(Graphics g){
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();

        //if snake touch the walls of the game
        if (x == 0 || y == 0 || x == SimpleSnakeGame.WIDTH - snake.getWidth()
                || y == SimpleSnakeGame.HEIGHT - snake.getHeight()){
            gameOver(g);
        }
        //if snake touch its body
        if (snake.touch()){
            gameOver(g);
        }
    }

    private void gameOver(Graphics g){
        System.out.println("Game Over!");
    }

}
