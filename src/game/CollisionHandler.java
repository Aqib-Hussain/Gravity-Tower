package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;



public class CollisionHandler implements CollisionListener {
private Player player;
    public CollisionHandler() {
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void collide(CollisionEvent collisionEvent) {
        // collisions for the player's fireball
        if (collisionEvent.getReportingBody().getName() == "fireBall") {
            collisionEvent.getReportingBody().destroy();
            if (collisionEvent.getOtherBody() instanceof Slime) {
                ((Slime) collisionEvent.getOtherBody()).slimeLoseHealth(player);
            }
            if (collisionEvent.getOtherBody() instanceof Sorcerer) {
                ((Sorcerer) collisionEvent.getOtherBody()).sorcererLoseHealth(player);
            }
        }
        // collisions for the player's gravity shot
        if (collisionEvent.getReportingBody().getName() == "gravityShot") {
            collisionEvent.getReportingBody().destroy();
            if (collisionEvent.getOtherBody() instanceof Slime) {
                ((Slime) collisionEvent.getOtherBody()).slimeSwapGravity();
            }
        }
        // collisions for the slime dealing damage
        if (collisionEvent.getReportingBody() instanceof Player) {
            if (collisionEvent.getOtherBody() instanceof Slime) {
                ((Player) collisionEvent.getReportingBody()).playerLoseHealth();
            }
        }
        // collisions for the sorcerer enemy's electroball
        if (collisionEvent.getReportingBody().getName() == "electroBall") {
            if (collisionEvent.getOtherBody() instanceof Player) {
                collisionEvent.getReportingBody().destroy();
                ((Player) collisionEvent.getOtherBody()).playerLoseHealth();
            }
        }
        // collisions for spike traps
        if (collisionEvent.getOtherBody() instanceof Spikes) {
            if (collisionEvent.getReportingBody() instanceof Slime) {
              //  System.out.println("boi");
                ((Slime) collisionEvent.getReportingBody()).slimeHitSpikes(player);
            }
        }
        if (collisionEvent.getOtherBody() instanceof Spikes) {
                if (collisionEvent.getReportingBody() instanceof Player) {
                  //  System.out.println("boi2");
                    ((Player) collisionEvent.getReportingBody()).playerLoseHealth();
                }
        }
        // collisions for the healthpack pickup
        if (collisionEvent.getOtherBody() instanceof HealthPack) {
            ((Player) collisionEvent.getReportingBody()).playerGainHealth();
            collisionEvent.getOtherBody().destroy();
        }
    }
}