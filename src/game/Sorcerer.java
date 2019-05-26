package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Sorcerer extends Walker implements ActionListener {

    public boolean sorcererDead = false;
    public int distance;
    public float fX = 1.2f;
    public float fY = 3.5f;
    public int sorcererHealth = 3;
    private static final Shape sorcererShape = new BoxShape(1.2f, 1.8f);
    private static final BodyImage sorcererImage = new BodyImage("data/sorcerer-idle.gif", 3.5f);

    public Sorcerer(World world, Shape shape) {
        super(world, shape);
    }

    public static Shape getSorcererShape() {
        return sorcererShape;
    }

    public static BodyImage getSorcererImage() {
        return sorcererImage;
    }

    // The method for the sorcerer shooting an electroball
    public void shootElectroBall(Player player) {
        // checks if the sorcerer is alive
        if (!sorcererDead) {
            // checks if the player is in front or behind the sorcerer
            if (player.getPosition().x >= this.getPosition().x) {
                this.removeAllImages();
                this.addImage(new BodyImage("data/sorcerer-attack.gif", 3.5f));
                // creates a new electroball for the sorcerer to fire
                Shape circle = new CircleShape(0.5f);
                DynamicBody electroBall = new DynamicBody(getWorld(), circle);
                electroBall.addImage(new BodyImage("Data/electro-ball.gif", 2));
                electroBall.setPosition(new Vec2(this.getPosition().x + 1.1f, this.getPosition().y + 1f));
                // the sorcerer shoots in intervals
                timer.start();
                electroBall.setLinearVelocity(new Vec2(fX * 3, fY * 2));
                electroBall.setName("electroBall");
                electroBall.addCollisionListener(new CollisionHandler());
                this.removeAllImages();
                this.addImage(new BodyImage("data/sorcerer-idle.gif", 3.5f));
            } else {
                this.removeAllImages();
                this.addImage(new BodyImage("data/sorcerer-attack-backwards.gif", 3.5f));
                Shape circle = new CircleShape(0.5f);
                DynamicBody electroBall = new DynamicBody(getWorld(), circle);
                electroBall.addImage(new BodyImage("Data/electro-ball.gif", 2));
                electroBall.setPosition(new Vec2(this.getPosition().x - 1.1f, this.getPosition().y + 1f));
                timer.start();
                electroBall.setLinearVelocity(new Vec2(fX * -3, fY * 2));
                electroBall.setName("electroBall");
                electroBall.addCollisionListener(new CollisionHandler());
                this.removeAllImages();
                this.addImage(new BodyImage("data/sorcerer-idle-backwards.gif", 3.5f));
            }
        }
    }

    // the set interval at which the sorcerer can shoot
    Timer timer = new Timer(1750, this);

    // check the distance between the sorcerer and player
    public boolean distanceCheck(Player player) {
        float x1 = this.getPosition().x;
        float x2 = player.getPosition().x;
        float y1 = this.getPosition().y;
        float y2 = player.getPosition().y;
        distance = (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return distance < 10;
    }

    // the method called when the sorcerer loses health
    public void sorcererLoseHealth(Player player) {
        sorcererHealth = sorcererHealth - 1;
        if (sorcererHealth == 0) {
            ((GameLevel) this.getWorld()).setEnemyCounter(((GameLevel) this.getWorld()).getEnemyCounter() - 1);
            destroy();
            sorcererDead = true;
            player.killCount++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check world for dynamic bodies
        // if instance is an instance of name electroball destroy
        List<DynamicBody> bodies = getWorld().getDynamicBodies();
        for (DynamicBody body : bodies) {
            if (body.getName() == "electroBall") {
                body.destroy();
            }
        }

    }
}
