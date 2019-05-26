package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

import static game.StepHandler.getTime;


public class MyView extends UserView {

    // the background image
    private ImageIcon Backdrop = new ImageIcon("Data/brick-background.png");

    public MyView(World w, int width, int height) {
        super(w, width, height);
    }

    // method for drawing the background image
    private void drawImage(Graphics2D g, float scale) {
        float width = Backdrop.getIconWidth() * scale;
        float height = Backdrop.getIconHeight() * scale;
        g.drawImage(Backdrop.getImage(), 0, 0, (int) width, (int) height, this);
    }

    // draw the background
    @Override
    public void paintBackground(Graphics2D g) {
        drawImage(g, 1);
    }

    // draw the overlay elements
    @Override
    public void paintForeground(Graphics2D g) {
        if (Game.getLevel() > 0) {
            g.drawString("Enemy count: " + GameLevel.getEnemyCounter(), 750, 25);
            g.drawString("Total kill count: " + Player.getKillCount(), 743, 35);
            g.drawString("Time taken: " + getTime(), 50, 25);
        }
    }

}
