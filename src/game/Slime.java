package game;

import city.cs.engine.*;


/**
 * This is the class which defines the slime enemy type
 */
public class Slime extends Walker {
    /**
     * This is the slimes health
     */
    private int sHealth = 3;

    /**
     * <p>This is the constructor for the slime enemy type</p>
     * <p>
     * The current world the slime is in
     *
     * @param world </p>
     *              <p>
     *              The shape of the slime
     * @param shape </p>
     */
    Slime(World world, Shape shape) {
        super(world, shape);
    }

    /**
     * <p>
     * Whenever the slime is hit this method is called.
     * This decreases the slimes health by 1.
     * When the slime is destroyed the enemy counter will decrease by 1.
     * When the slime is destroyed the players kill counter will increase by 1.
     * </p>
     * <p>
     * Player is passed as the players kill count will increase when the slime is killed.
     *
     * @param player </p>
     */
    void slimeLoseHealth(Player player) {
        sHealth = sHealth - 1;
        if (sHealth == 0) {
            ((GameLevel) this.getWorld()).setEnemyCounter(((GameLevel) this.getWorld()).getEnemyCounter() - 1);
            destroy();
            player.killCount++;
        }
    }

    /**
     * <p>
     *     Whenever the slime hits a spike trap this method is called.
     *     The slime is instantly destroyed on contact with spikes.
     *     The enemy counter decreases by 1 when this is called.
     *     The players kill counter increases by 1 when this is called.
     * </p>
     * <p>
     * Player is passed as the players kill count will increase when the slime is killed
     * @param player
     * </p>
     */
    void slimeHitSpikes(Player player) {
        ((GameLevel) this.getWorld()).setEnemyCounter(((GameLevel) this.getWorld()).getEnemyCounter() - 1);
        this.destroy();
        player.killCount++;
    }

    /**
     * <p>
     *     This method swaps the slimes gravity when called.
     *     It is used when the slime is hit with a gravity shot.
     * </p>
     */
    void slimeSwapGravity() {
        if (this.getGravityScale() == 1) {
            this.setGravityScale(-1);
        } else this.setGravityScale(1);
    }

    /**
     * <p>
     *     This method is called so the slime moves towards the player.
     *     The x position of the slime changes repeatedly.
     *     This depends on if the players x position is more than or less than itself.
     * </p>
     * <p>
     *     The player is passed through as the slime needs a target to approach.
     * @param player
     * </p>
     */
    void sApproachPlayer(Player player) {
        if (getPosition().x < player.getPosition().x) {
            startWalking(2f);
        }
        if (getPosition().x > player.getPosition().x) {
            startWalking(-2f);
        }
    }

    /**
     * <p>
     *     This allows the slime to react to collisions
     * </p>
     * <p>
     *     A collision listener is passed through so the slime responds to collisions
     * @param listener
     * </p>
     */
    @Override
    public void addCollisionListener(CollisionListener listener) {
        super.addCollisionListener(new CollisionHandler());
    }

}
