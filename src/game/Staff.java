package game;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Staff implements ActionListener {
    private float fX;
    private float fY;
    private Player player;
    private GameLevel gameLevel;
    private boolean canShoot = true;

    public Staff(GameLevel gameLevel, Player player) {
        this.gameLevel = gameLevel;
        this.player = player;
    }
    // spawns a fireball
    @SuppressWarnings("Duplicates")
    public void fireSpawnPoint(float angle) {
        // check if enough time has passed for the player to be able to shoot
        if (canShoot) {
            timer.start();
            // check if the player is in antigrav
            if (player.getGravityScale() == -1) {
                fX = (float) Math.cos(Math.toRadians(angle)) * 1.7f;
                fY = (float) Math.sin(Math.toRadians(angle)) * 1.7f;
                Shape box = new BoxShape(0.5f, 0.5f);
                DynamicBody fireBall = new DynamicBody(gameLevel, box);
                fireBall.addImage(new BodyImage("Data/fireball.gif", 2));
                fireBall.setPosition(new Vec2(player.getPosition().x + fX, player.getPosition().y - fY));
                fireBall.setLinearVelocity(new Vec2(fX * 7, -fY * 7));
                fireBall.setName("fireBall");
                fireBall.addCollisionListener(new CollisionHandler());
                canShoot = false;
            } else {
                fX = (float) Math.cos(Math.toRadians(angle)) * 1.7f;
                fY = (float) Math.sin(Math.toRadians(angle)) * 1.7f;
                Shape box = new BoxShape(0.5f, 0.5f);
                DynamicBody fireBall = new DynamicBody(gameLevel, box);
                fireBall.addImage(new BodyImage("Data/fireball.gif", 2));
                fireBall.setPosition(new Vec2(player.getPosition().x + fX, player.getPosition().y + fY));
                fireBall.setLinearVelocity(new Vec2(fX * 7, fY * 7));
                fireBall.setName("fireBall");
                fireBall.addCollisionListener(new CollisionHandler());
                canShoot = false;
            }
        }
    }
    Timer timer = new Timer(1250, this);


    @Override
    public void actionPerformed(ActionEvent e) {
        canShoot = true;
    }
}
