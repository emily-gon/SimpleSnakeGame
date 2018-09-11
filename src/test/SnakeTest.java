package test;

import model.Parts;
import model.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.SimpleSnakeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeTest {
    private Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake();
        assertEquals(Snake.START_LENGTH, snake.getLength());
        Parts head = snake.getHead();
        assertEquals(head.getX(), SimpleSnakeGame.WIDTH/2);
    }

    @Test
    void testConstructor1() {

    }
}
