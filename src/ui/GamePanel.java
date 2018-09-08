package ui;


import model.SGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private SGame game;

    public GamePanel(SGame game){
        this.game = game;
        setPreferredSize(new Dimension(SimpleSnakeGame.WIDTH,SimpleSnakeGame.HEIGHT));
        setBackground(Color.gray);
    }


}
