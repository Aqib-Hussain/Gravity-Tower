package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * This is the Class that defines the player
 */
public class Player extends Walker {
    /**
     * The players health
     */
    private static Float pHealth;
    /**
     * The players number of kills
     */
    static Float killCount = 0f;
    /**
     * The players hearts being displayed
     */
    private HeartDisplay heartDisplay;
    /**
     * The game to which the player is in
     */
    private Game game;
    /**
     * The view the player uses
     */
    private MyView view;
    /**
     * The shape of the players body
     */
    private static final Shape playerShape = new PolygonShape(0.149f, 0.975f, 0.775f, 0.193f, 0.772f, -0.099f, 0.401f, -0.928f, -0.36f, -0.922f, -0.719f, -0.025f, -0.725f, 0.163f, -0.14f, 0.972f);
    /**
     * The image of the player
     */
    private AttachedImage playerImage = new AttachedImage(this, new BodyImage("data/wiz.gif"), 2.25f, 0, new Vec2(0, 0));
    /**
     * The rotation value of the players staff
     */
    private float r = 0;
    /**
     * The image of the players staff
     */
    private AttachedImage staffImage = new AttachedImage(this, new BodyImage("data/staff.gif"), 6.0f, r, new Vec2(0, 0));

    /**
     * <p>This is the constructor for the player</p>
     * <p>
     * The world that the player is in
     *
     * @param world   </p>
     *                <p>
     *                The players current health attribute
     * @param pHealth </p>
     *                <p>
     *                The current game world the player is in
     * @param game    </p>
     *                <p>
     *                The view that the player uses
     * @param view    </p>
     */
    public Player(World world, Float pHealth, Game game, MyView view) {
        super(world, playerShape);
        Player.pHealth = pHealth;
        heartDisplay = new HeartDisplay(world);
        this.game = game;
        this.view = view;
    }

    /**
     * <p>
     * This adds a collision listener to the player so it responds to collisions
     *
     * @param listener </p>
     */
    @Override
    public void addCollisionListener(CollisionListener listener) {
        super.addCollisionListener(new CollisionHandler());
    }

    /**
     * <p>
     * This method is called whenever the player loses health.
     * It reduces the players health variable by 1.
     * It removes a heart from the players screen to represent this.
     * When the players health reaches 0 the player is destroyed and you are sent to the main menu.
     * </p>
     */
    void playerLoseHealth() {
        this.applyForce(new Vec2(-10f, 10));
        pHealth = pHealth - 1;
        if (pHealth == 0) {
            destroy();
            getWorld().stop();
            Game.frame.requestFocusInWindow();
            game.setLevel(-1f);
            view.removeAll();
            game.goNextLevel();
        } else {
            heartDisplay.removeAttachedImage(heartDisplay.getHearts().get(heartDisplay.getHearts().size() - 1));
            heartDisplay.getHearts().remove(heartDisplay.getHearts().size() - 1);
        }
    }

    /**
     * <p>
     * This method is called whenever the player gains health through pickups.
     * It increases the players health variable by 1.
     * It adds a heart to the players screen to represent this.
     * </p>
     */
    void playerGainHealth() {
        setpHealth((getpHealth() + 1));
        heartDisplay.addHeart();
    }

    /**
     * <p>
     * This returns the players number of kills.
     *
     * @return </p>
     */
    static Float getKillCount() {
        return killCount;
    }

    /**
     * <p>
     * This sets the players number of kills, used when loading.
     *
     * @param killCount </p>
     */
    static void setKillCount(Float killCount) {
        Player.killCount = killCount;
    }

    /**
     * This method flips the players image, used when changing gravity.
     */
    void flipPlayerImageVertical() {
        getPlayerImage().flipVertical();
    }

    /**
     * <p>
     * This method sets the players health
     *
     * @param pHealth </p>
     */
    private static void setpHealth(Float pHealth) {
        Player.pHealth = pHealth;
    }

    /**
     * <p>
     * This method gets the players current health
     *
     * @return </p>
     */
    static Float getpHealth() {
        return pHealth;
    }

    /**
     * <p>
     * This method gets the players image
     *
     * @return </p>
     */
    AttachedImage getPlayerImage() {
        return playerImage;
    }

    /**
     * <p>
     * This method gets the image of the staff used on the player
     *
     * @return </p>
     */
    public AttachedImage getStaffImage() {
        return staffImage;
    }

    /**
     * <p>
     * This method gets the players x position
     *
     * @return </p>
     */
    public float getPositionX() {
        return this.getPosition().x;
    }

    /**
     * <p>
     * This method gets the players y position
     *
     * @return </p>
     */
    public float getPositionY() {
        return this.getPosition().y;
    }
}

