package game;

import city.cs.engine.SimulationSettings;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StepHandler implements StepListener, ActionListener {
    boolean canShoot = true;
    private Slime slime;
    private Player player;
    private Sorcerer sorcerer;
    private static float time = 0;
    private SimulationSettings settings = new SimulationSettings(60);

    public StepHandler(Slime slime, Player player, Sorcerer sorcerer) {
        this.slime = slime;
        this.player = player;
        this.sorcerer = sorcerer;
    }

    public StepHandler(Slime slime, Player player) {
        this.slime = slime;
        this.player = player;
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        // Create a timer to time how long the player has taken
        Game.frame.requestFocusInWindow();
        time += settings.getTimeStep();
        time = Float.parseFloat(String.format("%.2f", getTime()));
    }


    @Override
    public void postStep(StepEvent stepEvent) {
        slime.sApproachPlayer(player);
        if (canShoot) {
            if (sorcerer != null) {
                if (sorcerer.distanceCheck(player)) {
                    sorcerer.shootElectroBall(player);
                    canShoot = false;
                    timer.start();
                }
            }
        }
    }
    Timer timer = new Timer(1750, this);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!canShoot) {
                timer.start();
                canShoot = true;
            }
    }

    public static float getTime() {
        return time;
    }

    public static void setTime(float time) {
        StepHandler.time = time;
    }

}
