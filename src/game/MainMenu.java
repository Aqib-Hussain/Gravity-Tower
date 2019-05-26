package game;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainMenu extends Container {
    private JPanel mainMenu;
    private JButton startButton;
    private JButton exitButton;
    private JButton controlsButton;
    private JButton loadButton;
    private Game game;
    private GameLevel gameLevel;
    private PlayerStateReader load;

    public MainMenu(final Game game, final MyView view) {
        this.game = game;
        this.load = new PlayerStateReader();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //removeAll();
                game.setLevel((Game.getLevel() + 1));
                view.removeAll();
                KeyHandler.setIsPaused(false);
                game.goNextLevel();
                //KeyHandler.setIsPaused(false);
                Game.frame.requestFocusInWindow();
                //view.remove(getMainMenu());
//                game.getFrame().add(game.view);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.exit();
                Game.frame.requestFocusInWindow();
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
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader("Data/Saves/PlayerState.txt"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (br.readLine() == null) {
                        System.out.println("No save existing");

                    } else {
                        try {
                            load.readFile();
                            System.out.println();
                            StepHandler.setTime(load.getSaveData().get(0));
                            game.setLevel(load.getSaveData().get(5) - 1);
                            view.removeAll();
                            KeyHandler.setIsPaused(false);
                            game.goNextLevel();
                            ((GameLevel) game.getWorld()).getPlayer().setPosition(new Vec2(load.getSaveData().get(1), load.getSaveData().get(2)));
                            ((GameLevel) game.getWorld()).getPlayer().setKillCount(load.getSaveData().get(3));
                            for (int i = 0; i < 5 - load.getSaveData().get(4); i++) {
                                ((GameLevel) game.getWorld()).getPlayer().playerLoseHealth();
                            }
                            game.getPlayer().setGravityScale(load.getSaveData().get(6));
                            if (game.getPlayer().getGravityScale()== -1.0){
                                game.getPlayer().getPlayerImage().flipVertical();
                            }

                            Game.frame.requestFocusInWindow();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public JPanel getMainMenu() {
        return mainMenu;
    }
}
