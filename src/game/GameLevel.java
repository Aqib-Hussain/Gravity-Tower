package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {

    private Player player;
    private static int enemyCounter;
    private Staff staff;
    private GravityGun gravityGun;


    public GameLevel() {
        super(60);
    }

    public Player getPlayer() {
        return player;
    }

    public static int getEnemyCounter() {
        return enemyCounter;
    }

    public void setEnemyCounter(int enemyCounter) {
        this.enemyCounter = enemyCounter;
    }

    // Create a new World
    public void create(Game game, Float pHealth, MyView view) {
        player = new Player(this, pHealth, game, view);
        player.setPosition(startPosition());
        gravityGun = new GravityGun(this, player);
        staff = new Staff(this, player);
        Ladder ladder = new Ladder(this);
        ladder.setPosition(ladderPosition());
        ladder.addCollisionListener(new LadderListener(game));
        enemyCounter = enemyCounter();
    }


    // Player initial position
    public abstract Vec2 startPosition();

    // Position of the exit ladder
    public abstract Vec2 ladderPosition();

    // is the level complete?
    public abstract boolean isCompleted();

    // how many enemies on the level?
    public abstract int enemyCounter();


}
