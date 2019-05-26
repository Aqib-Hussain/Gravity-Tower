package game;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GravityGun implements ActionListener {

    private float fX;
    private float fY;
    private Player player;
    private GameLevel gameLevel;
    private boolean canShoot = true;

    // Construct a gravity gun
    public GravityGun(GameLevel gameLevel, Player player) {
        this.gameLevel = gameLevel;
        this.player = player;
    }

    // Spawn a new gravity ball
    @SuppressWarnings("Duplicates")
    public void GravityShotSpawnPoint(float angle) {
        if (canShoot) {
            timer.start();
            if (player.getGravityScale() == -1) {
                fX = (float) Math.cos(Math.toRadians(angle)) * 1.7f;
                fY = (float) Math.sin(Math.toRadians(angle)) * 1.7f;
                Shape box = new BoxShape(0.5f, 0.5f);
                DynamicBody gravityShot = new DynamicBody(gameLevel, box);
                gravityShot.addImage(new BodyImage("Data/gravity-shot.gif", 2));
                gravityShot.setPosition(new Vec2(player.getPosition().x + fX, player.getPosition().y - fY));
                gravityShot.setLinearVelocity(new Vec2(fX * 7, -fY * 7));
                gravityShot.setName("gravityShot");
                gravityShot.addCollisionListener(new CollisionHandler());
                canShoot = false;
            } else {
                // System.out.println(angle);
                fX = (float) Math.cos(Math.toRadians(angle)) * 1.7f;
                fY = (float) Math.sin(Math.toRadians(angle)) * 1.7f;
                Shape box = new BoxShape(0.5f, 0.5f);
                DynamicBody gravityShot = new DynamicBody(gameLevel, box);
                gravityShot.addImage(new BodyImage("Data/gravity-shot.gif", 2));
                gravityShot.setPosition(new Vec2(player.getPosition().x + fX, player.getPosition().y + fY));
                gravityShot.setLinearVelocity(new Vec2(fX * 7, fY * 7));
                gravityShot.setName("gravityShot");
                gravityShot.addCollisionListener(new CollisionHandler());
                canShoot = false;
            }
        }
    }

    // the delay on how long it takes to fire
    Timer timer = new Timer(1250, this);

    @Override
    public void actionPerformed(ActionEvent e) {
        canShoot = true;
    }
}
