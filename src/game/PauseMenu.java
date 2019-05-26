package game;


import city.cs.engine.World;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PauseMenu extends Container {
    private Game game;
    private JPanel pauseMenu;

    private JButton mainMenuButton;
    private JButton exitButton;
    private JButton controlsButton;
    private JButton saveButton;
    private KeyHandler keyHandler;
    private PlayerStateWriter playerStateWriter;
    private PlayerStateReader playerStateReader;
    private Player player;


    public PauseMenu(final Game game, final MyView view) {
        this.getPauseMenu().setBackground(Color.darkGray);
        this.game = game;



        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.frame.requestFocusInWindow();
                game.setLevel((float) -1);
                view.removeAll();
                game.goNextLevel();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.frame.requestFocusInWindow();
                game.exit();
            }
        });
        controlsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "W : Jump"
                        + "\r\n" + "D : Move Right"
                        + "\r\n" + "A : Move Left"
                        + "\r\n" + "J : Move Staff clockwise"
                        + "\r\n" + "H : Move Staff anti-clockwise"
                        + "\r\n" + "K : Fire Gravity shot"
                        + "\r\n" + "L : Fire fireball"
                        + "\r\n" + "E : Swap gravity");
                Game.frame.requestFocusInWindow();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerStateWriter writer = new PlayerStateWriter("Data/Saves/PlayerState.txt");
                try {
                    writer.writeState(StepHandler.getTime(), game.getPlayer().getPositionX(), game.getPlayer().getPositionY(), Player.getKillCount(), Player.getpHealth(), Game.getLevel(), game.getPlayer().getGravityScale());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Game.frame.requestFocusInWindow();

            }
        });
    }


    public JPanel getPauseMenu() {
        return pauseMenu;
    }


}
